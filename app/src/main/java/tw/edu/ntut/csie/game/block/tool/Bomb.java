package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Bomb extends Tool {
    public Bomb(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        //_picture = new MovingBitmap(R.drawable.bomb);
        SetBombAnimation();
    }

    @Override
    public void Active() {
        ExplodeAll(_arrayX, _arrayY);
    }

    @Override
    public void ExplodeAll(int arrayX, int arrayY) {
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        if (arrayY == 0)
        {
            Explode(arrayX - 1, arrayY);
            Explode(arrayX + 1, arrayY);
            Explode(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                Explode(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                Explode(arrayX + 1, arrayY + 1);
        }
        else if (arrayY == BLOCK_COLUMN - 1)
        {
            Explode(arrayX - 1, arrayY);
            Explode(arrayX + 1, arrayY);
            Explode(arrayX, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                Explode(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                Explode(arrayX + 1, arrayY - 1);
        }
        else
        {
            Explode(arrayX - 1, arrayY);
            Explode(arrayX + 1, arrayY);
            Explode(arrayX, arrayY - 1);
            Explode(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                Explode(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                Explode(arrayX + 1, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                Explode(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                Explode(arrayX + 1, arrayY + 1);
        }
    }

    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 1;
    }

    public void SetBombAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.bomb);
        //_blockAnimation.addFrame(R.drawable.blue);
        _blockAnimation.setDelay(2);
    }
}
