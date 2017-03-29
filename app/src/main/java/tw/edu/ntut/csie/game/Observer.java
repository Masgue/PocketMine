package tw.edu.ntut.csie.game;

/**
 * Created by ChenKeng on 2017/3/29.
 */

public abstract class Observer {
    protected GameMap _map;
    public abstract void update();
}
