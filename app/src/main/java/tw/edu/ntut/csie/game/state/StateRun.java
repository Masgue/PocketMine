package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameMap;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.card.character.DurabilityBonus;
import tw.edu.ntut.csie.game.card.mine.CoalBonus;
import tw.edu.ntut.csie.game.card.tool.BombBonus;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.MapObserver;

public class StateRun extends GameState {
    private static final int DEFAULT_SCORE = 0;

    private Audio _audio;

    private MovingBitmap _background;
    private MovingBitmap _pause;
    private GameMap _map;
    private int _score;
    private int _durabiility;
    private boolean _isPaused;
    private ArrayList<CardAttributes> _cardAttributes = new ArrayList<CardAttributes>();

    private tw.edu.ntut.csie.game.MapObserver _observer;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _audio = new Audio(R.raw.music);
        _background = new MovingBitmap(R.drawable.background);
        _pause = new MovingBitmap(R.drawable.pause, 0, 0);
        _cardAttributes = (ArrayList<CardAttributes>) data.get("CardAttributes");
        _map = new GameMap(_cardAttributes);
        _score = DEFAULT_SCORE;
        _isPaused = false;

        _audio.setRepeating(true);
        _audio.play();

        _observer = new MapObserver(_map) {
            @Override
            public void update() {
                Map<String, Object> map = new HashMap<String, Object>();
                _score = _map.GetScore();
                map.put("Score", _score);
                _audio.stop();
                _audio.release();
                changeState(Game.OVER_STATE, map);
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
            if (touchX >= 0 && touchX <= 60 && touchY >= 0 && touchY <= 60) {
                if(!_isPaused)
                    pause();
                else
                    resume();
            }
            else if (!_isPaused) {
                if (touchY >= 10 && touchY <= 370)
                    _map.ResetAllBlock(touchX, touchY);
            }
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
        _audio.pause();
        _pause = new MovingBitmap(R.drawable.resume, 0, 0);
        _map.SetPause(_isPaused);
    }

    @Override
    public void resume() {
        _isPaused = false;
        _audio.resume();
        _pause = new MovingBitmap(R.drawable.pause, 0, 0);
        _map.SetPause(_isPaused);
    }
}
