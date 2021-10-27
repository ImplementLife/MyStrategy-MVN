package game.myStrategy.lib.draw.drawer;

import game.myStrategy.lib.draw.drawer.drawCall.DrawCall;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import static java.util.Objects.*;

public class DrawCallManagerImpl implements DrawCallManager {
    private final Map<Integer, Queue<DrawCall>> stack;
    private Drawer drawer;

    public DrawCallManagerImpl(Drawer drawer) {
        this.stack = new TreeMap<>();
        this.drawer = drawer;
    }

    @Override
    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    @Override
    public void add(DrawCall drawCall) {
        Queue<DrawCall> queue;
        if (!stack.containsKey(drawCall.getLayer())) {
            queue = new LinkedList<>();
            stack.put(drawCall.getLayer(), queue);
        } else {
            queue = stack.get(drawCall.getLayer());
        }
        queue.offer(drawCall);
    }

    @Override
    public void draw() {
        requireNonNull(drawer, "drawer");
        for (Queue<DrawCall> queue : stack.values()) {
            for (DrawCall drawCall : queue) {
                drawCall.draw(drawer);
            }
            queue.clear();
        }
    }
}
