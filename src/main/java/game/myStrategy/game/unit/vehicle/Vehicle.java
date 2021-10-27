package game.myStrategy.game.unit.vehicle;

import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;

import java.util.HashMap;

public abstract class Vehicle extends Unit {
    //==========     Static     =============//
    private static HashMap<Integer, Schema> mapSchemas = new HashMap<>();

    private static class Schema {

    }
    //=======================================//


    public Vehicle(GameObjectType type) {
        super(type);
    }
}
