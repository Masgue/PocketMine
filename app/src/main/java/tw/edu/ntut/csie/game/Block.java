package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class Block implements GameObject {
    private MovingBitmap B_hit_once;
    private MovingBitmap B_hit_twice;
    private int blockType;
    private int arrayX;
    private int arrayY;
    private int viewHeight;
    private int x, y;

    public Block(int blockType, int arrayX, int arrayY, int viewHeight) {
        B_hit_once = new MovingBitmap(R.drawable.block_hit_once);
        B_hit_twice = new MovingBitmap(R.drawable.block_hit_twice);
        this.blockType = blockType;
        this.arrayX = arrayX;
        this.arrayY = arrayY;
        this.viewHeight = viewHeight;
    }

    @Override
    public void release() {
        B_hit_once.release();
        B_hit_once.release();
        B_hit_twice = null;
        B_hit_twice = null;
    }
    @Override
    public void move() { }

    @Override
    public void show() {
       for (int i = 0; i < 8; i++)
       {
           for (int j = 0; j < 6; j++)
           {
               if (blockType == 1) {
                   caculate();
                   B_hit_once.setLocation(x, y);
                   B_hit_once.show();
               } else if (blockType == 2) {
                   caculate();
                   B_hit_twice.setLocation(x, y);
                   B_hit_twice.show();
               }
           }
       }
    }

    public void caculate() {
        y = 10 + 60 * arrayY;
        x = 160 + 60 * arrayX - viewHeight;
    }
}
