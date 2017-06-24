package graphics;

import javafx.scene.control.Slider;

/*slider wyznaczajacy opoznienie pomiedzy kolejnymi wyswietleniami*/
class IntervalSlider {
    static Slider makeIntervalSlider(int min, int max, int value) {
        Slider intervalSlider = new Slider(min, max, value);
        intervalSlider.setShowTickLabels(true);
        intervalSlider.setShowTickMarks(true);
        intervalSlider.setMinWidth(200);
        intervalSlider.setMinorTickCount(3);
        intervalSlider.setMajorTickUnit(2);
        intervalSlider.setSnapToTicks(true);
        return intervalSlider;
    }
}
