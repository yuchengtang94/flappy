package hud;

import com.awesometuts.flappybird.GameMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import helpers.Gameinfo;
import scenes.Gameplay;
import scenes.MainMenu;

/**
 * Created by Tang on 2017/7/29.
 */

public class UIHud {

    private GameMain game;

    private Stage stage;
    private Viewport gameViewport;

    private Label scoreLabel;

    private ImageButton retryBtn, quitBtn;

    private int score;

    public UIHud(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(Gameinfo.WIDTH, Gameinfo.HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        createLable();

        stage.addActor(scoreLabel);
    }

    void createLable() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("Fonts/04b_19.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;

        BitmapFont font = generator.generateFont(parameter);

        scoreLabel = new Label(String.valueOf(score), new Label.LabelStyle(font, Color.WHITE));

        scoreLabel.setPosition(Gameinfo.WIDTH / 2f - scoreLabel.getWidth() / 2f, Gameinfo.HEIGHT / 2f + 200);

    }

    public void incrementScore() {
        score++;
        scoreLabel.setText(String.valueOf(score));
    }

    public void createButtons() {
        retryBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Retry.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Quit.png"))));

        retryBtn.setPosition(Gameinfo.WIDTH / 2f - retryBtn.getWidth() / 2f - 100,
                Gameinfo.HEIGHT / 2f - 50f);
        quitBtn.setPosition(Gameinfo.WIDTH / 2f - quitBtn.getWidth() / 2f + 100,
                Gameinfo.HEIGHT / 2f - 50f);

        retryBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Gameplay(game));
                stage.dispose();
            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
                stage.dispose();
            }
        });

        stage.addActor(retryBtn);
        stage.addActor(quitBtn);
    }

    public void showScore() {
        scoreLabel.setText(String.valueOf(score));
        stage.addActor(scoreLabel);
    }

    public int getScore() {
        return this.score;
    }

    public Stage getStage() {
        return this.stage;
    }

}// UI Hud
