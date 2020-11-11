/****
 * Made by Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: PolarCoordinate
 * Package: com.tejasmehta.OdometryCore.math
 */
package com.tejasmehta.OdometryCore.math;


public class PolarCoordinate {
    private final double r;
    private final double theta;

    public static PolarCoordinate fromCartesian(CartesianCoordinate cartesian) {
        double x = cartesian.getX();
        double y = cartesian.getY();
        double r = Math.sqrt(x * x + y * y);
        double theta = x != 0 ? Math.atan(y/x) : 0;
        return new PolarCoordinate(r, theta);
    }

    public PolarCoordinate(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    public double getR() {
        return r;
    }

    public double getTheta() {
        return theta;
    }

    @Override
    public String toString() {
        return "PolarCoordinate{" +
                "r=" + r +
                ", theta=" + theta +
                '}';
    }
}
