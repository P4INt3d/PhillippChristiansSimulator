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
                System.out.println("Elefant geht nach Süden");
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
                System.out.println("Elefant geht nach Osten");
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
                System.out.println("Elefant geht nach Norden");
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
                System.out.println("Elefant geht nach Westen");
            }
        }
        System.out.println("Elefant:" + TERRITORIUM.getxPosition() + ", " + TERRITORIUM.getyPosition());
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
        if(TERRITORIUM.getvDirection() == 4) {
            newV = 1;
        }
        else {
            newV = TERRITORIUM.getvDirection()+1;
        }
        TERRITORIUM.setvDirection(newV);

        switch(TERRITORIUM.getvDirection()){
            case 1:
                System.out.println("Elefant schaut nach Süden");
                break;
            case 2:
                System.out.println("Elefant schaut nach Osten");
                break;
            case 3:
                System.out.println("Elefant schaut nach Norden");
                break;
            case 4:
                System.out.println("Elefant schaut nach Westen");
                break;
        }
    }
}