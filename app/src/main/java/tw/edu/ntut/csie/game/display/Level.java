package tw.edu.ntut.csie.game.display;

import java.util.ArrayList;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by Johnson on 2017/5/23.
 */

public class Level {
    private EnergyBar _level;
    private int _currentLevel;
    private int _currentExperience;

    public Level(int currentLevel, int x, int y) {
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
        _currentLevel = currentLevel;
        _currentExperience = 0;
        _level = new EnergyBar(bar, _currentLevel, x, y);
    }

    public void Add(int experience) {
        _currentExperience += experience;
        _currentLevel = _currentExperience / 10;
        _level.SetMovingBitmap(SetMovingBitmap());
    }

    private SetMovingBitmap() {
        return bar.get(_currentExperience % 10);
    }

    public MovingBitmap GetBar() {
        return _level.GetBar();
    }

    public Integer GetInteger() {
        return _level.GetInteger();
    }
}
