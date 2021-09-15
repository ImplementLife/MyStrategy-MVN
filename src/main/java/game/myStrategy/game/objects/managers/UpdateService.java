package game.myStrategy.game.objects.managers;

import game.myStrategy.game.objects.GameObject;

import java.util.HashSet;
import java.util.LinkedList;

//TODO схлопнуть до джинериков
public class UpdateService {
    //region Singleton
    private static UpdateService instance;
    public static UpdateService get() {
        if (instance == null) instance = new UpdateService();
        return instance;
    }
    private UpdateService() {}
    //endregion

    //region Fields
    private final HashSet<Update> updateSet = new HashSet<>();
    private final LinkedList<Update> addList = new LinkedList<>();
    private final LinkedList<Update> removeList = new LinkedList<>();
    //endregion

    public void iterate() {
        updateList();
        for (Update target : updateSet) target.update();
    }
    private synchronized void updateList() {
        updateSet.removeAll(removeList);
        updateSet.addAll(addList);
        removeList.clear();
        addList.clear();
    }
    private synchronized void put(Update o) {
        addList.add(o);
    }
    private synchronized void remove(Update o) {
        removeList.add(o);
    }

    //==============================================//
    public static class Update {
        private final GameObject target;
        private boolean update;

        public Update(GameObject target) {
            this.target = target;
            get().put(this);
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }

        public void update() {
            if (update) target.update();
        }

        public void remove() {
            get().remove(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Update)) return false;
            Update update = (Update) o;
            return target.equals(update.target);
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }
    }
}
