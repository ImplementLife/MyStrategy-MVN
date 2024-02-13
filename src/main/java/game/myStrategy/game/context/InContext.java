package game.myStrategy.game.context;

public abstract class InContext {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
