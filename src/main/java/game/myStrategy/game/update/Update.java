package game.myStrategy.game.update;

import game.myStrategy.lib.threads.bt.DT;

public interface Update {
    void update(DT dt);
    boolean isCanUpdate();
}
