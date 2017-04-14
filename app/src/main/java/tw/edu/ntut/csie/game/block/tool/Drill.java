package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Drill extends Tool {
    private int[][] _blockArray;

    public Drill(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, picture, blockArray);
        _blockArray = blockArray;
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        Explode(_arrayX + 1, _arrayY);
        Explode(_arrayX + 2, _arrayY);
        Explode(_arrayX + 3, _arrayY);
        Explode(_arrayX + 4, _arrayY);
    }

    private void Explode(int x, int y) {
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            if (_blockArray[x][y] >= 0 && _blockArray[x][y] != 1)
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;

            if (_blockArray[x][y] == blockType) {
                Drill drill = new Drill(_blockType, x, y, _viewHeight, _picture, _blockArray);
                drill.Active();
            }
        }
    }
}
