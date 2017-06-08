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
        _number = new Integer(3, 0, x, y);
    }

    public EnergyBar (ArrayList<MovingBitmap> bar, int number, int x, int y) {
        _bar = bar;
        _x = x;
        _y = y;
        InitializeMovingBitmap();
        _number = new Integer(3, number, x, y);
    }

    private void InitializeMovingBitmap() {
        GetBar().setLocation(_x, _y);
    }

    public MovingBitmap GetBar() {
        return _bar.get(10);
    }

    public Integer GetInteger() {
        return _number;
    }

}
