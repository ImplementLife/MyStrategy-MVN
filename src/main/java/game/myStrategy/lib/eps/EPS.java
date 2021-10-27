package game.myStrategy.lib.eps;

import static game.myStrategy.lib.math.TimeConstants.TIME_NANO_PER_SECOND;

/**
 * EPS - Execute Per Second
 * Замеряет время выполнения переданного кода
 */
public class EPS implements Runnable {
    private final Runnable exe;
    private long time;

    public EPS(Runnable exe) {
        this.exe = exe;
    }

    public long getEPS() {
        if (time == 0) return Long.MAX_VALUE;
        return TIME_NANO_PER_SECOND / time;
    }
    public long getTime() {
        return time;
    }

    @Override
    public void run() {
        long tempTime = System.nanoTime();
        exe.run();
        time = System.nanoTime() - tempTime;
    }
}
