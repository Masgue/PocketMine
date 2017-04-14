package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Diamond extends CommonBlock {
    private static final int DIAMOND_POINTS = 5;
    private static final int DIAMOND_CONSUME = 2;

    public Diamond(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block7_diamond);
    }

    @Override
    public int GetPoints() {
        return COAL_POINTS;
    }

    @Override
    public int GetDurability() {
        return COAL_CONSUME;
    }
}
