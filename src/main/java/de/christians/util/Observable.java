package de.christians.util;

import java.io.Serial;
import java.util.ArrayList;

public class Observable extends ArrayList<Observer> {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void notifyObserver() {
        for (Observer obs : this) {
            obs.update();
        }
    }
}
