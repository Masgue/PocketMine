package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.block.ActiveBlockList;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Bomb extends Tool {
    public Bomb(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        SetBombAnimation();
    }

    @Override
    public void Active() {
        DetectWarningBlocks(_arrayX, _arrayY);
    }

    @Override
    public void DetectWarningBlocks(int arrayX, int arrayY) {
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        if (arrayY == 0)
        {
            _activeBlockList.AddToList(arrayX - 1, arrayY);
            _activeBlockList.AddToList(arrayX + 1, arrayY);
            _activeBlockList.AddToList(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                _activeBlockList.AddToList(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                _activeBlockList.AddToList(arrayX + 1, arrayY + 1);
        }
        else if (arrayY == BLOCK_COLUMN - 1)
        {
            _activeBlockList.AddToList(arrayX - 1, arrayY);
            _activeBlockList.AddToList(arrayX + 1, arrayY);
            _activeBlockList.AddToList(arrayX, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                _activeBlockList.AddToList(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                _activeBlockList.AddToList(arrayX + 1, arrayY - 1);
        }
        else
        {
            _activeBlockList.AddToList(arrayX - 1, arrayY);
            _activeBlockList.AddToList(arrayX + 1, arrayY);
            _activeBlockList.AddToList(arrayX, arrayY - 1);
            _activeBlockList.AddToList(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                _activeBlockList.AddToList(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                _activeBlockList.AddToList(arrayX + 1, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                _activeBlockList.AddToList(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                _activeBlockList.AddToList(arrayX + 1, arrayY + 1);
        }
    }

    @Override
    public int ExplodeAll(int arrayX, int arrayY) {
        int score = 0;
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        if (arrayY == 0)
        {
            score += Explode(arrayX - 1, arrayY);
            score += Explode(arrayX + 1, arrayY);
            score += Explode(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                score += Explode(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                score += Explode(arrayX + 1, arrayY + 1);
        }
        else if (arrayY == BLOCK_COLUMN - 1)
        {
            score += Explode(arrayX - 1, arrayY);
            score += Explode(arrayX + 1, arrayY);
            score += Explode(arrayX, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                score += Explode(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                score += Explode(arrayX + 1, arrayY - 1);
        }
        else
        {
            score += Explode(arrayX - 1, arrayY);
            score += Explode(arrayX + 1, arrayY);
            score += Explode(arrayX, arrayY - 1);
            score += Explode(arrayX, arrayY + 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                score += Explode(arrayX - 1, arrayY - 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY - 1] != 0)
                score += Explode(arrayX + 1, arrayY - 1);
            if (_blockArray[arrayX - 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                score += Explode(arrayX - 1, arrayY + 1);
            if (_blockArray[arrayX + 1][arrayY] != 0 || _blockArray[arrayX][arrayY + 1] != 0)
                score += Explode(arrayX + 1, arrayY + 1);
        }
        return score;
    }

    @Override
    public int GetPoints() {
        return 0;
    }

    public void SetBombAnimation() {
        _blockAnimation = new Animation();
        _blockAnimation.addFrame(R.drawable.bomb);
        _blockAnimation.setDelay(0);
    }
}
