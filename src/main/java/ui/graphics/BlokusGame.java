package ui.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import control.BlokusDuoPlay;
import model.Board;

import java.io.PrintStream;

public class BlokusGame extends Game {

    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 810;

    private StartScreen startScreen;
    public PlayScreen playScreen;
    private BlokusDuoPlay blokusDuoPlay;
    public PrintStream pipe;
    public OrthographicCamera camera;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;
    private Thread gameplay;
    public TiledMap bored;
    SpriteBatch batch;

    public BlokusGame(BlokusDuoPlay blokusDuoPlay) {
        this.blokusDuoPlay = blokusDuoPlay;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH  * 0.5f, VIRTUAL_HEIGHT * 0.5f, 0.0f);
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("scene.json"));
        Gdx.input.setInputProcessor(stage);

        bored = new TmxMapLoader().load("playscreen.tmx");

        startScreen = new StartScreen(this);
        playScreen = new PlayScreen(this);
        setStartScreen();

        // give reference to self (libGDX game) to UI to change state of libGDX app by posting runnables
        GraphicsUI ui = (GraphicsUI)blokusDuoPlay.getUI();
        ui.setBlokusGame(this);

        // Piped stream provided by UI will be used for sending user input to UI as text strings
        pipe = new PrintStream(ui.getPipe());

        // start Blokus Duo control in a separate thread.
        gameplay = new Thread(blokusDuoPlay);
        gameplay.start();
    }

    @Override
    public void dispose() {
        gameplay.interrupt();
        gameplay.stop();
        startScreen.dispose();
        playScreen.dispose();
        skin.dispose();
        stage.dispose();
    }

    public void setStartScreen() {
        setScreen(startScreen);
    }

    public void setPlayScreen() {
        setScreen(playScreen);
    }

    void showDialog(String text) {
        Dialog dialog = new Dialog("Attention", skin, "default");
        dialog.text(text);
        dialog.button("OK");
        dialog.getContentTable().pad(10);
        dialog.getButtonTable().pad(10);
        dialog.show(stage);
    }

    // add a single-run code to be executed in GDX game loop between window updates
    public void postRunnable(Runnable runnable) {
        Gdx.app.postRunnable(runnable);
    }

    public static int getVirtualWidth() {
        return VIRTUAL_WIDTH;
    }

    public void updateBoard(Board board) {
        playScreen.graphicalBoard.updateBoard(board);
    }

    public static int getVirtualHeight() {
        return VIRTUAL_HEIGHT;
    }

    public PrintStream getPipe() {
        return pipe;
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() { return camera; }

    public Skin getSkin() { return skin; }
}
