package de.nachname;

import de.nachname.controller.MainStageController;
import de.nachname.controller.TerritoriumPaneController;
import de.nachname.model.Territorium;
import de.nachname.view.MainStage;
import de.nachname.view.TerritoriumPane;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // VM Args: --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Territorium territorium = new Territorium();
        MainStage stage = new MainStage(territorium);
        TerritoriumPaneController tpc = new TerritoriumPaneController(territorium, stage.getTerritoriumPane());
        new MainStageController(territorium, stage, tpc);
    }
}