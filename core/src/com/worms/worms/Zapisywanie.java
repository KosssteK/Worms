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

    public void zapiszPlik(int H, int W)
    {

        File plik = new File(lokalizacjaPliku);
        try {

            FileWriter zapis = new FileWriter(plik);
            BufferedWriter bufforowanyZapis = new BufferedWriter(zapis);
            //bufforowanyZapis.write(daneDoWpisania);
            int licznik = 100;
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


//                    if(j<licznik)
//                    {
//                        bufforowanyZapis.write(49);
//                    }else
//                    {
//                        bufforowanyZapis.write(48);
//                    }
//                    //bufforowanyZapis.write(4);



                }
                licznik++;
            }
            bufforowanyZapis.close();
            zapis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}