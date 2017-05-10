package tw.edu.ntut.csie.game.card.tool;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.core.MovingBitmap;


public class BombBonus extends Card {
    public BombBonus() {
        super();
        _cardAttributes = new CardAttributes(8, 500, -1);
    }
}
