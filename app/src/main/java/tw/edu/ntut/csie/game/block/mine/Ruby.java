package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Ruby extends CommonBlock {
    private static final int RUBY_POINTS = 10;
    private static final int RUBY_CONSUME = 3;

    public Ruby(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return RUBY_POINTS;
    }

    @Override
    public int GetDurability() {
        return RUBY_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block8_ruby);
        _blockAnimation.setDelay(1);
    }
}
