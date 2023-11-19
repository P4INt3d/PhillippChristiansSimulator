package de.nachname.view;

import de.nachname.model.Territorium;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class MainStage extends Stage {

    private final Territorium territorium;
    private final TerritoriumPane territoriumPane;
    private final ScrollPane scPane;
    private final Label messageLabel;
    private final Menu editorMenu,territoriumMenu, save, elefantMenu, simulationMenu;
    private final MenuItem neu, open, compile, print1, quit, xml, jaxb, serial, print2,
            saveAP, load, changeSize, amountP, leftTurn, step, take, give, play, pause, stop;
    private final RadioMenuItem placeElephant, placePeanut, placeMouse, deleteCell;
    private final MenuBar menuBar;
    private final Button newT, loadT, saveT, compileT, gridT, eMitET, turnT, stepT, takeT,
            dropT, playT, pauseT, stopT;

    private final ToggleButton eleT, peanutT, mouseT, deleteT;


    public MainStage(Territorium ter){

        this.territorium = ter;
        territoriumPane = new TerritoriumPane(ter, this);

        // Editor Menü
        Image neu16 = new Image("New16.gif");
        ImageView neu16View = new ImageView(neu16);
        Image open16 = new Image("Open16.gif");
        ImageView open16View = new ImageView(open16);
        Image print16 = new Image("Print16.gif");
        ImageView print16View = new ImageView(print16);
        editorMenu = new Menu("_Editor");
        neu = new MenuItem("_Neu");
        neu.setAccelerator(KeyCombination.valueOf("SHORTCUT+N"));
        neu.setGraphic(neu16View);
        open = new MenuItem("_Öffnen");
        open.setAccelerator(KeyCombination.valueOf("SHORTCUT+O"));
        open.setGraphic(open16View);
        compile = new MenuItem("_Kompilieren");
        compile.setAccelerator(KeyCombination.valueOf("SHORTCUT+K"));
        print1 = new MenuItem("_Drucken");
        print1.setGraphic(print16View);
        print1.setAccelerator(KeyCombination.valueOf("SHORTCUT+P"));
        quit = new MenuItem("_Beenden");
        quit.setAccelerator(KeyCombination.valueOf("SHORTCUT+Q"));
        editorMenu.getItems().addAll(neu, open, new SeparatorMenuItem(), compile, print1, new SeparatorMenuItem(), quit);


        //Territorium Menü
        territoriumMenu = new Menu("_Territorium");
        save = new Menu("S_peichern");
        xml = new MenuItem("XML");
        jaxb = new MenuItem("JAXB");
        serial = new MenuItem("Serialisieren");
        load = new Menu("La_den");
        saveAP = new Menu("_Als Bild speichern");
        print2 = new MenuItem("_Drucken");
        changeSize = new MenuItem("_Größe ändern...");
        placeElephant = new RadioMenuItem("Elefant _platzieren");
        placePeanut = new RadioMenuItem("E_rdnuss platzieren");
        placeMouse = new RadioMenuItem("_Maus platzieren");
        deleteCell = new RadioMenuItem("_Kachel löschen");
        save.getItems().addAll(xml, jaxb, serial);
        territoriumMenu.getItems().addAll(save, load, saveAP, print2, changeSize, new SeparatorMenuItem(), placeElephant, placePeanut, placeMouse, deleteCell);


        //Elefant Menü
        elefantMenu = new Menu("E_lefant");
        amountP = new MenuItem("Gesammelte Erdnüsse");
        leftTurn = new MenuItem("linksUm");
        leftTurn.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+L"));
        step = new MenuItem("vor");
        step.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+V"));
        take = new MenuItem("nimm");
        take.setAccelerator(KeyCombination.valueOf("SHORTCUT+SHIFT+N"));
        give = new MenuItem("gib");
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
        simulationMenu = new Menu("_Simulation");
        Image pauseImage = new Image("Pause16.gif");
        ImageView pauseImageView = new ImageView(pauseImage);
        Image stopImage = new Image("Stop16.gif");
        ImageView stopImageView = new ImageView(stopImage);
        play = new MenuItem("Start/Fortsetzen");
        play.setAccelerator(KeyCombination.valueOf("SHORTCUT+F11"));
        play.setGraphic(playImageView);
        pause = new MenuItem("Pause");
        pause.setGraphic(pauseImageView);
        stop = new MenuItem("Stopp");
        stop.setAccelerator(KeyCombination.valueOf("SHORTCUT+F12"));
        stop.setGraphic(stopImageView);
        simulationMenu.getItems().addAll(play, pause, stop);


        menuBar = new MenuBar();
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
        newT = new Button();
        newT.setGraphic(newImageView);
        newT.setTooltip(new Tooltip("Neu"));
        loadT = new Button();
        loadT.setGraphic(loadImageView);
        loadT.setTooltip(new Tooltip("Laden"));
        saveT = new Button();
        saveT.setGraphic(saveImageView);
        saveT.setTooltip(new Tooltip("Speichern"));
        compileT = new Button();
        compileT.setGraphic(compileImageView);
        compileT.setTooltip(new Tooltip("Kompilieren"));
        gridT = new Button();
        gridT.setGraphic(gridImageView);
        gridT.setTooltip(new Tooltip("Größe ändern"));
        ToggleGroup toolToggle = new ToggleGroup();
        eleT = new ToggleButton();
        eleT.setGraphic(elefantImageView);
        eleT.setTooltip(new Tooltip("Elefant"));
        eleT.setToggleGroup(toolToggle);
        eleT.selectedProperty().bindBidirectional(placeElephant.selectedProperty());
        peanutT = new ToggleButton();
        peanutT.setGraphic(peanutImageView);
        peanutT.setTooltip(new Tooltip("Erdnuss"));
        peanutT.setToggleGroup(toolToggle);
        peanutT.selectedProperty().bindBidirectional(placePeanut.selectedProperty());
        mouseT = new ToggleButton();
        mouseT.setGraphic(mouseImageView);
        mouseT.setTooltip(new Tooltip("Maus"));
        mouseT.setToggleGroup(toolToggle);
        mouseT.selectedProperty().bindBidirectional(placeMouse.selectedProperty());
        deleteT = new ToggleButton();
        deleteT.setGraphic(deleteImageView);
        deleteT.setTooltip(new Tooltip("Löschen"));
        deleteT.setToggleGroup(toolToggle);
        deleteT.selectedProperty().bindBidirectional(deleteCell.selectedProperty());
        eMitET = new Button();
        eMitET.setGraphic(eMitEImageView);
        eMitET.setTooltip(new Tooltip("???"));
        turnT = new Button();
        turnT.setGraphic(turnImageView);
        turnT.setTooltip(new Tooltip("Drehung Links"));
        stepT = new Button();
        stepT.setGraphic(stepImageView);
        stepT.setTooltip(new Tooltip("Vor"));
        takeT = new Button();
        takeT.setGraphic(takeImageView);
        takeT.setTooltip(new Tooltip("Nimm"));
        dropT = new Button();
        dropT.setGraphic(dropImageView);
        dropT.setTooltip(new Tooltip("Fallen lassen"));
        playT = new Button();
        playT.setGraphic(playImageView24);
        playT.setTooltip(new Tooltip("Play"));
        pauseT = new Button();
        pauseT.setGraphic(pauseImageView24);
        pauseT.setTooltip(new Tooltip("Pause"));
        stopT = new Button();
        stopT.setGraphic(stopImageView24);
        stopT.setTooltip(new Tooltip("Stop"));

        ToolBar toolbar = new ToolBar(
                newT, loadT, new Separator(), saveT, compileT, new Separator(), gridT, eleT, peanutT, mouseT, deleteT, new Separator(), eMitET, turnT, stepT, takeT, dropT, new Separator(), playT, pauseT, stopT
        );



        scPane = new ScrollPane(territoriumPane);
        scPane.setFitToHeight(true);
        scPane.setFitToWidth(true);

        SplitPane pane = new SplitPane();
        //pane.setDividerPositions(0.35);
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

    public Territorium getTerritorium() {
        return territorium;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public MenuItem getNeu() {
        return neu;
    }

    public MenuItem getOpen() {
        return open;
    }

    public MenuItem getCompile() {
        return compile;
    }

    public MenuItem getPrint1() {
        return print1;
    }

    public MenuItem getQuit() {
        return quit;
    }

    public MenuItem getXml() {
        return xml;
    }

    public MenuItem getJaxb() {
        return jaxb;
    }

    public MenuItem getSerial() {
        return serial;
    }

    public MenuItem getPrint2() {
        return print2;
    }

    public MenuItem getSaveAP() {
        return saveAP;
    }

    public MenuItem getLoad() {
        return load;
    }

    public MenuItem getChangeSize() {
        return changeSize;
    }

    public MenuItem getAmountP() {
        return amountP;
    }

    public MenuItem getLeftTurn() {
        return leftTurn;
    }

    public MenuItem getStep() {
        return step;
    }

    public MenuItem getTake() {
        return take;
    }

    public MenuItem getGive() {
        return give;
    }

    public MenuItem getPlay() {
        return play;
    }

    public MenuItem getPause() {
        return pause;
    }

    public MenuItem getStop() {
        return stop;
    }

    public RadioMenuItem getPlaceElephant() {
        return placeElephant;
    }

    public RadioMenuItem getPlacePeanut() {
        return placePeanut;
    }

    public RadioMenuItem getPlaceMouse() {
        return placeMouse;
    }

    public RadioMenuItem getDeleteCell() {
        return deleteCell;
    }

    public Button getNewT() {
        return newT;
    }

    public Button getLoadT() {
        return loadT;
    }

    public Button getSaveT() {
        return saveT;
    }

    public Button getCompileT() {
        return compileT;
    }

    public Button getGridT() {
        return gridT;
    }

    public Button geteMitET() {
        return eMitET;
    }

    public Button getTurnT() {
        return turnT;
    }

    public Button getStepT() {
        return stepT;
    }

    public Button getTakeT() {
        return takeT;
    }

    public Button getDropT() {
        return dropT;
    }

    public Button getPlayT() {
        return playT;
    }

    public Button getPauseT() {
        return pauseT;
    }

    public Button getStopT() {
        return stopT;
    }

    public ToggleButton getEleT() {
        return eleT;
    }

    public ToggleButton getPeanutT() {
        return peanutT;
    }

    public ToggleButton getMouseT() {
        return mouseT;
    }

    public ToggleButton getDeleteT() {
        return deleteT;
    }

    public TerritoriumPane getTerritoriumPane() {
        return territoriumPane;
    }

    public void startSizeDialog(){
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


    }
}
