package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/6/7.
 */

public class BoxBlock extends Block {
    public BoxBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }
    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 0;
    }

    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.box);
        _blockAnimation.setDelay(1);
    }
}