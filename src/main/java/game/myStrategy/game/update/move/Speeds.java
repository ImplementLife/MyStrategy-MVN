package game.myStrategy.game.update.move;

import game.myStrategy.Boot;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.Data;
import game.myStrategy.lib.threads.bt.DT;

import java.util.HashMap;
import java.util.Objects;

public class Speeds {
    private static final HashMap<Integer, Speeds> mapSpeeds = new HashMap<>();
    private static final Data<Speeds> data = new Data<>();

    public final float maxSpeedForward;    //Максимальная скорость движения вперёд
    public final float maxSpeedBack;       //Максимальная скорость движения назад
    public final float acceleration;       //Ускорение скорости движения
    public final float accelToBrake;       //Ускорение торможения

    public final float distToBrakeForward; //Длинна тормозного пути при движении вперёд
    public final float distToBrakeBack;    //Длинна тормозного пути при движении назад
    public final float distAccelForward;   //Длинна пути при разгоне вперёд
    public final float distAccelBack;      //Длинна пути при разгоне назад

    DT dt = Boot.getBean(UpdateService.class).getDt();


    protected static Speeds getSpeeds(float maxSpeed, float maxSpeedBack, float accel, float accelToBrake) {
        Speeds speeds;
        Integer hash = Objects.hash(maxSpeed, maxSpeedBack, accel, accelToBrake);
        if (mapSpeeds.containsKey(hash)) {
            speeds = mapSpeeds.get(hash);
        } else {
            speeds = new Speeds(maxSpeed, maxSpeedBack, accel, accelToBrake);
            mapSpeeds.put(hash, speeds);
        }
        return speeds;
    }

    //===   Constructor
    private Speeds(float maxSpeed, float maxSpeedBack, float accel, float accelToBrake) {
        this.maxSpeedForward = maxSpeed;
        this.maxSpeedBack = maxSpeedBack;
        this.acceleration = accel;
        this.accelToBrake = accelToBrake;

        this.distAccelForward = dist(maxSpeed, accel);
        this.distAccelBack = dist(maxSpeedBack, accel);

        this.distToBrakeForward = distToBrake(maxSpeed, accelToBrake);
        this.distToBrakeBack = distToBrake(maxSpeedBack, accelToBrake);
    }

    //===   Other methods
    public float notFullWayIntegral(double length) {
        float distAccel = 0;
        float distToBrake;
        float speed = 0;
        do {
            speed += dt.scalar(acceleration);
            distAccel += dt.scalar(speed);
            distToBrake = distToBrake(speed, accelToBrake);
        } while (!(distAccel + distToBrake > length));
        return distToBrake;
    }

    private float dist(float max, float accel) {
        float dist = 0;
        float speed = 0;
        while (speed < max) {
            speed += dt.scalar(accel);
            dist += dt.scalar(speed);
        }
        return dist;
    }

    private float distToBrake(float speed, float accel) {
        float dist = 0;
        while (speed > 0) {
            speed -= dt.scalar(accel);
            dist += dt.scalar(speed);
        }
        return dist;
    }

    //===   Override methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speeds speeds = (Speeds) o;
        return Float.compare(speeds.maxSpeedForward, maxSpeedForward) == 0 &&
            Float.compare(speeds.maxSpeedBack, maxSpeedBack) == 0 &&
            Float.compare(speeds.acceleration, acceleration) == 0 &&
            Float.compare(speeds.accelToBrake, accelToBrake) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxSpeedForward, maxSpeedBack, acceleration, accelToBrake);
    }
}
