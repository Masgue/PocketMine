package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Stone extends CommonBlock {
    private static final int STONE_POINTS = 0;
    private static final int STONE_CONSUME = 2;

    public Stone(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block3_stone);
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
