package de.nachname.util;

import java.util.ArrayList;

public class Observable extends ArrayList<Observer> {

    private static final long serialVersionUID = 1L;

    protected void notifyObserver() {
        for (Observer obs : this) {
            obs.update();
        }
    }
}
