package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.Integer;


/**
 * Created by ChenKeng on 2017/3/16.
 */

public class PocketMine implements GameObject {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background;
    private MovingBitmap _android;
    private MovingBitmap _cloud;
    private MovingBitmap _door;
    private MovingBitmap _message;

    private GameMap mMap = new GameMap();

    private Animation _flower;

    private Integer _scores;

    private int _cx, _cy;

    private Audio _music;

    public PocketMine() {
        _background = new MovingBitmap(R.drawable.background);
        _message = new MovingBitmap(R.drawable.message, 130, 150);

        _android = new MovingBitmap(R.drawable.android_green);
        _android.setLocation(300, 200);

        _cloud = new MovingBitmap(R.drawable.cloud);
        _cx = 100;
        _cy = 50;
        _cloud.setLocation(_cx, _cy);

        _door = new MovingBitmap(R.drawable.door);
        _door.setLocation(300, 200);

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 50, 550, 10);

        _flower = new Animation();
        _flower.setLocation(560, 310);
        _flower.addFrame(R.drawable.flower1);
        _flower.addFrame(R.drawable.flower2);
        _flower.addFrame(R.drawable.flower3);
        _flower.addFrame(R.drawable.flower4);
        _flower.addFrame(R.drawable.flower5);
        _flower.setDelay(2);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
    }

    @Override
    public void move() {
        _flower.move();
        _cloud.setLocation(_cx, _cy);
        mMap.move();
    }

    @Override
    public void show() {
        //_background.show();
        _scores.show();
        _flower.show();
        _message.show();
        _cloud.show();
        _door.show();
        _android.show();
        mMap.show();
    }

    @Override
    public void release() {
        _background.release();
        _scores.release();
        _android.release();
        _flower.release();
        _message.release();
        _cloud.release();
        _music.release();
        _door.release();
        mMap.release();

        _background = null;
        _scores = null;
        _android = null;
        _flower = null;
        _message = null;
        _cloud = null;
        _music = null;
        _door = null;
        mMap = null;
    }

    public void orientationChanged(float pitch, float azimuth, float roll) {
        if (roll > 15 && roll < 60 && _cx > 50)
            _cx -= 2;
        if (roll < -15 && roll > -60 && _cx + _cloud.getWidth() < 500)
            _cx += 2;
    }

    public void pause() {
        _music.pause();
    }

    public void resume() {
        _music.resume();
    }

    public GameMap GetGameMap() {
        return mMap;
    }
}


