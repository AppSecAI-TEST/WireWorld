package graphics;

import javafx.scene.paint.Color;

class SequenceOfColors {
    private static Color[] colors = {Color.RED, Color.YELLOW, Color.BLACK, Color.BLUE};
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
