package file.reader;

import logic.Cell;
import logic.Coordinates;

import java.io.File;
import java.util.HashMap;

public class Reader {

    public HashMap<Coordinates, Cell> readFile(File file) {
        HashMap<Coordinates, Cell> cellMap = null;
        if (file.getName().endsWith(".json"))
            cellMap = new JSONReader().readFile(file);
        return cellMap;
    }
}