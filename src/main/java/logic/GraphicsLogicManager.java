package logic;

import graphics.Mesh;
import javafx.scene.paint.Color;
import logic.Coordinates;
import logic.Generation;
import logic.State;

import java.util.HashMap;


/*klasa posredniczaca w wymianie danych pomiedzy czescia logiki a czescia graficzna*/
public class GraphicsLogicManager {

    private Mesh mesh;
    private Generation generation;

    public GraphicsLogicManager(Mesh mesh, Generation generation){
        this.mesh = mesh;
        this.generation = generation;
    }

    /*aktualizacja planszy*/
    public void updateMesh() {
        HashMap<Coordinates, Color> colorHashMap = new HashMap<>(generation.getGeneration().size());
        for(Coordinates coordinates : generation.getGeneration().keySet()) {
            Color color = stateToColor(generation.getGeneration().get(coordinates).getState());
            colorHashMap.put(coordinates, color);
        }
        mesh.update(colorHashMap);
    }

    /*aktualizacja generacji poprzez aktualny stan planszy*/
    public void updateGeneration() {
        HashMap<Coordinates, State> cellHashMap = new HashMap<>(mesh.getMesh().size());
        for(Coordinates coordinates : mesh.getMesh().keySet()) {
            State state = colorToState(mesh.getMesh().get(coordinates));
            cellHashMap.put(coordinates, state);
        }
        generation.updateByState(cellHashMap);
    }

    /*aktualizacja generacji poprzez nowa mape komorek*/
    public void updateGeneration(HashMap<Coordinates, Cell> cellHashMap) {
        generation.updateByCell(cellHashMap);
    }

    private Color stateToColor(State state) {
        switch (state) {
            case CONDUCTOR:
                return Color.YELLOW;
            case EMPTY:
                return Color.BLACK;
            case HEAD:
                return Color.BLUE;
            case TAIL:
                return Color.RED;
        }
        return null;
    }

    private State colorToState(Color color) {
        if(color == Color.BLACK)
            return State.EMPTY;
        else if(color == Color.RED)
            return State.TAIL;
        else if(color == Color.YELLOW)
            return State.CONDUCTOR;
        else if(color == Color.BLUE)
            return State.HEAD;
        else
            return null;
    }
}
