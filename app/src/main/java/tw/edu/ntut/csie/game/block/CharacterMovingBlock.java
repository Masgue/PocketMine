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
        _blockAnimation.addFrame(R.drawable.character_right);
        _blockAnimation.setDelay(1);
    }

    public void ResetAnimation(int way) {
        switch (way)
        {
            case 0:
                _blockAnimation.release();
                _blockAnimation = new Animation();
                _blockAnimation.addFrame(R.drawable.character_left);
                _blockAnimation.addFrame(R.drawable.character_moving_left);
                _blockAnimation.setDelay(0);
                break;
            case 1:
                _blockAnimation.release();
                _blockAnimation = new Animation();
                _blockAnimation.addFrame(R.drawable.character_right);
                _blockAnimation.addFrame(R.drawable.character_moving_right);
                _blockAnimation.setDelay(0);
            default:
                break;
        }
    }

    public void SetRepeating(boolean repeat) {
        _blockAnimation.setRepeating(repeat);
    }
}
