package de.nachname;

import de.nachname.exceptions.MouseException;
import de.nachname.exceptions.NoPeanutException;
import de.nachname.exceptions.RandException;
import de.nachname.exceptions.TileException;

public class Elefant {
    
    private final Territorium TERRITORIUM;

    public Elefant(Territorium territorium){
        TERRITORIUM = territorium;
    }

    public boolean freeTile(){
        switch (TERRITORIUM.getvDirection()){
            case 1:
                return !(TERRITORIUM.getxPosition()+1 == TERRITORIUM.getRows());
            case 2:
                return !(TERRITORIUM.getyPosition()+1 == TERRITORIUM.getCols());
            case 3:
                return !(TERRITORIUM.getxPosition()-1 < 0);
            case 4:
                return !(TERRITORIUM.getyPosition()-1 < 0);
            default:
                return false;

        }
    }

    public boolean peanutFound(){
        return (TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition())>0);
    }

    public boolean hasNoPeanut(){
        return (TERRITORIUM.getTotalAmount()==0);
    }

    public int getDirection(){
        return TERRITORIUM.getvDirection();
    }

    public int getTotalAMount(){
        return TERRITORIUM.getTotalAmount();
    }

    public int getXPosition(){
        return TERRITORIUM.getxPosition();
    }

    public int getYPosition(){
        return TERRITORIUM.getyPosition();
    }

    public void setVDirection(int newV){
        TERRITORIUM.setvDirection(newV);
    }

    public void setTotalAMount(int amount){
        TERRITORIUM.setTotalAmount(amount);
    }

    public void setTile(int r, int c){
        TERRITORIUM.setxPosition(r);
        TERRITORIUM.setyPosition(c);
    }

    public void step() throws MouseException, RandException {
        if(TERRITORIUM.getvDirection() == 1){
            if(TERRITORIUM.getxPosition()+1 == TERRITORIUM.getRows()) {
                throw new RandException();
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition()+1, TERRITORIUM.getyPosition())){
                throw new MouseException();
            }
            else {
                TERRITORIUM.setxPosition(TERRITORIUM.getxPosition() + 1);
                System.out.println("Elefant geht nach Süden");
            }
        }
        if(TERRITORIUM.getvDirection() == 2){
            if(TERRITORIUM.getyPosition()+1 == TERRITORIUM.getCols()) {
                throw new RandException();
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()+1)){
                throw new MouseException();
            }
            else {
                TERRITORIUM.setyPosition(TERRITORIUM.getyPosition() + 1);
                System.out.println("Elefant geht nach Osten");
            }
        }
        if(TERRITORIUM.getvDirection() == 3){
            if(TERRITORIUM.getxPosition()-1 < 0) {
                throw new RandException();
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition()-1, TERRITORIUM.getyPosition())){
                throw new MouseException();
            }
            else {
                TERRITORIUM.setxPosition(TERRITORIUM.getxPosition() - 1);
                System.out.println("Elefant geht nach Norden");
            }
        }
        if(TERRITORIUM.getvDirection() == 4){
            if(TERRITORIUM.getyPosition()-1 < 0) {
                throw new RandException();
            }
            else if (TERRITORIUM.hasMouse(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()-1)){
                throw new MouseException();
            }
            else {
                TERRITORIUM.setyPosition(TERRITORIUM.getyPosition() - 1);
                System.out.println("Elefant geht nach Westen");
            }
        }
        System.out.println("Elefant:" + TERRITORIUM.getxPosition() + ", " + TERRITORIUM.getyPosition());
    }

    public void takeP() throws TileException {
        if(TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) > 0){
            TERRITORIUM.setP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition(), TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition() - 1));
            TERRITORIUM.setTotalAmount(TERRITORIUM.getTotalAmount() + 1);
        }
        else {
            throw new TileException("Hier liegen keine Erdnüsse");
        }
    }
    public void dropP() throws TileException, NoPeanutException {
        if(TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) < 9) {
            if (TERRITORIUM.getTotalAmount() > 0) {
                TERRITORIUM.setP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition(), TERRITORIUM.getP(TERRITORIUM.getxPosition(), TERRITORIUM.getyPosition()) + 1);
                TERRITORIUM.setTotalAmount(TERRITORIUM.getTotalAmount() - 1);
            }
            else{
                throw new NoPeanutException();
            }
        }
        else {
            throw new TileException("Hier liegen schon zu viele Erdnüsse");
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