package com.worms.worms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Wojciech on 2016-03-17.
 */
public class Zapisywanie {

    private String lokalizacjaPliku = "D://InteliJ//Worms//core//assets//podklad4.txt";
    public static String lokalizacjaPliku1 = "D://InteliJ//Worms//core//assets//zapisanaMapa.txt";
    public void zapiszPlik(int H, int W)
    {

        File plik = new File(lokalizacjaPliku);
        try {

            FileWriter zapis = new FileWriter(plik);
            BufferedWriter bufforowanyZapis = new BufferedWriter(zapis);

            for(int i = 0; i< H ; i++)
            {
                for(int j = 0; j < W ; j++)
                {
                    if(i<H/5)
                    {
                        bufforowanyZapis.write(49);
                    }
                    else{
                        if(i<H/3.5 && (j<W/8))
                        {
                            bufforowanyZapis.write(49);
                        }else if((i<H/3 && (j>4*W/5)))
                        {
                            bufforowanyZapis.write(49);
                        }else
                        {
                            bufforowanyZapis.write(48);
                        }
                    }



                }

            }
            bufforowanyZapis.close();
            zapis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void zapiszMape(int [][]tab, int H, int W)
    {

        File plik = new File(lokalizacjaPliku1);
        try {

            FileWriter zapis = new FileWriter(plik);
            BufferedWriter bufforowanyZapis = new BufferedWriter(zapis);

            for(int i = 0; i< H ; i++)
            {
                for(int j = 0; j < W ; j++)
                {
                        bufforowanyZapis.write(tab[i][j]+48);
                }
            }
            bufforowanyZapis.close();
            zapis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void miejscePoWybuchu(int [][] tab, int H,int W, int X, int Y)
    {
        int R = 75;
        double y1,y2;
        double x;

        for(int i = -75; i < 75; i++)
        {
            y1 = Math.sqrt(R*R - i*i);
            y2 = -y1+Y+10;
            y1 = y1+Y+10;
            x = i + X;
            for(int j = (int)y2; j<y1; j++)
            {
                if(j>0 && x<W-1 && x>0 && j<H-1)
                {
                    tab[(int) j][(int) x] = 0;
                   // System.out.print("f\n");
                }
            }
        }
    }


}