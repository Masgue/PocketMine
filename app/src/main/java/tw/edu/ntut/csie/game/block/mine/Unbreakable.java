package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/4/19.
 */

public class Unbreakable extends CommonBlock {
        private static final int UNBREAKABLE_POINTS = 0;
        private static final int UNBREAKABLE_CONSUME = 1;

        public Unbreakable(int blockType, int arrayX, int arrayY, int viewHeight) {
            super(blockType, arrayX, arrayY, viewHeight);
            SetAnimation();
        }

        @Override
        public int GetPoints() {
            return UNBREAKABLE_POINTS;
        }

        @Override
        public int GetDurability() {
            return UNBREAKABLE_CONSUME;
        }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block1_unbreakable);
        _blockAnimation.setDelay(1);
    }
}
