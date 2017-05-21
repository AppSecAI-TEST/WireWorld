package graphics;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

class Square {
    private Color color = Color.BLACK;
    private Rectangle square;

    Rectangle makeSquare(double dimension, Color color) {
        this.color = color;
        square = new Rectangle(dimension, dimension);
        square.setFill(Paint.valueOf(color.toString()));
        square.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 1) {
                Color newColor = SequenceOfColors.getNext(this.color);
                this.color = newColor;
                square.setFill(Paint.valueOf(newColor.toString()));
            }
        });
        square.setOnContextMenuRequested(e -> System.out.println("Menu kontekstowe!!!"));
        square.setStroke(Paint.valueOf("grey"));
        return square;
    }

    Rectangle makeSquare(double dimension) {
        return makeSquare(dimension, Color.BLACK);
    }

}
