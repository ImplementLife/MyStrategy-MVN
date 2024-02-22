package game.myStrategy.game.update;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.noConcurrent.NoConcurrentList;
import game.myStrategy.lib.threads.bt.DT;
import game.myStrategy.lib.threads.bt.WhileThreadBT;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public final class UpdateService {
    private final NoConcurrentList<Update> obj = new NoConcurrentList<>();
    private final WhileThreadBT thread;
    private DT dt;

    private UpdateService() {
        thread = new WhileThreadBT(() -> obj.forEach(o -> {
            if (o.isCanUpdate()) {
                o.update(dt);
            }
        }), "Потік оновлення об'єктів гри");
        dt = thread.getDt();
    }

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

    public DT getDt() {
        return dt;
    }

    public void delete(GameObject gameObject) {
        obj.remove(gameObject);
    }
}
