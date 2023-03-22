package game.myStrategy.lib.math.bezier;

import game.myStrategy.lib.lists.ILLinkedList;
import game.myStrategy.lib.lists.Node;
import game.myStrategy.lib.math.Line;
import game.myStrategy.lib.math.Vec2D;

import java.util.LinkedList;
import java.util.List;

public interface BezierCurve {

    List<GlobalNode> getGlobalNodes();

    void update(Vec2D first, Vec2D middle, Vec2D end);

    LinkedList<Vec2D> getBuildPoints();

    LinkedList<Line> getValue();

    LinkedList<Vec2D> getPoints();

    ILLinkedList<Vec2D> getNodes();

    Node<Vec2D> getNearest(Vec2D vec);

    double getLength();

    double getRadius();

    Vec2D getCenter();
}
