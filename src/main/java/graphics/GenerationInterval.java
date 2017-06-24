package graphics;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/*klasa stanowiaca opakowanie dla slidera, ktory wyznacza czas opoznienia*/
    class GenerationInterval {
    private HBox generationInterval;
    private Slider intervalSlider;
    private Label captionLabel;
    private Label valueLabel;

    public GenerationInterval(int min, int max, int value) {
        generationInterval = new HBox(10);
        intervalSlider = IntervalSlider.makeIntervalSlider(min, max, value);
        captionLabel = new Label("Interwal");
        valueLabel = new Label("[s]");
        generationInterval.getChildren().addAll(captionLabel, intervalSlider, valueLabel);
    }

    HBox getGenerationInterval() {
        return generationInterval;
    }

    Slider getIntervalSlider() {
        return intervalSlider;
    }
}
