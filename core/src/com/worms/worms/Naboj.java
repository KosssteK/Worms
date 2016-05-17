package com.worms.worms;

import java.util.List;

/**
 * Created by Wojciech on 2016-05-04.
 */
public class Naboj {

    int x,y; // pozycja pocisku
    int a,b; //pozycja myszki
    double s1,s2; //stosunki lewo/prawo, gora/dol


    public Naboj() {
        x=0;
        y=0;
    }
    public Naboj(int x, int y, int a, int b,double s1, double s2) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.s1 = s1;
        this.s2 = s2;
    }

    public static void obliczStosunek(List<Naboj> listaNaboi, int sila)
    {
        int predkosc = sila;
        double X,Y;
        double Z;
        double A, B;

        X = listaNaboi.get(0).a - listaNaboi.get(0).x;
        Y = listaNaboi.get(0).b - listaNaboi.get(0).y;

        Z = Math.sqrt(X*X + Y*Y);

        A = (predkosc*X)/Z;
        B = (predkosc*Y)/Z;

        listaNaboi.set(0,new Naboj(listaNaboi.get(0).x,listaNaboi.get(0).y,listaNaboi.get(0).a,listaNaboi.get(0).b,A,B));

    }
    public static void poruszanie(List<Naboj> listaNaboi)
    {
       // System.out.print(listaNaboi.get(0).x + "    " + listaNaboi.get(0).y + "\n");
        //System.out.print(listaNaboi.get(0).s1 + "    " + listaNaboi.get(0).s2 + "\n");
        double grawitacja = 0.15;



        listaNaboi.set(0,new Naboj(listaNaboi.get(0).x+(int)listaNaboi.get(0).s1,listaNaboi.get(0).y +(int)listaNaboi.get(0).s2,listaNaboi.get(0).a,listaNaboi.get(0).b,listaNaboi.get(0).s1,listaNaboi.get(0).s2-grawitacja));
    }
    public static boolean usun(List<Naboj> listaNaboi,int [][]tab)
    {
        if(listaNaboi.size()==1) {
            if (listaNaboi.get(0).x < 10 || listaNaboi.get(0).x > 1590 || listaNaboi.get(0).y < 10 || listaNaboi.get(0).y > 880) {
                return true;
            }else if(tab[listaNaboi.get(0).y][listaNaboi.get(0).x] == 1)
            {
                return true;
            } else {
                return false;
            }
        }else
        {return false;}

    }
}
