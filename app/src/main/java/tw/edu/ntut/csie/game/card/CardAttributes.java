package tw.edu.ntut.csie.game.card;

/**
 * Created by Johnson on 2017/5/5.
 */

public class CardAttributes {
    protected int _blockType;
    protected int _blockSpawningRate;
    protected int _durability;
    protected  int _cardType;

    public CardAttributes(int blockType, int blockSpawningRate, int durability) {
        _blockType = blockType;
        _blockSpawningRate = blockSpawningRate;
        _durability = durability;
    }
}
