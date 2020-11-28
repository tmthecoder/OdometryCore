package com.tejasmehta.OdometryCore.localization;

/****
 * @author  Tejas Mehta
 * Made on Thursday, November 12, 2020
 * File Name: EncoderTicks
 * A class to store the Odometry position in the format of an x coordinate, a y coordinate, and an angle measure
 */
public class OdometryPosition {
    private final double x;
    private final double y;
    private final double heading;
    private final HeadingUnit unit;

    /**
     * The constructor for the OdometryPosition class
     * @param x - The x value on a cartesian plane (in inches)
     * @param y - The y value on a cartesian plane (in inches)
     * @param heading - The robot's current heading (in degrees or radians)
     * @param unit - The unit for the heading (either HeadingUnit.degrees or HeadingUnit.radians)
     */
    public OdometryPosition(double x, double y, double heading, HeadingUnit unit) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.unit = unit;
    }

    /**
     * A getter for the x value
     * @return - The robot's x value (in inches)
     */
    public double getX() {
        return x;
    }

    /**
     * A getter for the y value
     * @return - The robot's y value (in inches)
     */
    public double getY() {
        return y;
    }

    /**
     * A getter for the robot's heading
     * @return - The robot's heading (in degrees)
     */
    public double getHeadingDegrees() {
        return unit == HeadingUnit.DEGREES ? heading : Math.toDegrees(heading);
    }

    /**
     * A getter for the robot's heading
     * @return - The robot's heading (in radians)
     */
    public double getHeadingRadians() {
        return unit == HeadingUnit.RADIANS ? heading : Math.toDegrees(heading);
    }
}
