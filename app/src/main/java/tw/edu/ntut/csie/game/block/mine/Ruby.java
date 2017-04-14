package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Ruby extends CommonBlock {
    private static final int RUBY_POINTS = 10;
    private static final int RUBY_CONSUME = 3;

    public Ruby(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block8_ruby);
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
