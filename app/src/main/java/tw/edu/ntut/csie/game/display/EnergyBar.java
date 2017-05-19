package tw.edu.ntut.csie.game.display;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Integer;

public class EnergyBar {
    private ArrayList<MovingBitmap> _bar;
    private Integer _number;
    private int _x;
    private int _y;

    public EnergyBar (ArrayList<MovingBitmap> bar, int x, int y) {
        _bar = bar;
        _x = x;
        _y = y;
        InitializeMovingBitmap();
        _number = new Integer(1, 0, x, y);
    }

    public EnergyBar (ArrayList<MovingBitmap> bar, int number, int x, int y) {
        _bar = bar;
        _x = x;
        _y = y;
        InitializeMovingBitmap();
        _number = new Integer(1, number, x, y);
    }

    private InitializeMovingBitmap() {
        GetBar().setLocation(_x, _y);
    }

    public GetBar() {
        return _bar.get(0);
    }

    public GetInteger() {
        return _number;
    }

}
