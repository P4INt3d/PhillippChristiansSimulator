package de.nachname.controller;

import de.nachname.model.Territorium;
import de.nachname.view.MainStage;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class MainStageController {

    private final Territorium ter;
    private final MainStage stage;
    private final TerritoriumPaneController territoriumPaneController;

    public MainStageController(Territorium ter, MainStage stage, TerritoriumPaneController tpc){
        this.ter = ter;
        this.stage = stage;
        this.territoriumPaneController = tpc;
        stage.getNeu().setOnAction(e -> {
            Territorium territoriumNew = new Territorium(8, 12);
            MainStage stageNeu = new MainStage(territoriumNew);
            stageNeu.show();
        });
        //Menus
        stage.getQuit().setOnAction(e -> Platform.exit());
        stage.getChangeSize().setOnAction(e -> stage.startSizeDialog());
        stage.getPlaceElephant().setOnAction(e -> tpc.placeMode(1));
        stage.getPlacePeanut().setOnAction(e -> tpc.placeMode(2));
        stage.getPlaceMouse().setOnAction(e -> tpc.placeMode(3));
        stage.getDeleteCell().setOnAction(e -> tpc.placeMode(4));
        //Toolbar
        stage.getNewT().setOnAction(this::reset);
        stage.getGridT().setOnAction(e -> stage.startSizeDialog());
        stage.getEleT().setOnAction(e -> tpc.placeMode(1));
        stage.getPeanutT().setOnAction(e -> tpc.placeMode(2));
        stage.getMouseT().setOnAction(e -> tpc.placeMode(3));
        stage.getDeleteT().setOnAction(e -> tpc.placeMode(4));

        stage.show();
    }

    private void reset(ActionEvent actionEvent) {
        ter.reset();
        stage.getMessageLabel().setText("Game has reset");
    }

}
