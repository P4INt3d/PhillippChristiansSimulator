package de.christians;

import de.christians.controller.MainStageController;
import de.christians.controller.TerritoriumPaneController;
import de.christians.model.Territorium;
import de.christians.view.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    MainStageController mpc;
    MainStage stage;

    // VM Args: --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Territorium territorium = new Territorium();
        stage = new MainStage(territorium);
        TerritoriumPaneController tpc = new TerritoriumPaneController(territorium, stage.getTerritoriumPane());
        mpc = new MainStageController(territorium, stage, tpc, "DefaultElephant");
        try {
            String dirName = "C:/Users/phill/IdeaProjects/PhillippChristiansSimulator/programs";
            Files.createDirectories(Paths.get(dirName));
        } catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void stop(){
        mpc.startSaveDialog();
        stage.close();
    }
}