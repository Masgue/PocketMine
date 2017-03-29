package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameMap;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;

public class StateRun extends GameState {
    private static final int DEFAULT_SCORE = 0;

    private GameMap _map;
    private int _score;
    private int _durabiility;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _map = new GameMap();
        _score = DEFAULT_SCORE;
    }

    @Override
    public void move() {
        _map.move();
    }

    @Override
    public void show() {
        _map.show();
    }

    @Override
    public void release() {
        _map.release();
        _map = null;
    }

    @Override
    public void keyPressed(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) {
        // TODO Auto-generated method stub
    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pointerPressed(List<Pointer> pointers) {
        if (pointers.size() == 1) {
            int touchX = pointers.get(0).getX();
            int touchY = pointers.get(0).getY();
            _map.ResetBlock(touchX, touchY);
        }

        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers) {
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers) {
        if (_map.gameOver())
        {
            _score = _map.GetScore();
            changeState(Game.OVER_STATE);
        }
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
