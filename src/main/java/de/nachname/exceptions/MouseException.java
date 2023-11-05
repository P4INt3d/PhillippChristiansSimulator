package de.nachname.exceptions;

public class MouseException extends RuntimeException{

    public MouseException (){
        super("Hier ist eine Maus im Weg");
    }
}
