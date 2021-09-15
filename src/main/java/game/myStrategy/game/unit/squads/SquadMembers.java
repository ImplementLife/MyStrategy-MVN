package game.myStrategy.game.unit.squads;

import game.myStrategy.game.objects.Id;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.math.Vec2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SquadMembers extends HashMap<Id, Unit> implements Iterable<Unit> {
    private final Squad squad;

    SquadMembers(Squad squad) {
        this.squad = squad;
    }

    Unit getRandom() {
        int i = (int) (this.size() * Math.random());
        return (Unit) this.values().toArray()[i];
    }

    Vec2D getPos() {
        List<Vec2D> pos = new ArrayList<>();
        for (Unit u : this.values()) pos.add(u.getPos());
        return Vec2D.midVec(pos);
    }

    @Override
    public Iterator<Unit> iterator() {
        return this.values().iterator();
    }

    public float getMaxDist() {
        return 50f;
    }
}
