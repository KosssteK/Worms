package com.worms.worms.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.worms.worms.Worms;

/**
 * Created by Wojciech on 2016-03-06.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label wormsLabel;

    public Hud(SpriteBatch sb) {
        worldTimer =30;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Worms.V_WIDTH,Worms.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.PINK));
        scoreLabel  = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.PINK));
        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.PINK));
        //levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.PINK));
        //worldLabel = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.PINK));
        wormsLabel = new Label("Worms", new Label.LabelStyle(new BitmapFont(), Color.PINK));

        wormsLabel.setFontScale((float) 1.5);
        scoreLabel.setFontScale((float) 1.5);
        countDownLabel.setFontScale((float) 1.5);
        timeLabel.setFontScale((float) 1.5);


        table.add(wormsLabel).expandX().padTop(10);
      //  table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
      //  table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);

    }







}
