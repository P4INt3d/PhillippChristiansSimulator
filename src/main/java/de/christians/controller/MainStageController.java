package de.christians.controller;

import de.christians.model.Territorium;
import de.christians.view.MainStage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Optional;

public class MainStageController {

    private final Territorium ter;
    private final MainStage stage;
    private final FileChooser fileChooser = new FileChooser();
    private static final String SUFFIX = ".java";
    private static final String PATH = "../PhillippChristiansSimulator/programs/";


    public MainStageController(Territorium ter, MainStage stage, TerritoriumPaneController tpc, String title){
        this.ter = ter;
        this.stage = stage;
        stage.setTitle(title);

        //Menus
        stage.getNeu().setOnAction(e -> startWindowDialog());
        stage.getOpen().setOnAction(e -> startOpenDialog());
        stage.getCompile().setOnAction(e -> compile());
        stage.getQuit().setOnAction(e -> {
            startSaveDialog();
            stage.close();
        });
        stage.getChangeSize().setOnAction(e -> startSizeDialog());
        stage.getPlaceElephant().setOnAction(e -> tpc.placeMode(1));
        stage.getPlacePeanut().setOnAction(e -> tpc.placeMode(2));
        stage.getPlaceMouse().setOnAction(e -> tpc.placeMode(3));
        stage.getDeleteCell().setOnAction(e -> tpc.placeMode(4));

        //Toolbar
        stage.getNewT().setOnAction(e -> startWindowDialog());
        stage.getOpenT().setOnAction(e -> startOpenDialog());
        stage.getSaveT().setOnAction(e -> startSaveDialog());
        stage.getCompileT().setOnAction(e -> compile());
        stage.getGridT().setOnAction(e -> startSizeDialog());
        stage.getEleT().setOnAction(e -> tpc.placeMode(1));
        stage.getPeanutT().setOnAction(e -> tpc.placeMode(2));
        stage.getMouseT().setOnAction(e -> tpc.placeMode(3));
        stage.getDeleteT().setOnAction(e -> tpc.placeMode(4));

        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(PATH));
        File f = new File(PATH + stage.getTitle() + SUFFIX);
        if(f.exists() && !f.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                String firstLine = br.readLine();
                stage.getTextArea().appendText(firstLine.substring(39+stage.getTitle().length()));
                while ((line = br.readLine()) != null) {
                    stage.getTextArea().appendText("\n");
                    stage.getTextArea().appendText(line);
                }
                stage.getTextArea().setText(stage.getTextArea().getText().substring(0,stage.getTextArea().getText().length()-1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            stage.getTextArea().appendText("void main() { \n\n}");
        }
        stage.show();
    }

    public void startSizeDialog(){
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Change Size");
        ButtonType applyButton = new ButtonType("Apply", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButton, ButtonType.CANCEL);

        Spinner<Integer> rowSpinner = new Spinner<>(4, 20, ter.getRows());
        Spinner<Integer> colSpinner = new Spinner<>(4, 20, ter.getCols());

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
            ter.setRows(pair.getKey());
            ter.setCols(pair.getValue());
            ter.setGrid(pair.getKey(), pair.getValue());
            stage.getTerritoriumPane().eraseGrid();
            stage.getTerritoriumPane().printCanvas();
        });
    }

    public void startWindowDialog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create new window");
        dialog.setHeaderText("Name of your new program");

        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()){
            if(!result.get().matches("[A-Z]+[a-zA-Z0-9]*")){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Please use camelcase, starting with a capital letter");
                a.showAndWait();
            } else {
                result.ifPresent(name -> {
                    Territorium territoriumNew = new Territorium(8, 12);
                    MainStage stageNew = new MainStage(territoriumNew);
                    TerritoriumPaneController tpcNew = new TerritoriumPaneController(territoriumNew, stageNew.getTerritoriumPane());
                    new MainStageController(territoriumNew, stageNew, tpcNew, name);
                    stageNew.show();
                });
            }
        }
    }

    public void startOpenDialog(){

        File result = fileChooser.showOpenDialog(this.stage);

        if(result != null){
            Territorium territoriumNew = new Territorium(8, 12);
            MainStage stageNew = new MainStage(territoriumNew);
            TerritoriumPaneController tpcNew = new TerritoriumPaneController(territoriumNew, stageNew.getTerritoriumPane());
            new MainStageController(territoriumNew, stageNew, tpcNew, result.getName().substring(0, result.getName().length()-5));
            stageNew.show();
        }
    }

    public void startSaveDialog(){

        Dialog<File> a = new Dialog<>();
        File result = new File(PATH + stage.getTitle() + SUFFIX);

        try {
            boolean b = result.createNewFile();
            if(!b){
                a.setHeaderText("Overwrite existing " + stage.getTitle() +  " file?");
                a.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);

                a.setResultConverter(dialogButton -> {
                    if(dialogButton == ButtonType.YES){
                       result.delete();
                       return new File(PATH + stage.getTitle() + SUFFIX);
                    }
                    return null;
                });
                Optional<File> saveFile = a.showAndWait();
                fileWriter(saveFile.get(), stage.getTextArea());
            } else {
                fileWriter(result, stage.getTextArea());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriter(File savePath, TextArea textArea) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(savePath));){
            bf.write("public class " + stage.getTitle() + " extends Elefant { public " + textArea.getText() + "}");
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compile(){
        startSaveDialog();
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        boolean success = javac.run(null, null, err, PATH + stage.getTitle() + SUFFIX) == 0;
        if(!success){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(err.toString());
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("File did compile");
            a.show();

        }
    }
}
