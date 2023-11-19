package de.nachname.controller;

import de.nachname.model.Territorium;
import de.nachname.view.TerritoriumPane;

public class TerritoriumPaneController {

    private final Territorium ter;
    private final TerritoriumPane pane;



    public TerritoriumPaneController(Territorium ter, TerritoriumPane pane){
        this.ter = ter;
        this.pane = pane;


    }

    public void placeMode(int i){
        pane.getCanvas().setOnMouseClicked(null);

        switch(i){
            case 1:
                pane.setOnMouseClicked(pane::placeElephant);
                break;
            case 2:
                pane.setOnMouseClicked(pane::placePeanut);
                break;
            case 3:
                pane.setOnMouseClicked(pane::placeMouse);
                break;
            case 4:
                pane.setOnMouseClicked(pane::clear);
                break;
        }
    }
}
