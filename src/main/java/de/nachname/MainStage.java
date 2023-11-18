package de.nachname;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class MainStage extends Stage {

    private Territorium territorium;
    private TerritoriumPane territoriumPane;
    private Label messageLabel;

    public Label getMessageLabel(){
        return messageLabel;
    }

    public MainStage(Territorium ter){

        this.territorium = ter;

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
        neu.setOnAction(e -> {
            Territorium territoriumNew = new Territorium(8, 12);
            MainStage stage = new MainStage(territoriumNew);
            stage.show();
        });
        MenuItem open = new MenuItem("_Öffnen");
        open.setAccelerator(KeyCombination.valueOf("SHORTCUT+O"));
        open.setGraphic(open16View);
        MenuItem compile = new MenuItem("_Kompilieren");
        compile.setAccelerator(KeyCombination.valueOf("SHORTCUT+K"));
        MenuItem print1 = new MenuItem("_Drucken");
        print1.setGraphic(print16View);
        print1.setAccelerator(KeyCombination.valueOf("SHORTCUT+P"));
        MenuItem quit = new MenuItem("_Beenden");
        quit.setAccelerator(KeyCombination.valueOf("SHORTCUT+Q"));
        quit.setOnAction(e -> Platform.exit());
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
        changeSize.setOnAction(e -> {
            Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
            dialog.setTitle("Change Size");
            ButtonType applyButton = new ButtonType("Apply", ButtonBar.ButtonData.APPLY);
            dialog.getDialogPane().getButtonTypes().addAll(applyButton, ButtonType.CANCEL);

            Spinner<Integer> rowSpinner = new Spinner<>(4, 20, territorium.getRows());
            Spinner<Integer> colSpinner = new Spinner<>(4, 20, territorium.getCols());

            VBox vbox = new VBox();
            vbox.getChildren().addAll(rowSpinner, colSpinner);
            dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if(dialogButton == applyButton){
                    return new Pair<>(rowSpinner.getValue(), colSpinner.getValue());
                }
                return null;
            });

            Optional<Pair<Integer, Integer>> result = dialog.showAndWait();

            result.ifPresent(pair -> {
                territorium.setRows(pair.getKey());
                territorium.setCols(pair.getValue());
                territorium.setGrid(pair.getKey(), pair.getValue());
                territoriumPane.eraseGrid();
                territoriumPane.printCanvas();
            });
        });
        RadioMenuItem placeElephant = new RadioMenuItem("Elefant _platzieren");
        placeElephant.setOnAction(e -> territoriumPane.placeMode(1));
        RadioMenuItem placePeanut = new RadioMenuItem("E_rdnuss platzieren");
        placePeanut.setOnAction(e -> territoriumPane.placeMode(2));
        RadioMenuItem placeMouse = new RadioMenuItem("_Maus platzieren");
        placeMouse.setOnAction(e -> territoriumPane.placeMode(3));
        RadioMenuItem deleteCell = new RadioMenuItem("_Kachel löschen");
        deleteCell.setOnAction(e -> territoriumPane.placeMode(4));
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
        Image eMitEImage = new Image("elefantMitE24.png");
        ImageView eMitEImageView = new ImageView(eMitEImage);
        Image turnImage = new Image("turn24.png");
        ImageView turnImageView = new ImageView(turnImage);
        Image stepImage = new Image("elefantSchritt24.png");
        ImageView stepImageView = new ImageView(stepImage);
        Image takeImage = new Image("ErdnussAuf24.png");
        ImageView takeImageView = new ImageView(takeImage);
        Image dropImage = new Image("ErdnussAb24.png");
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
        newT.setOnAction(this::reset);
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
        gridT.setTooltip(new Tooltip("Größe ändern"));
        gridT.setOnAction(e -> {
            Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
            dialog.setTitle("Change Size");
            ButtonType applyButton = new ButtonType("Apply", ButtonBar.ButtonData.APPLY);
            dialog.getDialogPane().getButtonTypes().addAll(applyButton, ButtonType.CANCEL);

            Spinner<Integer> rowSpinner = new Spinner<>(4, 20, territorium.getRows());
            Spinner<Integer> colSpinner = new Spinner<>(4, 20, territorium.getCols());

            VBox vbox = new VBox();
            vbox.getChildren().addAll(rowSpinner, colSpinner);
            dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if(dialogButton == applyButton){
                    return new Pair<>(rowSpinner.getValue(), colSpinner.getValue());
                }
                return null;
            });

            Optional<Pair<Integer, Integer>> result = dialog.showAndWait();

            result.ifPresent(pair -> {
                territorium.setRows(pair.getKey());
                territorium.setCols(pair.getValue());
                territorium.setGrid(pair.getKey(), pair.getValue());
                territoriumPane.eraseGrid();
                territoriumPane.printCanvas();
            });
        });
        ToggleGroup toolToggle = new ToggleGroup();
        ToggleButton eleT = new ToggleButton();
        eleT.setGraphic(elefantImageView);
        eleT.setTooltip(new Tooltip("Elefant"));
        eleT.setOnAction(e -> territoriumPane.placeMode(1));
        eleT.setToggleGroup(toolToggle);
        eleT.selectedProperty().bindBidirectional(placeElephant.selectedProperty());
        ToggleButton peanutT = new ToggleButton();
        peanutT.setGraphic(peanutImageView);
        peanutT.setTooltip(new Tooltip("Erdnuss"));
        peanutT.setOnAction(e -> territoriumPane.placeMode(2));
        peanutT.setToggleGroup(toolToggle);
        peanutT.selectedProperty().bindBidirectional(placePeanut.selectedProperty());
        ToggleButton mouseT = new ToggleButton();
        mouseT.setGraphic(mouseImageView);
        mouseT.setTooltip(new Tooltip("Maus"));
        mouseT.setOnAction(e -> territoriumPane.placeMode(3));
        mouseT.setToggleGroup(toolToggle);
        mouseT.selectedProperty().bindBidirectional(placeMouse.selectedProperty());
        ToggleButton deleteT = new ToggleButton();
        deleteT.setGraphic(deleteImageView);
        deleteT.setTooltip(new Tooltip("Löschen"));
        deleteT.setOnAction(e -> territoriumPane.placeMode(4));
        deleteT.setToggleGroup(toolToggle);
        deleteT.selectedProperty().bindBidirectional(deleteCell.selectedProperty());
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


        territoriumPane = new TerritoriumPane(ter, this);
        ScrollPane scPane = new ScrollPane(territoriumPane);
        scPane.setFitToHeight(true);
        scPane.setFitToWidth(true);

        SplitPane pane = new SplitPane();
        messageLabel = new Label("Welcome");
        pane.getItems().addAll(new TextArea("Code here"), scPane);
        VBox vbox = new VBox();
        VBox.setVgrow(scPane, Priority.ALWAYS);
        vbox.getChildren().addAll(menuBar, toolbar, pane, messageLabel);

        this.setTitle("Nelly the Elephant");
        Scene scene = new Scene(vbox);
        this.setScene(scene);
    }

    private void reset(ActionEvent ev){
        territorium.reset();
        territoriumPane.printCanvas();
        messageLabel.setText("game has reset");
    }
}
