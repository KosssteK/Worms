package com.worms.worms;

/**
 * Created by Wojciech on 2016-03-14.
 */
public class Poruszanie {
    public static float gravity;
    public static boolean wylacz;
    public Poruszanie()
    {
        this.gravity=-1;
        this.wylacz=true;
    }
    public static float skok(float y, float wGore, int H)
    {
//        System.out.print("Skok\n");
        if(y+wGore < H) {
            y = y + wGore;
            wGore--;
        }else
        {
            wGore = 0;
        }
        return y;
    }

    public static float grawitacjav2(float y ,float grawitacja, int [][]tab,float x)
    {
       // System.out.print(y + "   " + x+ "\n");
        if(y<20)
        {
            System.out.print("smierc \n");

            return y;
        }else {
                if (tab[(int) y][(int) x] == 0 && tab[(int) (y - 1)][(int) x] == 1) {  //normalne stanie na ziemi
                    gravity = -1;
                } else if (tab[(int) y][(int) x] == 0 && tab[(int) (y - 1)][(int) x] == 0 && tab[(int) (y + gravity)][(int) x] == 0) {    //kiedy jest sie w powietrzu i roznica tez wypada w powietrzu
                    y = y + gravity;
                    gravity--;
                } else if (tab[(int) y][(int) x] == 0 && tab[(int) (y - 1)][(int) x] == 0 && tab[(int) (y + gravity)][(int) x] == 1) {    // kiedy jest siew powietrzu ale laduje sie w ziemi
                    float delta = -gravity;
                    if (delta % 2 == 1) {
                        delta += 1;
                    }
                    // System.out.print("----" + -gravity + "   " + delta + "\n");
                    for (int i = 0; i < -gravity; i++) {
                        if (tab[(int) (y - delta)][(int) x] == 0 && tab[(int) (y - delta - 1)][(int) x] == 1) {
                            y = y - delta;
                            wylacz = true;
                            return y;

                        }
                        delta--;
                    }
                } else {
                    System.out.print("sdss" + "\n");

                }
        }
        return y;
    }
    public static void strzelanie(Mysz B, float x, float y)
    {
            int proporcja=10;
            double wynikX=0;
            double wynikY=0;
            double z = Math.sqrt((B.X-x)*(B.X-x)+(B.Y - y)*(B.Y - y));
            wynikY = (proporcja *B.Y)/z;
            wynikX = (proporcja * B.X)/z;


    }
}
