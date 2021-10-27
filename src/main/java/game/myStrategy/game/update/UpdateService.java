package game.myStrategy.game.update;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.CallManager;
import game.myStrategy.lib.threads.bt.DT;
import game.myStrategy.lib.threads.bt.WhileThreadBT;

public final class UpdateService extends InContext {
    //region Singleton
    private static UpdateService instance;
    public static UpdateService get() {
        if (instance == null) instance = new UpdateService();
        return instance;
    }
    private UpdateService() {
        thread = new WhileThreadBT(() -> callManager.iterate(GameObject::update), "Поток обновления объектов игры");
    }
    //endregion

    public final static DT dt = get().thread.getDt();
    private final WhileThreadBT thread;

    public void start() {
        thread.start();
    }

    public void pause() {
        if (thread.isPause()) thread.play();
        else thread.pause();
    }

    public long getEPS() {
        return thread.getEPS();
    }

    //------------------------
    private final CallManager<GameObject> callManager = new CallManager<>();

    public CallManager.Call<GameObject> get(GameObject gameObject) {
        return callManager.get(gameObject);
    }

    interface Updating {
        void update(DT dt);
    }
}
