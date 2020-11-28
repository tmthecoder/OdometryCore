package com.tejasmehta.OdometryCore.math;

/****
 * @author Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: PolarCoordinate
 * A class to store a PolarCoordinate and simplify polar and cartesian operations
 */
public class PolarCoordinate {
    private final double r;
    private final double theta;

    /**
     * A method to create a PolarCoordinate from a given CartesianCoordinate
     * @param cartesian - The CartesianCoordinate to convert
     * @return - The converted polar coordinate
     */
    public static PolarCoordinate fromCartesian(CartesianCoordinate cartesian) {
        double x = cartesian.getX();
        double y = cartesian.getY();
        double r = Math.sqrt(x * x + y * y);
        double theta = x != 0 ? Math.atan(y/x) : 0;
        return new PolarCoordinate(r, theta);
    }

    /**
     * A constructor for the PolarCoordinate class
     * @param r - The r value of the polar coordinate (distance from 0)
     * @param theta - The theta value of the polar coordinate (angle from 0)
     */
    public PolarCoordinate(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    /**
     * A getter for the r value
     * @return - The r value of the polar coordinate
     */
    public double getR() {
        return r;
    }

    /**
     * A getter for the theta value
     * @return - The theta value of the polar coordinate
     */
    public double getTheta() {
        return theta;
    }

    /**
     * A toString method for string information calls
     * @return - A string value containing the coordinate's r value and theta
     */
    @Override
    public String toString() {
        return "PolarCoordinate{" +
                "r=" + r +
                ", theta=" + theta +
                '}';
    }
}
