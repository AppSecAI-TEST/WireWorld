package file.reader;

import logic.Cell;
import logic.Coordinates;

import java.io.File;
import java.util.HashMap;

interface ReaderInterface {
    HashMap<Coordinates, Cell> readFile(File file);
}
