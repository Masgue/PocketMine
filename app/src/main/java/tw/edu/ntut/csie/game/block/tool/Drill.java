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
        _digPower = 4;
        SetDrillAnimation();
    }

    @Override
    public void Active() {
        DetectWarningBlocks(_arrayX, _arrayY);
        }

    @Override
    public void DetectWarningBlocks(int arrayX, int arrayY) {
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= _digPower; i++)
        {
            if (_blockArray[arrayX + i][arrayY] != 0)
                _activeBlockList.AddToList(arrayX + i, arrayY);
            else
                break;
        }
    }

    @Override
    public int ExplodeAll(int arrayX, int arrayY) {
        int score = 0;
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= _digPower; i++)
        {
            if (_blockArray[arrayX + i][arrayY] != 0)
                score += Explode(arrayX + i, arrayY);
            else
                break;
        }
        return score;
    }

    public void SetDrillAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.drill);
        //_blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(2);
    }
}
