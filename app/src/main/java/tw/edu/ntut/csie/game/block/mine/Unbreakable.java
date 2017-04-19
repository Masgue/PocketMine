package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/4/19.
 */

public class Unbreakable extends CommonBlock {
        private static final int UNBREAKABLE_POINTS = 0;
        private static final int UNBREAKABLE_CONSUME = 1;

        public Unbreakable(int blockType, int arrayX, int arrayY, int viewHeight) {
            super(blockType, arrayX, arrayY, viewHeight);
            _picture = new MovingBitmap(R.drawable.block1_unbreakable);
        }

        @Override
        public int GetPoints() {
            return UNBREAKABLE_POINTS;
        }

        @Override
        public int GetDurability() {
            return UNBREAKABLE_CONSUME;
        }
}
