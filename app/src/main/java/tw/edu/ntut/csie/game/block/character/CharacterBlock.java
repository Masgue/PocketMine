package tw.edu.ntut.csie.game.block.character;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/22.
 */

public class CharacterBlock extends Block {

    public CharacterBlock(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int multiArrayNum) {
        super(blockType, arrayX, arrayY, viewHeight, picture, multiArrayNum);
    }

    @Override
    public int GetPoints() {
        return -1;
    }
}
