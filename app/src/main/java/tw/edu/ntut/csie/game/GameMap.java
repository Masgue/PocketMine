package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private MovingBitmap background;
    private int MovingViewHeight;
    private int[][] blockArray = {{1,2,1,2,1,2}, {2,1,2,1,2,1}, {1,2,1,2,1,2}, {2,1,2,1,2,1},
                                  {1,2,1,2,1,2}, {2,1,2,1,2,1}, {1,2,1,2,1,2}, {2,1,2,1,2,1} };

    public GameMap() {
        background = new MovingBitmap(R.drawable.background);
        MovingViewHeight = 0;
    }

    @Override
    public void release() {
        background.release();
        background = null;
    }

    @Override
    public void move() {
        MovingViewHeight += 3;
    }

    @Override
    public void show() {
        background.show();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                Block blocks = new Block(blockArray[i][j], i, j, MovingViewHeight);
                blocks.show();
            }
        }
    }

    public void ResetBlock(int touchX, int touchY) {
        int breakpoint = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if ((touchX > 160 + 60 * i - MovingViewHeight) && (touchX < 160 + 60 * (i + 1) - MovingViewHeight)
                        && (touchY > 10 + 60 * j) && (touchY < 10 + 60 * (j + 1)))
                {
                    blockArray[i][j] = 0;
                    breakpoint = 1;
                    break;
                }
            }
            if (breakpoint == 1)
                break;
        }
    }
}
