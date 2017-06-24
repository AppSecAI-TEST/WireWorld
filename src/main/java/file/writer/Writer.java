package file.writer;

import logic.Cell;
import logic.Coordinates;

import java.io.File;
import java.util.HashMap;

public class Writer {
    static public void writeFile(HashMap<Coordinates, Cell> cellHashMap, File file) {
        if (file.getName().endsWith(".json"))
            new JSONWriter().writeFile(cellHashMap, file);
    }
}
