package game.myStrategy.game.draw;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.CallManager;
import game.myStrategy.lib.draw.drawer.Drawer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class DrawService {
    private final CallManager<GameObject> callManager = new CallManager<>();

    public CallManager.Call<GameObject> get(GameObject gameObject) {
        return callManager.get(gameObject);
    }

    public void iterate(Drawer drawer) {
        callManager.iterate(gameObject -> gameObject.draw(drawer));
    }
}
