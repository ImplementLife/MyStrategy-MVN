package game.myStrategy.lib.noConcurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class NoConcurrentList<V> {
    private final List<V> curList;
    private final List<V> addList;
    private final List<V> delList;

    public NoConcurrentList() {
        curList = new LinkedList<>();
        addList = new LinkedList<>();
        delList = new LinkedList<>();
    }

    public void clear() {
        curList.clear();
        addList.clear();
        delList.clear();
    }

    public void add(V v) {
        addList.add(v);
    }

    public void remove(V v) {
        delList.add(v);
    }

    public void forEach(Consumer<? super V> action) {
        update();
        synchronized (this) {
            curList.forEach(action);
        }
    }

    private void update() {
        curList.addAll(addList);
        addList.clear();
        curList.removeAll(delList);
        delList.clear();
    }
}

