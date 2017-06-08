package tw.edu.ntut.csie.game.display;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by Johnson on 2017/5/28.
 */

public class BuyableButton {
    private BitmapButton _bitmapButton;
    private MovingBitmap _movingBitmap;
    private Integer _integer;

    public BuyableButton(BitmapButton bitmapButton, MovingBitmap movingBitmap, int value, int x, int y) {
        _movingBitmap = movingBitmap;
        _movingBitmap.setLocation(x, y);
        _bitmapButton = bitmapButton;
        _bitmapButton.setLocation(x, y + 60);
        _integer = new Integer(3, value, x, (y + 120));
    }

    public BitmapButton GetBitmapButton() {
        return _bitmapButton;
    }

    public MovingBitmap GetMovingBitmap() {
        return _movingBitmap;
    }

    public Integer GetInteger() {
        return _integer;
    }
}
