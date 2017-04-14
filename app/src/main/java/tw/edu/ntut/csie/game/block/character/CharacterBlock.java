package tw.edu.ntut.csie.game.block.character;

import tw.edu.ntut.csie.game.block.BlockObject;

/**
 * Created by ChenKeng on 2017/3/22.
 */

public class CharacterBlock extends BlockObject {

    public CharacterBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
    }

    @Override
    public int GetPoints() {
        return -1;
    }
}
