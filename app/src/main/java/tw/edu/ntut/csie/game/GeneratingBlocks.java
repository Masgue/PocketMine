package tw.edu.ntut.csie.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ChenKeng on 2017/4/14.
 */

public class GeneratingBlocks {
    private static final int BLOCK_COLUMN = 6;
    private static final int DEFAULT_NONE_BLOCK_TYPE = -1;

    private ArrayList<ArrayListBlock> _mineBlockList;
    private ArrayList<ArrayListBlock> _toolBlockList;
    private ArrayList<ArrayListBlock> _characterBlockList;
    private int _blockRow;

    public GeneratingBlocks(int blockRow) {
        _blockRow = blockRow;
        AddMineBlocks();
        AddToolBlocks();
        AddCharacterBlocks();
    }

    private void AddMineBlocks() {
        _mineBlockList.add(new ArrayListBlock(0, 1 ,"block1_unbreakable"));
        _mineBlockList.add(new ArrayListBlock(1, 25, "block2_dirt"));
        _mineBlockList.add(new ArrayListBlock(2, 10, "block3_stone"));
        _mineBlockList.add(new ArrayListBlock(3, 25, "block4_coal"));
        _mineBlockList.add(new ArrayListBlock(4, 20, "block5_gold"));
        _mineBlockList.add(new ArrayListBlock(5, 15, "block6_iron"));
        _mineBlockList.add(new ArrayListBlock(6, 10, "block7_diamond"));
        _mineBlockList.add(new ArrayListBlock(7, 5, "block8_ruby"));
        //_mineBlockList.add(new ArrayListBlock(0, 0, "block0_invisible"));
    }

    private void AddToolBlocks() {
        _toolBlockList.add(new ArrayListBlock(0, 0, "digit_6"));
        //_toolBlockList.add(new ArrayListBlock(1, 1, "digit_8"));
    }
    private void AddCharacterBlocks() {
        _characterBlockList.add(new ArrayListBlock(0, 0, "digit_8"));
    }

    public int[][] GenerateMap() {
        int[][] blockArray = new int[_blockRow][BLOCK_COLUMN];
        GenerateRandomBlockArray(blockArray);
        return blockArray;
    }

    private void ChangeBlockAppearingRate(int blockType, int spawningRate) {

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
//            blockArray[1][3] = DEFAULT_TOOL_TYPE;
//            blockArray[5][2] = DEFAULT_TOOL_TYPE;
//            blockArray[6][3] = DEFAULT_TOOL_TYPE;
    }

    private int ChooseCharacter() {
        int character = 0;
        return _mineBlockList.size() + _toolBlockList.size() + _characterBlockList.get(character).GetBlockArrayNum();
    }

    public int GetMineBlocksArraySize() {   return _mineBlockList.size();}

    public int GetToolBlocksArraySize() {   return _toolBlockList.size();}
}
