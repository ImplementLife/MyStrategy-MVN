package game.myStrategy.lib;

import java.util.HashMap;

public class Data<T> {
    private final HashMap<Integer, T> data = new HashMap<>();

    public T getData(int hash) {
        T instance = data.get(hash);
        if (instance == null) throw new RuntimeException("Instance not found.");
        else return instance;
    }

    public T get(T t) {
        return getData(t.hashCode());
    }

    public T put(T t) {
        return data.put(t.hashCode(), t);
    }
}
