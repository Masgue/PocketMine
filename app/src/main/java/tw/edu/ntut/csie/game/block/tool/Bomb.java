package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Bomb extends Tool {
    private int[][] _blockArray;

    public Bomb(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, picture, blockArray);
        _blockArray = blockArray;
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        Explode(_arrayX - 1, _arrayY - 1);
        Explode(_arrayX - 1, _arrayY);
        Explode(_arrayX - 1, _arrayY + 1);
        Explode(_arrayX, _arrayY - 1);
        Explode(_arrayX, _arrayY + 1);
        Explode(_arrayX + 1, _arrayY - 1);
        Explode(_arrayX + 1, _arrayY);
        Explode(_arrayX + 1, _arrayY + 1);
    }

    private void Explode(int x, int y) {
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            if (_blockArray[x][y] >= 0 && _blockArray[x][y] != 1)
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;

            if (_blockArray[x][y] == DEFAULT_TOOL_TYPE) {
                Bomb bomb = new Bomb(_blockType, x, y, _viewHeight, _picture, _blockArray);
                bomb.Active();
            }
        }
    }
}
