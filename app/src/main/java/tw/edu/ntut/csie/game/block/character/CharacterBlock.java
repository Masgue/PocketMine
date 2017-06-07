package tw.edu.ntut.csie.game.block.character;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/3/22.
 */

public class CharacterBlock extends Block {
    int _chosenWay;
    public CharacterBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _chosenWay = 0;
        SetAnimation();
    }

    @Override
    public int GetPoints() {
        return -1;
    }

    @Override
    public int GetDurability() {
        return -1;
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.character_right);
        _blockAnimation.setDelay(2);
    }

    public void ResetAnimation(int way) {
        switch (way)
        {
            case 0:
                _blockAnimation.release();
                _blockAnimation = new Animation();
                _blockAnimation.addFrame(R.drawable.character_left);
                _blockAnimation.setDelay(1);
                break;
            case 1:
                _blockAnimation.release();
                _blockAnimation = new Animation();
                _blockAnimation.addFrame(R.drawable.character_right);
                _blockAnimation.setDelay(1);
                break;
            case 2:
                _blockAnimation.release();
                _blockAnimation = new Animation();
                _blockAnimation.addFrame(R.drawable.android_green_60x60);
                _blockAnimation.setDelay(1);
                break;
            default:
                break;
        }
    }
}
