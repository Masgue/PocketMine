package tw.edu.ntut.csie.game;

import javax.sql.CommonDataSource;

/**
 * Created by ChenKeng on 2017/3/19.
 */

public class CommonBlock extends Block {
    //private int commonBlocType;

    public CommonBlock(int blockType, int arrayX, int arrayY, int viewHeight) {
        super(blockType, arrayX, arrayY, viewHeight);
    }

    CommonBlock(int blockType, int arrayX, int arrayY, int viewHeight, int commonBlockType) {
        super(blockType, arrayX, arrayY, viewHeight);
        //this.commonBlocType = commonBlockType;
    }

    public int GetPoints() {
       switch (blockType) {
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

    public int GetHitLoss() {
        switch (blockType) {
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
