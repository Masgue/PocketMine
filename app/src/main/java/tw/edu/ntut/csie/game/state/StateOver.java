package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.extend.Integer;

public class StateOver extends AbstractGameState {
    private BitmapButton _menuButton;
    private Integer _score;
    private Integer _experience;
    private int _money;
    private int _level;
    private int _energy;

    public StateOver(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(new MovingBitmap(R.drawable.state_ready_background));
        _menuButton = new BitmapButton(R.drawable.menu_in_state_over, 500, 10);
        _menuButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("Score", _score.getValue());
                map.put("Experience", _experience.getValue());
                map.put("Money", _money);
                map.put("Level", _level);
                map.put("Energy", _energy);
                changeState(Game.INITIAL_STATE, map);
            }
        });
        addGameObject(_menuButton);
        addPointerEventHandler(_menuButton);
        _score = new Integer(3, (int)data.get("Score"), 10, 10);
        _experience = new Integer(2, (int)data.get("Experience"), 10, 200);
        _money = (int)data.get("Money");
        _level = (int)data.get("Level");
        _energy = (int)data.get("Energy");
        ShowScore();
        ShowFloor();
    }

    private void ShowScore() {
        addGameObject(_score);
        _score.show();
    }

    private void ShowFloor() {
        addGameObject(_experience);
        _experience.show();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}