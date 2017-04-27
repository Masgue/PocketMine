package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Bomb extends Tool {
    public Bomb(int blockType, int arrayX, int arrayY, int viewHeight, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight, blockArray);
        _blockArray = blockArray;
        _picture = new MovingBitmap(R.drawable.bomb);
    }

    @Override
    public void Active() {
        _blockArray[_arrayX][_arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        if (_arrayY == 0)
        {
            Explode(_arrayX - 1, _arrayY);
            Explode(_arrayX + 1, _arrayY);
            Explode(_arrayX, _arrayY + 1);
            if (_blockArray[_arrayX - 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY + 1] != 0)
                Explode(_arrayX - 1, _arrayY + 1);
            if (_blockArray[_arrayX + 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY + 1] != 0)
                Explode(_arrayX + 1, _arrayY + 1);
        }
        else if (_arrayY == BLOCK_COLUMN - 1)
        {
            Explode(_arrayX - 1, _arrayY);
            Explode(_arrayX + 1, _arrayY);
            Explode(_arrayX, _arrayY - 1);
            if (_blockArray[_arrayX - 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY - 1] != 0)
                Explode(_arrayX - 1, _arrayY - 1);
            if (_blockArray[_arrayX + 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY - 1] != 0)
                Explode(_arrayX + 1, _arrayY - 1);
        }
        else
        {
            Explode(_arrayX - 1, _arrayY);
            Explode(_arrayX + 1, _arrayY);
            Explode(_arrayX, _arrayY - 1);
            Explode(_arrayX, _arrayY + 1);
            if (_blockArray[_arrayX - 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY - 1] != 0)
                Explode(_arrayX - 1, _arrayY - 1);
            if (_blockArray[_arrayX + 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY - 1] != 0)
                Explode(_arrayX + 1, _arrayY - 1);
            if (_blockArray[_arrayX - 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY + 1] != 0)
                Explode(_arrayX - 1, _arrayY + 1);
            if (_blockArray[_arrayX + 1][_arrayY] != 0 || _blockArray[_arrayX][_arrayY + 1] != 0)
                Explode(_arrayX + 1, _arrayY + 1);
        }

//        if (_arrayY > 0)
//        {
//            Explode(_arrayX - 1, _arrayY - 1);
//            Explode(_arrayX, _arrayY - 1);
//            Explode(_arrayX + 1, _arrayY - 1);
//        }
//        if (_arrayY < BLOCK_COLUMN - 1)
//        {
//            Explode(_arrayX - 1, _arrayY + 1);
//            Explode(_arrayX, _arrayY + 1);
//            Explode(_arrayX + 1, _arrayY + 1);
//        }
//        Explode(_arrayX - 1, _arrayY);
//        Explode(_arrayX + 1, _arrayY);

    }

    private void Explode(int x, int y) {
        //int presentBlockNumber;
        if (x >= 0 && x < BLOCK_ROW && y >= 0 && y < BLOCK_COLUMN) {
            //presentBlockNumber = _blockArray[x][y];
            if (_blockArray[x][y] > 0) {
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;
            }

           //if (presentBlockNumber >= _mineNumber && presentBlockNumber < _mineNumber + _toolList.length) {
               // _toolList[presentBlockNumber - _mineNumber].Active();
                //Bomb bomb = new Bomb(_blockType, x, y, _viewHeight, _blockArray);
                //bomb.Active();
            //}
        }
    }

    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 1;
    }
}
