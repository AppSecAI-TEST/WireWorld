package graphics;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*kolory dostepne w programie wraz z ich nazwa*/
public class Colors{
    public static final Color[] colors = {Color.RED, Color.YELLOW, Color.BLACK, Color.BLUE};

    public static Color[] getColors() {
        return colors;
    }

    public static List<Color> getListOfColors() {
        return Arrays.asList(colors);
    }

    public static HashMap<Color, String> getNamesOfColors() {
        HashMap<Color, String> namesOfColors = new HashMap<>(colors.length);
        namesOfColors.put(Color.RED, "czerwony");
        namesOfColors.put(Color.BLACK, "czarny");
        namesOfColors.put(Color.BLUE, "niebieski");
        namesOfColors.put(Color.YELLOW, "zolty");
        return namesOfColors;
    }
}
