package tw.edu.ntut.csie.game.card.tool;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.card.CardAttributes;

/**
 * Created by Johnson on 2017/5/10.
 */

public class DrillBonus extends Card {
    public DrillBonus() {
        super();
        _cardType = 3;
        _cardAttributes = new CardAttributes(9, 15, -1);
    }
}
