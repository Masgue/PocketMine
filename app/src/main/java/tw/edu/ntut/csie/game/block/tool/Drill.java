package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Drill extends Tool {
    protected int _digPower;

    public Drill(int blockType, int arrayX, int arrayY, int viewHeight, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, blockArray);
        _blockArray = blockArray;
        _picture = new MovingBitmap(R.drawable.drill);
        _digPower = 4;
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= _digPower; i++)
        {
            if (_blockArray[_arrayX + i][_arrayY] != 0)
                Explode(_arrayX + i, _arrayY);
            else
                break;
        }
    }

    private void Explode(int x, int y) {
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            if (_blockArray[x][y] > 0)
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;

            if (_blockArray[x][y] == _blockType) {
                //Drill drill = new Drill(_blockType, x, y, _viewHeight, _blockArray);
                //drill.Active();
            }
        }
    }
}
