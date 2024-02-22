package game.myStrategy.game.objects;

import game.myStrategy.game.objects.managers.GameObjectType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.TreeMap;

@Component
@Scope("singleton")
public class IdService {
    private static class List extends ArrayList<Id> {
        private int countNum = 0;
        private int getUnitNum() { return countNum++; }
    }
    private final TreeMap<GameObjectType, List> listsId;
    //private static TreeMap<Boolean, Id> mapId;

    public IdService() {
        listsId = new TreeMap<>();
        for (GameObjectType type : GameObjectType.values()) listsId.put(type, new List());
    }

    public Id getId(GameObjectType type) {
        return new Id(type, listsId.get(type).getUnitNum());
    }
}
