package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Coal extends CommonBlock {
    private static final int COAL_POINTS = 1;
    private static final int COAL_CONSUME = 1;

    public Coal(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block4_coal);
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
