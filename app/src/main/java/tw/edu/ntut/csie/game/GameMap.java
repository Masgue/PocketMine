package tw.edu.ntut.csie.game;

import tw.edu.ntut.csie.game.block.Explosion;
import tw.edu.ntut.csie.game.block.Invisible;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.core.MovingBitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenKeng on 2017/3/16.
 */

public class GameMap implements GameObject {
    private static final int BLOCK_ROW = 50;
    private static final int BLOCK_COLUMN = 6;
    private static final int MOVING_VIEW_SPEED = 5;
    private static final int DIGIT_LENGTH = 18;
    private static final int DEFAULT_SCORE = 0;
    private static final int DEFAULT_DURABILITY = 50;
    private static final int DEFAULT_NONE_BLOCK_TYPE = -1;

    private MovingBitmap[] _digitNumberList;
    private int[][] _blockArray;
    private int _score;
    private int _durability;
    private int CharacterX = 0, CharacterY = 5;
    private int _floor;
    private boolean _isPaused;
    private GeneratingBlocks _generatingBlocks;
    private int _characterNum;
    private Invisible _invisible;
    private Explosion _explosion;
    private int _timer = 0;
    private boolean _timerSwitch = false;
    private int toolX, toolY, _toolType;
    private ArrayList<CardAttributes> _cardAttributes = new ArrayList<CardAttributes>();

    private List<tw.edu.ntut.csie.game.Observer> _observers;

    public GameMap(ArrayList<CardAttributes> cardAttributes) {
        _cardAttributes = cardAttributes;
        _score = DEFAULT_SCORE;
        SetDurability();
        _generatingBlocks = new GeneratingBlocks(BLOCK_ROW, _cardAttributes);
        _blockArray = _generatingBlocks.GetBlocksArray();
        LoadMovingBitMap();
        _characterNum = _generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize();
        _floor = 0;
        _isPaused = false;

        _observers = new ArrayList<tw.edu.ntut.csie.game.Observer>();
        _invisible = new Invisible(0,0,0,0);
        _explosion = new Explosion(0,0,0,0);
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
    }

    @Override
    public void release() {
        for (MovingBitmap movingBitmap : _digitNumberList) {
            movingBitmap.release();
        }
    }

    @Override
    public void move() {
        if (!_isPaused) {
            _explosion.GetAnimation().move();
            if (_durability == 0)
                _generatingBlocks.SetMovingViewHeight(_generatingBlocks.GetMovingViewHeight() + 3 *  MOVING_VIEW_SPEED);
            else
                _generatingBlocks.SetMovingViewHeight(_generatingBlocks.GetMovingViewHeight() +  MOVING_VIEW_SPEED);
        ExplodeTimer();
//            BlockAnimationMove();
        }
    }

    @Override
    public void show() {
        if ( _generatingBlocks.GetMovingViewHeight() >= 60 * (_floor + 3) + 160)
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
                if (touchX > (160 - _generatingBlocks.GetMovingViewHeight())) {
                    int x = touchX, y = touchY;
                    int arrayX = 0, arrayY = 0;

                    while ((x - 160 + _generatingBlocks.GetMovingViewHeight()) > 60) {
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
            blockArray[arrayX][arrayY] =_characterNum;
            CharacterX = arrayX;
            CharacterY = arrayY;
        }
        else if (blockArray[arrayX][arrayY] >= 0 && blockArray[arrayX][arrayY] < _generatingBlocks.GetMineBlocksArraySize()) {
            _score += _generatingBlocks.GetPoints(arrayX, arrayY);
            _durability -= _generatingBlocks.GetDurability(arrayX, arrayY);

            if (_durability < 0)
                _durability = 0;

            if (blockArray[arrayX][arrayY] >= 1) {
                blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
                blockArray[arrayX][arrayY] = _characterNum;
                CharacterX = arrayX;
                CharacterY = arrayY;
            }
        }
        else if (blockArray[arrayX][arrayY] > 0 && blockArray[arrayX][arrayY] < _generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize()) {
            _toolType = blockArray[arrayX][arrayY] - _generatingBlocks.GetMineBlocksArraySize();
            _generatingBlocks.ActiveTool(arrayX, arrayY);
            blockArray[CharacterX][CharacterY] = DEFAULT_NONE_BLOCK_TYPE;
            CharacterX = arrayX;
            CharacterY = arrayY;
            toolX = arrayX;
            toolY = arrayY;
            for (int k = 0; k <  _generatingBlocks.GetActiveBlockList().GetBlockListSize(); k++)
            {
                int x = _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockX();
                int y = _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockY();
                if (blockArray[x][y] != 0)
                {
                    blockArray[_generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockX()][_generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockY()] += (_generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize() + 2);
                }
            }
            if ( _generatingBlocks.GetActiveBlockList().GetBlockListSize() != 0)
            {
                _timerSwitch = true;
            }
            else
            {
                _timerSwitch = false;
            }
            blockArray[arrayX][arrayY] = _characterNum;
        }
    }
    private void ShowMap() {
        ShowArray(_blockArray);
    }

    private void ShowArray(int[][] blockArray) {
        ShowBlocks(blockArray);
    }

    private void ShowBlocks(int[][] blockArray) {
        for (int i = 0; i < BLOCK_ROW; i++)
        {
            for (int j = 0; j < BLOCK_COLUMN; j++)
            {
                if (isVisible(i, j, blockArray)) {
                    if (_blockArray[i][j] != DEFAULT_NONE_BLOCK_TYPE)
                    {
                        if (_blockArray[i][j] < _generatingBlocks.GetMineBlocksArraySize()) {
                            _generatingBlocks.ShowMineBlock(i, j);
                        }
                        else if (_blockArray[i][j] < _generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize()){
                            _generatingBlocks.ShowToolBlock(i, j);
                        }
                        else if (_blockArray[i][j] == _generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize()){
                            _generatingBlocks.ShowCharacterBlock(i, j);
                           }
                        else
                        {
                            _explosion.SetBlock(i, j, _generatingBlocks.GetMovingViewHeight());
                            _explosion.show();
                        }
                    }
                }
                else
                {
                    _invisible.SetBlock(i, j, _generatingBlocks.GetMovingViewHeight());
                    _invisible.show();
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
            _digitNumberList[9].setLocation(0,190 - DIGIT_LENGTH);
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

    public void SetPause(boolean isPaused) {
        _isPaused = isPaused;
    }

    private boolean isVisible(int i, int j, int[][] blockArray) {
        if (i < BLOCK_ROW - 1 && (blockArray[i + 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i + 1][j] >= _characterNum))
            return true;
        else if (i > 0 && (blockArray[i - 1][j] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i - 1][j] >= _characterNum))
            return true;
        else if (j < BLOCK_COLUMN - 1 && (blockArray[i][j + 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j + 1] >= _characterNum))
            return true;
        else if (j > 0 && (blockArray[i][j - 1] == DEFAULT_NONE_BLOCK_TYPE || blockArray[i][j - 1] >= _characterNum))
            return true;
        else if (_blockArray[i][j] ==  _characterNum)
            return true;
        else if (_generatingBlocks.GetActiveBlockList().GetBlockListSize() != 0)
        {
            for (int k = 0; k < _generatingBlocks.GetActiveBlockList().GetBlockListSize(); k++)
            {
                if (i == _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockX() && j == _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockY())
                    return true;
            }
        }
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

    private void BlockAnimationMove() {
        for (int j = 0; j < _generatingBlocks.GetActiveBlockList().GetBlockListSize(); j++)
        {
            for (int i = 0; i < _generatingBlocks.GetMineBlocksArraySize(); i++)
            {
                _generatingBlocks.GetMineList()[i].GetAnimation().move();
            }

            for (int i = 0; i < _generatingBlocks.GetToolBlocksArraySize(); i++)
            {
                _generatingBlocks.GetToolList()[i].GetAnimation().move();
            }
        }
    }

    private void ExplodeTimer() {
        if (_timerSwitch == true)
        {
            _timer++;
            if (_timer >= 10)
            {
                for (int k = 0; k <  _generatingBlocks.GetActiveBlockList().GetBlockListSize(); k++)
                {
                    int x = _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockX();
                    int y = _generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockY();
                    if (_blockArray[x][y] != 0)
                    {
                        _blockArray[_generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockX()][_generatingBlocks.GetActiveBlockList().GetActiveList(k).GetBlockY()] -= (_generatingBlocks.GetMineBlocksArraySize() + _generatingBlocks.GetToolBlocksArraySize() + 2);
                    }
                }
                _timer = 0;
                _timerSwitch = false;
                _generatingBlocks.GetToolList()[_toolType].Explosion();
                while(_generatingBlocks.GetActiveBlockList().GetBlockListSize() != 0)
                {
                    _generatingBlocks.GetActiveBlockList().RemoveBlockList();
                }
               _blockArray[CharacterX][CharacterY] = _characterNum;
            }
        }
    }

    private void SetDurability() {
        _durability = DEFAULT_DURABILITY;
        for (int i = 0; i < 3; i++)
        {
            int type = _cardAttributes.get(i).GetBlockType();
            if (type == -1)
            {
                _durability += _cardAttributes.get(i).GetDurability();
            }
        }
    }
}
