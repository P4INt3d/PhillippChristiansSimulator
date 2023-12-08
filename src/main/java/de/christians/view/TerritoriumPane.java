package de.christians.view;

import de.christians.model.Territorium;
import de.christians.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;


public class TerritoriumPane extends StackPane implements Observer {

    public static final int BORDER_SIZE = 20;
    public static final int TILE_SIZE = 35;
    private final int padding;
    private final Territorium territorium;
    private final Image maus;
    private final Image elefant;
    private Canvas canvas;
    private GraphicsContext gc;

    public TerritoriumPane(Territorium ter){
        this.territorium = ter;
        maus = new Image("maus24.png");
        elefant = new Image("elefant24.png");
        padding = (TILE_SIZE - 24) / 2;
        printCanvas();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void eraseGrid(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    public void printCanvas(){
        canvas = new Canvas(calcWidth(), calcHeight());
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);
        gc.setStroke(Color.DIMGRAY);
        Transform rot;

       for (int r = 0; r < territorium.getRows(); r++){
            for (int c = 0; c < territorium.getCols(); c++){
                gc.setFill(Color.MISTYROSE);
                gc.fillRect((double)BORDER_SIZE + c * TILE_SIZE, (double)BORDER_SIZE + r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if(territorium.hasMouse(r, c)){
                    gc.drawImage(maus, (double)BORDER_SIZE + padding + c * TILE_SIZE, (double)BORDER_SIZE + padding + r * TILE_SIZE);
                }
                if(territorium.getP(r, c) > 0){
                    gc.save();
                    gc.setLineWidth(0.5);
                    gc.setStroke(Color.BLACK);
                    int content = territorium.getP(r, c);
                    gc.strokeText(String.valueOf(content), BORDER_SIZE + ((double)TILE_SIZE/2) + padding + c * TILE_SIZE, BORDER_SIZE + ((double)TILE_SIZE/2) + padding + r * TILE_SIZE);
                    gc.restore();
                }
                if(territorium.getxPosition()==c && territorium.getyPosition()==r){
                    gc.save();
                    rot = rotateElephant(r, c);
                    gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());
                    gc.drawImage(elefant,(double)BORDER_SIZE + padding + c * TILE_SIZE, (double)BORDER_SIZE + padding + r * TILE_SIZE);
                    gc.restore();
                }
            }
        }
        for(int r = 0; r <= territorium.getRows(); r++){
            gc.strokeLine(BORDER_SIZE, (double)BORDER_SIZE + r * TILE_SIZE, (double)BORDER_SIZE + territorium.getCols() * TILE_SIZE, (double)BORDER_SIZE + r * TILE_SIZE);
        }
        for(int c = 0; c <= territorium.getCols(); c++){
            gc.strokeLine((double) BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE, (double)BORDER_SIZE + c * TILE_SIZE, (double)BORDER_SIZE + territorium.getRows() * TILE_SIZE);
        }
        this.getChildren().add(canvas);
    }

    private Transform rotateElephant(int r, int c){

        return switch (territorium.getvDirection()) {
            case 1 ->
                    new Rotate(90, BORDER_SIZE + ((double) TILE_SIZE / 2) + c * TILE_SIZE, BORDER_SIZE + ((double) TILE_SIZE / 2) + r * TILE_SIZE);
            case 3 ->
                    new Rotate(-90, BORDER_SIZE + ((double) TILE_SIZE / 2) + c * TILE_SIZE, BORDER_SIZE + ((double) TILE_SIZE / 2) + r * TILE_SIZE);
            case 4 ->
                    new Rotate(180, BORDER_SIZE + ((double) TILE_SIZE / 2) + c * TILE_SIZE, BORDER_SIZE + ((double) TILE_SIZE / 2) + r * TILE_SIZE);
            default ->
                    new Rotate(0, BORDER_SIZE + ((double) TILE_SIZE / 2) + c * TILE_SIZE, BORDER_SIZE + ((double) TILE_SIZE / 2) + r * TILE_SIZE);
        };
    }

    private int calcWidth(){
        return 2 * BORDER_SIZE + territorium.getCols() * TILE_SIZE;
    }

    private int calcHeight(){
        return 2 * BORDER_SIZE + territorium.getRows() * TILE_SIZE;
    }

    @Override
    public void update() {
        printCanvas();
    }
}
