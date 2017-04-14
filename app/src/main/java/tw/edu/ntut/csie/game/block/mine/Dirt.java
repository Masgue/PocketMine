package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Dirt extends CommonBlock {
    private static final int DIRT_POINTS = 0;
    private static final int DIRT_CONSUME = 1;

    public Dirt(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block2_dirt);
    }

    @Override
    public int GetPoints() {
        return DIRT_POINTS;
    }

    @Override
    public int GetDurability() {
        return DIRT_CONSUME;
    }
}
