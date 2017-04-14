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
        ArrayListBlock arrayListBlock;
        arrayListBlock = new ArrayListBlock(0, 0, "block0_invisible");
        _mineBlockList.add(arrayListBlock);
       arrayListBlock.SetBlock(1, 1 ,"block1_unbreakable");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(2, 25, "block2_dirt");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(3, 10, "block3_stone");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(4, 25, "block4_coal");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(5, 20, "block5_gold");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(6, 15, "block6_iron");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(7, 10, "block7_diamond");
        _mineBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(8,5, "block8_ruby");
        _mineBlockList.add(arrayListBlock);
    }

    private void AddToolBlocks() {
        _toolBlockList.add(new ArrayListBlock(0, 0, "digit_8"));
        _toolBlockList.add(new ArrayListBlock(1, 1, "digit_6"));
    }
    private void AddCharacterBlocks() {
        _characterBlockList.add(new ArrayListBlock(0, 0, "digit_8"));
    }

    public int[][] GenerateMap(int ) {
        int[][] blockArray = new int[_blockRow][BLOCK_COLUMN];


    }

    private void ChangeBlockAppearingRate(int blockType, int spawningRate) {

    }

    private void GenerateRandomBlockArray(int[][] blockArray, int[] blockSpawningRate) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] blockSpawningArray;
        int amount = _mineList.length;
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

//            blockArray[0][5] = DEFAULT_CHARACTER_TYPE;
//            blockArray[1][3] = DEFAULT_TOOL_TYPE;
//            blockArray[5][2] = DEFAULT_TOOL_TYPE;
//            blockArray[6][3] = DEFAULT_TOOL_TYPE;
    }
}
