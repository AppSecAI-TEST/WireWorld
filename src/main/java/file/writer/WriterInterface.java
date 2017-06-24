package file.writer;

import logic.Cell;
import logic.Coordinates;

import java.io.File;
import java.util.HashMap;

public interface WriterInterface {
    public void writeFile(HashMap<Coordinates, Cell> cellHashMap, File file);

}
