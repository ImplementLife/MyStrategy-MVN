package game.myStrategy.lib.noConcurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class NoConcurrentList<V> {
    private final List<V> currentList;
    private final List<V> addList;
    private final List<V> deleteList;

    public NoConcurrentList() {
        currentList = new LinkedList<>();
        addList = new LinkedList<>();
        deleteList = new LinkedList<>();
    }

    public void clear() {
        currentList.clear();
        addList.clear();
        deleteList.clear();
    }

    public void add(V v) {
        addList.add(v);
    }

    public void remove(V v) {
        deleteList.add(v);
    }

    public synchronized void forEach(Consumer<? super V> action) {
        update();
        currentList.forEach(action);
    }

    private void update() {
        currentList.addAll(addList);
        addList.clear();
        currentList.removeAll(deleteList);
        deleteList.clear();
    }
}

