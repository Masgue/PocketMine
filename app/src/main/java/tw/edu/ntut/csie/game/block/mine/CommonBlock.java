package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class CommonBlock extends Block {

    public CommonBlock(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int multiArrayNumber) {
        super(blockType, arrayX, arrayY, viewHeight, picture, multiArrayNumber);
    }

    @Override
    public int GetPoints() {
       switch (_blockType) {
           case 4:
               return 1;
           case 5:
               return 2;
           case 6:
               return 3;
           case 7:
               return 5;
           case 8:
               return 10;
           default:
               return 0;
       }
    }

    @Override
    public int GetDurability() {
        switch (_blockType) {
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 1;
            case 7:
                return 2;
            case 8:
                return 3;
            default:
                return 1;
        }
    }
}
