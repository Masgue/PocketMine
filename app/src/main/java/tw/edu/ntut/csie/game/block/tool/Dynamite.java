package tw.edu.ntut.csie.game.block.tool;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by Johnson on 2017/4/7.
 */

public class Dynamite extends Tool {
    public Dynamite(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.dynamite);
    }

    @Override
    public void Active() {
        ExplodeAll(_arrayX, _arrayY);
    }

    @Override
    public void ExplodeAll(int arrayX, int arrayY) {
        _blockArray[arrayX][arrayY] = DEFAULT_NONE_BLOCK_TYPE;
        for (int i = 1; i <= 5; i++)
        {
            if (arrayY - i >= 0 && _blockArray[arrayX][arrayY - i] != 0)
                Explode(arrayX, arrayY - i);
            else
                break;
        }
        for (int i = 1; i <= 5; i++)
        {
            if (arrayY + i < BLOCK_COLUMN && _blockArray[arrayX][arrayY + i] != 0)
                Explode(arrayX, arrayY + i);
            else
                break;
        }
    }
}
