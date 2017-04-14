package tw.edu.ntut.csie.game;

import java.util.ArrayList;

/**
 * Created by ChenKeng on 2017/4/14.
 */

public class GeneratingBlocks {

    private ArrayList<ArrayListBlock> _mineBlockList;
    private ArrayList<ArrayListBlock> _toolBlockList;
    private ArrayList<ArrayListBlock> _characterBlockList;

    public GeneratingBlocks() {}

    private void AddAllBlocksToList() {
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
        ArrayListBlock arrayListBlock;
        arrayListBlock = new ArrayListBlock(0, 0, "digit_8");
        _toolBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(1, 1 ,"digit_6");
    }
    private void AddCharacterBlocks() {
        ArrayListBlock arrayListBlock;
        arrayListBlock = new ArrayListBlock(0, 0, "digit_8");
        _toolBlockList.add(arrayListBlock);
        arrayListBlock.SetBlock(1, 1 ,"digit_6");
    }

}
