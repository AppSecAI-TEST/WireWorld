package graphics;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.List;

/*klasa zawierajaca implementacje kwadratu na planszy*/
class Square {
    private Color color = Color.BLACK;
    private Rectangle square;
    private double dimension;
    private boolean isMarked = false;
    private Mesh mesh;
    private ContextMenu contextMenu;

    Square(double dimension, Color color, Mesh mesh) {
        this.mesh = mesh;
        this.dimension = dimension;
        square = new Rectangle(this.dimension, this.dimension);
        this.setProperties(color, color, false);
        square.setOnMouseClicked(e -> {
            if (e.isControlDown() && !isMarked()) {
                /*jesli wcisniety control i kwadrat nie byl wczesniej zaznaczony to ustawiamy go jako zaznaczony*/
                this.setAsMarked();
                /*esli wcisniety control i kwadrat byl wczesniej zaznaczony to go odznaczamy*/
            } else if (e.isControlDown() && isMarked()) {
                this.setAsUnmarked();
                /*zwykle klikniecie na kwadrat powoduje zmiane koloru na nastepny kolor w sekwencji*/
            } else if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 1 && !isMarked()) {
                this.setNextColor();
            }
        });
        square.setOnContextMenuRequested(e -> contextMenu.show(this.square, e.getScreenX(), e.getScreenY()));
        square.setStroke(Paint.valueOf("grey"));
        this.getContextMenu();
    }

    Rectangle getSquare() {
        return square;
    }

    private void setProperties(Color color, Color fillColor, Boolean isMarked) {
        if(color != null)
            this.color = color;
        if(fillColor != null)
            this.square.setFill(Paint.valueOf(fillColor.toString()));
        if(isMarked != null)
            this.isMarked = isMarked;
    }

    /*menu kontekstowe*/
    private void getContextMenu() {
        List<Color> listOfColors = Colors.getListOfColors();
        HashMap<Color, String> namesOfColors = Colors.getNamesOfColors();
        contextMenu = new ContextMenu();

        for(Color singleColor : listOfColors) {
            MenuItem item = new MenuItem(namesOfColors.get(singleColor));
            item.setOnAction(e -> {
                if (mesh.sizeOfMarkedSquares() > 0) {
                    for (Square square : mesh.getMarkedSquares()) {
                        square.changeColor(singleColor);
                    }
                    mesh.setAsUnmarked();
                }
                else
                    this.changeColor(singleColor);
            });
            contextMenu.getItems().add(item);
        }
    }

    private void setAsMarked() {
        Color selectionColor = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 0.5);
        this.setProperties(null, selectionColor, true);
        mesh.setAsMarked(this);
    }

    private void setAsUnmarked() {
        this.setProperties(null, this.color, false);
        mesh.setAsUnmarked(this);
    }

    private boolean isMarked() {
        return isMarked;
    }

    private void setNextColor() {
        Color newColor = SequenceOfColors.getNext(this.color);
        this.setProperties(newColor, newColor, null);
    }

    void changeColor(Color color){
        this.setProperties(color, color, false);
    }

    Color getColor() {
        return color;
    }
}
