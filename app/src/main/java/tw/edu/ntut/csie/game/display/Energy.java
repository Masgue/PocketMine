package tw.edu.ntut.csie.game.display;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by Johnson on 2017/5/23.
 */

public class Energy {
    private EnergyBar _energy;
    private int _currentEnergy;

    public Energy(int currentEnergy, int x, int y) {
        ArrayList<MovingBitmap> bar = new ArrayList<MovingBitmap>();
        bar.add(new MovingBitmap(R.drawable.exp_0));
        bar.add(new MovingBitmap(R.drawable.exp_1));
        bar.add(new MovingBitmap(R.drawable.exp_2));
        bar.add(new MovingBitmap(R.drawable.exp_3));
        bar.add(new MovingBitmap(R.drawable.exp_4));
        bar.add(new MovingBitmap(R.drawable.exp_5));
        bar.add(new MovingBitmap(R.drawable.exp_6));
        bar.add(new MovingBitmap(R.drawable.exp_7));
        bar.add(new MovingBitmap(R.drawable.exp_8));
        bar.add(new MovingBitmap(R.drawable.exp_9));
        bar.add(new MovingBitmap(R.drawable.exp_10));
        _currentEnergy = currentEnergy;
        _energy = new EnergyBar(bar, _currentEnergy, x, y);
    }

    public void SetMovingBitmap() {
        _energy.SetMovingBitmap(_energy.GetInteger().getValue());
    }

    public MovingBitmap GetBar() {
        return _energy.GetMovingBitmap();
    }

    public Integer GetInteger() {
        return _energy.GetInteger();
    }
}
