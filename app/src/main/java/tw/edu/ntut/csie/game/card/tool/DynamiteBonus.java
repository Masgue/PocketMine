package tw.edu.ntut.csie.game.card.tool;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.card.CardAttributes;

/**
 * Created by Johnson on 2017/5/10.
 */

public class DynamiteBonus extends Card {
    public DynamiteBonus() {
        super();
        _cardType = 4;
        _cardAttributes = new CardAttributes(10, 15, -1);
    }
}
