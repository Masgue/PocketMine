package tw.edu.ntut.csie.game.state;

import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.PocketMine;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.engine.GameEngine;

public class StateRun extends GameState {
    private PocketMine _pocketMine;
    private boolean _grab;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _pocketMine = new PocketMine();
        _grab = false;
    }

    @Override
    public void move() {
        _pocketMine.move();
    }

    @Override
    public void show() {
        _pocketMine.show();
    }

    @Override
    public void release() {
        _pocketMine.release();
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
        _pocketMine.orientationChanged(pitch, azimuth, roll);
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
            _pocketMine.GetGameMap().ResetBlock(touchX, touchY);
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers) {
        if (_grab)
            changeState(Game.OVER_STATE);
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers) {
        _grab = false;
        return false;
    }

    @Override
    public void pause() {
        _pocketMine.pause();
    }

    @Override
    public void resume() {
        _pocketMine.resume();
    }
}
/*
 @Override
    public boolean pointerPressed(List<Pointer> pointers) {
        _message.setVisible(false);
        if (pointers.size() == 1) {
            int touchX = pointers.get(0).getX();
            int touchY = pointers.get(0).getY();
            if (touchX > _android.getX() && touchX < _android.getX() + _android.getWidth() &&
                    touchY > _android.getY() && touchY < _android.getY() + _android.getHeight()) {
                _grab = true;
            } else {
                _grab = false;
            }
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers) {
        if (_grab)
            _android.setLocation(pointers.get(0).getX() - _android.getWidth() / 2, pointers.get(0).getY() - _android.getHeight() / 2);
        int moveX = _android.getX();
        int moveY = _android.getY();
        if (moveX + _android.getWidth() / 2 > _door.getX() && moveX < _door.getX() + _door.getWidth() / 2 &&
                moveY + _android.getHeight() / 2 > _door.getY() && moveY < _door.getY() + _door.getHeight() / 2)
            changeState(Game.OVER_STATE);
        return false;
    }
 */
