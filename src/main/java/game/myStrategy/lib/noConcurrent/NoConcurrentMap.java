package game.myStrategy.lib.noConcurrent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.function.Consumer;

public class NoConcurrentMap<K extends Comparable<K>, V> {
    private final TreeMap<K, V> currMap;
    private final HashMap<K, V> putMap;
    private final LinkedList<V> delList;

    public NoConcurrentMap() {
        currMap = new TreeMap<>();
        delList = new LinkedList<>();
        putMap = new HashMap<>();
    }

    public void put(K k, V v) {
        putMap.put(k, v);
    }

    public void remove(V v) {
        delList.add(v);
    }

    public void remove(K k) {
        remove(currMap.get(k));
    }

    public V get(K k) {
        V v = currMap.get(k);
        if (v == null) v = putMap.get(k);
        return v;
    }

    public void forEach(Consumer<? super V> action) {
        update();
        synchronized (this) {
            currMap.values().forEach(action);
        }
    }

    public boolean containsKey(K k) {
        return currMap.containsKey(k) || putMap.containsKey(k);
    }

    private void update() {
        currMap.putAll(putMap);
        putMap.clear();
        for (V v : delList) currMap.remove(v);
        delList.clear();
    }
}
