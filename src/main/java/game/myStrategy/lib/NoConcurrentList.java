package game.myStrategy.lib;

import java.util.ArrayList;

public class NoConcurrentList<T> {

    private final ArrayList<T> curList;
    private final ArrayList<T> addList;
    private final ArrayList<T> delList;

    public NoConcurrentList() {
        curList = new ArrayList<>();
        addList = new ArrayList<>();
        delList = new ArrayList<>();
    }

    public void add(T t) {
        addList.add(t);
    }

    public void del(T t) {
        delList.add(t);
    }

    public void iterate(R<T> r) {
        updateList();
        for (T t : curList) r.run(t);
    }

    private void updateList() {
        curList.addAll(addList);
        addList.clear();
        curList.removeAll(delList);
        delList.clear();
    }

    @FunctionalInterface
    public interface R<T> {
        void run(T t);
    }
}

