package graphics;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;


/*klasa reprezentujaca tablice, skladajaca sie z kwadratow, zarzadza wyswietlaniem oraz aktualizacja planszy*/
public class Mesh {

    private HashMap<Coordinates, Square> meshHashMap;
    private MeshView meshView;
    private int rows;
    private int columns;
    private ArrayList<Square> markedSquares;
    private double dimensionOfCell;

    public Mesh(int rows, int columns, double dimensionOfCell) {
        this.columns = columns;
        this.rows = rows;
        this.dimensionOfCell = dimensionOfCell;
        meshHashMap = makeHashMap(rows, columns, dimensionOfCell);
        meshView = new MeshView(rows, columns, dimensionOfCell, this, meshHashMap);
        markedSquares = new ArrayList<>(10);
    }

    public GridPane getMeshView() {
        return meshView.getMeshView();
    }

    private HashMap<Coordinates, Square> makeHashMap(int rows, int columns, double dimension) {
        HashMap<Coordinates, Square> meshHashMap = new HashMap<>(rows * columns);
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                meshHashMap.put(new Coordinates(column, row), new Square(dimension, Color.BLACK, this));
            }
        }
        return meshHashMap;
    }

    /*aktualizacja planszy, jako parametr otrzymuje generacje*/
    public void update(HashMap<Coordinates, Color> colorHashMap){
        HashMap<Coordinates, Square> meshMap = new HashMap<>(colorHashMap.size());
        for(Coordinates coordinates : colorHashMap.keySet()) {
            Color color = colorHashMap.get(coordinates);
            meshMap.put(coordinates, new Square(dimensionOfCell, color, this));
        }
        for(Coordinates coordinates : meshMap.keySet()) {
            if(this.meshHashMap.containsKey(coordinates)) {
                if(!this.meshHashMap.get(coordinates).equals(meshMap.get(coordinates)))
                    this.meshHashMap.replace(coordinates, meshMap.get(coordinates));
            } else {
                this.meshHashMap.put(coordinates, new Square(dimensionOfCell, meshMap.get(coordinates).getColor(), this));
            }
        }
        meshView.update(meshMap);
    }

    void setAsMarked(Square square) {
        if (!isInMarkedSquares(square)) {
            markedSquares.add(square);
        }
    }

    void setAsUnmarked(Square square) {
        if (isInMarkedSquares(square)) {
            markedSquares.remove(square);
        }
    }

    public void setAsUnmarked() {
        markedSquares = new ArrayList<>(10);
    }

    private boolean isInMarkedSquares(Square square) {
        if(markedSquares.contains(square))
            return true;
        return false;
    }

    int sizeOfMarkedSquares() {
        return markedSquares.size();
    }

    ArrayList<Square> getMarkedSquares() {
        return markedSquares;
    }

    public HashMap<Coordinates, Color> getMesh() {
        HashMap<Coordinates, Color> colorHashMap = new HashMap<>(meshHashMap.size());
        for(Coordinates coordinates : meshHashMap.keySet()) {
            Color color = meshHashMap.get(coordinates).getColor();
            colorHashMap.put(coordinates, color);
        }
        return colorHashMap;
    }
}
