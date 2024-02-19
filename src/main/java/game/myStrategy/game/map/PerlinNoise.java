package game.myStrategy.game.map;

import game.myStrategy.lib.math.Vec2D;

import java.util.ArrayList;

public class PerlinNoise {
    private long seed;
    private long octaves = 2;

    public PerlinNoise(long seed) {
        this.seed = seed;
    }
    public PerlinNoise(long seed, long octaves) {
        this(seed);
        setOctaves(octaves);
    }

    public void setOctaves(long octaves) {
        if (octaves < 1 || octaves > 6) throw new IllegalArgumentException("Octaves must be from 1 to 6");
        this.octaves = octaves;
    }

    /*==================================================*/
    private double coefficient(double c) {
        //return ((1 - Math.cos(c * Math.PI)) / 2); //Через косинус
        return (c * c * (3 - 2 * c)); //Через функцію подібну до косінуса (швидше)
    }
    private double valueNoise(long x, long y) {
        long n = x + y * 367;
        n = ((n + seed) << 11) ^ n;
        return (1 - ((n * (n * n * 20183 + 815279) + seed) & 0x7fffffff) / 1073741824.0);
    }
    private double interpolateNoise(double x, double y) {
        long longX = (long) Math.floor(x);
        long longY = (long) Math.floor(y);
        double cx = coefficient(x - longX);
        double cy = coefficient(y - longY);

        double interpolateA = lineInterpolate(valueNoise(longX, longY), valueNoise(longX + 1, longY), cx);
        double interpolateB = lineInterpolate(valueNoise(longX, longY + 1), valueNoise(longX + 1, longY + 1), cx);
        return lineInterpolate(interpolateA, interpolateB, cy);
    }
    private double lineInterpolate(double a, double b, double c) {
        return a + c * (b - a);
    }
    private double cosInterpolate(double a, double b, double c) {
        return lineInterpolate(a, b, coefficient(c));
    }

    public double get(Vec2D pos) {
        return get(pos.getX(), pos.getY());
    }
    public double get(double x, double y) {
        double frequency = 0.01;
        double amplitude = 1;
        double scale = 0;
        double total = 0;

        for (long i = 0; i < octaves; i++) {
            total += interpolateNoise(x * frequency, y * frequency) * amplitude;
            scale += amplitude;
            frequency *= 2;
            amplitude *= 0.5;
        }
        return total / scale;
    }
    public ArrayList<ArrayList<Double>> getHeightMap(Vec2D size) {
        ArrayList<ArrayList<Double>> map = new ArrayList<>();
        for (int X = 0; X <= size.getX(); X++) {
            map.add(new ArrayList<>());
            for (int Y = 0; Y <= size.getY(); Y++) map.get(X).add(get(X, Y));
        }
        return map;
    }
}
