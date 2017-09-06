package scenes;

import com.awesometuts.flappybird.GameMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import helpers.Gameinfo;
import hud.MainMenuButtons;

/**
 * Created by Tang on 2017/7/29.
 */

public class MainMenu implements Screen{

    private GameMain game;

    private Texture bg;

    private MainMenuButtons btns;

    private OrthographicCamera maincamera;
    private Viewport gameViewport;

    public MainMenu(GameMain game) {
        this.game = game;

        maincamera = new OrthographicCamera();
        maincamera.setToOrtho(false, Gameinfo.WIDTH, Gameinfo.HEIGHT);
        maincamera.position.set(Gameinfo.WIDTH / 2f, Gameinfo.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(Gameinfo.WIDTH, Gameinfo.HEIGHT, maincamera);

        bg = new Texture("Backgrounds/Night.jpg");

        btns = new MainMenuButtons(game);

        Gdx.input.setInputProcessor(btns.getStage());


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().end();

        game.getBatch().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {

        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}// main menu
