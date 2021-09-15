package game.myStrategy.game.objects.managers;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.draw.drawer.GameDrawer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class DrawService {
    //region Singleton
    private static DrawService instance;
    public static DrawService get() {
        if (instance == null) instance = new DrawService();
        return instance;
    }
    private DrawService() {}
    //endregion

    //region Fields
    private final TreeMap<Integer, HashSet<Draw>> mapDrawSets = new TreeMap<>();
    private final ArrayList<Draw> tempAdd = new ArrayList<>();
    private final ArrayList<Draw> tempRemove = new ArrayList<>();
    //endregion

    public void iterate(GameDrawer gameDrawer) {
        updateList();
        for (HashSet<Draw> set : mapDrawSets.values()) {
            for (Draw d : set) {
                try {
                    d.draw(gameDrawer);
                } catch (Exception ignore) { /*ignore.printStackTrace();*/ }
            }
        }
    }

    private synchronized void updateList() {
        for (Draw draw : tempRemove) {
            if (mapDrawSets.containsKey(draw.layer)) mapDrawSets.get(draw.layer).remove(draw);
        }

        for (Draw draw : tempAdd) {
            if (!mapDrawSets.containsKey(draw.layer)) mapDrawSets.put(draw.layer, new HashSet<>());
            mapDrawSets.get(draw.layer).add(draw);
        }

        tempRemove.clear();
        tempAdd.clear();
    }

    private synchronized void put(Draw o) {
        tempAdd.add(o);
    }
    private synchronized void remove(Draw o) {
        tempRemove.add(o);
    }

    //==============================================//
    public static class Draw {
        private Integer layer;
        private final GameObject target;
        private boolean draw;

        //==============================================//
        public Draw(GameObject target) {
            this(target, 0);
        }
        public Draw(GameObject target, Integer layer) {
            this.target = target;
            setLayer(layer);
        }

        //==============================================//
        public Integer getLayer() {
            return layer;
        }
        public void setLayer(Integer layer) {
            this.layer = layer;
            get().put(this);
            remove();
        }

        public void setDraw(boolean draw) {
            this.draw = draw;
        }
        public void remove() {
            get().remove(this);
        }

        public void draw(GameDrawer drawer) {
            if (draw) target.draw(drawer);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Draw)) return false;
            Draw draw = (Draw) o;
            return target.equals(draw.target);
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }
    }
}
