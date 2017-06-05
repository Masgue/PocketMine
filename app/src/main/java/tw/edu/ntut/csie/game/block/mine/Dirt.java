package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Dirt extends CommonBlock {
    private static final int DIRT_POINTS = 0;
    private static final int DIRT_CONSUME = 1;

    public Dirt(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return DIRT_POINTS;
    }

    @Override
    public int GetDurability() {
        return DIRT_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block2_dirt);
        _blockAnimation.setDelay(1);
    }
}
