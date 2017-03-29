package tw.edu.ntut.csie.game.state;

import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameMap;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.MapObserver;

public class StateRun extends GameState {
    private static final int DEFAULT_SCORE = 0;

    private MovingBitmap _background;
    private MovingBitmap _pause;
    private GameMap _map;
    private int _score;
    private int _durabiility;
    private boolean _isPaused;

    private tw.edu.ntut.csie.game.MapObserver _observer;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _background = new MovingBitmap(R.drawable.background);
        _pause = new MovingBitmap(R.drawable.pause, 0, 0);
        _map = new GameMap();
        _score = DEFAULT_SCORE;
        _isPaused = false;

        _observer = new MapObserver(_map) {
            @Override
            public void update() {
                _score = _map.GetScore();
                changeState(Game.OVER_STATE);
            }
        };
    }

    @Override
    public void move() {
        _map.move();
    }

    @Override
    public void show() {
        _background.show();
        _map.show();
        _pause.show();
    }

    @Override
    public void release() {
        _background.release();
        _background = null;
        _map.release();
        _map = null;
        _pause.release();
        _pause = null;
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
            if (touchX >= 0 && touchX <= 60) {
                if (touchY >= 0 && touchY <= 60) {
                    if(!_isPaused)
                        pause();
                    else
                        resume();
                }
            }
            else if (!_isPaused)
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
        return false;
    }

    @Override
    public void pause() {
        _isPaused = true;
        _pause = new MovingBitmap(R.drawable.resume, 0, 0);
        _map.SetPause(_isPaused);
    }

    @Override
    public void resume() {
        _isPaused = false;
        _pause = new MovingBitmap(R.drawable.pause, 0, 0);
        _map.SetPause(_isPaused);
    }
}
