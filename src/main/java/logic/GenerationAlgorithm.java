package logic;

import java.util.HashMap;

public interface GenerationAlgorithm {
    public HashMap<Coordinates, Cell> getNextGeneration(HashMap<Coordinates, Cell> cellHashMap);
}
