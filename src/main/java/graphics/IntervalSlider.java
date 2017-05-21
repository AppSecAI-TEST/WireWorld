package graphics;

import javafx.scene.control.Slider;

class IntervalSlider {
    private Slider intervalSlider;

    Slider makeIntervalSlider(int min, int max, int value) {
        intervalSlider = new Slider(min, max, value);
        intervalSlider.setShowTickLabels(true);
        intervalSlider.setShowTickMarks(true);
        intervalSlider.setMinWidth(200);
        intervalSlider.setMinorTickCount(3);
        intervalSlider.setMajorTickUnit(2);
        return intervalSlider;
    }
}
