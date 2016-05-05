package com.worms.worms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Wojciech on 2016-04-12.
 */
public class Mysz {
    public static float X;
    public static float Y;

    public Mysz()
    {
        X=0;
        Y=0;
    }
    public static boolean mouseButtonReleased()
    {
        boolean just = false;
        boolean buttonReleased = false;
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            just = true;
            System.out.print("JustPressed");
        }

        if(just && Gdx.input.isKeyPressed(Input.Keys.SPACE) )
        {
           // sila = sila +0.3;
           // System.out.print(sila + "\n");
        }else if( just && !Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            just = false;
            buttonReleased = true;
            System.out.print("released");
        }
        return false;
    }


}
