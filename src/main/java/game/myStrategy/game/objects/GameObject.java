package game.myStrategy.game.objects;

import game.myStrategy.game.context.Context;
import game.myStrategy.game.objects.managers.DrawService;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.objects.managers.UpdateService;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.draw.drawer.drawCall.DrawCall;

public class GameObject implements Comparable<Object> {
    //region Fields
    private final Id id;
    private final GameObjectType type;
    private boolean deleted;
    private DrawService.Draw draw;
    private UpdateService.Update update;
    protected Context context;
    protected DrawCall drawCall;
    //endregion

    //region Constructor
    public GameObject(GameObjectType type) {
        this.context = Context.get();
        this.type = type;
        this.id = this.context.getIdService().getId(type);
    }
    //endregion

    public final Id getId() {
        return id;
    }

    public void delete() {
        if (draw != null) draw.remove();
        if (update != null) update.remove();
        deleted = true;
    }

    public final boolean isDeleted() {
        return deleted;
    }

    //region Enable/Disable
    public DrawService.Draw getDraw() {
        if (draw == null) draw = new DrawService.Draw(this, (int) type.type);
        return draw;
    }

    public UpdateService.Update getUpdate() {
        if (update == null) update = new UpdateService.Update(this);
        return update;
    }

    public final void enableUpdateDraw() {
        enableUpdate();
        enableDraw();
    }

    public final void disableUpdateDraw() {
        disableUpdate();
        disableDraw();
    }

    public final void enableDraw() {
        getDraw().setDraw(true);
    }

    public final void disableDraw() {
        getDraw().setDraw(false);
    }

    public final void enableUpdate() {
        getUpdate().setUpdate(true);
    }

    public final void disableUpdate() {
        getUpdate().setUpdate(false);
    }

    public final void setLayer(Integer layer) {
        getDraw().setLayer(layer);
    }
    //endregion

    //region For Wail
    public void update() {}
    public void draw(Drawer drawer) {}
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

