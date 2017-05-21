package graphics;

import javafx.stage.Stage;

public class MainStage{
    private Stage mainStage;

    public MainStage(double minWidth, double minHeight, String title) {
        makeMainStage(minWidth, minHeight, title);
    }

    public Stage makeMainStage() {
        return mainStage;
    }

    public Stage makeMainStage(double minWidth, double minHeight, String title) {
        mainStage = new Stage();
        mainStage.setMinHeight(minHeight);
        mainStage.setMinWidth(minWidth);
        mainStage.setTitle(title);
        return mainStage;
    }
}
