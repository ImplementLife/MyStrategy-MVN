package game.myStrategy.lib.noConcurrent;

@FunctionalInterface
public interface Run<T> {
    void run(T t);
}
