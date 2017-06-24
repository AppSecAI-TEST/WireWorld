package graphics;

import javafx.stage.Stage;

public class MainStage{

    private Stage mainStage;

    public MainStage(double minWidth, double minHeight, String title) {
        mainStage = new Stage();
        mainStage.setMinHeight(minHeight);
        mainStage.setMinWidth(minWidth);
        mainStage.setTitle(title);
    }

    public Stage getStage() {
        return mainStage;
    }
}
