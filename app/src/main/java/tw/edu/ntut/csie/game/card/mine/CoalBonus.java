package tw.edu.ntut.csie.game.card.mine;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class CoalBonus extends Card {
    public CoalBonus() {
        super();
        _cardType = 1;
        _cardAttributes = new CardAttributes(3, 100, -1);
    }
}
