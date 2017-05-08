package tw.edu.ntut.csie.game.block;

/**
 * Created by ChenKeng on 2017/5/5.
 */

public class ActiveBlocks {
    private int _blockX;
    private int _blockY;
    public ActiveBlocks(int blockX, int blockY) {
        _blockX = blockX;
        _blockY = blockY;
    }

    public int GetBlockX() {return _blockX;}

    public int GetBlockY() {return _blockY;}
}
