package logic;

import java.util.*;

/*klasa reprezentujaca cala generacje komorek*/
public class Generation {
    private HashMap<Coordinates, Cell> generation;

    public Generation (){
        generation = new HashMap<>();
    }

    public Generation(HashMap<Coordinates, Cell> generation) {
        this.generation = generation;
    }

    public HashMap<Coordinates, Cell> getGeneration() {
        return generation;
    }

    /*aktualizacja poprzez nowe stany komorek*/
    public void updateByState(HashMap<Coordinates, State> stateHashMap) {
        for(Coordinates coordinates : stateHashMap.keySet())
            if(generation.containsKey(coordinates)) {
                if (generation.get(coordinates).getState() != stateHashMap.get(coordinates))
                    generation.replace(coordinates, new Cell(coordinates, stateHashMap.get(coordinates)));
            } else {
                generation.put(coordinates, new Cell(coordinates, stateHashMap.get(coordinates)));
            }
    }

    /*aktualizacja poprzez nowe komorki*/
    public void updateByCell(HashMap<Coordinates, Cell> cellHashMap) {
        for(Coordinates coordinates : cellHashMap.keySet()) {
            if(this.generation.containsKey(coordinates)) {
                if(!this.generation.get(coordinates).equals(cellHashMap.get(coordinates)))
                    this.generation.replace(coordinates, cellHashMap.get(coordinates));
            } else {
                generation.put(coordinates, cellHashMap.get(coordinates));
            }
        }
    }

    public HashMap<Coordinates, Cell> getNextGeneration(GenerationAlgorithm generationAlgorithm) {
        generation = generationAlgorithm.getNextGeneration(generation);
        return generation;
    }

    @Override
    public String toString() {
        return new String(generation.toString());
    }

    @Override
    public boolean equals(Object generation) {
        if ( generation == null ) return false;
        if ( this == generation ) return true;
        if ( generation.getClass() != Generation.class ) return false;

        Generation otherGeneration = (Generation) generation;

        if(this.generation.size() != otherGeneration.getGeneration().size()) return false;

        List<Cell> cells = new ArrayList<>(this.generation.size());
        for(Coordinates coordinates: this.generation.keySet())
            cells.add(this.generation.get(coordinates));
        Collections.sort(cells, Cell.compareCells());

        List<Cell> otherCells = new ArrayList<>(otherGeneration.generation.size());
        for(Coordinates coordinates: otherGeneration.generation.keySet())
            otherCells.add(otherGeneration.generation.get(coordinates));
        Collections.sort(otherCells, Cell.compareCells());

        for(int i = 0; i < cells.size(); i++)
            if(!cells.get(i).equals(otherCells.get(i)))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Coordinates coordinates : this.generation.keySet()) {
            hashCode += this.generation.get(coordinates).hashCode();
        }
        return (hashCode * 619) % 5011;
    }
}