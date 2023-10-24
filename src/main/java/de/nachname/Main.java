package de.nachname;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Menu load = new Menu("L_aden");
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

        //ToolBar
        Button newT = new Button();
        Button loadT = new Button();
        Button saveT = new Button();
        Button cleanT = new Button();
        Button gridT = new Button();
        Button eleT = new Button();
        Button peanutT = new Button();
        Button mouseT = new Button();
        Button abortT = new Button();
        Button takeT = new Button();
        Button turnT = new Button();
        Button stepT = new Button();
        Button playT = new Button();
        Button pauseT = new Button();
        Button stopT = new Button();

        ToolBar toolbar = new ToolBar(
                newT, loadT, new Separator(), saveT, cleanT, new Separator(), gridT, eleT, peanutT, mouseT, abortT, new Separator(), takeT, turnT, stepT, new Separator(), playT, pauseT, stopT
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