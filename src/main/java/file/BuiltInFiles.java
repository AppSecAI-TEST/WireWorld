package file;

import java.io.File;
import java.io.IOException;

/*klasa sluzaca do znajdywania wbudowanych struktur, posiada sciezke wzgledna do folderu zawierajacego wbudowane struktury*/
public class BuiltInFiles implements BuiltInFilesInterface {

    private static final File BUILT_IN_FILES_DIRECTORY = new File(System.getProperty("user.dir") + "/built_in_structures");

    public File[] getListOfBuiltInFiles() {
        return BUILT_IN_FILES_DIRECTORY.listFiles();
    }

    public File getBuiltInFile(File file) {
        File builtInFile = null;
        for (File singleFile : getListOfBuiltInFiles()) {
            if (singleFile.getName().compareToIgnoreCase(file.getName()) == 0) {
                try {
                    builtInFile = singleFile.getCanonicalFile();
                } catch(IOException e) {
                    return null;
                }
            }
        }
        return builtInFile;
    }
}
