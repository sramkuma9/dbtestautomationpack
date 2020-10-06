package bni.regression.libraries.common;

public class Util {
    public static double numberRound(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }

    public static String numberFormat(double value, int scale) {
        return String.format( "%."+scale+"f", value );
    }
}
