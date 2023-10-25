package de.nachname;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // VM Args: --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Editor Menü
        Menu editorMenu = new Menu("_Editor");
        MenuItem neu = new MenuItem("_Neu");
        MenuItem open = new MenuItem("_Öffnen");
        MenuItem compile = new MenuItem("_Kompilieren");
        MenuItem print1 = new MenuItem("_Drucken");
        MenuItem quit = new MenuItem("_Beenden");
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
        MenuItem step = new MenuItem("vor");
        MenuItem take = new MenuItem("nimm");
        MenuItem give = new MenuItem("gib");
        ToggleGroup terriToggle = new ToggleGroup();
        placeElephant.setToggleGroup(terriToggle);
        placePeanut.setToggleGroup(terriToggle);
        placeMouse.setToggleGroup(terriToggle);
        deleteCell.setToggleGroup(terriToggle);
        elefantMenu.getItems().addAll(amountP, leftTurn, step, take, give);

        //Simualation Menü
        Menu simulationMenu = new Menu("_Simulation");
        MenuItem play = new MenuItem("Start/Fortsetzen");
        MenuItem pause = new MenuItem("Pause");
        MenuItem stop = new MenuItem("Stopp");
        simulationMenu.getItems().addAll(play, pause, stop);


        //MenüBar
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
        //views in order pls
        Image deleteImage = new Image("Delete24.gif");
        ImageView deleteImageView = new ImageView(deleteImage);
        //
        Image playImage = new Image("Play24.gif");
        ImageView playImageView = new ImageView(playImage);
        Image pauseImage = new Image("Pause24.gif");
        ImageView pauseImageView = new ImageView(pauseImage);
        Image stopImage = new Image("Stop24.gif");
        ImageView stopImageView = new ImageView(stopImage);


        //ToolBar
        Button newT = new Button();
        newT.setGraphic(newImageView);
        Button loadT = new Button();
        loadT.setGraphic(loadImageView);
        Button saveT = new Button();
        saveT.setGraphic(saveImageView);
        Button compileT = new Button();
        compileT.setGraphic(compileImageView);
        Button gridT = new Button();
        gridT.setGraphic(gridImageView);
        Button eleT = new Button();
        Button peanutT = new Button();
        Button mouseT = new Button();
        Button deleteT = new Button();
        deleteT.setGraphic(deleteImageView);

        Button turnT = new Button();
        Button stepT = new Button();
        Button takeT = new Button();
        Button playT = new Button();
        playT.setGraphic(playImageView);
        Button pauseT = new Button();
        pauseT.setGraphic(pauseImageView);
        Button stopT = new Button();
        stopT.setGraphic(stopImageView);

        ToolBar toolbar = new ToolBar(
                newT, loadT, new Separator(), saveT, compileT, new Separator(), gridT, eleT, peanutT, mouseT, deleteT, new Separator(), takeT, turnT, stepT, new Separator(), playT, pauseT, stopT
        );


        SplitPane pane = new SplitPane();
        Label bot = new Label("Default");
        pane.getItems().add(new TextArea("Code here"));
        pane.getItems().add(new Pane());
        VBox vbox = new VBox(menuBar, toolbar, pane, bot);



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(new StackPane(vbox)));
        primaryStage.show();
    }
}