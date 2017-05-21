package graphics;

import javafx.scene.layout.GridPane;

public class Mesh {
    private GridPane mesh;

    public GridPane makeMesh(int numberOfRows, int numberOfColumns, int dimensionOfCell) {
        mesh = new GridPane();
        for(int row = 0; row < numberOfRows; row++)
            for(int column = 0; column < numberOfColumns; column++)
                mesh.add(new Square().makeSquare(dimensionOfCell), column, row );
        return mesh;
    }

}
