package game.myStrategy.lib.math;

public final class Util {
    private Util() {}

    public static double ceil(double c) {
        int f = (int) (c * 1000);
        return (double) f/1000;
    }
}
