package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Drill extends Tool {
    protected int _digPower;

    public Drill(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        //_picture = new MovingBitmap(R.drawable.drill);
        _digPower = 4;
        SetBombAnimation();
    }

    @Override
    public void Active() {
        ExplodeAll(_arrayX, _arrayY);
        }

    @Override
    public void ExplodeAll(int arrayX, int arrayY) {
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= _digPower; i++)
        {
            if (_blockArray[arrayX + i][arrayY] != 0)
                Explode(arrayX + i, arrayY);
            else
                break;
        }
    }

    public void SetBombAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.drill);
        //_blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(2);
    }
}
