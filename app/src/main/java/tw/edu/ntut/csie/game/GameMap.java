package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import java.util.Random;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private static final int NUMBER_OF_BLOCK_TYPE = 8;
    private static final int BLOCK_ROW = 30;
    private static final int BLOCK_COLUMN = 6;
    private static final int MOVING_VIEW_SPEED = 10;
    private static final int DIGIT_LENGTH = 18;
    private static final int DEFAULT_SCORE = 0;
    private static final int DEFAULT_DURABILITY = 15;

    private MovingBitmap[] _digitNumberList;
    private MovingBitmap[] _MineList;
    private MovingBitmap _background;
    private int [][] _blockArray;
    private int [] _blockSpawningRate;
    private int _movingViewHeight;
    private int _score;
    private int _durability;
    //private int [][] _blockArray = {{1,2,3,4,5,6}, {7,8,7,6,5,4}, {3,2,1,2,1,2}, {2,1,2,1,2,1},
     //                            {1,2,1,2,1,2}, {2,1,2,1,2,1}, {1,2,1,2,1,2}, {2,1,2,1,2,1} };


    public GameMap() {
        LoadMovingBitMap();
        _background = new MovingBitmap(R.drawable.background);
        _blockArray = new int [BLOCK_ROW][BLOCK_COLUMN];
        _blockSpawningRate = new int[]{30, 25, 10, 25,  20, 15, 10, 5};
        _movingViewHeight = 0;
        _score = DEFAULT_SCORE;
        _durability = DEFAULT_DURABILITY;
        ChangeBlockAppearingRate();
        GenerateRandomBlockArray();
    }

    private void LoadMovingBitMap() {
        _digitNumberList = new MovingBitmap[]{new MovingBitmap(R.drawable.digit_0),
                new MovingBitmap(R.drawable.digit_1),
                new MovingBitmap(R.drawable.digit_2),
                new MovingBitmap(R.drawable.digit_3),
                new MovingBitmap(R.drawable.digit_4),
                new MovingBitmap(R.drawable.digit_5),
                new MovingBitmap(R.drawable.digit_6),
                new MovingBitmap(R.drawable.digit_7),
                new MovingBitmap(R.drawable.digit_8),
                new MovingBitmap(R.drawable.digit_9)};
        _MineList = new MovingBitmap[]{new MovingBitmap(R.drawable.block1_hit_once),
                new MovingBitmap(R.drawable.block2_hit_twice),
                new MovingBitmap(R.drawable.block3_unbreakable),
                new MovingBitmap(R.drawable.block4_coal),
                new MovingBitmap(R.drawable.block5_gold),
                new MovingBitmap(R.drawable.block6_iron),
                new MovingBitmap(R.drawable.block7_diamond),
                new MovingBitmap(R.drawable.block8_ruby)};
    }

    @Override
    public void release() {
        _background.release();
        _background = null;
        for (int i = 0; i < _digitNumberList.length; i++) {
            _digitNumberList[i].release();
        }
        for (int i = 0; i < _MineList.length; i++) {
            _MineList[i].release();
        }
    }

    @Override
    public void move() {
        _movingViewHeight += MOVING_VIEW_SPEED;
    }

    @Override
    public void show() {
//        _background.show();
        showBlocks();
        showScores();
        showDurability();

    }

    public void ResetBlock(int touchX, int touchY) {
        int breakpoint = 0;
        for (int i = 0; i < BLOCK_ROW; i++)
        {
            for (int j = 0; j < BLOCK_COLUMN; j++)
            {
                if ((touchX > 160 + 60 * i - _movingViewHeight) && (touchX < 160 + 60 * (i + 1) - _movingViewHeight)
                        && (touchY > 10 + 60 * j) && (touchY < 10 + 60 * (j + 1)))
                {
                    if (_durability == 0)
                    {
                        breakpoint = 1;
                        break;
                    }
                    _durability--;
                    if (_blockArray[i][j] != 3)      //unbreakable block
                    {
                        Block blocks;
                        if (_blockArray[i][j] > 0 && _blockArray[i][j] < 9)
                        {
                            blocks = new CommonBlock(_blockArray[i][j], i, j, _movingViewHeight);
                            _score += blocks.GetPoints();
                        }
                        _blockArray[i][j] = 0;
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
        for (int i = 0; i < BLOCK_ROW; i++)
        {
            for (int j = 0; j < BLOCK_COLUMN; j++)
            {
                //Block blocks;
                if (_blockArray[i][j] > 0 && _blockArray[i][j] < 9)
                {
                    CommonBlock blocks = new CommonBlock(_blockArray[i][j], i, j, _movingViewHeight);
                    blocks.show();
                }
                else
                {
                    //blocks = new Block(_blockArray[i][j], i, j, _movingViewHeight);
                }
                //blocks.show();
            }
        }
    }

    private void showScores() {
        int thousands = _score / 1000 % 10;
        int hundreds = _score / 100 % 10;
        int tens = _score / 10 % 10;
        int units = _score % 10;
        if (_score > 10000)
        {
            _digitNumberList[9].setLocation(0,0);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 3 * DIGIT_LENGTH);
            _digitNumberList[9].show();
        }
        else
        {
            _digitNumberList[units].setLocation(0, 0);
            _digitNumberList[units].show();
            if (_score >= 10)
            {
                _digitNumberList[tens].setLocation(0,DIGIT_LENGTH);
                _digitNumberList[tens].show();
                if (_score >= 100)
                {
                    _digitNumberList[hundreds].setLocation(0, 2 * DIGIT_LENGTH);
                    _digitNumberList[hundreds].show();
                    if (_score >= 1000)
                    {
                        _digitNumberList[thousands].setLocation(0, 3 * DIGIT_LENGTH);
                        _digitNumberList[thousands].show();
                    }
                }
            }
        }
    }

//    private int GetTotalHits() {
//        return 15;
//    }

    private void showDurability()
    {
        int thousands = _durability / 1000 % 10;
        int hundreds = _durability / 100 % 10;
        int tens = _durability / 10 % 10;
        int units = _durability % 10;
        if (_durability > 10000)
        {
            _digitNumberList[9].setLocation(0,380 - 4 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 380 - 3 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 380 - 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 380 - DIGIT_LENGTH);
            _digitNumberList[9].show();
        }
        else
        {
            _digitNumberList[units].setLocation(0,380 - 4 * DIGIT_LENGTH);
            _digitNumberList[units].show();
            if (_durability >= 10)
            {
                _digitNumberList[tens].setLocation(0, 380 - 3 * DIGIT_LENGTH);
                _digitNumberList[tens].show();
                if (_durability >= 100)
                {
                    _digitNumberList[hundreds].setLocation(0, 380 - 2 * DIGIT_LENGTH);
                    _digitNumberList[hundreds].show();
                    if (_durability >= 1000)
                    {
                        _digitNumberList[thousands].setLocation(0, 380 - DIGIT_LENGTH);
                        _digitNumberList[thousands].show();
                    }
                }
            }
        }
    }

    private void ChangeBlockAppearingRate() {
        _blockSpawningRate[0] = 40;
    }

    private void GenerateRandomBlockArray() {
        Random rnd = new Random();
        int blockType;
        int count;
        int sum = 0;

        for (blockType = 0; blockType < NUMBER_OF_BLOCK_TYPE; blockType++)
        {
            sum += _blockSpawningRate[blockType];
        }

        int blockSpawningArray[] = new int[sum];
        sum = 0;

        for (blockType = 0; blockType < NUMBER_OF_BLOCK_TYPE; blockType++)
        {
            for (count = 0; count < _blockSpawningRate[blockType]; count++)
            {
                blockSpawningArray[sum] = blockType;
                sum++;
            }
        }

        for (blockType = 0; blockType < BLOCK_ROW; blockType++)
        {
            for (count = 0; count < BLOCK_COLUMN; count++)
            {
                _blockArray[blockType][count] = blockSpawningArray[rnd.nextInt(sum)];
            }
        }
    }
}
//System.currentTimeMillis()
