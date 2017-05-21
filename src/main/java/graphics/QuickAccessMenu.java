package graphics;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class QuickAccessMenu {
    private HBox quickAccessMenu;

    public HBox makeQuickAccessMenu() {
        quickAccessMenu = new HBox();
        quickAccessMenu.setMinWidth(1024);

        //Buttons
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(30);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
        Button backButton = new Button("Wstecz");
        Button nextButton = new Button("Do przodu");
        ToggleButton startStop = new ToggleButton("Start/Stop");
        buttonsHBox.getChildren().addAll(backButton, startStop, nextButton);

        HBox textFieldsHBox = new HBox();
        textFieldsHBox.setMinWidth(800);
        textFieldsHBox.setSpacing(30);
        TextField mapXLabel = new TextField();
        mapXLabel.setPromptText("Mapa X");
        mapXLabel.setMaxWidth(60);
        TextField mapYLabel = new TextField();
        mapYLabel.setPromptText("Mapa Y");
        mapYLabel.setMaxWidth(60);
        TextField numberOfGenerations = new TextField();
        numberOfGenerations.setMinWidth(100);
        numberOfGenerations.setMaxWidth(100);
        numberOfGenerations.setPromptText("Liczba generacji");

//        HBox generationInterval = new GenerationInterval().makeGenerationInterval(0, 10, 5);
//        Slider generationInterval = new IntervalSlider().makeIntervalSlider(0, 10, 5);
        GenerationInterval generationInterval = new GenerationInterval();
        generationInterval.makeGenerationInterval(0, 10, 5);;

        textFieldsHBox.getChildren().addAll(generationInterval.getGenerationInterval(), mapXLabel, mapYLabel, numberOfGenerations);

        quickAccessMenu.setSpacing(30);
        quickAccessMenu.setAlignment(Pos.BOTTOM_CENTER);
        quickAccessMenu.getChildren().addAll(textFieldsHBox, buttonsHBox);
        return quickAccessMenu;
    }

}
