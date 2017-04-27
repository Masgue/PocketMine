package tw.edu.ntut.csie.game.card.mine;

import tw.edu.ntut.csie.game.card.Card;

public class CoalBonus extends Card {
    public CoalBonus() {
        super();
        _blockType = 3;
        _blockSpawningRate = 500;
        _hasChangedBlock = true;
    }
}
