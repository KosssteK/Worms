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
    public static float skok(float y, float wGore)
    {
//        System.out.print("Skok\n");
        if(y+wGore < 560) {
            y = y + wGore;
            wGore--;
        }else
        {
            wGore = 0;
        }
        return y;
    }
    public static float grawitacja(float y, float wDol,int [][]tab, float x,float wGore)
    {
        float licznik = wDol;

////        System.out.print("Grawitacja\n");
//        if(tab[(int)(y+wDol)][(int)x]!=1 && tab[(int)(y+wDol+1)][(int)x]!=1) //&& tablicaPodklad[(int) (spritePostaci.getY()+1)][(int)spritePostaci.getX()] != 1
//        {
//            y=y+wDol;
//        }else {
//
//            if(tab[(int)(y+wDol)][(int)x]==1 && tab[(int)(y+wDol+1)][(int)x]!=1) {
//            return y;
//            }else if(tab[(int)(y+wDol)][(int)x]==1 && tab[(int)(y+wDol+1)][(int)x]==1) {












//            for(int i=0;i<-wDol;i++)
//            {
//                System.out.print("Return\n");
//
//                if(tab[(int)(y+wDol)][(int)x]==1 && tab[(int)(y+wDol+1)][(int)x]==1)
//                 {
//                     wDol=wDol+1;
//
//                 }
//                if(tab[(int)(y)][(int)x]==1 && tab[(int)(y+1)][(int)x]==0)
//                {
//                    return y;
//                }
           // }
        //  y=y+wDol;
        //  }
        //wczesniejsza funkcja
        y=y+wDol;
//      tab[(int)y][(int)x]==1
        return y;
    }
    public static float grawitacjav2(float y ,float grawitacja, int [][]tab,float x)
    {
      //  System.out.print(gravity);

        if(tab[(int)y][(int)x]==0 && tab[(int)(y-1)][(int)x]==1)
        {
            gravity=-1;
        }else if(tab[(int)y][(int)x]==0 && tab[(int)(y-1)][(int)x]==0 && tab[(int)(y+gravity)][(int)x]==0)
        {
            y=y+gravity;
            gravity--;
        }else if(tab[(int)y][(int)x]==0 && tab[(int)(y-1)][(int)x]==0 && tab[(int)(y+gravity)][(int)x]==1)
        {
            float delta = -gravity;
            if(delta %2 ==1)
            {
                delta += 1;
            }
            System.out.print("----" + -gravity + "   " + delta + "\n");
            for (int  i = 0; i < -gravity; i++)
            {
                if(tab[(int)(y-delta)][(int)x]==0 && tab[(int)(y-delta-1)][(int)x]==1)
                {
                    y=y-delta;
                    wylacz = true;
                    return y;

                }
                delta--;
            }
        }else
        {
            System.out.print("sdss");

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
