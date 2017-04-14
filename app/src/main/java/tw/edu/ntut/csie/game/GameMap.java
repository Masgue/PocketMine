package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.block.BlockObject;
import tw.edu.ntut.csie.game.block.character.CharacterBlock;
import tw.edu.ntut.csie.game.block.mine.CommonBlock;
import tw.edu.ntut.csie.game.block.tool.Bomb;
import tw.edu.ntut.csie.game.core.MovingBitmap;

import java.util.ArrayList;
import java.util.List;

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
    private MovingBitmap[] _mineList;
    private MovingBitmap[] _toolList;
    private int[][] _blockArray;
    // private int[] _blockSpawningRate;
    private int _movingViewHeight;
    private int _score;
    private int _durability;
    private int CharacterX, CharacterY;
    private int _floor;
    private boolean _isPaused;
    private GeneratingBlocks _generatingBlocks;

    private List<tw.edu.ntut.csie.game.Observer> _observers;

    public GameMap() {
        LoadMovingBitMap();
        // _blockArray = new int[BLOCK_ROW][BLOCK_COLUMN];
        // _blockSpawningRate = new int[]{0, 1, 25, 10, 25,  20, 15, 10, 5};
        _movingViewHeight = 0;
        _score = DEFAULT_SCORE;
        _durability = DEFAULT_DURABILITY;
        // GenerateRandomBlockArray(_blockArray, _blockSpawningRate);
        _generatingBlocks = new GeneratingBlocks(BLOCK_ROW);
        _blockArray = _generatingBlocks.GenerateMap();

        _floor = 0;
        _isPaused = false;

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
        _characterList = new MovingBitmap[]{new MovingBitmap(R.drawable.android_green_60x60)};
        _mineList = new MovingBitmap[]{new MovingBitmap(R.drawable.block0_invisible),
                new MovingBitmap(R.drawable.block1_unbreakable),
                new MovingBitmap(R.drawable.block2_dirt),
                new MovingBitmap(R.drawable.block3_stone),
                new MovingBitmap(R.drawable.block4_coal),
                new MovingBitmap(R.drawable.block5_gold),
                new MovingBitmap(R.drawable.block6_iron),
                new MovingBitmap(R.drawable.block7_diamond),
                new MovingBitmap(R.drawable.block8_ruby)};
        _toolList = new MovingBitmap[]{new MovingBitmap(R.drawable.digit_8)};
    }

    @Override
    public void release() {
        for (MovingBitmap movingBitmap : _digitNumberList) {
            movingBitmap.release();
        }
        for (MovingBitmap movingBitmap : _mineList) {
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
        ShowMap();
        showScores();
        showDurability();
    }

    public void ResetAllBlock(int touchX, int touchY) {
        ResetBlock(touchX, touchY, _blockArray);
    }

    private void ResetBlock(int touchX, int touchY, int[][] blockArray) {
        if (!_isPaused) {
            if (_durability != 0) {
                if (touchX > (160 - _movingViewHeight)) {
                    int x = touchX, y = touchY;
                    int arrayX = 0, arrayY = 0;

                    while ((x - 160 + _movingViewHeight) > 60) {
                        arrayX++;
                        x -= 60;
                    }

                    if (arrayX < BLOCK_ROW) {
                        while (y > 70) {
                            arrayY++;
                            y -= 60;
                        }

                        if(isVisible(arrayX, arrayY, blockArray)) {
                            DigBlock(arrayX, arrayY, blockArray);

                            if (arrayX >= _floor) {
                                _floor = arrayX;
                            }
                        }
                    }
                }
            }
        }
    }

    private void DigBlock(int arrayX, int arrayY, int[][] blockArray) {
        if(blockArray[arrayX][arrayY] == DEFAULT_NONE_BLOCK_TYPE) {
            blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
            blockArray[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
        }
        else if (blockArray[arrayX][arrayY] == DEFAULT_TOOL_TYPE) {
            Bomb bomb = new Bomb(blockArray[arrayX][arrayY], arrayX, arrayY, _movingViewHeight, blockArray);
            bomb.Active();
            blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
            blockArray[arrayX][arrayY] = DEFAULT_CHARACTER_TYPE;
        }
        else if (blockArray[arrayX][arrayY] > 0) {
            CommonBlock blocks = new CommonBlock(blockArray[arrayX][arrayY], arrayX, arrayY, _movingViewHeight);
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
    private void ShowMap() {
        ShowArray(_blockArray);
    }

    private void ShowArray(int[][] blockArray) {
        ShowBlocks(blockArray);
    }

    private void ShowBlocks(int[][] blockArray) {
        int amount = _mineList.length;
        BlockObject block;

        for (int i = 0; i < BLOCK_ROW; i++) {
            for (int j = 0; j < BLOCK_COLUMN; j++) {
                if (isVisible(i, j, blockArray)) {
                    if (blockArray[i][j] >= 0 && blockArray[i][j] < amount) {
                        block = new CommonBlock(_blockArray[i][j], i, j, _movingViewHeight);
                        block.show();
                    }
                    else if (blockArray[i][j] == DEFAULT_CHARACTER_TYPE) {
                        block = new CharacterBlock(blockArray[i][j], i, j, _movingViewHeight);
                        block.show();
                        CharacterX = i;
                        CharacterY = j;
                    }
                    else if (blockArray[i][j] == DEFAULT_TOOL_TYPE) {
                        block = new Bomb(blockArray[i][j], i, j, _movingViewHeight, blockArray);
                        block.show();
                    }
                }
                else {
                    block = new CommonBlock(_blockArray[i][j], i, j, _movingViewHeight);
                    block.show();
                }
            }
        }

//        //用來看陣列有沒有互換，但是第三格不能挖
//        for (int i = 1; i < BLOCK_ROW; i += 2) {
//            block = new CommonBlock(_blockArray[i][3], i, 3, _movingViewHeight, _mineList[_blockArray[i][3]], multiArrayNumber);
//            block.show();
//        }
    }

    private void showScores() {
        int thousands = _score / 1000 % 10;
        int hundreds = _score / 100 % 10;
        int tens = _score / 10 % 10;
        int units = _score % 10;
        if (_score > 10000)
        {
            _digitNumberList[9].setLocation(0,190 - 1 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + 1 * DIGIT_LENGTH);
            _digitNumberList[9].show();
            _digitNumberList[9].setLocation(0, 190 + 2 * DIGIT_LENGTH);
            _digitNumberList[9].show();
        }
        else
        {
            _digitNumberList[units].setLocation(0, 190 - 1 * DIGIT_LENGTH);
            _digitNumberList[units].show();
            if (_score >= 10)
            {
                _digitNumberList[tens].setLocation(0,190);
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
            observer.update();
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
