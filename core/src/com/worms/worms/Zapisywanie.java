package com.worms.worms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Wojciech on 2016-03-17.
 */
public class Zapisywanie {

    private String lokalizacjaPliku = "D://InteliJ//Worms//core//assets//podklad3.txt";

    public void zapiszPlik(String daneDoWpisania)
    {

        File plik = new File(lokalizacjaPliku);
        try {

            FileWriter zapis = new FileWriter(plik);
            BufferedWriter bufforowanyZapis = new BufferedWriter(zapis);
            //bufforowanyZapis.write(daneDoWpisania);
            int licznik = 100;
            for(int i = 0; i< 600 ; i++)
            {
                for(int j = 0; j < 800 ; j++)
                {
                    if(i<199)
                    {
                        bufforowanyZapis.write(49);
                    }
                    else{
                        if(i<299 && (j<100))
                        {
                            bufforowanyZapis.write(49);
                        }else if((i<348 && (j>650)))
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