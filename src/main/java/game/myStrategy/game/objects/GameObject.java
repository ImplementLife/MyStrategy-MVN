package game.myStrategy.game.objects;

import game.myStrategy.game.context.Context;
import game.myStrategy.game.objects.managers.DrawService;
import game.myStrategy.game.objects.managers.GameObjectTypes;
import game.myStrategy.game.objects.managers.UpdateService;
import game.myStrategy.lib.draw.drawer.Draw;

public class GameObject implements Comparable<Object> {
    //region Fields

    private final Id id;
    private final GameObjectTypes type;
    private boolean deleted;
    private DrawService.Draw draw;
    private UpdateService.Update update;
    protected Context context;

    //endregion

    //region Constructors

    public GameObject(GameObjectTypes type) {
        this(type, true, true);
    }

    public GameObject(GameObjectTypes type, boolean update, boolean draw) {
        this.context = Context.get();
        this.type = type;
        this.id = this.context.getIdService().getId(type);
        this.setUpdate(update);
        this.setDraw(draw);
    }

    public GameObject() {
        this(GameObjectTypes.EXPLOSION);
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

    //==============================================//
    public final void setDraw(boolean isDraw) {
        if (isDraw) {
            if (draw == null) draw = new DrawService.Draw(this, (int) type.type);
            draw.setDraw(true);
        } else if (draw != null) draw.setDraw(false);
    }
    public final void setLayer(Integer layer) {
        draw.setLayer(layer);
    }

    public final void setUpdate(boolean isUpdate) {
        if (isUpdate) {
            if (update == null) update = new UpdateService.Update(this);
            update.setUpdate(true);
        } else if (update != null) update.setUpdate(false);
    }

    //==============================================//
    public void update() {}

    public void draw(Draw drawer) {}

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

