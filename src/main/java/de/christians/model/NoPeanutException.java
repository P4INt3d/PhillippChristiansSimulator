package de.christians.model;

public class NoPeanutException extends RuntimeException{

    public NoPeanutException(){
        super("Du hast keine Erdnüsse mehr");
    }
}
