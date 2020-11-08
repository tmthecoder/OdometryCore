/****
 * Made by Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: LocalCoordinate
 * Package: com.tejasmehta.OdometryCore.math
 */
package com.tejasmehta.OdometryCore.math;

public class CartesianCoordinate {
    private final double x;
    private final double y;

    public static CartesianCoordinate fromPolar(PolarCoordinate polar) {
        double r = polar.getR();
        double theta = polar.getTheta();
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new CartesianCoordinate(x, y);
    }

    public CartesianCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "CartesianCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
