package com.worms.worms;

import java.io.*;

/**
 * Created by Wojciech on 2016-03-15.
 */
public class Otwieranie {

    private String lokalizacjaPliku = "D://InteliJ//Worms//core//assets//podklad3.txt";

    public void otworzPlik(char [][] tab){
        File plik = new File(lokalizacjaPliku);
        try {
            FileReader odczyt = new FileReader(lokalizacjaPliku);
            BufferedReader buffOdczyt = new BufferedReader(odczyt);
            for(int i = 0; i<600;i++)
            {
                    buffOdczyt.read(tab[i]);
            }
            buffOdczyt.close();
            odczyt.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void wypiszTablice(int [][] tab)
    {
        for(int i =0; i < 600;i++)
        {
            for(int j =0; j < 800;j++)
            {
                System.out.print(tab[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public static void zamien(char[][]tab1,int [][]tab2)
    {
        for(int i =0; i < 600;i++)
        {
            for(int j =0; j < 800;j++) {
                tab2[i][j] = (int) tab1[i][j] -48;
               // System.out.print(tab1[i][j] + " ");
            }
           // System.out.print("\n");
        }
    }

}
