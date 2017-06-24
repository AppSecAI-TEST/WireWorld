package logic;

import java.util.HashMap;

/*algorytm uzyty do obliczenia kolejnych generacji komorek*/
public class WireWorldAlgorithm implements GenerationAlgorithm{
    @Override
    /*metoda obliczajaca kolejne generacje, kazdy kolejny if to jedna z zasad, jakie sa ustalone*/
    public HashMap<Coordinates, Cell> getNextGeneration(HashMap<Coordinates, Cell> cellHashMap) {
        HashMap<Coordinates, Cell> newCellHashMap = new HashMap<>(cellHashMap.size());
        for(Coordinates coordinates : cellHashMap.keySet()) {
            if(cellHashMap.get(coordinates).getState().equals(State.EMPTY))
                newCellHashMap.put(coordinates, new Cell(coordinates, State.EMPTY));
            else if(cellHashMap.get(coordinates).getState().equals(State.HEAD))
                newCellHashMap.put(coordinates, new Cell(coordinates, State.TAIL));
            else if(cellHashMap.get(coordinates).getState().equals(State.TAIL))
                newCellHashMap.put(coordinates, new Cell(coordinates, State.CONDUCTOR));
            else if(getHeadNeighbours(coordinates, cellHashMap) == 1 || getHeadNeighbours(coordinates, cellHashMap) == 2)
                newCellHashMap.put(coordinates, new Cell(coordinates, State.HEAD));
            else
                newCellHashMap.put(coordinates, new Cell(coordinates, State.CONDUCTOR));
        }
        return newCellHashMap;
    }

    /*w algorytmie podanym w zadaniu tylko komorki w stanie Glowa są zalezne od ilosci sasiadów*/
    private int getHeadNeighbours(Coordinates coordinates, HashMap<Coordinates, Cell> cellHashMap) {
        int headNeighbours = 0;
        int x = coordinates.getX();
        int y = coordinates.getY();
        for(int i = -1; i <= 1; i++) {
            if(cellHashMap.containsKey(new Coordinates(x + i, y - 1)))
                if(cellHashMap.get(new Coordinates(x + i, y - 1)).getState().equals(State.HEAD))
                    headNeighbours++;
        }
        for(int i = -1; i <= 1; i++) {
            if(cellHashMap.containsKey(new Coordinates(x + i, y + 1)))
                if(cellHashMap.get(new Coordinates(x + i, y + 1)).getState().equals(State.HEAD))
                    headNeighbours++;
        }
        if(cellHashMap.containsKey(new Coordinates(x - 1, y)))
            if(cellHashMap.get(new Coordinates(x - 1, y)).getState().equals(State.HEAD))
                headNeighbours++;
        if(cellHashMap.containsKey(new Coordinates(x + 1, y)))
            if(cellHashMap.get(new Coordinates(x + 1, y)).getState().equals(State.HEAD))
                headNeighbours++;
        return headNeighbours;
    }
}
