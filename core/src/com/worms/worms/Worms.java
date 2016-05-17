package com.worms.worms;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.sun.java_cup.internal.runtime.Scanner;
import com.worms.worms.Screens.PlayScreen;
import java.net.Socket;

import java.util.LinkedList;
import java.util.List;

import static com.worms.worms.Naboj.obliczStosunek;
import static com.worms.worms.Naboj.poruszanie;
import static com.worms.worms.Naboj.usun;
import static com.worms.worms.Otwieranie.zamien;

import static com.worms.worms.Poruszanie.grawitacjav2;
import static com.worms.worms.Poruszanie.skok;

import static com.worms.worms.Mysz.mouseButtonReleased;
import static com.worms.worms.Zapisywanie.miejscePoWybuchu;
import static com.worms.worms.Zapisywanie.zapiszMape;


public class Worms extends Game {
	public static final int V_WIDTH = 1600;
	public static final int V_HEIGHT = 900;
	public SpriteBatch batch;

	private float poczatek = 0;
	static Socket sck = null;



	private Texture texture;
	private Sprite sprite;

	private Texture teksturaPostaci;
	private Sprite spritePostaci;

	private Texture teksturaPodkladu;
	private Sprite spritePodkladu;

	private Texture teksturaPocisku;
	private Sprite spritePocisku;

	private Texture teksturaDziury;
	private Sprite spriteDziury;

	float poprzedniX, poprzedniY;


	float skok = 20;
	float wGore = skok;
	float grawitacja = -1;
	Integer gravity = -1;
	int speed = 5;
	Vector3 wektor;
	double sila = 0;
	boolean wlaczPocisk = false;

	boolean just = false;
	boolean buttonReleased = true;
	int licznikLeft = 0;
	boolean masywnyWlacznikCalegoJust = true;



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


	List<Naboj> listaNaboi = new LinkedList<Naboj>();






	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

// =======================================================   tekstury i sprite =============================================

		texture = new Texture(Gdx.files.internal("mapa4.png"));
		sprite = new Sprite(texture);

		teksturaPostaci = new Texture(Gdx.files.internal("postac.png"));
		spritePostaci = new Sprite(teksturaPostaci);
		spritePostaci.setPosition(3*V_WIDTH/4,V_HEIGHT/2);




		poczatek = spritePostaci.getY();
		teksturaPodkladu = new Texture(Gdx.files.internal("podklad.png"));
		spritePodkladu = new Sprite(teksturaPodkladu);

		teksturaPocisku = new Texture(Gdx.files.internal("pocisk.png"));
		spritePocisku = new Sprite(teksturaPocisku);
		spritePocisku.setPosition(-20,-20);

		teksturaDziury = new Texture(Gdx.files.internal("dziura.png"));
		spriteDziury = new Sprite(teksturaDziury);
		spriteDziury.setPosition(-150,-150);
		//spriteDziury.setCenter((float)75,(float)75);;


// ================================================================ zapis i odczyt map  ================================






        zapisPliku.zapiszPlik(V_HEIGHT,V_WIDTH);
        odczytPliku.otworzPlik(tablicaPodkladChar,V_HEIGHT,V_WIDTH);
		zamien(tablicaPodkladChar,tablicaPodklad,V_HEIGHT,V_WIDTH);




//==============================================   sokety   ================================================


		KnockKnockClient C = new KnockKnockClient();
		Thread watek = new Thread(C);
		watek.start();



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

			}else  if(tablicaPodklad[(int)( spritePostaci.getY()+2)][(int)spritePostaci.getX()-speed] !=1)
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

		// =============================================   NABOJE =======================================================================================================





		if(masywnyWlacznikCalegoJust) {


			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && licznikLeft == 0) {
				licznikLeft++;
				just = true;

				//System.out.print("JustPressed" + "\n");
			}

			if (just && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && sila < 16) {
				sila = sila + 0.3;
				//System.out.print(sila + "\n");
			} else {
				if (just && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					wlaczPocisk = true;
					//just = false;
					masywnyWlacznikCalegoJust =false;
					//buttonReleased = true;
					//System.out.print("released przetrzymany " + "\n");
				}
				if (just && !Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					wlaczPocisk = true;
					just = false;
					masywnyWlacznikCalegoJust =true;
					//buttonReleased = true;
					//System.out.print("released let go" + "\n");
				}
			}
		}else
		if (just && !Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			//wlaczPocisk = true;
			just = false;
			masywnyWlacznikCalegoJust =true;
			//buttonReleased = true;
			//System.out.print("released wylacznikowy" + "\n");
		}






		if(listaNaboi.size()==0 && wlaczPocisk)
		{


			wektor = PlayScreen.getMousePosInGameWorld();
			B.X = wektor.x+V_WIDTH/2;
			B.Y = wektor.y+V_HEIGHT/2;
			listaNaboi.add(0,new Naboj((int) spritePostaci.getX(),(int)spritePostaci.getY(),(int)B.X,(int)B.Y,0,0));
			obliczStosunek(listaNaboi,(int)sila);
			sila =0;
			wlaczPocisk = false;
		}


		if(listaNaboi.size()!=0)
		{
			poruszanie(listaNaboi);
			spritePocisku.setPosition(listaNaboi.get(0).x,listaNaboi.get(0).y);
		}
		if(usun(listaNaboi,tablicaPodklad))
		{
			spriteDziury.setPosition(listaNaboi.get(0).x-75,listaNaboi.get(0).y-75+10);


			//zapiszMape(tablicaPodklad, V_HEIGHT,V_WIDTH);
			//System.out.print(listaNaboi.get(0).x + "   " + listaNaboi.get(0).y);

			miejscePoWybuchu(tablicaPodklad, V_HEIGHT, V_WIDTH, listaNaboi.get(0).x, listaNaboi.get(0).y);
			listaNaboi.remove(0);
			spritePocisku.setPosition(-20,-20);
			licznikLeft = 0;
		}

// ====================================================    KONIEC NABOI  ====================================================================


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






		batch.begin();

		sprite.draw(batch);
		spritePocisku.draw(batch);
		spriteDziury.draw(batch);
		spritePostaci.draw(batch);


		batch.end();



		super.render();
	}
}
