package game.myStrategy.lib.threads.bt;

import static game.myStrategy.lib.math.TimeConstants.TIME_MILLIS_PER_SECOND;
import static game.myStrategy.lib.math.TimeConstants.TIME_NANO_PER_SECOND;

public class DT {
    private double value;

    DT(double value) {
        this.value = value;
    }

    //======================================//

    public double getValue() {
        return value;
    }

    public double scalar(double var) {
        return var * value;
    }

    //======================================//

    long getValueNano() {
        return (long) (value * TIME_NANO_PER_SECOND);
    }
    long getValueMillis() {
        return (long) (value * TIME_MILLIS_PER_SECOND);
    }

    void setValue(double value) {
        this.value = value;
    }
    void setValueNano(long valueNano) {
        this.value = (double) valueNano / TIME_NANO_PER_SECOND;
    }

    /**
     * Метод нужен только для балансировки потока по времини
     */
    void add() {
        value += value * 2/3;
    }
}
