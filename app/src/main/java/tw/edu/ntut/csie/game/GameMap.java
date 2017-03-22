package tw.edu.ntut.csie.game;

import java.util.logging.Level;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import java.util.Random;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private MovingBitmap background;
    private MovingBitmap digit_0 = new MovingBitmap(R.drawable.digit_0);
    private MovingBitmap digit_1 = new MovingBitmap(R.drawable.digit_1);
    private MovingBitmap digit_2 = new MovingBitmap(R.drawable.digit_2);
    private MovingBitmap digit_3 = new MovingBitmap(R.drawable.digit_3);
    private MovingBitmap digit_4 = new MovingBitmap(R.drawable.digit_4);
    private MovingBitmap digit_5 = new MovingBitmap(R.drawable.digit_5);
    private MovingBitmap digit_6 = new MovingBitmap(R.drawable.digit_6);
    private MovingBitmap digit_7 = new MovingBitmap(R.drawable.digit_7);
    private MovingBitmap digit_8 = new MovingBitmap(R.drawable.digit_8);
    private MovingBitmap digit_9 = new MovingBitmap(R.drawable.digit_9);
    private MovingBitmap[] digits = {digit_0, digit_1, digit_2, digit_3, digit_4, digit_5, digit_6, digit_7, digit_8, digit_9};
    private int MovingViewHeight;
    //private int [][] blockArray = {{1,2,3,4,5,6}, {7,8,7,6,5,4}, {3,2,1,2,1,2}, {2,1,2,1,2,1},
     //                            {1,2,1,2,1,2}, {2,1,2,1,2,1}, {1,2,1,2,1,2}, {2,1,2,1,2,1} };
    private int blockRow = 30, blockCol = 6;
    private int [][] blockArray = new int [blockRow][blockCol];

    private int points;
    private int LeastHits;
    private int digit_length = 18;
    private int blocksTypeAmount = 8;

    private int [] blockAppearingRate = {30, 25, 10, 25,  20, 15, 10, 5};


    public GameMap() {
        background = new MovingBitmap(R.drawable.background);
        MovingViewHeight = points = 0;
        LeastHits = GetTotalHits();
        ChangeBlockAppearingRate();
        GernerateRandomBlockArray();
    }

    @Override
    public void release() {
        background.release();
        digit_0.release();
        digit_1.release();
        digit_2.release();
        digit_3.release();
        digit_4.release();
        digit_5.release();
        digit_6.release();
        digit_7.release();
        digit_8.release();
        digit_9.release();
        background = null;
        digit_0 = null;
        digit_1 = null;
        digit_2 = null;
        digit_3 = null;
        digit_4 = null;
        digit_5 = null;
        digit_6 = null;
        digit_7 = null;
        digit_8 = null;
        digit_9 = null;
    }

    @Override
    public void move() {
        MovingViewHeight += 10;
    }

    @Override
    public void show() {
        //background.show();
        showBlocks();
        showPoints();
        showLeastHitTimes();

    }

    public void ResetBlock(int touchX, int touchY) {
        int breakpoint = 0;
        for (int i = 0; i < blockRow; i++)
        {
            for (int j = 0; j < blockCol; j++)
            {
                if ((touchX > 160 + 60 * i - MovingViewHeight) && (touchX < 160 + 60 * (i + 1) - MovingViewHeight)
                        && (touchY > 10 + 60 * j) && (touchY < 10 + 60 * (j + 1)))
                {
                    if (LeastHits == 0)
                    {
                        breakpoint = 1;
                        break;
                    }
                    LeastHits--;
                    if (blockArray[i][j] != 3)      //unbreakable block
                    {
                        Block blocks;
                        if (blockArray[i][j] > 0 && blockArray[i][j] < 9)
                        {
                            blocks = new CommonBlock(blockArray[i][j], i, j, MovingViewHeight);
                            points += blocks.GetPoints();
                        }
                        blockArray[i][j] = 0;
                        breakpoint = 1;
                        break;
                    }
                }
            }
            if (breakpoint == 1)
                break;
        }
    }

    private void showBlocks()
    {
        for (int i = 0; i < blockRow; i++)
        {
            for (int j = 0; j < blockCol; j++)
            {
                //Block blocks;
                if (blockArray[i][j] > 0 && blockArray[i][j] < 9)
                {
                    CommonBlock blocks = new CommonBlock(blockArray[i][j], i, j, MovingViewHeight);
                    blocks.show();
                }
                else
                {
                    //blocks = new Block(blockArray[i][j], i, j, MovingViewHeight);
                }
                //blocks.show();
            }
        }
    }

    private void showPoints() {
        int thousands = points / 1000 % 10;
        int hundreds = points / 100 % 10;
        int tens = points / 10 % 10;
        int units = points % 10;
        if (points > 10000)
        {
            digits[9].setLocation(0,0);
            digits[9].show();
            digits[9].setLocation(0, digit_length);
            digits[9].show();
            digits[9].setLocation(0, 2 * digit_length);
            digits[9].show();
            digits[9].setLocation(0, 3 * digit_length);
            digits[9].show();
        }
        else
        {
            digits[units].setLocation(0, 0);
            digits[units].show();
            if (points >= 10)
            {
                digits[tens].setLocation(0,digit_length);
                digits[tens].show();
                if (points >= 100)
                {
                    digits[hundreds].setLocation(0, 2 * digit_length);
                    digits[hundreds].show();
                    if (points >= 1000)
                    {
                        digits[thousands].setLocation(0, 3 * digit_length);
                        digits[thousands].show();
                    }
                }
            }
        }
    }

    private int GetTotalHits() {
        return 15;
    }

    private void showLeastHitTimes()
    {
        int thousands = LeastHits / 1000 % 10;
        int hundreds = LeastHits / 100 % 10;
        int tens = LeastHits / 10 % 10;
        int units = LeastHits % 10;
        if (LeastHits > 10000)
        {
            digits[9].setLocation(0,380 - 4 * digit_length);
            digits[9].show();
            digits[9].setLocation(0, 380 - 3 * digit_length);
            digits[9].show();
            digits[9].setLocation(0, 380 - 2 * digit_length);
            digits[9].show();
            digits[9].setLocation(0, 380 - digit_length);
            digits[9].show();
        }
        else
        {
            digits[units].setLocation(0,380 - 4 * digit_length);
            digits[units].show();
            if (LeastHits >= 10)
            {
                digits[tens].setLocation(0, 380 - 3 * digit_length);
                digits[tens].show();
                if (LeastHits >= 100)
                {
                    digits[hundreds].setLocation(0, 380 - 2 * digit_length);
                    digits[hundreds].show();
                    if (LeastHits >= 1000)
                    {
                        digits[thousands].setLocation(0, 380 - digit_length);
                        digits[thousands].show();
                    }
                }
            }
        }
    }

    private void ChangeBlockAppearingRate() {
        blockAppearingRate[0] = 40;
    }

    private void GernerateRandomBlockArray() {
        int sum = 0;
        for (int i = 0; i < blocksTypeAmount; i++)
        {
            sum += blockAppearingRate[i];
        }

        int array[] = new int[sum];
        sum = 0;

        for (int i = 0; i < blocksTypeAmount ; i++)
        {
            for (int j = 0; j < blockAppearingRate[i]; j++)
            {
                array[sum] = i;
                sum++;
            }
        }

        for (int i = 0; i < blockRow; i++)
        {
            for (int j = 0; j < blockCol; j++)
            {
                Random rnd = new Random();
                blockArray[i][j] = array[rnd.nextInt(sum)];
            }
        }
    }
}
//System.currentTimeMillis()
