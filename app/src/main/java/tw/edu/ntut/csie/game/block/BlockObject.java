package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public interface BlockObject {
    public void CalculateXY(int viewHeight);

    public int GetPoints();

    public int GetDurability();
}
