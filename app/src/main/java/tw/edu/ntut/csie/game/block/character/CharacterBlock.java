package tw.edu.ntut.csie.game.block.character;

import tw.edu.ntut.csie.game.block.Block;

/**
 * Created by ChenKeng on 2017/3/22.
 */

public class CharacterBlock extends Block {

    public CharacterBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
    }

    @Override
    public int GetPoints() {
        return -1;
    }

    @Override
    public int GetDurability() {
        return -1;
    }
}
