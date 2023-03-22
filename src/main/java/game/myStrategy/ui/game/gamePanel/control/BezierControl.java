package game.myStrategy.ui.game.gamePanel.control;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.math.bezier.BezierCurveSingle;
import game.myStrategy.lib.math.bezier.BezierMovePoint;
import game.myStrategy.lib.math.bezier.GlobalNode;
import game.myStrategy.ui.game.gamePanel.events.Event;
import game.myStrategy.ui.game.gamePanel.listener.Listener;

import java.awt.*;
import java.util.Map;

import static game.myStrategy.game.objects.managers.GameObjectType.DECAL;

public class BezierControl {
    public BezierControl(Map<String, Integer> keys) {
        this.keys = keys;
    }
    private Map<String, Integer> keys;

    private boolean createBezierCurveEnable;
    private TempLineDraw globalCursor = new TempLineDraw();
    private TempVecDraw s, m, om, e;
    private BezierCurveSingle currentCurve;
    public GlobalNode globalNodeInSelect, startNode, endNode;

    private static class TempVecDraw extends GameObject {
        Vec2D v;
        String s;
        public TempVecDraw(Vec2D v, String s) {
            super(DECAL);
            this.v = v;
            this.s = s;
            enableDraw();
        }

        @Override
        public void draw(Drawer drawer) {
            drawer.drawCircle(v, 10, Color.magenta, 2);
            drawer.drawString(v, s, 14, Color.blue);
        }
    }
    private static class TempLineDraw extends GameObject {
        Vec2D mouse = Listener.getGlobalMousePos(), mirror, oldEnd;
        final float drawRadius = 10;
        public TempLineDraw() {
            super(DECAL);
        }

        public void setOldEnd(Vec2D vec) {
            oldEnd = vec;
        }

        @Override
        public void update() {
            if (oldEnd != null) {
                mirror = Vec2D.newAngVec(oldEnd, Vec2D.getLength(oldEnd, mouse)-drawRadius, Vec2D.getAngle(oldEnd, mouse)-Math.PI);
            }
        }

        @Override
        public void draw(Drawer drawer) {
            if (mirror != null && oldEnd != null) {
                drawer.drawLine(mouse.clone().addAngVec(mouse.getAngle()-Math.PI, drawRadius), mirror, Color.magenta, 2);
                drawer.drawCircle(mirror, drawRadius, Color.magenta, 2);
            }
            drawer.drawCircle(mouse, drawRadius, Color.magenta, 2);
        }
    }

    void bezierCurve(Event e) {
        if (e.isReleased(keys.get("newCurveEnable"))) {
            createBezierCurveEnable = !createBezierCurveEnable;
            if (createBezierCurveEnable) {
                globalCursor.enableUpdateDraw();
            } else {
                globalCursor.disableUpdateDraw();
                globalCursor.setOldEnd(null);
                if (s != null) s.delete();
                if (m != null) m.delete();
                if (om != null) om.delete();
                if (this.e != null) this.e.delete();

                s = om = m = this.e = null;
                currentCurve = null;
                startNode = endNode = null;
            }
        }
        if (createBezierCurveEnable && e.isClicked(keys.get("newCurve"))) {
            if (s == null) {
                s = new TempVecDraw(Listener.getGlobalMousePos().clone(), "s");
                startNode = globalNodeInSelect;
                return;
            }

            if (m == null) {
                m = new TempVecDraw(Listener.getGlobalMousePos().clone(), "m");
                return;
            }
            if (this.e == null) {
                this.e = new TempVecDraw(Listener.getGlobalMousePos().clone(), "e");
                globalCursor.setOldEnd(this.e.v);
                endNode = globalNodeInSelect;
            }
            //region Create
            BezierCurveSingle.Builder builder = new BezierCurveSingle.Builder()
                .setFirst(s.v)
                .setMiddle(m.v)
                .setEnd(this.e.v)
            ;
            if (currentCurve != null) {
                builder.setStart(currentCurve.gnEnd);
            } else {
                if (startNode != null) {
                    builder.setStart(startNode);
                }
                if (endNode != null) {
                    builder.setEnd(endNode);
                }
            }
            currentCurve = builder.build();
            new BezierMovePoint(currentCurve.gnStart);
            //endregion

            s.delete();
            if (om != null) {
                om.delete();
            }
            this.e.delete();

            s = this.e;
            om = m;
            m = null;
            this.e = null;
        }
    }
}
