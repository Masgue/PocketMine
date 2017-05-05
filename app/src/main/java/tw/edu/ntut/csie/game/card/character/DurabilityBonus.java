package tw.edu.ntut.csie.game.card.character;

import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class DurabilityBonus extends Card {
    public DurabilityBonus() {
        super();
        _durability = 1;
        _hasChangedDurability = true;
    }
}