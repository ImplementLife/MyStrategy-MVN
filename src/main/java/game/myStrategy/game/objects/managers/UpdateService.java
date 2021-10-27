package game.myStrategy.game.objects.managers;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.noConcurrent.NoConcurrentList;

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

    private final NoConcurrentList<Update> list = new NoConcurrentList<>();

    public void iterate() {
        list.forEach(Update::update);
    }

    private void put(Update o) {
        list.add(o);
    }

    private void remove(Update o) {
        list.remove(o);
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
