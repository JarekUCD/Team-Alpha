package ui.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import model.Board;


public class PlayScreen extends ScreenAdapter {

    private BlokusGame blokusGame;
    private Stage stage;
    Array<GraphicalGamepiece> pieces;
    GraphicalBoard graphicalBoard;
    String message;
    BitmapFont messageFont;
    float messageX;
    float messageY;
    float messageWidth;

    public PlayScreen(BlokusGame blokusGame) {
        this.blokusGame = blokusGame;
        this.stage = blokusGame.getStage();

        MapLayer layer = blokusGame.bored.getLayers().get("Object Layer");
        MapObjects objects = layer.getObjects();

        pieces = new Array<GraphicalGamepiece>();
        TextureRegion xPicture = blokusGame.getSkin().getRegion("round-white-small");
        TextureRegion oPicture = blokusGame.getSkin().getRegion("round-white-small");
        //addPlayerGamepieces(pieces,objects,xPicture, Board.X);
        //addPlayerGamepieces(pieces,objects,oPicture,Board.O);

        MapObject boardLocation = objects.get("Board");
        float boardX = (float) boardLocation.getProperties().get("x");
        float boardY = (float) boardLocation.getProperties().get("y");
        float boardHeight = (float) boardLocation.getProperties().get("height");
        float boardWidth = (float) boardLocation.getProperties().get("width");
        graphicalBoard = new GraphicalBoard(boardX,boardY,boardWidth,boardHeight,xPicture,oPicture,new Board());
    }

    public void render(float delta) {
        super.render(delta);
        // just show light blue window for now
        Gdx.gl.glClearColor(0.8196f, 0.7803f, 0.4274f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}
