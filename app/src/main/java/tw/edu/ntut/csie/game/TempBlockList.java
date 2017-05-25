package tw.edu.ntut.csie.game;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.block.ActiveBlocks;

/**
 * Created by ChenKeng on 2017/5/19.
 */

public class TempBlockList {
    private ArrayList<ActiveBlocks> _blocks;

    public TempBlockList() {
        _blocks = new ArrayList<ActiveBlocks>();
    }

    public void AddToList(int blockX, int blockY) {
        _blocks.add(new ActiveBlocks(blockX, blockY));
    }

    public ActiveBlocks GetLastActiveList() {
        return _blocks.get(_blocks.size() - 1);
    }

    public void RemoveBlockList() {
        _blocks.remove(_blocks.size() - 1);
    }

    public int GetBlockListSize() {
        return _blocks.size();
    }

    public ActiveBlocks GetActiveList(int i) {
        return _blocks.get(i);
    }
}
