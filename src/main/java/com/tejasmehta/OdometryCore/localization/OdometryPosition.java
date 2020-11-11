package com.tejasmehta.OdometryCore.localization;

/****
 * Made by Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: OdometryPosition
 * Package: com.tejasmehta.com.tejasmehta.OdometryCore.old.OdometryCore*
 */

public class OdometryPosition {
    private final double x;
    private final double y;
    private final double heading;
    private final HeadingUnit unit;

    public OdometryPosition(double x, double y, double heading, HeadingUnit unit) {
        this.x = x;
        this.y = y;
        this.heading = Math.toDegrees(heading);
        this.unit = unit;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeadingDegrees() {
        return unit == HeadingUnit.DEGREES ? heading : Math.toDegrees(heading);
    }

    public double getHeadingRadians() {
        return unit == HeadingUnit.RADIANS ? heading : Math.toDegrees(heading);
    }
}
