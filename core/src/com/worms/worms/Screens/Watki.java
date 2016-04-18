package com.worms.worms.Screens;

/**
 * Created by Wojciech on 2016-04-12.
 */
public class Watki implements Runnable {

    private int id;

    public Watki(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Watek " + id);
            try {
                //usypiamy wątek na 100 milisekund
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}