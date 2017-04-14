package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Dynamite extends Tool {
    private int[][] _blockArray;

    public Dynamite(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, picture, blockArray);
        _blockArray = blockArray;
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        Explode(_arrayX, 0);
        Explode(_arrayX, 1);
        Explode(_arrayX, 2);
        Explode(_arrayX, 3);
        Explode(_arrayX, 4);
        Explode(_arrayX, 5);
    }

    private void Explode(int x, int y) {
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            if (_blockArray[x][y] >= 0 && _blockArray[x][y] != 1)
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;

            if (_blockArray[x][y] == blockType) {
                Dynamite dynamite = new Dynamite(_blockType, x, y, _viewHeight, _picture, _blockArray);
                dynamite.Active();
            }
        }
    }
}
