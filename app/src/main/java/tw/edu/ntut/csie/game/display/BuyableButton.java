package tw.edu.ntut.csie.game.display;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by Johnson on 2017/5/28.
 */

public class BuyableButton {
    private BitmapButton _bitmapButton;
    private Integer _integer;

    public BuyableButton(BitmapButton bitmapButton, int value, int x, int y) {
        _bitmapButton = bitmapButton;
        _bitmapButton.setLocation(x, y);
        _integer = new Integer(3, value, x, (y + 60));
    }

    public BitmapButton GetBitmapButton() {
        return _bitmapButton;
    }

    public Integer GetInteger() {
        return _integer;
    }
}
