package tw.edu.ntut.csie.game.card.character;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.core.MovingBitmap;




public class DurabilityBonus extends Card {
    public DurabilityBonus() {
        super();
        _cardType = 0;
        _cardAttributes = new CardAttributes(-1, -1, 10);
    }
}
