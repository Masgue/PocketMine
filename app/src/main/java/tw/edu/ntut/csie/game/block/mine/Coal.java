package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Coal extends CommonBlock {
    private static final int COAL_POINTS = 1;
    private static final int COAL_CONSUME = 1;

    public Coal(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        //_picture = new MovingBitmap(R.drawable.block4_coal);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return COAL_POINTS;
    }

    @Override
    public int GetDurability() {
        return COAL_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block4_coal);
        _blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(1);
    }
}
