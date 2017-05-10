package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.block.ActiveBlockList;
import tw.edu.ntut.csie.game.block.Block;


/**
 * Created by Johnson on 2017/4/7.
 */

public abstract class Tool extends Block{
    protected static int[][] _blockArray;
    protected static Tool[] _toolList;
    protected static int _mineNum;
    protected static int _toolNum;
    protected static ActiveBlockList _activeBlockList;

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

    public static void SetBlockArray(int[][] blockArray) {
        _blockArray = blockArray;
    }

    public static void SetMineNum(int mineNum) {
        _mineNum = mineNum;
    }

    public static void SetToolNum(int toolNum) {
        _toolNum = toolNum;
    }

    public void SetBlockXY(int arrayX, int arrayY) {
        _arrayX = arrayX;
        _arrayY = arrayY;
    }

    public void ExplodeAll(int arrayX, int arrayY) { }

    public void DetectWarningBlocks(int arrayX, int arrayY) { }

    public void Explosion() {
        for (int i = 0; i < _activeBlockList.GetBlockListSize(); i++)
        {
            Explode(_activeBlockList.GetActiveList(i).GetBlockX(),_activeBlockList.GetActiveList(i).GetBlockY());
        }
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

    protected void Explode(int x, int y) {
        int presentBlockNumber;
        if (x >= 0 && x < _blockRow && y >= 0 && y < BLOCK_COLUMN) {
            presentBlockNumber = _blockArray[x][y];
            if (_blockArray[x][y] > 0)
            {

                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;
            }

            if (presentBlockNumber > _mineNum - 1 && presentBlockNumber != _mineNum + _toolNum) {
                _blockArray[x][y] = DEFAULT_NONE_BLOCK_TYPE;
                _toolList[presentBlockNumber - _mineNum].ExplodeAll(x,y);
            }
        }
    }
}
