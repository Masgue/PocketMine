package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class Block implements GameObject {
    private MovingBitmap block1;
    private MovingBitmap block2;
    private int blockType;
    private int arrayX;
    private int arrayY;
    private int x, y;

    public Block(int blockType, int arrayX, int arrayY) {
            block1 = new MovingBitmap(R.drawable.block1);
            block2 = new MovingBitmap(R.drawable.block2);
            this.blockType = blockType;
            this.arrayX = arrayX;
            this.arrayY = arrayY;
    }

    @Override
    public void release() {
        block1.release();
        block2.release();
        block1 = null;
        block2 = null;
    }

    @Override
    public void move() {}

    @Override
    public void show() {
       for (int i = 0; i < 8; i++)
       {
           for (int j = 0; j < 6; j++)
           {
               if (blockType == 1) {
                   caculate();
                   block1.setLocation(x, y);
                   block1.show();
               } else if (blockType == 2) {
                   caculate();
                   block2.setLocation(x, y);
                   block2.show();
               }
           }
       }
    }

    public void caculate() {
        y = 10 + 60 * arrayY;
        x = 160 + 60 * arrayX;
    }
}
