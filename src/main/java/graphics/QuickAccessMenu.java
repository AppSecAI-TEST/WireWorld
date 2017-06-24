package graphics;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/*menu szybkiego dostepu zawierajace pola z liczba generacji, rozmiarami mapy, przyciskami sterujacymi oraz sliderem do ustawienia czasu wyswetlania pojedynczej generacji*/
public class QuickAccessMenu {
    private HBox quickAccessMenu;
    private Button backButton;
    private Button nextButton;
    private Button startStopButton;
    private TextField mapXLabel;
    private TextField mapYLabel;
    private TextField numberOfGenerations;
    private GenerationInterval generationInterval;

    public QuickAccessMenu() {
        quickAccessMenu = new HBox();
        quickAccessMenu.setMinWidth(1024);

        //Buttons
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(30);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
        backButton = new Button("Wstecz");
        nextButton = new Button("Do przodu");
        startStopButton = new Button("Start");
        startStopButton.setDisable(true);
        buttonsHBox.getChildren().addAll(backButton, startStopButton, nextButton);

        //TextFields and IntervalSlider
        HBox textFieldsHBox = new HBox();
        textFieldsHBox.setMinWidth(800);
        textFieldsHBox.setSpacing(30);
        mapXLabel = new TextField();
        mapXLabel.setPromptText("Mapa X");
        mapXLabel.setMaxWidth(60);
        mapYLabel = new TextField();
        mapYLabel.setPromptText("Mapa Y");
        mapYLabel.setMaxWidth(60);
        numberOfGenerations = new TextField();
        numberOfGenerations.setMinWidth(100);
        numberOfGenerations.setMaxWidth(100);
        numberOfGenerations.setPromptText("Liczba generacji");
        generationInterval = new GenerationInterval(0, 10, 5);
        textFieldsHBox.getChildren().addAll(generationInterval.getGenerationInterval(), mapXLabel, mapYLabel, numberOfGenerations);

        quickAccessMenu.setSpacing(30);
        quickAccessMenu.setAlignment(Pos.BOTTOM_CENTER);
        quickAccessMenu.getChildren().addAll(textFieldsHBox, buttonsHBox);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getStartStopButton() {
        return startStopButton;
    }

    public TextField getMapXLabel() {
        return mapXLabel;
    }

    public TextField getMapYLabel() {
        return mapYLabel;
    }

    public TextField getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public GenerationInterval getGenerationInterval() {
        return generationInterval;
    }

    public HBox getQuickAccessMenu() {
        return quickAccessMenu;
    }

    public Slider getIntervalSlider() {
        return generationInterval.getIntervalSlider();
    }

    public void clearNumberOfGenerations() {
        this.numberOfGenerations.setText(new String(""));
    }
}
