package tw.edu.ntut.csie.game;

import java.util.ArrayList;
import java.util.Random;

import tw.edu.ntut.csie.game.block.ActiveBlockList;
import tw.edu.ntut.csie.game.block.Block;
import tw.edu.ntut.csie.game.block.character.CharacterBlock;
import tw.edu.ntut.csie.game.block.mine.Coal;
import tw.edu.ntut.csie.game.block.mine.CommonBlock;
import tw.edu.ntut.csie.game.block.mine.Diamond;
import tw.edu.ntut.csie.game.block.mine.Dirt;
import tw.edu.ntut.csie.game.block.mine.Gold;
import tw.edu.ntut.csie.game.block.mine.Iron;
import tw.edu.ntut.csie.game.block.mine.Ruby;
import tw.edu.ntut.csie.game.block.mine.Stone;
import tw.edu.ntut.csie.game.block.mine.Unbreakable;
import tw.edu.ntut.csie.game.block.tool.Bomb;
import tw.edu.ntut.csie.game.block.tool.Drill;
import tw.edu.ntut.csie.game.block.tool.Dynamite;
import tw.edu.ntut.csie.game.block.tool.Tool;
import tw.edu.ntut.csie.game.card.CardAttributes;

/**
 * Created by ChenKeng on 2017/4/14.
 */

public class GeneratingBlocks {
    private static final int BLOCK_COLUMN = 6;
    private static final int DEFAULT_NONE_BLOCK_TYPE = -1;

    private ArrayList<ArrayListBlock> _mineBlockList;
    private ArrayList<ArrayListBlock> _toolBlockList;
    private ArrayList<ArrayListBlock> _characterBlockList;
    private static int _blockRow;

    private int _movingViewHeight;
    private CommonBlock[] _mineList;
    private Tool[] _toolList;
    private CharacterBlock _character;
    private int[][] _blockArray;
    private static ActiveBlockList _activeBlockList;
    private ArrayList<CardAttributes> _cardAttributes  = new ArrayList<CardAttributes>();

    public GeneratingBlocks(int blockRow, ArrayList<CardAttributes> cardAttributes) {

        _cardAttributes = cardAttributes;
        _mineBlockList = new ArrayList<ArrayListBlock>();
        _toolBlockList = new ArrayList<ArrayListBlock>();
        _characterBlockList = new ArrayList<ArrayListBlock>();

        _blockRow = blockRow;
        AddMineBlocks();
        AddToolBlocks();
        AddCharacterBlocks();

        ChangeBlockSpawningRate();

        _mineList = new CommonBlock[_mineBlockList.size()];
        _toolList = new Tool[_toolBlockList.size()];

        _movingViewHeight = 0;
        _blockArray = new int[_blockRow][BLOCK_COLUMN];



        GenerateMap();
        _mineList = new CommonBlock[]{
                new Unbreakable(0,0,0,0),
                new Dirt(1,0,0,0),
                new Stone(2,0,0,0),
                new Coal(3,0,0,0),
                new Gold(4,0,0,0),
                new Iron(5,0,0,0),
                new Diamond(6,0,0,0),
                new Ruby(7,0,0,0)};
        _toolList =  new Tool[]{
                new Bomb(0,0,0,0),
                new Drill(1,0,0,0),
                new Dynamite(2,0,0,0)};
        _character = new CharacterBlock(0,0,0,0);

        SetToolList();
        SetBlockRow();
        SetBlockArray();
        SetMimeNum();
        SetToolNum();
        SetActiveBlockList();
    }

    private void AddMineBlocks() {
        _mineBlockList.add(new ArrayListBlock(0, 10 ,"block1_unbreakable"));
        _mineBlockList.add(new ArrayListBlock(1, 25, "block2_dirt"));
        _mineBlockList.add(new ArrayListBlock(2, 10, "block3_stone"));
        _mineBlockList.add(new ArrayListBlock(3, 25, "block4_coal"));
        _mineBlockList.add(new ArrayListBlock(4, 20, "block5_gold"));
        _mineBlockList.add(new ArrayListBlock(5, 15, "block6_iron"));
        _mineBlockList.add(new ArrayListBlock(6, 10, "block7_diamond"));
        _mineBlockList.add(new ArrayListBlock(7, 5, "block8_ruby"));
    }

    private void AddToolBlocks() {
        _toolBlockList.add(new ArrayListBlock(0, 10, "Bomb"));
        _toolBlockList.add(new ArrayListBlock(1, 10, "Drill"));
        _toolBlockList.add(new ArrayListBlock(2, 10, "Dynamite"));
    }
    private void AddCharacterBlocks() {
        _characterBlockList.add(new ArrayListBlock(0, 0, "digit_8"));
    }

    public void GenerateMap() {
        GenerateRandomBlockArray(_blockArray);
    }

    private void GenerateRandomBlockArray(int[][] blockArray) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] blockSpawningArray;
        int amount = _mineBlockList.size() + _toolBlockList.size();
        int blockType;
        int count;
        int sum = 0;

        for (blockType = 0; blockType < amount; blockType++) {
            if (blockType < _mineBlockList.size())
                sum += _mineBlockList.get(blockType).GetBlockSpawningRate();
            else
                sum += _toolBlockList.get(blockType - _mineBlockList.size()).GetBlockSpawningRate();
        }

        blockSpawningArray = new int[sum];
        sum = 0;

        for (blockType = 0; blockType < amount; blockType++) {
            if (blockType < _mineBlockList.size())
            {
                for (count = 0; count < _mineBlockList.get(blockType).GetBlockSpawningRate(); count++)
                {
                    blockSpawningArray[sum] = blockType;
                    sum++;
                }
            }
            else
            {
                for (count = 0; count < _toolBlockList.get(blockType - _mineBlockList.size()).GetBlockSpawningRate(); count++)
                {
                    blockSpawningArray[sum] = blockType;
                    sum++;
                }
            }
        }

        for (blockType = 0; blockType < _blockRow; blockType++) {
            for (count = 0; count < BLOCK_COLUMN; count++)
            {
                blockArray[blockType][count] = blockSpawningArray[rnd.nextInt(sum)];
            }
        }

        for (int i = 0; i < BLOCK_COLUMN; i++)
        {
            blockArray[0][i] = DEFAULT_NONE_BLOCK_TYPE;
        }

        blockArray[0][5] = ChooseCharacter();
        for (int i = 0; i < BLOCK_COLUMN; i++)
        {
            blockArray[_blockRow - 1][i] = 0;
        }
//        for (int i = 0 ; i < 6; i++)
//        {
//            blockArray[1][i] = _mineBlockList.size();
//        }
//        blockArray[2][3] = _mineBlockList.size();
//        blockArray[2][2] = _mineBlockList.size();
//        blockArray[3][3] =  _mineBlockList.size();
    }

    private int ChooseCharacter() {
        int character = 0;
        return _mineBlockList.size() + _toolBlockList.size() + _characterBlockList.get(character).GetBlockArrayNum();
    }
    public int[][] GetBlocksArray() {   return _blockArray;}

    public int GetMineBlocksArraySize() {   return _mineBlockList.size();}

    public int GetToolBlocksArraySize() {   return _toolBlockList.size();}

    public int GetMovingViewHeight() { return _movingViewHeight;}

    public void SetMovingViewHeight(int movingViewHeight) { _movingViewHeight = movingViewHeight;}

    public void ShowMineBlock(int i, int j) {
        _mineList[_blockArray[i][j]].SetBlock(i,j,_movingViewHeight);
        _mineList[_blockArray[i][j]].show();
    }

    public void ShowToolBlock(int i, int j) {
        _toolList[_blockArray[i][j] - GetMineBlocksArraySize()].SetBlock(i,j,_movingViewHeight);
        _toolList[_blockArray[i][j] - GetMineBlocksArraySize()].show();
    }

    public void ShowCharacterBlock(int i, int j) {
        _character.SetBlock(i,j,_movingViewHeight);
        _character.show();
    }

    public int GetPoints(int arrayX, int arrayY) {
        return _mineList[_blockArray[arrayX][arrayY]].GetPoints();
    }

    public int GetDurability(int arrayX, int arrayY) {
        return _mineList[_blockArray[arrayX][arrayY]].GetDurability();
    }

    public void ActiveTool(int arrayX, int arrayY) {
        _toolList[_blockArray[arrayX][arrayY] - GetMineBlocksArraySize()].SetBlockXY(arrayX,arrayY);
        _toolList[_blockArray[arrayX][arrayY] - GetMineBlocksArraySize()].Active();
    }

    private void SetToolList() {
        Tool.SetToolList(_toolList);
    }

    private void SetBlockArray() {
        Tool.SetBlockArray(_blockArray);
    }

    private void SetMimeNum() {
        Tool.SetMineNum(_mineBlockList.size());
    }

    private void SetToolNum() {
        Tool.SetToolNum(_toolBlockList.size());
    }

    private static void SetBlockRow() {
        Block.SetBlockRowNum(_blockRow);
    }

    private static void SetActiveBlockList() {
        _activeBlockList = new ActiveBlockList();
        Tool.SetActiveBlockList(_activeBlockList);
    }

    public Tool[] GetToolList() {
        return _toolList;
    }

    public CommonBlock[] GetMineList() {
        return _mineList;
    }

    public ActiveBlockList GetActiveBlockList() {return _activeBlockList;}

    public void RemoveActiveArray() {Tool.RemoveActiveArray();}

    private void ChangeBlockSpawningRate() {
        for (int i = 0; i < _cardAttributes.size(); i++)
        {
            int type = _cardAttributes.get(i).GetBlockType();
            if (type != -1)
            {
                if (type < _mineBlockList.size())
                {
                    ArrayListBlock mine = _mineBlockList.get(type);
                    mine.SetSpawningRate(_cardAttributes.get(i).GetBlockSpawningRate());
                    _mineBlockList.set(type, mine);
                }
                else if (type < _mineBlockList.size() + _toolBlockList.size())
                {
                    ArrayListBlock tool = _toolBlockList.get(type - _mineBlockList.size());
                    tool.SetSpawningRate(_cardAttributes.get(i).GetBlockSpawningRate());
                    _toolBlockList.set(type - _mineBlockList.size(), tool);
                }
            }
        }
    }
}
