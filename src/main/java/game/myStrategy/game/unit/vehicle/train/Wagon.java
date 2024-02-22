package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.Boot;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.game.unit.button.RatioButton;
import game.myStrategy.game.unit.button.Button;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.threads.bt.DT;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Wagon extends Unit {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.WAGON;

    private Image img = new ImageIcon("resource/entity/equipment/railEquipment/wagon/wagon.png").getImage();

    //=======================================//
    private final Button button;

    private final Vec2D frontTrolley;
    private final Vec2D backTrolley;
    private WayPoint nextWayPoint;

    //Направление движения вперёд или назад
    private boolean move = true;

    private final Vec2D posNow;
    private final float LENGTH = 400;
    private final float MAX_SPEED = 2f;
    private float speed = 0f;
    private double angleNow;

    private ArrayList<WayPoint> way;
    public void setWay(ArrayList<WayPoint> way) {
        this.way = new ArrayList<>(way);
    }

    public Wagon(RailWay railWay, double lengthOfBack) {
        super(TYPE);
        WayPoint frontWayPoint = railWay.getRight();
        WayPoint backWayPoint = railWay.getLeft();

        Vec2D temp = Vec2D.sub(frontWayPoint, backWayPoint);
        angleNow = (float) temp.getAngle();
        this.posNow = Vec2D.newAngVec(backWayPoint, lengthOfBack, angleNow);

        frontTrolley = Vec2D.newAngVec(posNow, LENGTH/2, angleNow);
        backTrolley = Vec2D.newAngVec(posNow, LENGTH/2, angleNow - Math.PI);

        nextWayPoint = frontWayPoint;

        button = new RatioButton(posNow, 30, () -> t = !t);
        super.enableUpdateDraw();
    }
    double angleFromBackTrolleyToNextPoint;

    private boolean t = false;
    private void move() {
        if (speed < MAX_SPEED) speed += 0.5;
        if (move) {
            //Задняя тележка
            Vec2D dirBackTrolleyToNextPoint = Vec2D.sub(nextWayPoint, backTrolley);

            if (dirBackTrolleyToNextPoint.getLength() < speed) {
                angleFromBackTrolleyToNextPoint = Vec2D.getAngle(nextWayPoint, nextWayPoint.getNext());
                backTrolley.setXY(Vec2D.newAngVec(nextWayPoint, speed - dirBackTrolleyToNextPoint.getLength(), angleFromBackTrolleyToNextPoint));
                nextWayPoint = nextWayPoint.getNext();
            } else {
                angleFromBackTrolleyToNextPoint = dirBackTrolleyToNextPoint.getAngle();
                backTrolley.addAngVec(speed, angleFromBackTrolleyToNextPoint);
            }

            //Передняя тележка
            dirBackTrolleyToNextPoint = Vec2D.sub(backTrolley, nextWayPoint);
            if (dirBackTrolleyToNextPoint.getLength() < LENGTH) {
                double newTempAngle = Vec2D.scalarProd(dirBackTrolleyToNextPoint, Vec2D.sub(nextWayPoint.getNext(), nextWayPoint));
                Vec2D temp = help(nextWayPoint.getNext(), dirBackTrolleyToNextPoint.getLength(), newTempAngle);
                frontTrolley.setXY(temp);
            } else {
                frontTrolley.addAngVec(speed, angleFromBackTrolleyToNextPoint);
                //System.out.println();
            }
            angleNow = Vec2D.getAngle(backTrolley, frontTrolley);
            posNow.setXY(Vec2D.newAngVec(backTrolley, LENGTH/2, angleNow));
        }
        if (t) Boot.getBean(GameDrawService.class).getCamera().tracking(Vec2D.newAngVec(new Vec2D(), speed, angleNow));
    }

    private Vec2D help(Vec2D dir, double length, double angle) {
        double length2 = getLength(angle, LENGTH, length);
        return Vec2D.newAngVec(nextWayPoint, length2, Vec2D.getAngle(nextWayPoint, dir));
    }
    private static double getLength(double ang, double length, double dir) {
        return length * (Math.sin(Math.PI - ang - Math.asin((dir / length) * Math.sin(ang))) / Math.sin(ang));
    }

    @Override
    public void moveTo(Vec2D pos) {

    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public Vec2D getPos() {
        return null;
    }

    @Override
    public float getSize() {
        return 0;
    }

    @Override
    public void update(DT dt) {
        move();
        button.setPos(posNow);
    }

    @Override
    public boolean kill() {
        return false;
    }

    @Override
    public void draw(Drawer drawer) {
        drawer.drawCircle(nextWayPoint, 100, Color.GREEN, 5);
        drawer.drawImage(posNow, img, new Angle(angleNow));
        drawer.drawString(posNow.clone().subX(100).subY(100), "angle = " + angleNow, 20, Color.BLACK);
        //drawer.drawRect(posNow, new Vec2D(200, 400), new Angle(angleNow), Color.RED, 1);
        drawer.drawCircle(backTrolley, 50, Color.RED, 2);
        drawer.drawCircle(frontTrolley, 50, Color.BLUE, 2);
    }

//    private static class Searcher {
//        private ArrayList<LinkedList<Id>> ways = new ArrayList<>();
//        private LinkedList<Id> way;
//
//        //========================================//
//
//        private void createWays(Id baseNow, LinkedList<Id> way) {
//            LinkedList<Id> tempWay = new LinkedList<>(way);
//            tempWay.addEvent(baseNow);
//            if (baseNow.equals(targetBase)) {
//                ways.addEvent(tempWay);
//                return;
//            }
//
//            for (Id link : ((Base) getObjFromId(baseNow)).getLinkBases()) {
//                if (!tempWay.contains(link)) {
//                    createWays(link, tempWay);
//                }
//            }
//        }
//
//        //========================================//
//
//        public void selectWay() {
//            createWays(baseNow, new LinkedList<Id>());
//            //Выбор случайного пути
//            {
//                int countBases = ways.size() - 1;
//                way = ways.get((int) Math.round(countBases * Math.random()));
//                ways.clear();
//            }
//        }
//        public void goToNextBase() {
//            if (!move) {
//                if (!way.isEmpty()) {
//                    //if (!way.isEmpty()) {
//                    nextBase = way.removeFirst();
//                    posEnd = new Vector2D(((Base) getObjFromId(nextBase)).getPosForUnit());
//                    move = true;
//                    //}
//                } else {
//                    selectWay();
//                }
//            }
//        }
//
//    }

}
