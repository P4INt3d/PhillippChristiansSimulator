package de.nachname;

public class Elefant {
    
    private final Territorium TERRITORIUM;

    public Elefant(Territorium territorium){
        TERRITORIUM = territorium;
    }

    public void step() throws RuntimeException{
        if(TERRITORIUM.getvDirection() == 1){
            if(TERRITORIUM.getxPosition()+1 == TERRITORIUM.getRows()) {
                throw new RuntimeException("Hier ist der Rand");
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition()+1, TERRITORIUM.getyPosition())){
                throw new RuntimeException("Maus im Weg");
            }
            else {
                TERRITORIUM.setxPosition(TERRITORIUM.getxPosition() + 1);
            }
        }
        if(TERRITORIUM.getvDirection() == 2){
            if(TERRITORIUM.getyPosition()+1 == TERRITORIUM.getCols()) {
                throw new RuntimeException("Hier ist der Rand");
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()+1)){
                throw new RuntimeException("Maus im Weg");
            }
            else {
                TERRITORIUM.setyPosition(TERRITORIUM.getyPosition() + 1);
            }
        }
        if(TERRITORIUM.getvDirection() == 3){
            if(TERRITORIUM.getxPosition()-1 < 0) {
                throw new RuntimeException("Hier ist der Rand");
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition()-1, TERRITORIUM.getyPosition())){
                throw new RuntimeException("Maus im Weg");
            }
            else {
                TERRITORIUM.setxPosition(TERRITORIUM.getxPosition() - 1);
            }
        }
        if(TERRITORIUM.getvDirection() == 4){
            if(TERRITORIUM.getyPosition()-1 < 0) {
                throw new RuntimeException("Hier ist der Rand");
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()-1)){
                throw new RuntimeException("Maus im Weg");
            }
            else {
                TERRITORIUM.setyPosition(TERRITORIUM.getyPosition() - 1);
            }
        }
    }

    public void takeP(){
        if(TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) > 0){
            TERRITORIUM.setP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition(), TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition() - 1));
            TERRITORIUM.setTotalAmount(TERRITORIUM.getTotalAmount() + 1);
        }
        else {
            throw new RuntimeException("Hier liegen keine Erdnüsse");
        }
    }
    public void dropP(){
        if(TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) < 9) {
            if (TERRITORIUM.getTotalAmount() > 0) {
                TERRITORIUM.setP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition(), TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) + 1);
                TERRITORIUM.setTotalAmount(TERRITORIUM.getTotalAmount() - 1);
            }
            else{
                throw new RuntimeException("Du hast keine Erdnüsse");
            }
        }
        else {
            throw new RuntimeException("Hier liegen schon zu viele Erdnüsse");
        }
    }

    public void turn(){
        int newV;
        if(TERRITORIUM.getvDirection() == 1) {
            newV = 4;
        }
        else {
            newV = TERRITORIUM.getvDirection()-1;
        }
        TERRITORIUM.setvDirection(newV);
    }
}
