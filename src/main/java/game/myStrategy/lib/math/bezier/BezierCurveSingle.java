package game.myStrategy.lib.math.bezier;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.lists.ILLinkedList;
import game.myStrategy.lib.lists.Node;
import game.myStrategy.lib.math.Line;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Кривая Безье
 */
public class BezierCurveSingle extends GameObject implements BezierCurve {
    private final int COUNT_LINES = 60;

    private LinkedList<Line> value = new LinkedList<>();
    private ILLinkedList<Vec2D> points = new ILLinkedList<>();

    private LinkedList<Vec2D> buildPoints = new LinkedList<>();
    private Vec2D first;
    private Vec2D middle;
    private Vec2D end;

    private Vec2D center;
    private double length;
    private double radius;

    public GlobalNode gnStart;
    public GlobalNode gnEnd;
    private Node<Vec2D> stepNodeFromStart;
    private Node<Vec2D> stepNodeFromEnd;

    private BezierCurveSingle() {
        super(GameObjectType.CURVE);
        super.enableUpdateDraw();
    }

    //region Old
    public BezierCurveSingle(Vec2D first, Vec2D middle, Vec2D end) {
        super(GameObjectType.CURVE);
        update(first, middle, end);
        super.enableDraw();
    }
    public BezierCurveSingle(GlobalNode nodeFrom, Vec2D middle, Vec2D end) {
        this(nodeFrom.getItem(), middle, end);
        gnStart = nodeFrom;
    }
    public BezierCurveSingle(Vec2D first, Vec2D middle, GlobalNode nodeTo) {
        this(first, middle, nodeTo.getItem());
        gnEnd = nodeTo;
    }
    //endregion

    public static class Builder {
        private GlobalNode gnStart;
        private GlobalNode gnEnd;

        private Vec2D first;
        private Vec2D middle;
        private Vec2D end;

        public Builder setFirst(Vec2D pos) {
            first = pos;
            return this;
        }
        public Builder setStart(GlobalNode node) {
            gnStart = node;
            setFirst(node.getItem());
            return this;
        }
        public Builder setMiddle(Vec2D pos) {
            middle = pos;
            return this;
        }
        public Builder setEnd(Vec2D pos) {
            end = pos;
            return this;
        }
        public Builder setEnd(GlobalNode node) {
            gnEnd = node;
            setEnd(node.getItem());
            return this;
        }

        public BezierCurveSingle build() {
            BezierCurveSingle newInstance = new BezierCurveSingle();
            newInstance.gnStart = gnStart;
            newInstance.gnEnd = gnEnd;
            newInstance.first = first;
            newInstance.middle = middle;
            newInstance.end = end;
            newInstance.create(first, middle, end);
            newInstance.initNeighbour();
            return newInstance;
        }
    }
    private void initNeighbour() {
        GlobalNode.GNInfo gnInfoWayToStart = new GlobalNode.GNInfo(gnStart, stepNodeFromEnd, false);
        GlobalNode.GNInfo gnInfoWayToEnd = new GlobalNode.GNInfo(gnEnd, stepNodeFromStart, true);
        gnStart.addNeighbour(gnInfoWayToEnd);
        gnEnd.addNeighbour(gnInfoWayToStart);
    }

    //region Old
    @Override
    public List<GlobalNode> getGlobalNodes() {
        return null;
    }

    @Override
    public void update(Vec2D first, Vec2D middle, Vec2D end) {
        updateBuildPoints(first, middle, end);
        create(first, middle, end);
//        updatePoints();
//        updateLength();
        updateRadius();
        updateCenter();
    }

    @Override
    public LinkedList<Vec2D> getBuildPoints() {
        return buildPoints;
    }
    @Override
    public LinkedList<Line> getValue() {
        return value;
    }
    @Override
    public LinkedList<Vec2D> getPoints() {
        return points.toLinkedList();
    }
    @Override
    public ILLinkedList<Vec2D> getNodes() {
        return points;
    }
    @Override
    public Node<Vec2D> getNearest(Vec2D vec) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public double getRadius() {
        return radius;
    }
    @Override
    public Vec2D getCenter() {
        return center;
    }
    @Override
    public double getLength() {
        return length;
    }

    public void updateCenter() {
        Vec2D f2e = Vec2D.sub(first, end);
        double b = f2e.getLength() / 2;
        double angA = Math.acos(b/radius);

        center = Vec2D.newAngVec(end, radius, f2e.getAngle() - angA);

        d1 = Vec2D.getLength(center, middle);
        if (d1-1d > radius || d1+1d < radius ) {
            oc = center;
            center = Vec2D.newAngVec(end, radius, f2e.getAngle() + angA);
            b2 = true;
        }
    }
    private void updateLength() {
        this.length = 0;
        for (Line line : value) {
            length += line.getLength();
        }
    }
    private void updateRadius() {
        double angle = Vec2D.sub(middle, first).angBtw(Vec2D.sub(end, first));
        double a = Vec2D.sub(middle, end).getLength();
        radius = Math.abs(a / (2 * Math.sin(angle)));
    }
    private void updatePoints() {
        points.clear();
        points.add(value.getFirst().getP1());
        for (Line line : value) {
            points.add(line.getP2());
        }
    }
    private void updateBuildPoints(Vec2D first, Vec2D middle, Vec2D end) {
        buildPoints.clear();
        buildPoints.add(first);
        buildPoints.add(middle);
        buildPoints.add(end);

        this.first = first;
        this.middle = middle;
        this.end = end;
    }

    private void createAlt(Vec2D first, Vec2D middle, Vec2D end) {
        value.clear();
        Vec2D beginPoint = nextPoint(first, middle, 0);
        for (int i = 1; i <= COUNT_LINES; i ++) {
            Vec2D nextPoint = nextPoint(nextPoint(first, middle, i), nextPoint(middle, end, i), i);
            value.add(new Line(beginPoint, nextPoint));
            beginPoint = nextPoint;
        }
    }

    //endregion

    public void updateAlt(Vec2D first, Vec2D middle, Vec2D end) {

    }
    public void quickUpdate(Vec2D first, Vec2D middle, Vec2D end) {

    }

    private void create(Vec2D first, Vec2D middle, Vec2D end) {
        if (gnStart == null) gnStart = new GlobalNode(nextPoint(first, middle, 0));
        Node<Vec2D> currentNode = gnStart;
        points.add(currentNode.getItem());
        for (int i = 1; i <= COUNT_LINES; i++) {
            Vec2D nextPoint = getNextPoint(first, middle, end, i);
            if (i != COUNT_LINES) {
                currentNode = currentNode.setAfter(nextPoint);
                if (i == 1) stepNodeFromStart = currentNode;
            } else {
                if (gnEnd == null) gnEnd = new GlobalNode(nextPoint);
                stepNodeFromEnd = currentNode;
                currentNode = currentNode.setAfter(gnEnd);
            }
            points.add(currentNode.getItem());
        }
    }

    private Vec2D getNextPoint(Vec2D first, Vec2D middle, Vec2D end, int i) {
        return nextPoint(nextPoint(first, middle, i), nextPoint(middle, end, i), i);
    }
    private Vec2D nextPoint(Vec2D v1, Vec2D v2, int c) {
        Vec2D temp = Vec2D.sub(v2, v1);
        return Vec2D.newAngVec(v1, temp.getLength() * (double) c / COUNT_LINES, temp.getAngle());
    }

    //region For Debug
    Vec2D oc;
    double d1 = 0;
    boolean b2;
    Collision collision = new Collision(this);
    @Override
    public void draw(Drawer drawer) {
        //collision.draw(drawer);
        drawer.drawBezierCurve(this, Color.DARK_GRAY, 3);
    }
    //endregion

}
