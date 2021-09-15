package game.myStrategy.lib.draw.drawer;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class ListDrawCall {
    private final Map<Integer, Queue<Runnable>> stack;
    private final Draw draw;

    public ListDrawCall(Draw draw) {
        this.draw = draw;
        stack = new TreeMap<>();
    }

    public void drawCall(int layer, Runnable exe) {
        Queue<Runnable> queue;
        if (!stack.containsKey(layer)) {
            queue = new LinkedList<>();
            stack.put(layer, queue);
        } else {
            queue = stack.get(layer);
        }
        queue.offer(exe);
    }

    public void drawAll() {
        for (Queue<Runnable> queue : stack.values()) {
            for (Runnable drawCall : queue) {
                drawCall.run();
            }
            queue.clear();
        }
    }
}
