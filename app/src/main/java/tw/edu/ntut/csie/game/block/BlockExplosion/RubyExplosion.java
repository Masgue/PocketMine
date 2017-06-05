package tw.edu.ntut.csie.game.block.BlockExplosion;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by ChenKeng on 2017/6/4.
 */

public class RubyExplosion extends BlockExplosion {
    public RubyExplosion(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetAnimation();
    }

    @Override
    public void SetAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.block8_ruby);
        _blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(1);
        _blockAnimation.setRepeating(true);
    }
}
