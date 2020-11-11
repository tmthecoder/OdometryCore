package com.tejasmehta.OdometryCore.old;

import com.tejasmehta.OdometryCore.localization.HeadingUnit;
import com.tejasmehta.OdometryCore.localization.OdometryPosition;
import com.tejasmehta.OdometryCore.math.CartesianCoordinate;
import com.tejasmehta.OdometryCore.math.CoreMath;
import com.tejasmehta.OdometryCore.math.PolarCoordinate;

@Deprecated
public class OdometryCore {

    final double WHEEL_DIAMETER = 1.5;
    final double CPR = 1450;
    final double TICKS_TO_INCH = (Math.PI * WHEEL_DIAMETER) / CPR;

    private static OdometryCore instance;
    private double previousInchesLeft;
    private double previousInchesRight;
    private double previousInchesFB;
    private double previousHeading;
    private CartesianCoordinate previousCoordinate;

    OdometryCore() {
        previousInchesFB = 0;
        previousInchesLeft = 0;
        previousInchesRight = 0;
        previousHeading = 0;
        previousCoordinate = new CartesianCoordinate(0, 0);
    }

    public static OdometryCore getInstance() {
        if (instance == null) instance = new OdometryCore();
        return instance;
    }

    public OdometryPosition getCurrentPosition(double leftTicks, double rightTicks, double frontBackTicks, double leftOffset, double rightOffset, double frontBackOffset) {
        double leftInches = ticksToInches(leftTicks);
        double rightInches = ticksToInches(rightTicks);
        double frontBackInches = ticksToInches(frontBackTicks);

        double leftChange = leftInches - previousInchesLeft;
        double rightChange = rightInches - previousInchesRight;
        double frontBackChange = frontBackInches - previousInchesFB;

        double absoluteHeading = CoreMath.getHeading(leftInches, rightInches, leftOffset, rightOffset);
        System.out.println("ABS Heading: " + absoluteHeading);
        double headingChange = absoluteHeading - previousHeading;
        System.out.println("Heading Change: " + headingChange);
        double averageOrientation = previousHeading + headingChange/2;
        System.out.println("Average Orientation: " + headingChange);

        previousInchesLeft = leftInches;
        previousInchesRight = rightInches;
        previousInchesFB = frontBackInches;
        previousHeading = absoluteHeading;

        PolarCoordinate localOffset = CoreMath.getPolarCoordinate(leftChange, rightChange, frontBackChange, leftOffset, rightOffset, frontBackOffset);
        System.out.println("LocalX: " + CartesianCoordinate.fromPolar(localOffset).getX() + ", LocalY: " + CartesianCoordinate.fromPolar(localOffset).getY());

        PolarCoordinate globalOffsetPolar = new PolarCoordinate(localOffset.getR(), -averageOrientation);
        System.out.println("Global Polar offset: " + globalOffsetPolar);

        CartesianCoordinate newGlobalOffset = CartesianCoordinate.fromPolar(globalOffsetPolar);
        System.out.println("Previous Cartesian Position: " + previousCoordinate);
        System.out.println("Global Cartesian offset: " + newGlobalOffset);

        CartesianCoordinate absolutePosition = new CartesianCoordinate(newGlobalOffset.getX() + previousCoordinate.getX(), newGlobalOffset.getY() + previousCoordinate.getY());
        System.out.println("New ABS: " + absolutePosition);

        previousCoordinate = newGlobalOffset;

        return new OdometryPosition(absolutePosition.getX(), absolutePosition.getY(), absoluteHeading, HeadingUnit.RADIANS);
    }

    double ticksToInches(double ticks) {
        return ticks * TICKS_TO_INCH;
    }
}
