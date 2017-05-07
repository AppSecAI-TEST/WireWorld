package main;

import file.reader.JSONReader;
import logic.Cell;
import logic.Coordinates;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        JSONReader reader = new JSONReader();
        HashMap<Coordinates, Cell> cells = reader.readFile(new File("data.json"));
        System.out.println(cells.toString());



    }
}
