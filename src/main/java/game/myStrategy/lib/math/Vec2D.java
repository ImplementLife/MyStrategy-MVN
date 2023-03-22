package game.myStrategy.lib.math;

import org.joml.Vector2d;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Vec2D extends Vector2d implements Serializable, Cloneable {

    //region Constructors
    public Vec2D() {
        x = 0;
        y = 0;
    }
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vec2D(Vec2D vec) {
        this(vec.x, vec.y);
    }
    public Vec2D(Dimension vec) {
        this(vec.width, vec.height);
    }
    //endregion

    //region Get
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public int getIntX() {
        return (int) x;
    }
    public int getIntY() {
        return (int) y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
    //endregion

    //region Math

    /**
     * Angle by clockwise
     * @return value in radians
     */
    public double getAngle() {
        if (x != 0) {
            if (x > 0) return Math.PI - Math.acos(y / getLength());
            else return Math.PI + Math.acos(y / getLength());
        } else {
            //if (y <= 0) return 0;????
            if (y < 0) return 0;
            else return Math.PI;
        }
    }

    /**
     * Умножение на число
     * @param s == all
     * @return this
     */
    public Vec2D scalar(double s) {
        x *= s;
        y *= s;
        return this;
    }

    /**
     * Деление на число
     * @param s != 0
     * @return this
     */
    public Vec2D antScalar(double s) {
        x /= s;
        y /= s;
        return this;
    }

    /**
     * Округление в меньшую сторону
     * @return this
     */
    public Vec2D floor() {
        x = Math.floor(x);
        y = Math.floor(y);
        return this;
    }

    /**
     * Округление в большую сторону
     * @return this
     */
    public Vec2D ceil() {
        x = Math.ceil(x);
        y = Math.ceil(y);
        return this;
    }

    /**
     * Математическое округление
     * @return this
     */
    public Vec2D round() {
        x = Math.round(x);
        y = Math.round(y);
        return this;
    }

    public Vec2D add(Vec2D vec) {
        return this.add(vec.x, vec.y);
    }
    public Vec2D sub(Vec2D vec) {
        return this.sub(vec.x, vec.y);
    }

    public Vec2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }
    public Vec2D sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2D addX(double d) {
        x += d;
        return this;
    }
    public Vec2D addY(double d) {
        y += d;
        return this;
    }

    public Vec2D subX(double d) {
        x -= d;
        return this;
    }
    public Vec2D subY(double d) {
        y -= d;
        return this;
    }

    /**
     * Построить новый вектор под углом от текущего
     * @param angle - in Radian
     * @return this
     */
    public Vec2D addAngVec(double length, double angle) {
        x += Math.sin(angle) * length;
        y -= Math.cos(angle) * length;
        return this;
    }

    /**
     * Проверка на положение вектора в прямоугольнике (не повёрнутом)
     */
    public boolean inRect(Vec2D firstPos, Vec2D endPos) {
        if (x < firstPos.x || y < firstPos.y) return false;
        else return !(x > endPos.x) && !(y > endPos.y);
    }

//    public Vec2D projectTo(Vec2D vec) {
//
//    }
//    public Vec2D rejectTo(Vec2D vec) {
//
//    }
//    public Vec2D reflectTo(Vec2D vec) {
//
//    }

    /**
     * Angle between vectors
     * @param vec vector to calculate angle to this
     * @return value in radians
     */
    public double angBtw(Vec2D vec) {
        return this.angle(vec);
    }

    public Vec2D project(Vec2D vec) {
        Vec2D thisNorm = this.normalize();

        double scalarProjLength = vec.dot(thisNorm);
        Vector2d res = thisNorm.mul(scalarProjLength);
        return (Vec2D) res;
    }

    public Vec2D reject(Vec2D vec) {
        Vec2D project = this.project(vec);
        return vec.clone().sub(project);
    }
//    public Vec2D reflect(Vec2D vec) {
//
//    }

    //endregion

    //region Override Vector2d

    @Override
    public Vec2D normalize() {
        return (Vec2D) super.normalize(this.clone());
    }

    //endregion

    //region Setters
    public Vec2D x(double x) {
        this.x = x;
        return this;
    }
    public Vec2D y(double y) {
        this.y = y;
        return this;
    }
    public Vec2D setXY(Vec2D vec) {
        x = vec.getX();
        y = vec.getY();
        return this;
    }
    public Vec2D setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public void setAngVec(Vec2D vec, double length, double angle) {
        this.setXY(vec);
        this.addAngVec(length, angle);
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    //endregion

    //region Override Object
    @Override
    public Vec2D clone() {
        return new Vec2D(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec2D) {
            return x == ((Vec2D)obj).x && y == ((Vec2D)obj).y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[X: " + x + "; Y: " + y + ";]";
    }
    //endregion

    //region Static
    /**
     * Нормализация вектора
     * @return new Vec2D();
     */
    public static Vec2D norm(Vec2D vec) {
        Vec2D res = vec.clone();
        res.normalize();
        return res;
    }

    /**
     * Умножение на скаляр
     * @return new Vec2D();
     */
    public static Vec2D scalar(Vec2D vec, double s) {
        return new Vec2D(vec).scalar(s);
    }

    /**
     * Деление на скаляр
     * @return new Vec2D();
     */
    public static Vec2D antiScalar(Vec2D vec, double s) {
        return new Vec2D(vec).antScalar(s);
    }

    /**
     * Произведение векторов
     * @return new Vec2D();
     */
    public static Vec2D prod(Vec2D vec1, Vec2D vec2) throws Exception {
        throw new Exception("Not Implemented.");
        //Vec2D vec = new Vec2D();
        //return null;
    }

    /**
     * Скалярное произведение
     */
    public static double scalarProd(Vec2D a, Vec2D b) {
        double d1 = (a.x * b.x) + (a.y * b.y);
        double d2 = a.getLength() * b.getLength();
        return Math.acos(d1 / d2);
    }

    /**
     * Новый вектор под углом и с заданой длиной относительно переданного
     * @return new Vec2D();
     */
    public static Vec2D newAngVec(Vec2D vec, double length, double angle) {
        return new Vec2D(vec).addAngVec(length, angle);
    }

    /**
     * Новый вектор под углом и с заданной длиной
     * @return new Vec2D();
     */
    public static Vec2D newAngVec(double length, double angle) {
        return new Vec2D().addAngVec(length, angle);
    }

    /**
     * Новый вектор под углом
     * @return new Vec2D();
     */
    public static Vec2D newAngVec(double angle) {
        return new Vec2D().addAngVec(1, angle);
    }

    /**
     * Новый вектор под случайным углом
     * @return new Vec2D();
     */
    public static Vec2D newRandomVec(Vec2D vec, double radius) {
        return newAngVec(vec, radius * Math.random(), Angle.E * Math.random());
    }

    /**
     * Угол вектора
     *
     * (по часовой стрелке от отрицательной оси Y)
     */
    public static double getAngle(Vec2D firstPos, Vec2D lastPos) {
        return Vec2D.sub(lastPos, firstPos).getAngle();
    }

    /**
     * Сумма двух векторов
     */
    public static Vec2D add(Vec2D vec1, Vec2D vec2) {
        return new Vec2D(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    /**
     * Разница двух векторов
     */
    public static Vec2D sub(Vec2D vec1, Vec2D vec2) {
        return new Vec2D(vec1.x - vec2.x,vec1.y - vec2.y);
    }

    /**
     * Длинна из двух векторов
     */
    public static double getLength(Vec2D vec1, Vec2D vec2) {
        return Vec2D.sub(vec1, vec2).getLength();
    }

    /**
     * Средний вектор из множества
     */
    public static Vec2D midVec(List<Vec2D> vectors) {
        Vec2D vec = new Vec2D();
        for (Vec2D v : vectors) {
            vec.x += v.x;
            vec.y += v.y;
        }
        vec.x /= vectors.size();
        vec.y /= vectors.size();
        return vec;
    }

    private static double ceil(double d) {
        d = (int)(d * 1000);
        return d / 1000;
    }
    //endregion

}
