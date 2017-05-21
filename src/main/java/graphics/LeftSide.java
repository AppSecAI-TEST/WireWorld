package graphics;


import file.BuiltInFiles;
import javafx.scene.control.ListView;

import java.io.File;

public class LeftSide {
    private ListView<String> leftSide;

    public ListView<String> makeLeftSide() {
        leftSide = new ListView<>();
        leftSide.setMaxWidth(100);
        File[] listOfFiles = BuiltInFiles.getListOfBuiltInFiles();
        for(File file: listOfFiles)
            leftSide.getItems().add(file.getName());
        return leftSide;
    }
}
