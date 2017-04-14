package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.BlockObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public abstract class CommonBlock extends Block{
    public CommonBlock(int blockType, int arrayX, int arrayY, int viewHeight, int[][] blockArray) {
        super(blockType, arrayX, arrayY, viewHeight);
    }
}
