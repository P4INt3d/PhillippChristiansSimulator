package de.nachname;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    // VM Args: --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Editor Menü
        Image neu16 = new Image("New16.gif");
        ImageView neu16View = new ImageView(neu16);
        Image open16 = new Image("Open16.gif");
        ImageView open16View = new ImageView(open16);
        Image print16 = new Image("Print16.gif");
        ImageView print16View = new ImageView(print16);
        Menu editorMenu = new Menu("_Editor");
        MenuItem neu = new MenuItem("_Neu");
        neu.setAccelerator(KeyCombination.valueOf("SHORTCUT+N"));
        neu.setGraphic(neu16View);
        MenuItem open = new MenuItem("_Öffnen");
        open.setAccelerator(KeyCombination.valueOf("SHORTCUT+O"));
        open.setGraphic(open16View);
        MenuItem compile = new MenuItem("_Kompilieren");
        compile.setAccelerator(KeyCombination.valueOf("SHORTCUT+K"));
        MenuItem print1 = new MenuItem("_Drucken");
        print1.setAccelerator(KeyCombination.valueOf("SHORTCUT+P"));
        MenuItem quit = new MenuItem("_Beenden");
        print1.setGraphic(print16View);
        quit.setAccelerator(KeyCombination.valueOf("SHORTCUT+Q"));
        editorMenu.getItems().addAll(neu, open, new SeparatorMenuItem(), compile, print1, new SeparatorMenuItem(), quit);


        //Territorium Menü
        Menu territoriumMenu = new Menu("_Territorium");
        Menu save = new Menu("S_peichern");
        MenuItem xml = new MenuItem("XML");
        MenuItem jaxb = new MenuItem("JAXB");
        MenuItem serial = new MenuItem("Serialisieren");
        Menu load = new Menu("La_den");
        Menu saveAP = new Menu("_Als Bild speichern");
        MenuItem print2 = new MenuItem("_Drucken");
        MenuItem changeSize = new MenuItem("_Größe ändern...");
        RadioMenuItem placeElephant = new RadioMenuItem("Elefant _platzieren");
        RadioMenuItem placePeanut = new RadioMenuItem("E_rdnuss platzieren");
        RadioMenuItem placeMouse = new RadioMenuItem("_Maus platzieren");
        RadioMenuItem deleteCell = new RadioMenuItem("_Kachel löschen");
        save.getItems().addAll(xml, jaxb, serial);
        territoriumMenu.getItems().addAll(save, load, saveAP, print2, changeSize, new SeparatorMenuItem(), placeElephant, placePeanut, placeMouse, deleteCell);


        //Elefant Menü
        Menu elefantMenu = new Menu("E_lefant");
        MenuItem amountP = new MenuItem("Gesammelte Erdnüsse");
        MenuItem leftTurn = new MenuItem("linksUm");
        leftTurn.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+L"));
        MenuItem step = new MenuItem("vor");
        step.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+V"));
        MenuItem take = new MenuItem("nimm");
        take.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+N"));
        MenuItem give = new MenuItem("gib");
        give.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+G"));
        ToggleGroup terriToggle = new ToggleGroup();
        placeElephant.setToggleGroup(terriToggle);
        placePeanut.setToggleGroup(terriToggle);
        placeMouse.setToggleGroup(terriToggle);
        deleteCell.setToggleGroup(terriToggle);
        elefantMenu.getItems().addAll(amountP, leftTurn, step, take, give);

        //Simualation Menü
        Image playImage = new Image("Play16.gif");
        ImageView playImageView = new ImageView(playImage);
        Menu simulationMenu = new Menu("_Simulation");
        Image pauseImage = new Image("Pause16.gif");
        ImageView pauseImageView = new ImageView(pauseImage);
        Image stopImage = new Image("Stop16.gif");
        ImageView stopImageView = new ImageView(stopImage);
        MenuItem play = new MenuItem("Start/Fortsetzen");
        play.setAccelerator(KeyCombination.valueOf("SHORTCUT+F11"));
        play.setGraphic(playImageView);
        MenuItem pause = new MenuItem("Pause");
        pause.setGraphic(pauseImageView);
        MenuItem stop = new MenuItem("Stopp");
        stop.setAccelerator(KeyCombination.valueOf("SHORTCUT+F12"));
        stop.setGraphic(stopImageView);
        simulationMenu.getItems().addAll(play, pause, stop);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(editorMenu, territoriumMenu, elefantMenu, simulationMenu);

        Image newImage = new Image("New24.gif");
        ImageView newImageView = new ImageView(newImage);
        Image loadImage = new Image("Open24.gif");
        ImageView loadImageView = new ImageView(loadImage);
        Image saveImage = new Image("Save24.gif");
        ImageView saveImageView = new ImageView(saveImage);
        Image compileImage = new Image("Compile24.gif");
        ImageView compileImageView = new ImageView(compileImage);
        Image gridImage = new Image("Terrain24.gif");
        ImageView gridImageView = new ImageView(gridImage);
        Image elefantImage = new Image("elefant24.png");
        ImageView elefantImageView = new ImageView(elefantImage);
        Image peanutImage = new Image("erdnuss24.png");
        ImageView peanutImageView = new ImageView(peanutImage);
        Image mouseImage = new Image("maus24.png");
        ImageView mouseImageView = new ImageView(mouseImage);
        Image deleteImage = new Image("Delete24.gif");
        ImageView deleteImageView = new ImageView(deleteImage);
        Image eMitEImage = new Image("Delete24.gif");
        ImageView eMitEImageView = new ImageView(eMitEImage);
        Image turnImage = new Image("Delete24.gif");
        ImageView turnImageView = new ImageView(turnImage);
        Image stepImage = new Image("Delete24.gif");
        ImageView stepImageView = new ImageView(stepImage);
        Image takeImage = new Image("Delete24.gif");
        ImageView takeImageView = new ImageView(takeImage);
        Image dropImage = new Image("Delete24.gif");
        ImageView dropImageView = new ImageView(dropImage);
        Image playImage24 = new Image("Play24.gif");
        ImageView playImageView24 = new ImageView(playImage24);
        Image pauseImage24 = new Image("Pause24.gif");
        ImageView pauseImageView24 = new ImageView(pauseImage24);
        Image stopImage24 = new Image("Stop24.gif");
        ImageView stopImageView24 = new ImageView(stopImage24);


        //ToolBar
        Button newT = new Button();
        newT.setGraphic(newImageView);
        newT.setTooltip(new Tooltip("Neu"));
        Button loadT = new Button();
        loadT.setGraphic(loadImageView);
        loadT.setTooltip(new Tooltip("Laden"));
        Button saveT = new Button();
        saveT.setGraphic(saveImageView);
        saveT.setTooltip(new Tooltip("Speichern"));
        Button compileT = new Button();
        compileT.setGraphic(compileImageView);
        compileT.setTooltip(new Tooltip("Kompilieren"));
        Button gridT = new Button();
        gridT.setGraphic(gridImageView);
        gridT.setTooltip(new Tooltip("Gitter"));
        Button eleT = new Button();
        eleT.setGraphic(elefantImageView);
        eleT.setTooltip(new Tooltip("Elefant"));
        Button peanutT = new Button();
        peanutT.setGraphic(peanutImageView);
        peanutT.setTooltip(new Tooltip("Erdnuss"));
        Button mouseT = new Button();
        mouseT.setGraphic(mouseImageView);
        mouseT.setTooltip(new Tooltip("Maus"));
        Button deleteT = new Button();
        deleteT.setGraphic(deleteImageView);
        deleteT.setTooltip(new Tooltip("Löschen"));
        Button eMitET = new Button();
        eMitET.setGraphic(eMitEImageView);
        eMitET.setTooltip(new Tooltip("???"));
        Button turnT = new Button();
        turnT.setGraphic(turnImageView);
        turnT.setTooltip(new Tooltip("Drehung Links"));
        Button stepT = new Button();
        stepT.setGraphic(stepImageView);
        stepT.setTooltip(new Tooltip("Vor"));
        Button takeT = new Button();
        takeT.setGraphic(takeImageView);
        takeT.setTooltip(new Tooltip("Nimm"));
        Button dropT = new Button();
        dropT.setGraphic(dropImageView);
        dropT.setTooltip(new Tooltip("Fallen lassen"));
        Button playT = new Button();
        playT.setGraphic(playImageView24);
        playT.setTooltip(new Tooltip("Play"));
        Button pauseT = new Button();
        pauseT.setGraphic(pauseImageView24);
        pauseT.setTooltip(new Tooltip("Pause"));
        Button stopT = new Button();
        stopT.setGraphic(stopImageView24);
        stopT.setTooltip(new Tooltip("Stop"));

        ToolBar toolbar = new ToolBar(
                newT, loadT, new Separator(), saveT, compileT, new Separator(), gridT, eleT, peanutT, mouseT, deleteT, new Separator(), eMitET, turnT, stepT, takeT, dropT, new Separator(), playT, pauseT, stopT
        );

        Territorium ter = new Territorium();
        TerritoriumPane terPane = new TerritoriumPane(ter);
        ScrollPane scPane = new ScrollPane(terPane);
        scPane.setFitToHeight(true);
        scPane.setFitToWidth(true);

        SplitPane pane = new SplitPane();
        Label bot = new Label("Welcome");
        pane.getItems().addAll(new TextArea("Code here"), scPane);
        VBox vbox = new VBox();
        VBox.setVgrow(scPane, Priority.ALWAYS);
        vbox.getChildren().addAll(menuBar, toolbar, pane, bot);


//
        primaryStage.setTitle("Nelly the Elephant");
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        test();
    }

    public void test(){
        Territorium t = new Territorium(9, 6);
        Elefant e = t.getActor();
        t.setMouse(3,5);
        t.setP(5,1,4);
        t.setP(1,2,3);
        System.out.println(Arrays.deepToString(t.getGrid()).replace("], ", "]\n"));
        System.out.println();

        try {
            e.step();
            e.step();
            e.step();
            e.turn();
            e.step();
            e.step();
        } catch (Exception exc){
            System.out.println(exc);
        }
        System.out.println();
        System.out.println(Arrays.deepToString(t.getGrid()).replace("], ", "]\n"));

    }
}