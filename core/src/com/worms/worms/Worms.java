package com.worms.worms;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;
import com.sun.java_cup.internal.runtime.Scanner;
import com.sun.java_cup.internal.runtime.Symbol;
import com.worms.worms.Screens.PlayScreen;
import java.net.Socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.worms.worms.Otwieranie.zamien;

import static com.worms.worms.Poruszanie.grawitacjav2;
import static com.worms.worms.Poruszanie.skok;

public class Worms extends Game {
	public static final int V_WIDTH = 1600;
	public static final int V_HEIGHT = 900;
	public SpriteBatch batch;

	private float poczatek = 0;
	private Texture texture;
	private Sprite sprite;

	private Texture teksturaPostaci;
	private Sprite spritePostaci;
	static Socket sck = null;

	private Texture teksturaPodkladu;
	private Sprite spritePodkladu;

	float skok = 20;
	float wGore = skok;
	float grawitacja = -1;
	Integer gravity = -1;
	int speed = 5;
	Vector3 wektor;



	Poruszanie A;
	Mysz B;

	float czasSpadania = 7;
	boolean wlacz = false;
	boolean wylacz = true;
	boolean wlaczGrawitacje = true;


	int [][] tablicaPodklad = new int[V_HEIGHT][V_WIDTH];
	char [][] tablicaPodkladChar = new char[V_HEIGHT][V_WIDTH];
	Otwieranie odczytPliku = new Otwieranie();
    Zapisywanie zapisPliku = new Zapisywanie();




	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		texture = new Texture(Gdx.files.internal("mapa4.png"));
		sprite = new Sprite(texture);

		teksturaPostaci = new Texture(Gdx.files.internal("postac.png"));
		spritePostaci = new Sprite(teksturaPostaci);
		spritePostaci.setPosition(3*V_WIDTH/4,V_HEIGHT/2);

		poczatek = spritePostaci.getY();
		teksturaPodkladu = new Texture(Gdx.files.internal("podklad.png"));
		spritePodkladu = new Sprite(teksturaPodkladu);


        zapisPliku.zapiszPlik(V_HEIGHT,V_WIDTH);
        odczytPliku.otworzPlik(tablicaPodkladChar,V_HEIGHT,V_WIDTH);
		zamien(tablicaPodkladChar,tablicaPodklad,V_HEIGHT,V_WIDTH);



		try {
			sck = new Socket("localhost", 1337);
		}catch(Exception e ){

		}
			Scanner sc1;



	}
	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		teksturaPodkladu.dispose();
		teksturaPostaci.dispose();

	}
	@Override
	public void render () {

		if(Gdx.input.isKeyPressed(Input.Keys.A) && spritePostaci.getX() > 0){
			if(tablicaPodklad[(int)( spritePostaci.getY()+20)][(int)spritePostaci.getX()-speed] !=1) {
				spritePostaci.translateX(-speed);

			}else  if(tablicaPodklad[(int)( spritePostaci.getY()+2)][(int)spritePostaci.getX()-1] !=1)
			{
				spritePostaci.translateX(-speed/2);
				spritePostaci.translateY(speed/2);
			}
		}
		if( Gdx.input.isKeyPressed(Input.Keys.D) && spritePostaci.getX() < V_WIDTH-40-speed ){
			if( tablicaPodklad[(int)( spritePostaci.getY()+20)][(int)(spritePostaci.getX()+40+speed)] !=1) {
				spritePostaci.translateX(speed);

			}else  if(tablicaPodklad[(int)( spritePostaci.getY()+2)][(int)spritePostaci.getX()+40+speed] !=1)
			{
				spritePostaci.translateX(speed/2);
				spritePostaci.translateY(speed/2);
			}
        }
//        if( Gdx.input.isKeyPressed(Input.Keys.S) && spritePostaci.getY() > 0){
//            spritePostaci.translateY(-10.0f);
//        }
//        if( Gdx.input.isKeyPressed(Input.Keys.W) && spritePostaci.getY() < V_HEIGHT-40){
//            spritePostaci.translateY(10.0f);
//


		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{


			wektor = PlayScreen.getMousePosInGameWorld();
			B.X = wektor.x+V_WIDTH/2;
			B.Y = wektor.y+V_HEIGHT/2;
		}


		if(Gdx.input.isKeyJustPressed(Input.Keys.W) && wylacz) {
			wlacz = true;
			wylacz = false;

		}



		if(wlaczGrawitacje)
		{
			spritePostaci.setY(grawitacjav2(spritePostaci.getY(),grawitacja,tablicaPodklad, spritePostaci.getX()));
			//grawitacja--;
		}




		if(wlacz) {  // if który odpowiada za dodawanie przy skoku

			spritePostaci.setY(skok(spritePostaci.getY(), wGore,V_HEIGHT));
			wGore--;
			wlaczGrawitacje = false;
		}

		if (wGore ==0) {
			wlacz = false;

			wGore = skok;
			wlaczGrawitacje = true;
		}
		if(tablicaPodklad[(int)(spritePostaci.getY())][(int)spritePostaci.getX()] == 0 && tablicaPodklad[(int)(spritePostaci.getY())-1][(int)spritePostaci.getX()]==1)
		{
			wylacz = true;
		}

		//System.out.print(tablicaPodklad[(int)(spritePostaci.getY())][(int)spritePostaci.getX()]+"    " + tablicaPodklad[(int)(spritePostaci.getY()-1)][(int)spritePostaci.getX()]  + "    " + gravity +"\n");
		//System.out.print(spritePostaci.getY()+ "   " + spritePostaci.getX() + "\n");
//		System.out.print("\n");
		System.out.print(B.X + "    " + B.Y + "\n");
		batch.begin();

		sprite.draw(batch);
		spritePostaci.draw(batch);
		batch.end();
		super.render();
	}
}
