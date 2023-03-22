package game.myStrategy.lib.noConcurrent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.function.Consumer;

public class NoConcurrentMap<K extends Comparable<K>, V> {
    private final TreeMap<K, V> currentMap;
    private final HashMap<K, V> putMap;
    private final LinkedList<V> deleteList;

    public NoConcurrentMap() {
        currentMap = new TreeMap<>();
        deleteList = new LinkedList<>();
        putMap = new HashMap<>();
    }

    public void put(K k, V v) {
        putMap.put(k, v);
    }

    public void remove(V v) {
        deleteList.add(v);
    }

    public void remove(K k) {
        remove(currentMap.get(k));
    }

    public V get(K k) {
        V v = currentMap.get(k);
        if (v == null) v = putMap.get(k);
        return v;
    }

    public synchronized void forEach(Consumer<? super V> action) {
        update();
        currentMap.values().forEach(action);
    }

    public boolean containsKey(K k) {
        return currentMap.containsKey(k) || putMap.containsKey(k);
    }

    private void update() {
        currentMap.putAll(putMap);
        putMap.clear();
        for (V v : deleteList) currentMap.remove(v);
        deleteList.clear();
    }
}
