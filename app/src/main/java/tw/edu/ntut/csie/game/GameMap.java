package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.character.CharacterBlock;
import tw.edu.ntut.csie.game.block.mine.CommonBlock;
import tw.edu.ntut.csie.game.core.MovingBitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private static final int BLOCK_ROW = 15;
    private static final int BLOCK_COLUMN = 6;
    private static final int MOVING_VIEW_SPEED = 5;
    private static final int DIGIT_LENGTH = 18;
    private static final int DEFAULT_SCORE = 0;
    private static final int DEFAULT_DURABILITY = 50;
    private static final int DEFAULT_CHARACTER_TYPE = -10;
    private static final int DEFAULT_NONE_BLOCK_TYPE = -1;
    private static final int DEFAULT_TOOL_TYPE = -200;
    private static final int INVISIBLE = 0;

    private MovingBitmap[] _digitNumberList;
    private MovingBitmap[] _characterList;
    private MovingBitmap[] _MineList;
    private int [][] _blockArray;
    private int [][] _blockArrayTwo;
    private int [] _blockSpawningRate;
    private int _movingViewHeight;
    private int _score;
    private int _durability;
    private int CharacterX, CharacterY;
    private int _floor;
    private boolean _isPaused;
    private int _multiArrayNumber;
    private int _firstTimeGeneratingTwoMaps;

    private List<tw.edu.ntut.csie.game.Observer> _observers;

    public GameMap() {
        LoadMovingBitMap();
        _blockArray = new int [BLOCK_ROW][BLOCK_COLUMN];
        _blockArrayTwo = new int [BLOCK_ROW][BLOCK_COLUMN];
        _blockSpawningRate = new int[]{0, 1, 25, 10, 25,  20, 15, 10, 5};
        _movingViewHeight = 0;
        _score = DEFAULT_SCORE;
        _durability = DEFAULT_DURABILITY;
        GenerateRandomBlockArray(_blockArray, _blockSpawningRate);
        GenerateRandomBlockArray(_blockArrayTwo, _blockSpawningRate);

        _floor = 0;
        _isPaused = false;
        _multiArrayNumber = 0;

        _observers = new ArrayList<tw.edu.ntut.csie.game.Observer>();
        _firstTimeGeneratingTwoMaps = 0;
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
        _characterList = new MovingBitmap[]{new MovingBitmap(R.drawable.android_green_60x60)};
        _MineList = new MovingBitmap[]{new MovingBitmap(R.drawable.block0_invisible),
                new MovingBitmap(R.drawable.block1_unbreakable),
                new MovingBitmap(R.drawable.block2_dirt),
                new MovingBitmap(R.drawable.block3_stone),
                new MovingBitmap(R.drawable.block4_coal),
                new MovingBitmap(R.drawable.block5_gold),
                new MovingBitmap(R.drawable.block6_iron),
                new MovingBitmap(R.drawable.block7_diamond),
                new MovingBitmap(R.drawable.block8_ruby)};
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
        ShowMap(_multiArrayNumber);
        showScores();
        showDurability();
    }

    public void ResetAllBlock(int touchX, int touchY) {
        if (!_isPaused) {
            if (_durability != 0) {
                if (touchX > (160 - _movingViewHeight)) {
                    int x = touchX, y = touchY;
                    int arrayX = 0, arrayY = 0;

                    while ((x - 160 + _movingViewHeight) > 60) {
                        arrayX++;
                        x -= 60;
                    }

                    while (y > 70) {
                        arrayY++;
                        y -= 60;
                    }
                    if (arrayX % BLOCK_ROW == 0 && CharacterX == BLOCK_ROW - 1)
                        DigSwitchBlock(arrayX - arrayX / BLOCK_ROW * BLOCK_ROW, arrayY,_blockArray, _blockArrayTwo, _multiArrayNumber + 1);
                    else if (arrayX % BLOCK_ROW == BLOCK_ROW - 1 && CharacterX == 0)
                        DigSwitchBlock(arrayX - arrayX / BLOCK_ROW * BLOCK_ROW, arrayY, _blockArrayTwo,_blockArray, _multiArrayNumber + 1);
                    else if (arrayX / BLOCK_ROW % 2 == 1)
                        DigBlock(arrayX - arrayX / BLOCK_ROW * BLOCK_ROW, arrayY, _blockArrayTwo, _multiArrayNumber + 1);
                    else
                        DigBlock(arrayX - arrayX / BLOCK_ROW * BLOCK_ROW, arrayY, _blockArray, _multiArrayNumber);

                    if (arrayX >= _floor) {
                        _floor = arrayX;
                    }
                }
            }
        }
    }

    private void DigBlock(int arrayX, int arrayY, int[][] blockArray, int multiArrayNumber) {

        if (isVisible(arrayX, arrayY, blockArray)) {
            if(blockArray[arrayX][arrayY] == DEFAULT_NONE_BLOCK_TYPE) {
                blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                blockArray[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
            }
            else if (blockArray[arrayX][arrayY] > 0) {
                CommonBlock blocks = new CommonBlock(blockArray[arrayX][arrayY], arrayX, arrayY, _movingViewHeight, _MineList[blockArray[arrayX][arrayY]], multiArrayNumber);
                _score += blocks.GetPoints();
                _durability -= blocks.GetDurability();

                if (_durability < 0)
                    _durability = 0;

                if (blockArray[arrayX][arrayY] > 1) {
                    blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                    blockArray[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
                }
            }
        }
    }

    private void DigSwitchBlock(int arrayX, int arrayY, int[][] blockArray, int[][] blockArrayTwo, int multiArrayNumber) {

        if (isVisible(arrayX, arrayY, blockArrayTwo)) {
            if(blockArrayTwo[arrayX][arrayY] == DEFAULT_NONE_BLOCK_TYPE) {
                blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                blockArrayTwo[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
            }
            else if (blockArrayTwo[arrayX][arrayY] > 0) {
                CommonBlock blocks = new CommonBlock(blockArrayTwo[arrayX][arrayY], arrayX, arrayY, _movingViewHeight, _MineList[blockArrayTwo[arrayX][arrayY]], multiArrayNumber);
                _score += blocks.GetPoints();
                _durability -= blocks.GetDurability();

                if (_durability < 0)
                    _durability = 0;

                if (blockArrayTwo[arrayX][arrayY] > 1) {
                    blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                    blockArrayTwo[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
                }
            }
        }
    }

    private void ShowMap(int multiArrayNumber) {
        SwapArray(multiArrayNumber);
        ShowArray(_blockArray, multiArrayNumber);
        ShowArray(_blockArrayTwo, multiArrayNumber + 1);
    }

    private void ShowArray(int[][] blockArray, int multiArrayNumber) {
        ShowBlocks(blockArray, multiArrayNumber);
    }

    private void ShowBlocks(int[][] blockArray, int multiArrayNumber) {
        int amount = _MineList.length;
        Block block;

        for (int i = 0; i < BLOCK_ROW; i++) {
            for (int j = 0; j < BLOCK_COLUMN; j++) {
                if (isVisible(i, j, blockArray)) {
                    if (blockArray[i][j] >= 0 && blockArray[i][j] < amount) {
                        block = new CommonBlock(blockArray[i][j], i, j, _movingViewHeight, _MineList[blockArray[i][j]], multiArrayNumber);
                        block.show();
                    }
                    else if (blockArray[i][j] == DEFAULT_CHARACTER_TYPE) {
                        block = new CharacterBlock(blockArray[i][j], i, j, _movingViewHeight, _characterList[0], multiArrayNumber);
                        block.show();
                        CharacterX = i;
                        CharacterY = j;
                    }
                }
                else {
                    block = new CommonBlock(blockArray[i][j], i, j, _movingViewHeight, _MineList[INVISIBLE], multiArrayNumber);
                    block.show();
                }
            }
        }

//        //用來看陣列有沒有互換，但是第三格不能挖
//        for (int i = 1; i < BLOCK_ROW; i += 2) {
//            block = new CommonBlock(_blockArray[i][3], i, 3, _movingViewHeight, _MineList[_blockArray[i][3]], multiArrayNumber);
//            block.show();
//        }
    }

    private void SwapArray(int multiArrayNumber) {
        if ((BLOCK_ROW * 60 * (multiArrayNumber + 1) + 160) - _movingViewHeight < 3) {
//            _blockArray = _blockArrayTwo;
//            _blockArrayTwo = new int[BLOCK_ROW][BLOCK_COLUMN];
//            GenerateRandomBlockArray(_blockArrayTwo, _blockSpawningRate);
            if (multiArrayNumber % 2 == 0) {
                ChangeBlockAppearingRate(400);
                GenerateRandomBlockArray(_blockArrayTwo, _blockSpawningRate);
            }
            else {
                ChangeBlockAppearingRate(1);
                GenerateRandomBlockArray(_blockArray, _blockSpawningRate);
            }
            _multiArrayNumber++;
        }
    }

    private void showScores() {
        int thousands = _score / 1000 % 10;
        int hundreds = _score / 100 % 10;
        int tens = _score / 10 % 10;
        int units = _score % 10;
        if (_score > 10000)
        {
            _digitNumberList[9].setLocation(0,190 -  DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
        }
        else
        {
            _digitNumberList[units].setLocation(0, 190 - DIGIT_LENGTH);
            _digitNumberList[units].show();
            if (_score >= 10)
            {
                _digitNumberList[tens].setLocation(0,190);
                _digitNumberList[tens].show();
                if (_score >= 100)
                {
                    _digitNumberList[hundreds].setLocation(0, 190 + DIGIT_LENGTH);
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

    private void ChangeBlockAppearingRate(int demo) {
        _blockSpawningRate[1] = demo;
    }

    private void GenerateRandomBlockArray(int[][] blockArray, int[] blockSpawningRate) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] blockSpawningArray;
        int amount = _MineList.length;
        int blockType;
        int count;
        int sum = 0;

        for (blockType = 0; blockType < amount; blockType++) {
            sum += blockSpawningRate[blockType];
        }

        blockSpawningArray = new int[sum];
        sum = 0;

        for (blockType = 0; blockType < amount; blockType++) {
            for (count = 0; count < blockSpawningRate[blockType]; count++)
            {
                blockSpawningArray[sum] = blockType;
                sum++;
            }
        }

        for (blockType = 0; blockType < BLOCK_ROW; blockType++) {
            for (count = 0; count < BLOCK_COLUMN; count++)
            {
                blockArray[blockType][count] = blockSpawningArray[rnd.nextInt(sum)];
            }
        }

        if (_firstTimeGeneratingTwoMaps == 0) {
            for (int i = 0; i < BLOCK_COLUMN; i++)
            {
                blockArray[0][i] = DEFAULT_NONE_BLOCK_TYPE;
            }
            blockArray[0][5] = DEFAULT_CHARACTER_TYPE;
            _firstTimeGeneratingTwoMaps = 1;
        }

        else if (_firstTimeGeneratingTwoMaps == 1)
        {
            for (int i = 0; i < BLOCK_COLUMN; i++)
            {
                //if (i % 2 == 0)
                blockArray[0][i] = DEFAULT_NONE_BLOCK_TYPE;
                //else
                //    blockArray[0][i] = 1;
            }
        }
    }

    public void SetPause(boolean isPaused) {
        _isPaused = isPaused;
    }

    private boolean isVisible(int i, int j, int[][] blockArray) {
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
//        return true;
    }

    public void attach(tw.edu.ntut.csie.game.Observer observer){
        _observers.add(observer);
    }

    public void notifyAllObservers(){
        for (tw.edu.ntut.csie.game.Observer observer : _observers) {
//            observer.update();
        }
    }

//    public int GetScore()
//    {
//        return _score;
//    }

//    private int GetTotalHits() {
//        return 15;
//    }
}
