package game.myStrategy.lib.noConcurrent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoConcurrentList<T> {
    private final List<T> curList;
    private final List<T> addList;
    private final List<T> delList;

    public NoConcurrentList() {
        curList = new ArrayList<>();
        addList = new ArrayList<>();
        delList = new ArrayList<>();
    }

    public int size() {
        update();
        return curList.size();
    }

    public boolean isEmpty() {
        update();
        return curList.isEmpty();
    }

    public boolean contains(T o) {
        update();
        return curList.contains(o);
    }

    public void sort(Comparator<? super T> c) {
        update();
        curList.sort(c);
    }

    public void clear() {
        curList.clear();
        addList.clear();
        delList.clear();
    }

    public void add(T t) {
        addList.add(t);
    }

    public void del(T t) {
        delList.add(t);
    }

    public void iterate(Run<T> run) {
        update();
        synchronized (this) {
            for (T t : curList) run.run(t);
        }
    }

    private void update() {
        curList.addAll(addList);
        addList.clear();
        curList.removeAll(delList);
        delList.clear();
    }
}

