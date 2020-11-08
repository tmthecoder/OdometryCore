package com.tejasmehta.OdometryCore.localization;

/****
 * Made by Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: OdometryPosition
 * Package: com.tejasmehta.com.tejasmehta.OdometryCore.OdometryCore*
 */

public class OdometryPosition {
    private final double x;
    private final double y;
    private final double heading;

    public OdometryPosition(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = Math.toDegrees(heading);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeading() {
        return heading;
    }
}
