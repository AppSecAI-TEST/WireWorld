package file.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import file.BuiltInFiles;
import logic.Cell;
import logic.Coordinates;
import logic.State;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class JSONReader implements ReaderInterface {

    public HashMap<Coordinates, Cell> readFile(File file) {
        HashMap<Coordinates, Cell> cellHashMap = new HashMap<Coordinates, Cell>();
        String jsonData = null;

        /*reading file as simple string*/
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line);
                jsonData = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            return null;
        } catch (IOException e) {
            System.out.println("Błąd wczytywania pliku.");
            return null;
        }

        /*parsing string to json*/
        try {
            JsonObject object = new JsonParser().parse(jsonData).getAsJsonObject();
            String nameOfStructure = object.get("name").getAsString();
            JsonArray structure = object.getAsJsonArray("structure");

            /*parsing built-in structure*/
            if (structure.size() == 1 && structure.get(0).getAsJsonObject().get("state") == null) {
                String fileName = nameOfStructure + ".json";
                /*looking for file with built-in structure*/
                File builtInFile = BuiltInFiles.getBuiltInFile(new File(fileName));
                if(builtInFile == null)
                    System.out.println("Nie znaleziono wbudowanej struktury.");

                /*read built-in structure*/
                HashMap<Coordinates, Cell> builtInStructure = readFile(builtInFile);

                /*shift in original file*/
                JsonArray coordinates = structure.get(0).getAsJsonObject().getAsJsonArray("coordinates");
                int x = coordinates.get(0).getAsJsonObject().get("x").getAsInt();
                int y = coordinates.get(0).getAsJsonObject().get("y").getAsInt();
                Coordinates shift = new Coordinates(x, y);

                /*final cellHashMap*/
                ArrayList<Cell> cells = new ArrayList<>(builtInStructure.values());
                for(Cell cell: cells) {
                    x = cell.getCoordinates().getX() + shift.getX();
                    y = cell.getCoordinates().getY() + shift.getY();
                    Cell newCell = new Cell(new Coordinates(x, y), cell.getState());
                    cellHashMap.put(newCell.getCoordinates(), newCell);
                }
            }
            /*simple parsing*/
            else {
                for (JsonElement singleState : structure) {
                    State stateName = State.valueOf(singleState.getAsJsonObject().get("state").getAsString().toUpperCase());
                    JsonArray coordinates = singleState.getAsJsonObject().getAsJsonArray("coordinates");
                    int x;
                    int y;
                    for (JsonElement singleCoordinates : coordinates) {
                        x = singleCoordinates.getAsJsonObject().get("x").getAsInt();
                        y = singleCoordinates.getAsJsonObject().get("y").getAsInt();
                        cellHashMap.put(new Coordinates(x, y), new Cell(new Coordinates(x, y), stateName));
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Niepoprawny format pliku.");
            return null;
        }
        return cellHashMap;
    }
}