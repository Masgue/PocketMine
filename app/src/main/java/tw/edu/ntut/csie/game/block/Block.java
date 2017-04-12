package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public abstract class Block implements GameObject {
    protected static final int DEFAULT_NUMBER = -1;
    protected static final int BLOCK_HEIGHT = 60;
    protected static final int BLOCK_ROW = 15;
    protected static final int BLOCK_COLUMN = 6;

    protected MovingBitmap _picture;
    protected int _blockType;
    protected int _durability;
    protected int _points;
    protected int _arrayX;
    protected int _arrayY;
    protected int _viewHeight;
    protected int _actualX;
    protected int _actualY;
    protected boolean _isVisible;

    public Block() { }

    public Block(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture) {
        _durability = DEFAULT_NUMBER;
        _points = DEFAULT_NUMBER;
        _blockType = blockType;
        _arrayX = arrayX;
        _arrayY = arrayY;
        _viewHeight = viewHeight;
        _picture = picture;
        CalculateXY(viewHeight);
    }

    @Override
    public void release() {
        _picture.release();
    }

    @Override
    public void move() { }

    @Override
    public void show() {
        if (_actualX < 800) {
            _picture.setLocation(_actualX, _actualY);
            _picture.show();
        }
    }

    protected void CalculateXY(int viewHeight) {
        _actualY = 10 + BLOCK_HEIGHT * _arrayY;
        _actualX = 160 + BLOCK_HEIGHT * _arrayX - _viewHeight;
    }

    public int GetPoints() {
        return  _points;
    }

    public int GetDurability() {
        return _durability;
    }
}
