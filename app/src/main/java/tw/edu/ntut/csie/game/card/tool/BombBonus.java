package tw.edu.ntut.csie.game.card.tool;

import tw.edu.ntut.csie.game.card.Card;


public class BombBonus extends Card {
    public BombBonus() {
        super();
        _blockType = 10;
        _blockSpawningRate = 500;
        _hasChangedBlock = true;
    }
}
