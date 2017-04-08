package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Bomb extends Tool {
    private int[][] _blockArray;

    public Bomb(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int[][] blockArray, int multiArrayNumber) {
        super(blockType, arrayX, arrayY, viewHeight, picture, blockArray, multiArrayNumber);
        _blockArray = blockArray;
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
    }
}
