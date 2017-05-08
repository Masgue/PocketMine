package tw.edu.ntut.csie.game.block;

import java.util.ArrayList;

/**
 * Created by ChenKeng on 2017/5/5.
 */

public class ActiveBlockList {
    private ArrayList<ActiveBlocks> _activeBlocks;
    public ActiveBlockList() {
        _activeBlocks = new ArrayList<ActiveBlocks>();
    }

    public void AddToList(int blockX, int blockY) {
       _activeBlocks.add(new ActiveBlocks(blockX,blockY));
    }

    public ActiveBlocks GetLastActiveList() {
        return  _activeBlocks.get(_activeBlocks.size() - 1);
    }

    public void RemoveBlockList() {
        _activeBlocks.remove(_activeBlocks.size() - 1);
    }

    public int GetBlockListSize() {
        return _activeBlocks.size();
    }

    public ActiveBlocks GetActiveList(int i) {
        return  _activeBlocks.get(i);
    }
}
