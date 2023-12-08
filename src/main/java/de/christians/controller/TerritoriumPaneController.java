package de.christians.controller;

import de.christians.util.Pair;
import de.christians.model.Territorium;
import de.christians.view.TerritoriumPane;
import javafx.scene.input.MouseEvent;

public class TerritoriumPaneController {

    private final Territorium territorium;
    private final TerritoriumPane pane;



    public TerritoriumPaneController(Territorium ter, TerritoriumPane pane){
        this.territorium = ter;
        this.pane = pane;


    }

    public void placeMode(int i){
        pane.getCanvas().setOnMouseClicked(null);

        switch(i){
            case 1:
                pane.setOnMouseClicked(this::placeElephant);
                break;
            case 2:
                pane.setOnMouseClicked(this::placePeanut);
                break;
            case 3:
                pane.setOnMouseClicked(this::placeMouse);
                break;
            default:
                pane.setOnMouseClicked(this::clear);
                break;
        }
    }

    public void placeElephant(MouseEvent ev){

        Pair<Integer> pair = checkCursor(ev);

        if(pair != null){

            if(territorium.hasMouse(pair.value1(), pair.value2())){
                return;
            }

            territorium.setxPosition(pair.value2());
            territorium.setyPosition(pair.value1());
        }


        pane.update();
    }

    public void placePeanut(MouseEvent ev){

        Pair<Integer> pair = checkCursor(ev);

        if(pair != null && territorium.getP(pair.value1(), pair.value2())<=8){
                territorium.setP(pair.value1(),pair.value2(), territorium.getP(pair.value1(), pair.value2())+1);
        }

        pane.update();
    }

    public void placeMouse(MouseEvent ev){

        Pair<Integer> pair = checkCursor(ev);
        if(pair != null){
            territorium.setMouse(pair.value1(), pair.value1());
        }


        pane.update();
    }

    public void clear(MouseEvent ev){

        Pair<Integer> pair = checkCursor(ev);
        if (pair!=null){
            territorium.clear(pair.value1(), pair.value2());
        }

        pane.update();
    }

    private Pair<Integer> checkCursor(MouseEvent ev){
        double x = ev.getX();
        double y = ev.getY();
        if (y < TerritoriumPane.BORDER_SIZE || x < TerritoriumPane.BORDER_SIZE || y > TerritoriumPane.BORDER_SIZE + territorium.getRows() * TerritoriumPane.TILE_SIZE || x > TerritoriumPane.BORDER_SIZE + territorium.getCols() * TerritoriumPane.TILE_SIZE) {
            return null;
        }

        int row = (int)((y - TerritoriumPane.BORDER_SIZE)/ TerritoriumPane.TILE_SIZE );
        int col = (int)((x - TerritoriumPane.BORDER_SIZE) / TerritoriumPane.TILE_SIZE);
        return new Pair<>(row, col);

    }
}
