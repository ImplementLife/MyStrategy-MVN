package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.lib.math.Vec2D;

import java.util.Objects;

public class WayPoint extends Vec2D {
    WayPoint previousWayPoint;
    WayPoint nextWayPoint;
    private boolean deadWay = true;

    //=======================================//
    public WayPoint(Vec2D pos) {
        super(pos);
        RailWay.allWayPoint.add(this);
    }

    //=======================================//
    public boolean isDeadWay() {
        return deadWay;
    }
    private void setDeadWay() {
        deadWay = previousWayPoint == null || nextWayPoint == null;
    }

    public void setNext(WayPoint nextWayPoint) {
        this.nextWayPoint = nextWayPoint;
        setDeadWay();
    }
    public void setPrevious(WayPoint previousWayPoint) {
        this.previousWayPoint = previousWayPoint;
        setDeadWay();
    }

    public WayPoint getNext(WayPoint wayPoint) {
        if (nextWayPoint.equals(wayPoint)) return previousWayPoint;
        if (previousWayPoint.equals(wayPoint)) return nextWayPoint;
        return null;
    }
    public WayPoint getNext() {
        return nextWayPoint;
    }
    public WayPoint getPrevious() {
        return previousWayPoint;
    }

    public WayPoint createNext(Vec2D pos) {
        WayPoint newWayPoint = new WayPoint(pos);

        this.nextWayPoint = newWayPoint;
        newWayPoint.previousWayPoint = this;
        return newWayPoint;
    }

    //=======================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WayPoint wayPoint = (WayPoint) o;
        return deadWay == wayPoint.deadWay &&
                Objects.equals(previousWayPoint, wayPoint.previousWayPoint) &&
                Objects.equals(nextWayPoint, wayPoint.nextWayPoint) &&
                Objects.equals(this, wayPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousWayPoint, nextWayPoint, deadWay, this);
    }
}
