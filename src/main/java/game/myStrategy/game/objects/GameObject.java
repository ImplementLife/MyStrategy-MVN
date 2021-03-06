package game.myStrategy.game.objects;

import game.myStrategy.game.context.Context;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.CallManager.Call;
import game.myStrategy.lib.draw.drawer.Drawer;

public class GameObject implements Comparable<Object> {
    //region Fields
    private final Id id;
    private final GameObjectType type;
    private boolean deleted;
    protected Context context;
    private Call<GameObject> update;
    private Call<GameObject> draw;
    //endregion

    //region Constructor
    public GameObject(GameObjectType type) {
        this.context = Context.context();
        this.type = type;
        this.id = this.context.getIdService().getId(type);

        this.update = this.context.getUpdateService().get(this);
        this.draw = this.context.getDrawService().get(this);
    }
    //endregion

    public final Id getId() {
        return id;
    }

    public void delete() {
        draw.remove();
        update.remove();
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
        update.setCanCall(true);
    }
    public final void disableUpdate() {
        update.setCanCall(false);
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

