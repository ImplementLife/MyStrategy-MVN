package game.myStrategy.game.diplomacy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class DiplomacyService {
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
