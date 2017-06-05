package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class Stone extends CommonBlock {
    private static final int STONE_POINTS = 0;
    private static final int STONE_CONSUME = 2;

    public Stone(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return STONE_POINTS;
    }

    @Override
    public int GetDurability() {
        return STONE_CONSUME;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block3_stone);
        _blockAnimation.setDelay(1);
    }
}
