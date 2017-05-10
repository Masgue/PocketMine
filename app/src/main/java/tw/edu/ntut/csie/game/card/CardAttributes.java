package tw.edu.ntut.csie.game.card;

/**
 * Created by Johnson on 2017/5/5.
 */

public class CardAttributes {
    protected int _blockType;
    protected int _blockSpawningRate;
    protected int _durability;

    public CardAttributes(int blockType, int blockSpawningRate, int durability) {
        _blockType = blockType;
        _blockSpawningRate = blockSpawningRate;
        _durability = durability;
    }

    public int GetBlockType() {return _blockType;}

    public int GetBlockSpawningRate() {return _blockSpawningRate;}

    public int GetDurability() {return _durability;}
}
