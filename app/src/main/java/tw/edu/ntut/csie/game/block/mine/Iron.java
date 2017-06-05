package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Iron extends CommonBlock {
    private static final int IRON_POINTS = 3;
    private static final int IRON_CONSUME = 1;

    public Iron(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return IRON_POINTS;
    }

    @Override
    public int GetDurability() {
        return IRON_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block6_iron);
        _blockAnimation.setDelay(1);
    }
}
