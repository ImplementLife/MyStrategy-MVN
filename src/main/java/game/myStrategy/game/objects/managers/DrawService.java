package game.myStrategy.game.objects.managers;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.draw.drawer.GameDrawer;
import game.myStrategy.lib.noConcurrent.NoConcurrentList;
import game.myStrategy.lib.noConcurrent.NoConcurrentMap;


public class DrawService {
    //region Singleton
    private static DrawService instance;
    public static DrawService get() {
        if (instance == null) instance = new DrawService();
        return instance;
    }
    private DrawService() {}
    //endregion

    private final NoConcurrentMap<Integer, NoConcurrentList<Draw>> mapDraw = new NoConcurrentMap<>();

    public void iterate(GameDrawer gameDrawer) {
        mapDraw.forEach(h -> h.forEach(d -> d.draw(gameDrawer)));
    }

    private void put(Draw d) {
        NoConcurrentList<Draw> tempList;
        if (!mapDraw.containsKey(d.layer)) {
            tempList = new NoConcurrentList<>();
            mapDraw.put(d.layer, tempList);
        } else {
            tempList = mapDraw.get(d.layer);
        }
        tempList.add(d);
    }

    private void remove(Draw d) {
        if (mapDraw.containsKey(d.layer)) mapDraw.get(d.layer).remove(d);
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
            if (this.layer == null) this.layer = -100;
            remove();
            this.layer = layer;
            get().put(this);
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
