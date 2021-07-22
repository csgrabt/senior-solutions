package microsevices.training.locations;

import lombok.Data;


public enum Type {

    LAT(-90l, 90l), LON(-180l, 180l);

    private double min;
    private double max;

    Type(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
