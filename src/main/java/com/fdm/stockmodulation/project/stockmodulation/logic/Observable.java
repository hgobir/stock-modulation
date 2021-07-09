package com.fdm.stockmodulation.project.stockmodulation.logic;

public interface Observable {

    void notifyObserver() throws InterruptedException;

}
