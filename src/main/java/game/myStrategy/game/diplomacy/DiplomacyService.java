package game.myStrategy.game.diplomacy;

import game.myStrategy.game.context.InContext;

public class DiplomacyService extends InContext {
    private Diplomacy diplomacy;
    public Diplomacy getDiplomacy() {
        if (diplomacy == null) diplomacy =  new Diplomacy();
        return diplomacy;
    }

    public DiplomacyService() {
        this.diplomacy = getDiplomacy();
    }

    public Player create(int i) {
        return diplomacy.create(i);
    }
}
