package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Gold extends CommonBlock {
    private static final int GOLD_POINTS = 2;
    private static final int GOLD_CONSUME = 1;

    public Gold(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        //_picture = new MovingBitmap(R.drawable.block5_gold);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return GOLD_POINTS;
    }

    @Override
    public int GetDurability() {
        return GOLD_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block5_gold);
        _blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(2);
    }
}
