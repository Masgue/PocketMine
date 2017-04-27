package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Dynamite extends Tool {
    public Dynamite(int blockType, int arrayX, int arrayY, int viewHeight, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, blockArray);
        _blockArray = blockArray;
        _picture = new MovingBitmap(R.drawable.dynamite);
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= 5; i++)
        {
            if (_arrayY - i >= 0 && _blockArray[_arrayX][_arrayY - i] != 0)
                Explode(_arrayX, _arrayY - i);
            else
                break;
        }
        for (int i = 1; i <= 5; i++)
        {
            if (_arrayY + i < BLOCK_COLUMN && _blockArray[_arrayX][_arrayY + i] != 0)
                Explode(_arrayX, _arrayY + i);
            else
                break;
        }
    }

    private void Explode(int x, int y) {
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            if (_blockArray[x][y] > 0)
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;

            if (_blockArray[x][y] == _blockType) {
                //Dynamite dynamite = new Dynamite(_blockType, x, y, _viewHeight, _blockArray);
                //dynamite.Active();
            }
        }
    }
}
