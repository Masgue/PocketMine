package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/5/25.
 */

public class CharacterMovingBlock extends Block {
    public CharacterMovingBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
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
//        _blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.addFrame(R.drawable.android_green_60x60);
        _blockAnimation.setDelay(1);
    }

    public void SetRepeating(boolean repeat) {
        _blockAnimation.setRepeating(repeat);
    }
}
