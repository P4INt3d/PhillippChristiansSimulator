package de.christians.model;

public class Elefant {
    
    private final Territorium territorium;

    public Elefant(Territorium territorium){
        this.territorium = territorium;
    }

    public boolean freeTile(){
        return switch (territorium.getvDirection()) {
            case 1 -> (territorium.getxPosition() + 1 != territorium.getRows());
            case 2 -> (territorium.getyPosition() + 1 != territorium.getCols());
            case 3 -> (territorium.getxPosition() - 1 >= 0);
            case 4 -> (territorium.getyPosition() - 1 >= 0);
            default -> false;
        };
    }

    public boolean peanutFound(){
        return (territorium.getP(territorium.getxPosition(), territorium.getyPosition())>0);
    }

    public boolean hasNoPeanut(){
        return (territorium.getTotalAmount()==0);
    }

    public int getDirection(){
        return territorium.getvDirection();
    }

    public int getTotalAMount(){
        return territorium.getTotalAmount();
    }

    public int getXPosition(){
        return territorium.getxPosition();
    }

    public int getYPosition(){
        return territorium.getyPosition();
    }

    public void setVDirection(int newV){
        territorium.setvDirection(newV);
    }

    public void setTotalAMount(int amount){
        territorium.setTotalAmount(amount);
    }

    public void setTile(int r, int c){
        territorium.setxPosition(r);
        territorium.setyPosition(c);
    }

    public void step() throws MouseException, RandException {
        if(territorium.getvDirection() == 1){
            if(territorium.getxPosition()+1 == territorium.getRows()) {
                throw new RandException();
            }
            else if (territorium.hasMouse(territorium.getxPosition()+1, territorium.getyPosition())){
                throw new MouseException();
            }
            else {
                territorium.setxPosition(territorium.getxPosition() + 1);
            }
        }
        if(territorium.getvDirection() == 2){
            if(territorium.getyPosition()+1 == territorium.getCols()) {
                throw new RandException();
            }
            else if (territorium.hasMouse(territorium.getxPosition(), territorium.getyPosition()+1)){
                throw new MouseException();
            }
            else {
                territorium.setyPosition(territorium.getyPosition() + 1);
            }
        }
        if(territorium.getvDirection() == 3){
            if(territorium.getxPosition()-1 < 0) {
                throw new RandException();
            }
            else if (territorium.hasMouse(territorium.getxPosition()-1, territorium.getyPosition())){
                throw new MouseException();
            }
            else {
                territorium.setxPosition(territorium.getxPosition() - 1);
            }
        }
        if(territorium.getvDirection() == 4){
            if(territorium.getyPosition()-1 < 0) {
                throw new RandException();
            }
            else if (territorium.hasMouse(territorium.getxPosition(), territorium.getyPosition()-1)){
                throw new MouseException();
            }
            else {
                territorium.setyPosition(territorium.getyPosition() - 1);
            }
        }
    }

    public void takeP() throws TileException {
        if(territorium.getP(territorium.getxPosition(), territorium.getyPosition()) > 0){
            territorium.setP(territorium.getxPosition(), territorium.getyPosition(), territorium.getP(territorium.getxPosition(), territorium.getyPosition() - 1));
            territorium.setTotalAmount(territorium.getTotalAmount() + 1);
        }
        else {
            throw new TileException("Hier liegen keine Erdnüsse");
        }
    }
    public void dropP() throws TileException, NoPeanutException {
        if(territorium.getP(territorium.getxPosition(), territorium.getyPosition()) < 9) {
            if (territorium.getTotalAmount() > 0) {
                territorium.setP(territorium.getxPosition(), territorium.getyPosition(), territorium.getP(territorium.getxPosition(), territorium.getyPosition()) + 1);
                territorium.setTotalAmount(territorium.getTotalAmount() - 1);
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
        if(territorium.getvDirection() == 4) {
            newV = 1;
        }
        else {
            newV = territorium.getvDirection()+1;
        }
        territorium.setvDirection(newV);
    }
    public void main(String[] args){ /* method to be overwritten by new user program*/ }
}