package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.character.CharacterBlock;
import tw.edu.ntut.csie.game.block.mine.CommonBlock;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private static final int NUMBER_OF_BLOCK_TYPE = 8;
    private static final int BLOCK_ROW = 15;
    private static final int BLOCK_COLUMN = 6;
    private static final int MOVING_VIEW_SPEED = 3;
    private static final int DIGIT_LENGTH = 18;
    private static final int DEFAULT_SCORE = 0;
    private static final int DEFAULT_DURABILITY = 50;
    private static final int DEFAULT_CHARACTER_TYPE = 100;
    private static final int DEFAULT_NONE_BLOCK_TYPE = 10;

    private MovingBitmap[] _digitNumberList;
    private MovingBitmap[] _MineList;
    private int [][] _blockArray;
    private int [][] _blockArrayTwo;
    private int [] _blockSpawningRate;
    private int _movingViewHeight;
    private int _score;
    private int _durability;
    private MovingBitmap _firstCharacter;
    private MovingBitmap _unvisibleBlock;
    private int firstCharacterX, firstCharacterY;
    private int _floor;
    private boolean _isPaused;
    private int _multiArrayNumber;

    private List<tw.edu.ntut.csie.game.Observer> _observers;

    public GameMap() {
        LoadMovingBitMap();
        _blockArray = new int [BLOCK_ROW][BLOCK_COLUMN];
        _blockArrayTwo = new int [BLOCK_ROW][BLOCK_COLUMN];
        _firstCharacter = new MovingBitmap(R.drawable.android_green_60x60);
        _unvisibleBlock = new MovingBitmap(R.drawable.block_unvisable);
        _blockSpawningRate = new int[]{1, 25, 10, 25,  20, 15, 10, 5};
        _movingViewHeight = 0;
        _score = DEFAULT_SCORE;
        _durability = DEFAULT_DURABILITY;
        GenerateRandomBlockArray(_blockArray, _blockSpawningRate);
        ChangeBlockAppearingRate();

        _floor = 0;
        _isPaused = false;
        _multiArrayNumber = 0;

        _observers = new ArrayList<tw.edu.ntut.csie.game.Observer>();
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
        _MineList = new MovingBitmap[]{
                new MovingBitmap(R.drawable.block0_unbreakable),
                new MovingBitmap(R.drawable.block1_hit_once),
                new MovingBitmap(R.drawable.block2_hit_twice),
                new MovingBitmap(R.drawable.block3_coal),
                new MovingBitmap(R.drawable.block4_gold),
                new MovingBitmap(R.drawable.block5_iron),
                new MovingBitmap(R.drawable.block6_diamond),
                new MovingBitmap(R.drawable.block7_ruby)};
    }

    @Override
    public void release() {
        for (MovingBitmap movingBitmap : _digitNumberList) {
            movingBitmap.release();
        }
        for (MovingBitmap movingBitmap : _MineList) {
            movingBitmap.release();
        }
    }

    @Override
    public void move() {
        if (!_isPaused) {
            if (_durability == 0)
                _movingViewHeight += 3 * MOVING_VIEW_SPEED;
            else
                _movingViewHeight += MOVING_VIEW_SPEED;
        }
    }

    @Override
    public void show() {
        if ( _movingViewHeight >= 60 * (_floor + 3) + 160)
            notifyAllObservers();
        showBlocks(_blockArray, _multiArrayNumber);
        showBlocks(_blockArrayTwo, _multiArrayNumber + 1);
        showScores();
        showDurability();
    }

    public void ResetAllBlock(int touchX, int touchY) {
        ResetBlock(touchX, touchY, _blockArray);
        ResetBlock(touchX, touchY, _blockArrayTwo);
    }

    private void ResetBlock(int touchX, int touchY, int[][] blockArray) {
        if (!_isPaused) {
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

                        if (checkVisible(i, j, blockArray))
                        {
                            if (blockArray[i][j] == 0)      //unbreakable block
                            {
                                _durability--;
                            }
                            else
                            {
                                CommonBlock blocks;
                                if (blockArray[i][j] >= 0 && blockArray[i][j] < 8)
                                {
                                    blocks = new CommonBlock(blockArray[i][j], (i + _multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _MineList[blockArray[i][j]]);
                                    _score += blocks.GetPoints();
                                    _durability -= blocks.GetDurability();
                                    if (_durability < 0)
                                        _durability = 0;
                                }
                                blockArray[firstCharacterX][firstCharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                                blockArray[i][j] = DEFAULT_CHARACTER_TYPE;
                                if (i >= _floor)
                                {
                                    _floor = i;
                                }
                                breakpoint = 1;
                                break;
                            }
                        }
                    }
                }
                if (breakpoint == 1)
                    break;
            }
        }
    }

//    public boolean gameOver()
//    {
//        if ( _movingViewHeight >= 60 * (_floor + 3) + 160)
//            return true;
//        return false;
//    }

    public int GetScore()
    {
        return _score;
    }

    private void showBlocks(int[][] blockArray, int multiArrayNumber)
    {
        if ((BLOCK_ROW * 60 * (multiArrayNumber + 1) + 160) - _movingViewHeight < 3)
        {
            if (multiArrayNumber % 2 == 0)
            {
                GenerateRandomBlockArray(_blockArrayTwo, _blockSpawningRate);
            }
            else
            {
                GenerateRandomBlockArray(_blockArray, _blockSpawningRate);
            }
            multiArrayNumber++;
            _multiArrayNumber = multiArrayNumber;
        }

        for (int i = 0; i < BLOCK_ROW; i++)
        {
            for (int j = 0; j < BLOCK_COLUMN; j++)
            {
                if (blockArray[i][j] >= 0 && blockArray[i][j] < 8)
                {
                    changeAroundVisible(i, j, multiArrayNumber, blockArray);
                    //CommonBlock commonBlock = new CommonBlock(_blockArray[i][j], i, j, _movingViewHeight, _MineList[_blockArray[i][j]]);
                    //commonBlock.show();
                }
                else if (blockArray[i][j] == DEFAULT_CHARACTER_TYPE)
                {
                    CharacterBlock characterBlock = new CharacterBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _firstCharacter, 0);
                    characterBlock.show();
                    firstCharacterX = i;
                    firstCharacterY = j;
                }
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
            _digitNumberList[9].setLocation(0,190 - 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 - 1 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + 1 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
        }
        else
        {
            _digitNumberList[units].setLocation(0, 190 - 2 * DIGIT_LENGTH);
            _digitNumberList[units].show();
            if (_score >= 10)
            {
                _digitNumberList[tens].setLocation(0,190 - 1 * DIGIT_LENGTH);
                _digitNumberList[tens].show();
                if (_score >= 100)
                {
                    _digitNumberList[hundreds].setLocation(0, 190 + 1 * DIGIT_LENGTH);
                    _digitNumberList[hundreds].show();
                    if (_score >= 1000)
                    {
                        _digitNumberList[thousands].setLocation(0, 190 + 2 * DIGIT_LENGTH);
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
        _blockSpawningRate[1] = 400;
    }

    private void GenerateRandomBlockArray(int[][] blockArray, int[] blockSpawningRate) {
        Random rnd = new Random(System.currentTimeMillis());
        int blockType;
        int count;
        int sum = 0;

        for (blockType = 0; blockType < NUMBER_OF_BLOCK_TYPE; blockType++)
        {
            sum += blockSpawningRate[blockType];
        }

        int blockSpawningArray[] = new int[sum];
        sum = 0;

        for (blockType = 0; blockType < NUMBER_OF_BLOCK_TYPE; blockType++)
        {
            for (count = 0; count < blockSpawningRate[blockType]; count++)
            {
                blockSpawningArray[sum] = blockType;
                sum++;
            }
        }

        for (blockType = 0; blockType < BLOCK_ROW; blockType++)
        {
            for (count = 0; count < BLOCK_COLUMN; count++)
            {
                blockArray[blockType][count] = blockSpawningArray[rnd.nextInt(sum)];
            }
        }

        if (_movingViewHeight == 0) {
            for (int i = 0; i < BLOCK_COLUMN; i++)
            {
                blockArray[0][i] = DEFAULT_NONE_BLOCK_TYPE;
            }
            blockArray[0][5] = DEFAULT_CHARACTER_TYPE;
        }

    }

    private void changeAroundVisible(int i, int j, int multiArrayNumber, int[][] blockArray) {
        if (i < BLOCK_ROW - 1 && (blockArray[i + 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i + 1][j] == DEFAULT_CHARACTER_TYPE))
        {
            CommonBlock commonBlock = new CommonBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _MineList[blockArray[i][j]]);
            commonBlock.show();
        }
        else if (i > 0 && (blockArray[i - 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i - 1][j] == DEFAULT_CHARACTER_TYPE))
        {
            CommonBlock commonBlock = new CommonBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _MineList[blockArray[i][j]]);
            commonBlock.show();
        }
        else if (j < BLOCK_COLUMN - 1 && (blockArray[i][j + 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j + 1] == DEFAULT_CHARACTER_TYPE))
        {
            CommonBlock commonBlock = new CommonBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _MineList[blockArray[i][j]]);
            commonBlock.show();
        }
        else if (j > 0 && (blockArray[i][j - 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j - 1] == DEFAULT_CHARACTER_TYPE))
        {
            CommonBlock commonBlock = new CommonBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _MineList[blockArray[i][j]]);
            commonBlock.show();
        }
        else
        {
            CommonBlock commonBlock = new CommonBlock(blockArray[i][j], (i + multiArrayNumber * BLOCK_ROW), j, _movingViewHeight, _unvisibleBlock);
            commonBlock.show();
        }
    }

    public void SetPause(boolean isPaused) {
        _isPaused = isPaused;
    }

    private boolean checkVisible(int i, int j, int[][] blockArray) {
        if (i < BLOCK_ROW - 1 && (blockArray[i + 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i + 1][j] == DEFAULT_CHARACTER_TYPE))
            return true;
        else if (i > 0 && (blockArray[i - 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i - 1][j] == DEFAULT_CHARACTER_TYPE))
            return true;
        else if (j < BLOCK_COLUMN - 1 && (blockArray[i][j + 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j + 1] == DEFAULT_CHARACTER_TYPE))
            return true;
        else if (j > 0 && (blockArray[i][j - 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j - 1] == DEFAULT_CHARACTER_TYPE))
            return true;
        else
            return false;
    }

    public void attach(tw.edu.ntut.csie.game.Observer observer){
        _observers.add(observer);
    }

    public void notifyAllObservers(){
        for (tw.edu.ntut.csie.game.Observer observer : _observers) {
            observer.update();
        }
    }
}
