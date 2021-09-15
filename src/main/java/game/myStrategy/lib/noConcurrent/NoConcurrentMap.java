package game.myStrategy.lib.noConcurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NoConcurrentMap<K extends Comparable<K>, T extends WithKey<K>> {
    private final Map<K, T> currMap;
    private final List<T> addList;
    private final List<T> delList;

    public NoConcurrentMap() {
        currMap = new TreeMap<>();
        addList = new ArrayList<>();
        delList = new ArrayList<>();
    }

    public void add(T t) {
        addList.add(t);
    }

    public void del(T t) {
        delList.add(t);
    }

    public void del(K k) {
        delList.add(currMap.get(k));
    }

    public void iterate(Run<T> run) {
        update();
        for (T t : currMap.values()) run.run(t);
    }

    private void update() {
        for (T t : addList) currMap.put(t.getKey(), t);
        addList.clear();
        for (T t : delList) currMap.remove(t.getKey(), t);
        delList.clear();
    }
}
