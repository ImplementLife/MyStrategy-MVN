package game.myStrategy.lib.noConcurrent;

import game.myStrategy.lib.math.Angle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoConcurrentListTest {
    private static class Q {}
    private NoConcurrentList<Q> list;

    @BeforeEach
    void before() {
        list = new NoConcurrentList<>();
        for (int i = 0; i < 10; i++) list.add(new Q());
    }

    @Test
    void testFunMainCase1() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 3; i++) list.forEach(q -> list.add(new Q()));
        });
    }

    @Test
    void testFunMainCase2() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 3; i++) list.forEach(q -> list.remove(q));
        });
    }

    @Test
    void testFunMainCase3() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 3; i++) {
                list.forEach(q -> {
                    Q q1 = new Q();
                    list.add(q1);
                    list.remove(q1);
                });
            }
        });
    }

    @Test
    void testConcurrentInTwoThreads() {
        for (int i = 0; i < 100; i++) list.add(new Q());
        assertDoesNotThrow(() -> {
            new Thread(() -> list.forEach(q -> list.add(new Q()))).start();
            new Thread(() -> list.forEach(q -> list.remove(q))).start();
        });
    }
}