package file.writer;

import com.google.gson.stream.JsonWriter;
import logic.Cell;
import logic.Coordinates;
import logic.State;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JSONWriter implements WriterInterface{
    @Override
    public void writeFile(HashMap<Coordinates, Cell> cellHashMap, File file) {
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            /*rozpoczecie pliku*/
            writer.beginObject();
            writer.name("name").value(file.getName().substring(0, file.getName().lastIndexOf('.')));
            writer.name("structure");
            writer.beginArray();

            /*znalezienie wszystkich niepustych stanow, jakie wystepuja w hashmapie*/
            List<State> states = new ArrayList<>();
            for(Cell cell : cellHashMap.values())
                if(cell.getState() != State.EMPTY && !states.contains(cell.getState()))
                    states.add(cell.getState());
            System.out.println(states);
            /*dla kazdego stanu zapisujemy komorki, ktore ten stan posiadaja*/
            for (State state : states) {
                writer.beginObject();
                writer.name("state").value(state.toString());
                writer.name("coordinates");
                writer.beginArray();
                for(Cell cell : cellHashMap.values()) {
                    if(cell.getState() == state) {
                        writer.beginObject();
                        writer.name("x").value(cell.getCoordinates().getX());
                        writer.name("y").value(cell.getCoordinates().getY());
                        writer.endObject();
                    }
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
            writer.endObject();
            writer.close();
        } catch (IOException e) {}
    }
}
