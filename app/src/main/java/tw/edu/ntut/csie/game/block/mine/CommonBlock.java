package tw.edu.ntut.csie.game.block.mine;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class CommonBlock extends Block {

    public CommonBlock(int blockType, int arrayX, int arrayY, int viewHeight, MovingBitmap picture, int multiArrayNum) {
        super(blockType, arrayX, arrayY, viewHeight, picture, multiArrayNum);
    }

    public int GetPoints() {
       switch (_blockType) {
           case 3:
               return 1;
           case 4:
               return 2;
           case 5:
               return 3;
           case 6:
               return 5;
           case 7:
               return 10;
           default:
               return 0;
       }
    }

    public int GetDurability() {
        switch (_blockType) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 3;
            default:
                return 1;
        }
    }
}
