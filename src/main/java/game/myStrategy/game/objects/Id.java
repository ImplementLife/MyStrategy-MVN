package game.myStrategy.game.objects;

import game.myStrategy.game.objects.managers.GameObjectTypes;

import java.util.ArrayList;
import java.util.TreeMap;

public final class Id implements Comparable<Object> {

    //region Fields

    private final byte type;
    private final int num;
    private final int hash;
    private String toString;

    //endregion

    //region Constructors

    public Id(GameObjectTypes type, int num) {
        this.type = type.type;
        this.num = num;
        this.hash = this.type * 1_000_000 + this.num;
    }

    public Id(Id id) {
        this.type = id.type;
        this.num = id.num;
        this.hash = id.hash;
        this.toString = id.toString;
    }

    //endregion

    //region Getters

    public byte getType() {
        return type;
    }

    public int getNum() {
        return num;
    }

    //endregion

    //region Object override

    @Override
    public String toString() {
        if (toString == null) toString = "type: " + type + "; unitNum: " + num + ";";
        return toString;
    }

    @Override
    public final int compareTo(Object arg) {
        Id id = (Id) arg;
        if (id.type != this.type) return this.type - id.type;
        else return this.num - id.num;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Id id)) return false;
        if (type != id.type) return false;
        return num == id.num;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    //endregion

}
