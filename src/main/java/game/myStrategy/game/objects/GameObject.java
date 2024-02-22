package game.myStrategy.game.objects;

import game.myStrategy.Boot;
import game.myStrategy.game.audio.AudioService;
import game.myStrategy.game.context.Context;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.update.Update;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.CallManager.Call;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.threads.bt.DT;

public class GameObject implements Comparable<Object>, Update {
    //region Fields
    private final Id id;
    private final GameObjectType type;
    private boolean deleted;
    private boolean canUpdate;
    private UpdateService updateService;
    private AudioService audioService;

    protected Context context;
    private Call<GameObject> draw;
    //endregion

    //region Constructor
    public GameObject(GameObjectType type) {
        this.context = Context.context();
        this.type = type;
        this.id = context.getIdService().getId(type);
        this.draw = context.getDrawService().get(this);
        updateService = Boot.getBean(UpdateService.class);
        updateService.put(this);
    }
    //endregion

    public final Id getId() {
        return id;
    }

    public void delete() {
        updateService.delete(this);
        draw.remove();
        deleted = true;
    }

    public final boolean isDeleted() {
        return deleted;
    }

    //region Enable/Disable
    public final void enableUpdateDraw() {
        enableUpdate();
        enableDraw();
    }
    public final void disableUpdateDraw() {
        disableUpdate();
        disableDraw();
    }

    public final void enableDraw() {
        draw.setCanCall(true);
    }
    public final void disableDraw() {
        draw.setCanCall(false);
    }

    public final void enableUpdate() {
        canUpdate = true;
    }
    public final void disableUpdate() {
        canUpdate = false;
    }
    //endregion

    //region For Wail
    public void draw(Drawer drawer) {}

    @Override
    public void update(DT dt) {}

    @Override
    public boolean isCanUpdate() {
        return canUpdate;
    }
    //endregion

    //region Object override
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GameObject gameObject)) return false;
        return id.equals(gameObject.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public final int compareTo(Object arg) {
        return id.compareTo(arg);
    }

    @Override
    public String toString() {
        return id.toString();
    }
    //endregion
}

