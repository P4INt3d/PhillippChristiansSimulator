package de.nachname.util.exceptions;

public class NoPeanutException extends RuntimeException{

    public NoPeanutException(){
        super("Du hast keine Erdnüsse mehr");
    }
}
