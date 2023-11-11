package de.nachname;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;

import javax.swing.*;


public class TerritoriumPane extends StackPane {

    private static final int BORDER_SIZE = 20;
    private static final int TILE_SIZE = 30;
    private int padding;
    private Territorium territorium;
    private Image maus;
    private Image elefant;

    public TerritoriumPane(Territorium ter){
        this.territorium = ter;
        maus = new Image("maus24.png");
        elefant = new Image("elefant24.png");
        padding = (TILE_SIZE - 24) / 2;
        Canvas canvas = new Canvas(calcWidth(), calcHeight());
        printCanvas(canvas.getGraphicsContext2D());
        this.getChildren().add(canvas);


    }

    private void printCanvas(GraphicsContext gc){
        gc.setLineWidth(2);
        gc.setStroke(Color.DIMGRAY);
        Transform rot;


       for (int r = 0; r < territorium.getRows(); r++){
            for (int c = 0; c < territorium.getCols(); c++){
                gc.setFill(Color.MISTYROSE);
                gc.fillRect(BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE + r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if(territorium.hasMouse(r, c)){
                    gc.drawImage(maus, BORDER_SIZE + padding+ c * TILE_SIZE, BORDER_SIZE + padding + r * TILE_SIZE);
                }
                if(territorium.getxPosition()==c && territorium.getyPosition()==r){
                    gc.save();
                    switch (territorium.getvDirection()){
                        case 1:
                            rot = new Rotate(90, BORDER_SIZE + (TILE_SIZE/2) + c * TILE_SIZE, BORDER_SIZE + (TILE_SIZE/2)  + r * TILE_SIZE);
                            gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());
                            break;
                        case 2:
                            break;
                        case 3:
                            rot = new Rotate(-90, BORDER_SIZE + (TILE_SIZE/2) + c * TILE_SIZE, BORDER_SIZE + (TILE_SIZE/2)  + r * TILE_SIZE);
                            gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());
                            break;
                        case 4:
                            rot = new Rotate(180, BORDER_SIZE + (TILE_SIZE/2) + c * TILE_SIZE, BORDER_SIZE + (TILE_SIZE/2)  + r * TILE_SIZE);
                            gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());
                            break;
                    }
                    gc.drawImage(elefant,BORDER_SIZE + padding + c * TILE_SIZE, BORDER_SIZE + padding + r * TILE_SIZE);
                    gc.restore();

                }
            }
        }
        for(int r = 0; r <= territorium.getRows(); r++){
            gc.strokeLine(BORDER_SIZE, BORDER_SIZE + r * TILE_SIZE, BORDER_SIZE + territorium.getCols() * TILE_SIZE, BORDER_SIZE + r * TILE_SIZE);
        }
        for(int c = 0; c <= territorium.getCols(); c++){
            gc.strokeLine(BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE, BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE + territorium.getRows() * TILE_SIZE);
        }

    }

    private int calcWidth(){
        return 2 * BORDER_SIZE + territorium.getCols() * TILE_SIZE;
    }

    private int calcHeight(){
        return 2 * BORDER_SIZE + territorium.getRows() * TILE_SIZE;
    }
}
