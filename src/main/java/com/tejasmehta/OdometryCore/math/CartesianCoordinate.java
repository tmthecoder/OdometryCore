package com.tejasmehta.OdometryCore.math;

/****
 * @author Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: LocalCoordinate
 * A class to signify a cartesian coordinate and to simplify polar and cartesian operations
 */
public class CartesianCoordinate {
    private final double x;
    private final double y;

    /**
     * A method to create a CartesianCoordinate from a given PolarCoordinate
     * @param polar - The polar coordinate to convert to a cartesian one
     * @return - The cartesian coordinate of the polar value
     */
    public static CartesianCoordinate fromPolar(PolarCoordinate polar) {
        double r = polar.getR();
        double theta = polar.getTheta();
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new CartesianCoordinate(x, y);
    }

    /**
     * The constructor for the CartesianCoordinate class
     * @param x - The x value of the coordinate
     * @param y - The y value of the coordinate
     */
    public CartesianCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A getter for the coordinate's x value
     * @return - the coordinate's x value
     */
    public double getX() {
        return x;
    }

    /**
     * A getter for the coordinate's y value
     * @return - the coordinate's y value
     */
    public double getY() {
        return y;
    }

    /**
     * Method to handle toString calls on the object
     * @return - A string with the coordinates x and y values
     */
    @Override
    public String toString() {
        return "CartesianCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
