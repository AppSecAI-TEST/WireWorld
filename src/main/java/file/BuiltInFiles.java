package file;

import java.io.File;
import java.io.IOException;

public class BuiltInFiles {
    private static final String BUILT_IN_FILES_DIRECTORY = new String(System.getProperty("user.dir") + "/built_in_structures");

    public static File[] getListOfBuiltInFiles() {
        return new File(BUILT_IN_FILES_DIRECTORY).listFiles();
    }

    public static File getBuiltInFile(File file) {
        File builtInFile = null;
        for (File singleFile : getListOfBuiltInFiles()) {
            if (singleFile.getName().compareToIgnoreCase(file.getName()) == 0) {
                try {
                    builtInFile = singleFile.getCanonicalFile();
                } catch(IOException e) {
                    System.out.println("Blad wczytywania wbudowanej struktury.");
                    return null;
                }
            }
        }
        return builtInFile;
    }
}
