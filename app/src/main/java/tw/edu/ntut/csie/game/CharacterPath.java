package tw.edu.ntut.csie.game;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.block.ActiveBlocks;

/**
 * Created by ChenKeng on 2017/5/24.
 */

public class CharacterPath {
    private final int BLOCK_COLUMN = 6;
    private final int DEFAULT_NONE_BLOCK_TYPE = -1;
    private int _blockRow;
    private int[][] _blockArray;
    private int _endX = 1, _endY = 1;
    private ArrayList<ActiveBlocks> _pathList;
    private boolean _findPath = false;
    private int _choice;

    public CharacterPath(int blockRow, int[][] blockArray) {
        _blockRow = blockRow;
        _blockArray = new int[_blockRow][BLOCK_COLUMN];
//        _blockArray = blockArray;
        _pathList = new ArrayList<ActiveBlocks>();
    }

    public ArrayList<ActiveBlocks> CharacterGo(int[][] maze, int startX, int startY, int endX, int endY) {
        _pathList.clear();
        SetChoice(startX,startY, endX, endY);
        SetEnd(endX, endY);
        SetMaze(maze);
        _findPath = false;
        visit(startX, startY);
        return _pathList;
    }

    private void SetMaze(int[][] blockArray) {
        for (int i = 0; i < _blockRow; i++)
        {
            for (int j = 0; j < BLOCK_COLUMN; j++)
            {
                if (blockArray[i][j] == DEFAULT_NONE_BLOCK_TYPE)
                    _blockArray[i][j] = 0;
                else
                    _blockArray[i][j] = 2;
            }
        }
        _blockArray[_endX][_endY] = 0;
    }


    private void SetEnd(int endX, int endY) {
        _endX = endX;
        _endY = endY;
    }

    private void visit(int i, int j) {
        SetChoice(i, j, _endX, _endY);
        _blockArray[i][j] = 1;
        _pathList.add(new ActiveBlocks(i,j));
        if (i != _endX || j != _endY)
        {
            JudgeFirst(i, j);
            if (_findPath == false)
            {
                _blockArray[i][j] = 0;
                _pathList.remove(_pathList.size() - 1);
            }
        }
        else
        {
            _findPath = true;
//            for (ActiveBlocks a : _pathList) {
//                System.out.println(a.GetBlockX() + "  " + a.GetBlockY());
//            }
//            System.out.println("Done");
        }
    }

    private void JudgeFirst(int i, int j) {
        switch (_choice)
        {
            case 1:  //目的地於左下
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                break;
            case 2:  //目的地於下
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                break;
            case 3:  //目的地於右下
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                break;
            case 4:  //目的地於右
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                break;
            case 5:  //目的地於右上
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                break;
            case 6:  //目的地於上
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                break;
            case 7:  //目的地於左上
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                break;
            case 8:  //目的地於左
                if(j != BLOCK_COLUMN - 1 && _blockArray[i][j+1] == 0 && _findPath == false) //左
                    visit(i, j+1);
                if(i != _blockRow - 1 && _blockArray[i+1][j] == 0 && _findPath == false) //下
                    visit(i+1, j);
                if(i != 0 && _blockArray[i-1][j] == 0 && _findPath == false) //上
                    visit(i-1, j);
                if(j != 0 && _blockArray[i][j-1] == 0 && _findPath == false) //右
                    visit(i, j-1);
                break;
            default:
                break;
        }
    }

    private void SetChoice(int CharacterX, int CharacterY, int arrayX, int arrayY) {
        if (CharacterX < arrayX && CharacterY < arrayY) //目的地於左下
            _choice = 1;
        else if (CharacterX < arrayX && CharacterY == arrayY)  //目的地於下
            _choice = 2;
        else if (CharacterX < arrayX && CharacterY > arrayY) //目的地於右下
            _choice = 3;
       else if (CharacterX == arrayX && CharacterY > arrayY) //目的地於右
            _choice = 4;
        else if (CharacterX > arrayX && CharacterY > arrayY) //目的地於右上
            _choice = 5;
        else if (CharacterX > arrayX && CharacterY == arrayY)  //目的地於上
            _choice = 6;
        else if (CharacterX > arrayX && CharacterY < arrayY)  //目的地於左上
            _choice = 7;
        else if (CharacterX == arrayX && CharacterY < arrayY)  //目的地於左
            _choice = 8;
    }
}
