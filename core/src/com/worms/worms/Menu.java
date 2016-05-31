package com.worms.worms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Scanner;

/**
 * Created by Wojciech on 2016-05-24.
 */
public class Menu implements Runnable{
    public boolean poczatekGry2;
    public boolean poczatekGry;
    public float wlacznikGry;
    public float wlacznikGry2;
    public boolean wlacznikWatek = true;

    public String login;
    public String haslo;
    Dane G = new Dane();
    Dane H = new Dane();
    Logowanie F = new Logowanie();
    Rejestracja I = new Rejestracja();


    @Override
    public void run(){

        DBConnect connect = new DBConnect();



        do {

            int liczba =0;
            boolean zmienna=true;
            while(zmienna) {
                if (Gdx.input.isKeyPressed(Input.Keys.F1)) {
                    liczba = 1;
                    zmienna = false;
                } else if (Gdx.input.isKeyPressed(Input.Keys.F2)) {
                    liczba = 2;
                    zmienna = false;
                } else if (Gdx.input.isKeyPressed(Input.Keys.F3)) {
                    liczba = 3;
                    zmienna = false;
                }
            }






            if (liczba == 1) {

                    F.odpalanie();

                    String logowanie = G.login;
                    String haslowanie= G.haslo;
                    poczatekGry = connect.logowanie(logowanie, haslowanie);
                    wlacznikGry2 = 1;



            } else if(liczba == 2) {

                I.odpalanie();


                String logowanie = H.login;
                String haslowanie= H.haslo;
                String nickowanie = H.nick;
                connect.registration(logowanie,haslowanie,nickowanie);

            }else {
                poczatekGry = true;
                wlacznikGry2 = 2;
            }

        }while(!poczatekGry);
        wlacznikGry = wlacznikGry2;



    }

}
