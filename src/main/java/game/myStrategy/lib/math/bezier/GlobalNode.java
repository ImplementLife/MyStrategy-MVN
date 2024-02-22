package game.myStrategy.lib.math.bezier;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.Id;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.lists.Node;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.control.Control;
import game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GlobalNode extends Node<Vec2D> {
    private Id id;
    private Map<Id, GNInfo> neighbours = new HashMap<>();

    public GlobalNode(Vec2D element) {
        super(null, element, null);
        GlobalNode globalNode = this;

        //region Temp for debug
        //TODO: refactor it
        GameObject gameObject = new GameObject(GameObjectType.DEV) {
            @Override
            public void draw(Drawer drawer) {
                Vec2D globalMousePos = MouseMotionListener.getGlobalMousePos();
                Vec2D item = getItem();
                double length = Vec2D.getLength(globalMousePos, item);
                if (length < 50) {
                    Control.get().bezierControl.globalNodeInSelect = globalNode;
                    drawer.drawCircle(item, 50, Color.magenta, 2);
                } else {
                    if (globalNode.equals(Control.get().getBezierControl().globalNodeInSelect)) {
                        Control.get().bezierControl.globalNodeInSelect = null;
                    }
                }
            }
        };

        gameObject.enableDraw();
        id = gameObject.getId();
        //endregion
    }

    public Id getId() {
        return id;
    }

    @Override //only for invoke from the replace method, don't use in other place
    public <N extends Node<Vec2D>> N setBefore(N node) {
        N n = super.setBefore(node);
        prev = null;
        return n;
    }
    @Override //only for invoke from the replace method, don't use in other place
    public <N extends Node<Vec2D>> N setAfter(N node) {
        N n = super.setAfter(node);
        next = null;
        return n;
    }

    @Override //for try to get a Next node use the getNodeWayToNeighbour() method
    public Node<Vec2D> getNext() {
        return null;
    }
    @Override //for try to get a Prev node use the getNodeWayToNeighbour() method
    public Node<Vec2D> getPrev() {
        return null;
    }


    public GNInfo getNodeWayToNeighbour(Id idTo) {
        return neighbours.get(idTo);
    }

    public Collection<GNInfo> getNeighbours() {
        return neighbours.values();
    }

    public void addNeighbour(GNInfo node) {
        neighbours.put(node.gn.id, node);
    }

//    public void removeNeighbour(GlobalNode node) {
//        neighbours.remove(node.id);
//    }

    public static class GNInfo {
        private GlobalNode gn;
        private Node<Vec2D> snTo;
        private boolean moveByNext; //if false move by prev

        public GNInfo(GlobalNode gn, Node<Vec2D> snTo, boolean moveByNext) {
            this.gn = gn;
            this.snTo = snTo;
            this.moveByNext = moveByNext;
        }

        public GlobalNode getGn() {
            return gn;
        }
        public Node<Vec2D> getSnTo() {
            return snTo;
        }
        public boolean isMoveByNext() {
            return moveByNext;
        }
    }
}
