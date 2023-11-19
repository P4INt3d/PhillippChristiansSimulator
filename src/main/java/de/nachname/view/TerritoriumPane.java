package de.nachname.view;

import de.nachname.model.Territorium;
import de.nachname.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;


public class TerritoriumPane extends StackPane implements Observer {

    private static final int BORDER_SIZE = 20;
    private static final int TILE_SIZE = 35;
    private int padding;
    private Territorium territorium;
    private MainStage stage;
    private Image maus;
    private Image elefant;
    private Canvas canvas;
    private GraphicsContext gc;

    public TerritoriumPane(Territorium ter, MainStage stage){
        this.territorium = ter;
        this.stage = stage;
        maus = new Image("maus24.png");
        elefant = new Image("elefant24.png");
        padding = (TILE_SIZE - 24) / 2;
        //canvas = new Canvas(calcWidth(), calcHeight());
        //gc = canvas.getGraphicsContext2D();
        printCanvas();
        //this.getChildren().add(canvas);


    }

    public Canvas getCanvas() {
        return canvas;
    }



    public void placeElephant(MouseEvent ev){

        System.out.println("in elephant placing mode");
        double x = ev.getX();
        double y = ev.getY();

        if (y < BORDER_SIZE || x < BORDER_SIZE || y > BORDER_SIZE + territorium.getRows() * TILE_SIZE || x > BORDER_SIZE + territorium.getCols() * TILE_SIZE) {
            return;
        }

        int row = (int)((y - BORDER_SIZE)/ TILE_SIZE );
        int col = (int)((x - BORDER_SIZE) / TILE_SIZE);

        if(territorium.hasMouse(row, col)){
            return;
        }

        territorium.setxPosition(col);
        territorium.setyPosition(row);

        printCanvas();
    }

    public void placePeanut(MouseEvent ev){

        System.out.println("in peanut placing mode");

        double x = ev.getX();
        double y = ev.getY();
        if (y < BORDER_SIZE || x < BORDER_SIZE || y > BORDER_SIZE + territorium.getRows() * TILE_SIZE || x > BORDER_SIZE + territorium.getCols() * TILE_SIZE) {
            return;
        }

        int row = (int)((y - BORDER_SIZE)/ TILE_SIZE );
        int col = (int)((x - BORDER_SIZE) / TILE_SIZE);

        if(territorium.getP(row, col)<=8){
            territorium.setP(row,col, territorium.getP(row, col)+1);
        }

        printCanvas();
    }

    public void placeMouse(MouseEvent ev){

        System.out.println("in Mouse placing mode");
        double x = ev.getX();
        double y = ev.getY();
        if (y < BORDER_SIZE || x < BORDER_SIZE || y > BORDER_SIZE + territorium.getRows() * TILE_SIZE || x > BORDER_SIZE + territorium.getCols() * TILE_SIZE) {
            return;
        }

        int row = (int)((y - BORDER_SIZE)/ TILE_SIZE );
        int col = (int)((x - BORDER_SIZE) / TILE_SIZE);

        territorium.setMouse(row, col);

        printCanvas();
    }


    public void clear(MouseEvent ev){
        System.out.println("clearmode");
        double x = ev.getX();
        double y = ev.getY();
        if (y < BORDER_SIZE || x < BORDER_SIZE || y > BORDER_SIZE + territorium.getRows() * TILE_SIZE || x > BORDER_SIZE + territorium.getCols() * TILE_SIZE) {
            return;
        }

        int row = (int)((y - BORDER_SIZE)/ TILE_SIZE );
        int col = (int)((x -BORDER_SIZE) / TILE_SIZE);

        territorium.clear(row, col);

        printCanvas();
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
                gc.fillRect(BORDER_SIZE + c * TILE_SIZE, BORDER_SIZE + r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if(territorium.hasMouse(r, c)){
                    gc.drawImage(maus, BORDER_SIZE + padding+ c * TILE_SIZE, BORDER_SIZE + padding + r * TILE_SIZE);
                }
                if(territorium.getP(r, c) > 0){
                    gc.save();
                    gc.setLineWidth(0.5);
                    gc.setStroke(Color.BLACK);
                    int content = territorium.getP(r, c);
                    gc.strokeText(String.valueOf(content), BORDER_SIZE + (TILE_SIZE/2) + padding+ c * TILE_SIZE, BORDER_SIZE + (TILE_SIZE/2) + padding + r * TILE_SIZE);
                    gc.restore();
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
        this.getChildren().add(canvas);
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
