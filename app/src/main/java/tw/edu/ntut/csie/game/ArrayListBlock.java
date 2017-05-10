package tw.edu.ntut.csie.game;

/**
 * Created by ChenKeng on 2017/4/14.
 */

public class ArrayListBlock {

    private int _blockType;
    private int _spawningRate;
    private String _name;

    public ArrayListBlock(int arrayNum, int spawningRate, String name) {
        _blockType = arrayNum;
        _spawningRate = spawningRate;
        _name = name;
    }

    public int GetBlockArrayNum() {return _blockType;}
    public int GetBlockSpawningRate() {return _spawningRate;}
    public String GetBlockName() {return _name;}

    public void SetSpawningRate(int spawningRate) {
        _spawningRate = spawningRate;
    }
}


