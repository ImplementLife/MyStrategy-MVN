package game.myStrategy.game.objects;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.objects.managers.GameObjectTypes;

import java.util.ArrayList;
import java.util.TreeMap;

public class IdService extends InContext {
    private static class List extends ArrayList<Id> {
        private int countNum = 0;
        private int getUnitNum() { return countNum++; }
    }
    private final TreeMap<GameObjectTypes, List> listsId;
    //private static TreeMap<Boolean, Id> mapId;

    public IdService() {
        listsId = new TreeMap<>();
        for (GameObjectTypes type : GameObjectTypes.values()) listsId.put(type, new List());
    }

    public Id getId(GameObjectTypes type) {
        return new Id(type, listsId.get(type).getUnitNum());
    }
}
