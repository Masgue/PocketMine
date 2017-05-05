package tw.edu.ntut.csie.game.card;


import tw.edu.ntut.csie.game.GameMap;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.BitmapButton;

public abstract class Card {
    protected BitmapButton _bitmapButton;
    protected int _blockType;
    protected int _blockSpawningRate;
    protected int _cardType;
    protected int _durability;
    protected boolean _hasChangedBlock;
    protected boolean _hasChangedDurability;

    public Card() {
        _hasChangedBlock = false;
        _hasChangedDurability = false;
    }

    public void Active(GameMap map) {
        if (_hasChangedBlock) {
            //map.GetGeneratingBlocks.ChangeBlock(_blockType, _blockSpawningRate);
        }

        if (_hasChangedDurability) {
            //map.SetDurability(_durability);
        }
    }

    public BitmapButton GetBitmapButton() {
        return _bitmapButton;
    }

    public void SetBitmapButton(BitmapButton bitmapButton) {
        _bitmapButton = bitmapButton;
    }
}