package graphics;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.Coordinates;

import java.util.HashMap;


/*klasa sluzaca do wyswietlania planszy*/
public class MeshView {

    private GridPane meshView;
    private int columns;
    private int rows;
    private double dimensionOfCell;
    private Mesh mesh;

    public MeshView(int rows, int columns, double dimensionOfCell, Mesh mesh, HashMap<Coordinates, Square> meshHashMap) {
        meshView = new GridPane();
        this.dimensionOfCell = dimensionOfCell;
        this.columns = columns;
        this.rows = rows;
        this.mesh = mesh;
        this.meshView = makeMeshView(meshHashMap);
    }

    public MeshView(int rows, int columns, double dimensionOfCell, Mesh mesh, GridPane meshView) {
        this.rows = rows;
        this.columns = columns;
        this.dimensionOfCell = dimensionOfCell;
        this.mesh = mesh;
        this.meshView = meshView;
    }

    public GridPane getMeshView() {
        return meshView;
    }

    public void update(HashMap<Coordinates, Square> meshHashMap) {
        meshView = makeMeshView(meshHashMap);
    }

    private GridPane makeMeshView(HashMap<Coordinates, Square> meshHashMap) {
        GridPane meshView = new GridPane();
        for(int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Coordinates coordinates = new Coordinates(column, row);
                Square square = meshHashMap.getOrDefault(coordinates, new Square(30, Color.BLACK, mesh));
                meshView.add(square.getSquare(), coordinates.getX(), coordinates.getY());
            }
        }
        return meshView;
    }
}
