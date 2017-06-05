package tw.edu.ntut.csie.game.block.BlockExplosion;

import tw.edu.ntut.csie.game.block.Block;

/**
 * Created by ChenKeng on 2017/6/4.
 */

public abstract class BlockExplosion extends Block {
    public BlockExplosion(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
    }
    @Override
    public int GetPoints() {
        return 0;
    }

    @Override
    public int GetDurability() {
        return 1;
    }
}
