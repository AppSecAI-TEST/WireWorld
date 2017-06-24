package logic;

import java.util.ArrayList;
import java.util.HashMap;

/*klasa reprezentujaca historie map komorek w programie*/
public class MapHistory {
    private ArrayList<HashMap<Coordinates, Cell>> mapHistory;
    private int currentIndex;

    public MapHistory(int size) {
        mapHistory = new ArrayList<>(size);
        currentIndex = -1;
    }

    public void add(HashMap<Coordinates, Cell> map) {
        HashMap<Coordinates, Cell> newMap = new HashMap<>(map.size());
        for (Coordinates coordinates : map.keySet())
            newMap.put(coordinates, new Cell(coordinates, map.get(coordinates).getState()));

        if(this.mapHistory.size() > 0) {
            Generation generation = new Generation(this.mapHistory.get(currentIndex));
            Generation otherGeneration = new Generation(newMap);
            if (generation.equals(otherGeneration))
                return;
        }

        currentIndex++;
        if (mapHistory.size() > currentIndex) {
            if (mapHistory.get(currentIndex) != null) {
                mapHistory.remove(currentIndex);
                mapHistory.add(currentIndex, newMap);
                /*w momencie gdy cofamy sie i nadpisujemy historie, jej dalsza czesc zostaje usunieta w celu zachowania spojnosci (zwlaszcza w trybie animacji)*/
                /*usuwamy od konca*/
                for(int i = mapHistory.size() - 1; i >= currentIndex + 1; i--)
                    mapHistory.remove(i);
            }
        } else
            mapHistory.add(currentIndex, newMap);
    }

    public HashMap<Coordinates, Cell> getNext() {
        if (currentIndex == (mapHistory.size() - 1))



            return null;
        else {
            currentIndex++;
            return mapHistory.get(currentIndex);
        }
    }

    public HashMap<Coordinates, Cell> getPrevious() {
        if (currentIndex == 0 || currentIndex == -1)
            return null;
        else {
            currentIndex--;
            return mapHistory.get(currentIndex);
        }
    }

    public HashMap<Coordinates, Cell> getCurrent() {
        if (currentIndex < 0)
            return null;
        return mapHistory.get(currentIndex);
    }

    public HashMap<Coordinates, Cell> getFirst() {
        if (mapHistory.size() >= 0)
            return mapHistory.get(0);
        else
            return null;
    }

    public HashMap<Coordinates, Cell> getLast() {
        if (mapHistory.size() >= 0)
            return mapHistory.get(mapHistory.size() - 1);
        else
            return null;
    }

    public int size() {
        return mapHistory.size();
    }
}
