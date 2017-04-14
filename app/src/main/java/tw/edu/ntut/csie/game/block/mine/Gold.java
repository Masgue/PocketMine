package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Gold extends CommonBlock {
    private static final int GOLD_POINTS = 2;
    private static final int GOLD_CONSUME = 1;

    public Gold(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block5_gold);
    }

    @Override
    public int GetPoints() {
        return GOLD_POINTS;
    }

    @Override
    public int GetDurability() {
        return GOLD_CONSUME;
    }
}
