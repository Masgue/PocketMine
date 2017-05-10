package tw.edu.ntut.csie.game.card;


import tw.edu.ntut.csie.game.GameMap;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.BitmapButton;

public abstract class Card {
    protected BitmapButton _bitmapButton;
    protected CardAttributes _cardAttributes;

    public Card() {
    }

    public BitmapButton GetBitmapButton() {
        return _bitmapButton;
    }

    public void SetBitmapButton(BitmapButton bitmapButton) {
        _bitmapButton = bitmapButton;
    }

    public CardAttributes GetCardAttributes() {
        return _cardAttributes;
    }
}
