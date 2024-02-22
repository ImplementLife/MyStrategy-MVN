package game.myStrategy.lib.math.bezier;

import game.myStrategy.game.context.Context;
import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.lists.Node;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.threads.bt.DT;
import game.myStrategy.ui.game.gamePanel.GamePanel;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BezierMovePoint extends GameObject {
    //region Old
    private List<BezierCurve> curves;
    private double anglePosToNextPoint;
    private Node<Vec2D> nextPoint;
    private double speed;
    //endregion

    private Vec2D pos;
    private Node<Vec2D> currNode;
    boolean directionNext;

    public BezierMovePoint(Node<Vec2D> startNode) {
        super(GameObjectType.DEV);
        this.pos = startNode.getItem().clone();
        this.currNode = startNode;
        enableUpdateDraw();
    }

    final int skipTick = 5;
    int skipTickCounter = 0;
    public void update(DT dt) {
        if (skipTickCounter < skipTick) {
            skipTickCounter++;
            return;
        }

        Node<Vec2D> temp;
        if (directionNext) {
            temp = currNode.getNext();
        } else {
            temp = currNode.getPrev();
        }
        if (temp == null) {
            GlobalNode node = (GlobalNode) currNode;
            Collection<GlobalNode.GNInfo> neighbours = node.getNeighbours();
            if (neighbours.size() <= 0) return;
            GlobalNode.GNInfo info = neighbours.stream().toList().get((int) (neighbours.size() * Math.random()));
            temp = info.getSnTo();
            directionNext = info.isMoveByNext();
        }

        skipTickCounter = 0;
        currNode = temp;

        pos.setXY(currNode.getItem());
    }

    //region Old
    public void updateAlt(DT dt) {
        double scalarSpeed = dt.scalar(speed);
        Vec2D dirPosToNextPoint = Vec2D.sub(nextPoint.getItem(), pos);

        if (dirPosToNextPoint.getLength() < scalarSpeed) {
            anglePosToNextPoint = Vec2D.getAngle(nextPoint.getItem(), nextPoint.getNext().getItem());
            double speedOfNewDir = scalarSpeed - dirPosToNextPoint.getLength();

            pos.setXY(Vec2D.newAngVec(nextPoint.getItem(), speedOfNewDir, anglePosToNextPoint));
            nextPoint = nextPoint.getNext();
        } else {
            anglePosToNextPoint = dirPosToNextPoint.getAngle();
            pos.addAngVec(scalarSpeed, anglePosToNextPoint);
        }
    }
    public void moveTo(Vec2D vec) {
        BezierCurve bcStart;
        BezierCurve bcEnd;
        double bcS = Double.MAX_VALUE;
        double bcE = Double.MAX_VALUE;
        for (BezierCurve c : curves) {
            if (bcS < c.getCenter().distanceSquared(pos)) bcStart = c;
            if (bcE < c.getCenter().distanceSquared(vec)) bcEnd = c;
        }
    }
    boolean modeChangeDir = true;
    public void updateNextGen(DT dt, double speed) {
        if (nextPoint == null) return;
        double scalarSpeed = dt.scalar(speed);
        boolean forwardDir = scalarSpeed >= 0;
        Vec2D dirPosToNextPoint = Vec2D.sub(nextPoint.getItem(), pos);

        if (dirPosToNextPoint.getLength() >= scalarSpeed) {
            anglePosToNextPoint = dirPosToNextPoint.getAngle();
            pos.addAngVec(scalarSpeed, anglePosToNextPoint);
        } else {
            double speedOfNewDir = scalarSpeed;
            Vec2D dir = dirPosToNextPoint;
            Node<Vec2D> point = nextPoint;
            Node<Vec2D> next;
            do {
                next = point.getNext() != null ? point.getNext() : point.getPrev();
                speedOfNewDir -= dir.getLength();
                dir = Vec2D.sub(point.getItem(), next.getItem());
                point = next;
            } while (dir.getLength() < speedOfNewDir);

            pos.setXY(Vec2D.newAngVec(point.getPrev().getItem(), speedOfNewDir, dir.getAngle()));
            nextPoint = point;
        }
    }
    private Node<Vec2D> getNext(Node<Vec2D> current, boolean forwardDir) {
        Node<Vec2D> result;
        if (forwardDir) {
            result = current.getNext();
            if (modeChangeDir && result == null) {
                result = current.getPrev();
            }
        } else {
            result = current.getPrev();
            if (modeChangeDir && result == null) {
                result = current.getNext();
            }
        }
        return result;
    }
    //endregion

    @Override
    public void draw(Drawer drawer) {
        drawer.drawCircle(pos, 15, Color.red, 2);
    }
}
