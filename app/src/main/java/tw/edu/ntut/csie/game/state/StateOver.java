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
    private int _experience;

    public StateOver(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(new MovingBitmap(R.drawable.gameoverbackground));
        _menuButton = new BitmapButton(R.drawable.menu_in_state_over, 500, 10);
        _menuButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("Score", _score.getValue());
                map.put("Experience", _experience);
                changeState(Game.INITIAL_STATE, map);
            }
        });
        addGameObject(_menuButton);
        addPointerEventHandler(_menuButton);
        _score = new Integer(4, (int)data.get("Score"), 10, 10);
        _experience = (int)data.get("Experience");
        ShowScore();
    }

    private void ShowScore() {
        addGameObject(_score);
        _score.show();
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