package game.myStrategy.lib.noConcurrent;

public class NoConcurrentMapWithKey<K extends Comparable<K>, V extends WithKey<K>> extends NoConcurrentMap<K, V> {
    public void put(V v) {
        put(v.getKey(), v);
    }
}
