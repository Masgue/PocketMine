package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Tool extends Block {
    protected static final int DEFAULT_NONE_BLOCK_TYPE = 10;

    public Tool(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int[][] blockArray, int multiArrayNumber) {
        super(blockType, arrayX, arrayY, viewHeight, picture, multiArrayNumber);
    }

    public void Active() {

    }
}
