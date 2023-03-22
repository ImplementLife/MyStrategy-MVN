package game.myStrategy.lib.math.bezier;

import game.myStrategy.game.unit.button.Button;
import game.myStrategy.game.unit.button.RatioButton;
import game.myStrategy.lib.lists.Node;
import game.myStrategy.lib.math.Vec2D;

import java.util.LinkedList;

public class BezierControl extends BezierCurveSingle {
    private Button btnFirst;
    private Button btnMiddle;
    private Button btnEnd;

    public static class LinkNode {
        private Node<Vec2D> v1;
        private Node<Vec2D> v2;

        private BezierControl curve;

        private LinkedList<Node<Vec2D>> vOtherCurve;

        public LinkNode(BezierControl curve) {
            this.curve = curve;
            v1 = curve.getNodes().getFirst();
            v2 = curve.getNodes().getLast();
        }
    }

    public BezierControl(Vec2D first, Vec2D middle, Vec2D end) {
        super(first, middle, end);
        btnFirst = new RatioButton(first, 10, () -> {});
        btnMiddle = new RatioButton(middle, 10, () -> {});
        btnEnd = new RatioButton(end, 10, () -> {});
    }
}
