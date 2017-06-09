package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.block.ActiveBlockList;
import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.mine.CommonBlock;


/**
 * Created by Johnson on 2017/4/7.
 */

public abstract class Tool extends Block{
    protected static int[][] _blockArray;
    protected static Tool[] _toolList;
    protected static CommonBlock[] _mineList;
    protected static int _mineNum;
    protected static int _toolNum;
    protected static ActiveBlockList _activeBlockList;
    protected static int _floor;

    public Tool(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
    }

    public void Active() { }

    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 1;
    }

    public static void SetToolList(Tool[] toolList) {
        _toolList = toolList;
    }

    public static void SetMineList(CommonBlock[] mineList) {
        _mineList = mineList;
    }

    public static void SetBlockArray(int[][] blockArray) {
        _blockArray = blockArray;
    }

    public static void SetMineNum(int mineNum) {
        _mineNum = mineNum;
    }

    public static void SetToolNum(int toolNum) {
        _toolNum = toolNum;
    }

    public static void SetFloor() {_floor = 0;}

    public void SetBlockXY(int arrayX, int arrayY) {
        _arrayX = arrayX;
        _arrayY = arrayY;
    }

    public int ExplodeAll(int arrayX, int arrayY) { return  0;}

    public void DetectWarningBlocks(int arrayX, int arrayY) { }

    public int Explosion() {
        int score = 0;
        for (int i = 0; i < _activeBlockList.GetBlockListSize(); i++)
        {
            score += Explode(_activeBlockList.GetActiveList(i).GetBlockX(),_activeBlockList.GetActiveList(i).GetBlockY());
        }
        return score;
    }

    public static void RemoveActiveArray() {
        while(_activeBlockList.GetBlockListSize() != 0)
        {
            _activeBlockList.RemoveBlockList();
        }
    }

    public static void SetActiveBlockList(ActiveBlockList activeBlockList) {
        _activeBlockList = activeBlockList;
    }

    public ActiveBlockList GetActiveBlockList() {
        return _activeBlockList;
    }

    protected int Explode(int x, int y) {
        int score = 0;
        int presentBlockNumber;
        if (x >= 0 && x < _blockRow && y >= 0 && y < BLOCK_COLUMN) {
            presentBlockNumber = _blockArray[x][y];

            if (_blockArray[x][y] > 0 &&  _blockArray[x][y] < _mineNum)
            {
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;
                score += _mineList[presentBlockNumber].GetPoints();
                if (x > _floor)
                    _floor = x;
            }

            else if (presentBlockNumber > _mineNum - 1 && presentBlockNumber != _mineNum + _toolNum) {
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;
                score += _toolList[presentBlockNumber - _mineNum].ExplodeAll(x,y);
            }
        }
        return score;
    }

    public static int GetFloor() {return _floor;}
}
