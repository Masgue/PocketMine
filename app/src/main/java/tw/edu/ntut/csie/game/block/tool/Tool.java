package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.BlockObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public abstract class Tool extends Block{
    protected int[][] _blockArray;

    public Tool(int blockType, int arrayX, int arrayY, int viewHeight, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight);
        _blockArray = blockArray;
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
}
