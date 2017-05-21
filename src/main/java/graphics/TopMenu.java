package graphics;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu {
    private MenuBar menuBar;

    public MenuBar makeTopMenu() {
        //File menu
        Menu fileMenu = new Menu("Plik");

        //Menu items
        MenuItem newMesh = new MenuItem("Nowa plansza");
        MenuItem openFile = new MenuItem("Otwórz...");
        MenuItem saveFile = new MenuItem("Zapisz");
        MenuItem saveFileAs = new MenuItem("Zapisz jako...");

        fileMenu.getItems().addAll(newMesh, openFile, saveFile, saveFileAs);



        //Edit menu
        Menu editMenu = new Menu("Edycja");

        //Menu items
        MenuItem back = new MenuItem("Wstecz");
        MenuItem next = new MenuItem("Do przodu");
        MenuItem startStop = new MenuItem("Start/Stop");
        MenuItem put = new MenuItem("Wstaw...");

        editMenu.getItems().addAll(back, next, startStop, put);



        //Settings menu
        Menu settingsMenu = new Menu("Ustawienia");

        //Menu items
        MenuItem preferences = new MenuItem("Preferencje");
        MenuItem optionsOfGeneration = new MenuItem("Opcje generacji");
        MenuItem restoreDefaultSettings = new MenuItem("Przywróć ustawienia domyślne");

        settingsMenu.getItems().addAll(preferences, optionsOfGeneration, restoreDefaultSettings);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, settingsMenu);
        return menuBar;
    }
}
