package logic;

import java.util.HashMap;

/*interfejs dla algorytmu generowania kolejnych komorek*/
public interface GenerationAlgorithm {
    public HashMap<Coordinates, Cell> getNextGeneration(HashMap<Coordinates, Cell> cellHashMap);
}
