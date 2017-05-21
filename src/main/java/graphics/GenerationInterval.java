package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

class GenerationInterval {
    private HBox generationInterval;
    private Slider intervalSlider;
    private Label captionLabel;
    private Label valueLabel;

    void makeGenerationInterval(int min, int max, int value) {
        generationInterval = new HBox(20);
        intervalSlider = new IntervalSlider().makeIntervalSlider(min, max, value);
        captionLabel = new Label("Interwał");
        valueLabel = new Label(Double.toString(intervalSlider.getValue()));

        intervalSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number oldValue, Number newValue) {
                valueLabel.setText(String.format("%.2f", newValue));
            }
        });

        generationInterval.getChildren().addAll(captionLabel, intervalSlider, valueLabel);
//        return generationInterval;
    }

    HBox getGenerationInterval() {
        return generationInterval;
    }

}
