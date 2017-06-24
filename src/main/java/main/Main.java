package main;

import file.BuiltInFiles;
import file.reader.Reader;
import file.writer.Writer;
import graphics.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.*;

import java.io.File;
import java.util.HashMap;

public class Main extends Application {

    private Generation generation;
    private GenerationAlgorithm generationAlgorithm;
    private MapHistory mapHistory;
    private GridPane board;
    private Mesh mesh;
    private GraphicsLogicManager graphicsLogicManager;
    private int numberOfGenerations;
    private int mapX = 35;
    private int mapY = 21;
    private int dimension = 30;
    private double delay = 5;

    private final int STAGE_WIDTH = 1191;
    private final int STAGE_HEIGHT = 850;
    private final String STAGE_TITLE = "WireWorld";

    private final int SCENE_WIDTH = 1024;
    private final int SCENE_HEIGHT = 768;

    private int SIZE_OF_MAP_HISTORY = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage mainStage = new MainStage(STAGE_WIDTH, STAGE_HEIGHT, STAGE_TITLE).getStage();
        mainStage.setResizable(false);

        /*----------LAYOUT----------*/
        BorderPane borderPaneLayout = new BorderPane();
        ListView<String> leftSide = new ListOfBuiltInFiles(new BuiltInFiles()).getListOfBuiltInFiles();

        /*----------MENU----------*/
        //FILE MENU
        Menu fileMenu = new Menu("Plik");
        MenuItem newMesh = new MenuItem("Nowa plansza");
        MenuItem openFile = new MenuItem("Otwórz...");
        MenuItem saveFileAs = new MenuItem("Zapisz jako...");
        fileMenu.getItems().addAll(newMesh, openFile, saveFileAs);

        MenuBar topMenu = new MenuBar();
        topMenu.getMenus().addAll(fileMenu);

        //init
        generationAlgorithm = new WireWorldAlgorithm();
        mesh = new Mesh(mapY, mapX, dimension);
        board = mesh.getMeshView();
        generation = new Generation();
        graphicsLogicManager = new GraphicsLogicManager(mesh, generation);
        graphicsLogicManager.updateGeneration();
        mapHistory = new MapHistory(SIZE_OF_MAP_HISTORY);
        mapHistory.add(generation.getGeneration());

        QuickAccessMenu quickAccessMenu = new QuickAccessMenu();
        quickAccessMenu.getMapXLabel().setText(mapX + "");
        quickAccessMenu.getMapYLabel().setText(mapY + "");
        HBox bottomMenu = quickAccessMenu.getQuickAccessMenu();

        borderPaneLayout.setMargin(leftSide, new Insets(0, 0, 37, 0));
        borderPaneLayout.setMargin(bottomMenu, new Insets(0, 0, 70, 0));
        borderPaneLayout.setTop(topMenu);
        borderPaneLayout.setLeft(leftSide);
        borderPaneLayout.setCenter(board);
        borderPaneLayout.setBottom(bottomMenu);
        Scene mainScene = new Scene(borderPaneLayout, SCENE_WIDTH, SCENE_HEIGHT);
        mainStage.setScene(mainScene);
        mainStage.show();


        /*QUICK_ACCESS_MENU LISTENERS*/
        quickAccessMenu.getIntervalSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                delay = newValue.doubleValue();
            }
        });

        quickAccessMenu.getMapXLabel().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mapX = Integer.valueOf(newValue);
            }
        });

        quickAccessMenu.getMapYLabel().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mapY = Integer.valueOf(newValue);
            }
        });

        quickAccessMenu.getNumberOfGenerations().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    numberOfGenerations = Integer.valueOf(newValue);
                } catch (NumberFormatException e) {
                }
                if(!newValue.equals(null) && !newValue.equals(new String()))
                    quickAccessMenu.getStartStopButton().setDisable(false);
                else
                    quickAccessMenu.getStartStopButton().setDisable(true);
            }
        });

        /*QUICK ACCESS MENU ACTIONS*/

        quickAccessMenu.getBackButton().setOnAction(e -> {
            graphicsLogicManager.updateGeneration();
            mapHistory.add(generation.getGeneration());
            if(mapHistory.size() > 0 && !(generation.getGeneration().equals(mapHistory.getFirst()))) {
                graphicsLogicManager.updateGeneration(mapHistory.getPrevious());
                graphicsLogicManager.updateMesh();
                borderPaneLayout.setCenter(mesh.getMeshView());
            }
        });
        quickAccessMenu.getNextButton().setOnAction(e -> {
            graphicsLogicManager.updateGeneration();
            mapHistory.add(generation.getGeneration());
            if(mapHistory.size() > 0 && !(generation.getGeneration().equals(mapHistory.getLast()))) {
                graphicsLogicManager.updateGeneration(mapHistory.getNext());
                graphicsLogicManager.updateMesh();
                borderPaneLayout.setCenter(mesh.getMeshView());
            }
        });

        quickAccessMenu.getStartStopButton().setOnAction(e -> {
            graphicsLogicManager.updateGeneration();
            mapHistory.add(generation.getGeneration());
            for(int i = 0; i < numberOfGenerations; i++) {
                generation.getNextGeneration(generationAlgorithm);
                mapHistory.add(generation.getGeneration());
            }
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    int i = 0;
                    while (i < numberOfGenerations) {
                        graphicsLogicManager.updateGeneration(mapHistory.getPrevious());
                        i++;
                    }
                    for(i = 0; i < numberOfGenerations; i++) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                graphicsLogicManager.updateGeneration(mapHistory.getNext());
                                graphicsLogicManager.updateMesh();
                                borderPaneLayout.setCenter(mesh.getMeshView());
                            }
                        });
                        Thread.sleep((int)(delay * 1000));
                    }
                    return null;
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        });

        /*ACTIONS*/
        /*FILE MENU ACTIONS*/
        /*OPEN FILE*/
        openFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Otwórz");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("*.json", "*.json"),
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );
            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                mesh.setAsUnmarked();
                graphicsLogicManager.updateGeneration();
                mapHistory.add(generation.getGeneration());
                generation.updateByCell(Reader.readFile(file));
                mapHistory.add(generation.getGeneration());
                graphicsLogicManager.updateMesh();
                borderPaneLayout.setCenter(mesh.getMeshView());
            }
            else
                System.out.println("Nie wybrano pliku.");
        });

        /*SAVE FILE*/
        saveFileAs.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz jako");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("*.json", "*.json"),
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );
            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                graphicsLogicManager.updateGeneration();
                Writer.writeFile(generation.getGeneration(), file);
            }
            else
                System.out.println("Nie wybrano pliku.");
        });

        /*NEW MESH*/
        newMesh.setOnAction(e -> {
            mesh = new Mesh(mapY, mapX, dimension);
            board = mesh.getMeshView();
            generation = new Generation();
            graphicsLogicManager = new GraphicsLogicManager(mesh, generation);
            graphicsLogicManager.updateGeneration();
            mapHistory = new MapHistory(SIZE_OF_MAP_HISTORY);
            mapHistory.add(generation.getGeneration());
            borderPaneLayout.setCenter(board);
            quickAccessMenu.clearNumberOfGenerations();
        });
    }
}













