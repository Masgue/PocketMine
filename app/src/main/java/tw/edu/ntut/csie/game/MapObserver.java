package tw.edu.ntut.csie.game;

import java.util.Observer;

import tw.edu.ntut.csie.game.GameMap;

/**
 * Created by ChenKeng on 2017/3/29.
 */

public class MapObserver extends tw.edu.ntut.csie.game.Observer {
    public MapObserver(GameMap map) {
        this._map = map;
        this._map.attach(this);
    }

    @Override
    public void update() {

    }
}
