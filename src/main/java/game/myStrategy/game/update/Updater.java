package game.myStrategy.game.update;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.UpdateService;
import game.myStrategy.lib.CallManager;
import game.myStrategy.lib.threads.bt.DT;
import game.myStrategy.lib.threads.bt.WhileThreadBT;

public final class Updater {
    private static Updater instance;
    public static Updater get() {
        if (instance == null) instance = new Updater();
        return instance;
    }
    private Updater() {
        thread = new WhileThreadBT(() -> UpdateService.get().iterate(), "Поток обновления объектов игры");
    }

    private final WhileThreadBT thread;
    public final static DT dt = get().thread.getDt();

    public static void start() {
        get().thread.start();
    }

    public void pause() {
        if (thread.isPause()) thread.play();
        else thread.pause();
    }

    public long getEPS() {
        return thread.getEPS();
    }

    //------------------------
    private CallManager<GameObject> callManager = new CallManager<>();

    public CallManager.Call<GameObject> get(GameObject gameObject) {
        return callManager.get(gameObject);
    }



    interface Updating {
        void update(DT dt);
    }
}
