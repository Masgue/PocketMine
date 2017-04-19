package tw.edu.ntut.csie.game.block;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/4/19.
 */

public class Invisible extends Block {
    public Invisible(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
        _picture = new MovingBitmap(R.drawable.block0_invisible);
    }
    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 0;
    }
}
