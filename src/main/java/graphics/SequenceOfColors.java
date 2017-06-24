package graphics;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/*klasa zawierajaca sekwencje kolorow, ktora wystepuje po pojedynczym kliknieciu na planszy na komorke*/
class SequenceOfColors {
    private static final Color[] colors = Colors.getColors();
    static Color getNext(Color color) {
        int index = 0;
        for(int i = 0; i < colors.length; i++)
            if(color == colors[i])
                index = i;
        if((index + 1) >= colors.length)
            index = 0;
        else index++;
        return colors[index];
    }
}
