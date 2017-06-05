package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Diamond extends CommonBlock {
    private static final int DIAMOND_POINTS = 5;
    private static final int DIAMOND_CONSUME = 2;

    public Diamond(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return DIAMOND_POINTS;
    }

    @Override
    public int GetDurability() {
        return DIAMOND_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block7_diamond);
        _blockAnimation.setDelay(1);
    }
}
