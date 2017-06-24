package graphics;


import file.BuiltInFilesInterface;
import javafx.scene.control.ListView;

import java.io.File;

/*klasa zajmujaca sie pobieraniem listy wbudowanych plikow, sluzy glowne do tworzenia listy po lewej stronie okna*/

public class ListOfBuiltInFiles {
    private ListView<String> listOfBuiltInFiles;

    public ListOfBuiltInFiles(BuiltInFilesInterface builtInFiles) {
        listOfBuiltInFiles = new ListView<>();
        listOfBuiltInFiles.setMaxWidth(100);
        File[] listOfFiles = builtInFiles.getListOfBuiltInFiles();
        for(File file: listOfFiles)
            listOfBuiltInFiles.getItems().add(file.getName());
    }

    public ListView<String> getListOfBuiltInFiles() {
        return listOfBuiltInFiles;
    }
}
