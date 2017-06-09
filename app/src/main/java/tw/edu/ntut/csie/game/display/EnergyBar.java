package tw.edu.ntut.csie.game.display;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Integer;

public class EnergyBar {
    private ArrayList<MovingBitmap> _bar;
    private MovingBitmap _movingBitmap;
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
        _movingBitmap = _bar.get(0);
        _movingBitmap.setLocation(_x, _y);
    }

    public void SetMovingBitmap(int movingBitmapNumber) {
        switch (movingBitmapNumber) {
            case 0:
                _movingBitmap.loadBitmap(R.drawable.exp_0);
                break;
            case 1:
                _movingBitmap.loadBitmap(R.drawable.exp_1);
                break;
            case 2:
                _movingBitmap.loadBitmap(R.drawable.exp_2);
                break;
            case 3:
                _movingBitmap.loadBitmap(R.drawable.exp_3);
                break;
            case 4:
                _movingBitmap.loadBitmap(R.drawable.exp_4);
                break;
            case 5:
                _movingBitmap.loadBitmap(R.drawable.exp_5);
                break;
            case 6:
                _movingBitmap.loadBitmap(R.drawable.exp_6);
                break;
            case 7:
                _movingBitmap.loadBitmap(R.drawable.exp_7);
                break;
            case 8:
                _movingBitmap.loadBitmap(R.drawable.exp_8);
                break;
            case 9:
                _movingBitmap.loadBitmap(R.drawable.exp_9);
                break;
            default:
                _movingBitmap.loadBitmap(R.drawable.exp_10);
                break;
        }

    }

    public MovingBitmap GetMovingBitmap() {
        return _movingBitmap;
    }

    public Integer GetInteger() {
        return _number;
    }

}
