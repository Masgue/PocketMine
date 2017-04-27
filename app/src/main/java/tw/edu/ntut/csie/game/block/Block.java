package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/4/14.
 */

public abstract class Block implements GameObject, BlockObject {
    protected static final int DEFAULT_NONE_BLOCK_TYPE = -1;
    protected static final int DEFAULT_NUMBER = -1;
    protected static final int BLOCK_HEIGHT = 60;
    protected static final int BLOCK_ROW = 50;
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

    public Block(int blockType, int arrayX, int arrayY, int viewHeight) {
        _blockType = blockType;
        _arrayX = arrayX;
        _arrayY = arrayY;
        _viewHeight = viewHeight;
    }

    @Override
    public void CalculateXY(int viewHeight) {
        _actualY = 10 + BLOCK_HEIGHT * _arrayY;
        _actualX = 160 + BLOCK_HEIGHT * _arrayX - _viewHeight;
    }

    @Override
    public void show() {
        CalculateXY(_viewHeight);
        _picture.setLocation(_actualX, _actualY);
        _picture.show();
    }

    @Override
    public void move() { }

    @Override
    public void release() { }

    public void SetBlock(int arrayX, int arrayY, int viewHeight) {
        _arrayX = arrayX;
        _arrayY = arrayY;
        _viewHeight = viewHeight;
    }
}
