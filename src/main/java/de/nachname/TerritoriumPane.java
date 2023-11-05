package de.nachname;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;



public class TerritoriumPane extends StackPane {

    private static final int BORDER_SIZE = 20;
    private static final int TILE_SIZE = 25;

    private Territorium territorium;

    public TerritoriumPane(Territorium ter){
        this.territorium = ter;
        Canvas canvas = new Canvas(calcWidth(), calcHeight());
        printCanvas(canvas.getGraphicsContext2D());
        this.getChildren().add(canvas);

    }

    private void printCanvas(GraphicsContext gc){
        gc.setLineWidth(1);
        gc.setStroke(Color.DIMGRAY);

        for (int r = 0; r < territorium.getRows(); r++){
            for (int c = 0; c < territorium.getCols(); c++){
                gc.setFill(Color.LIGHTPINK);
                gc.fillRect(BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE + r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                gc.strokeRect(BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE + r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private int calcWidth(){
        return 2 * BORDER_SIZE + territorium.getCols() * TILE_SIZE;
    }

    private int calcHeight(){
        return 2 * BORDER_SIZE + territorium.getRows() * TILE_SIZE;
    }
}
