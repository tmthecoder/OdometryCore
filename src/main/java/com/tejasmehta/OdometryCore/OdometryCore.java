package com.tejasmehta.OdometryCore;

import com.tejasmehta.OdometryCore.localization.EncoderPositions;
import com.tejasmehta.OdometryCore.localization.HeadingUnit;
import com.tejasmehta.OdometryCore.localization.OdometryPosition;
import com.tejasmehta.OdometryCore.math.CoreMath;

public class OdometryCore {

    private final double wheelDiameter;
    private final double cpr;
    private final double leftOffset;
    private final double rightOffset;
    private final double frontBackOffset;
    private static OdometryCore instance;
    private double leftInches = 0;
    private double rightInches = 0;
    private double frontBackInches = 0;
    private OdometryPosition position = new OdometryPosition(0, 0, 0, HeadingUnit.RADIANS);

    private OdometryCore(double cpr, double wheelDiameter, double leftOffset, double rightOffset, double frontBackOffset) {
        this.wheelDiameter = wheelDiameter;
        this.cpr = cpr;
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.frontBackOffset = frontBackOffset;
    }


    public static OdometryCore getInstance() {
        if (instance == null) throw new IllegalArgumentException("Please initialize OdometryCore with OdometryCore.initialize(cpr, wheelDiameter, leftOffset, rightOffset, frontBackOffset)");
        return instance;
    }

    public static void initialize(double cpr, double wheelDiameter, double leftOffset, double rightOffset, double frontBackOffset) {
        instance = new OdometryCore(cpr, wheelDiameter, leftOffset, rightOffset, frontBackOffset);
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public OdometryPosition getCurrentPosition(EncoderPositions positions) {
        double newLeftInches = CoreMath.ticksToInches(positions.getLeftPosition(), cpr, wheelDiameter);
        double newRightInches = CoreMath.ticksToInches(positions.getRightPosition(), cpr, wheelDiameter);
        double newFrontBackInches = CoreMath.ticksToInches(positions.getFrontBackPosition(), cpr, wheelDiameter);

        double leftChange = newLeftInches - leftInches;
        double rightChange = newRightInches - rightInches;
        double frontBackChange = newFrontBackInches - frontBackInches;

        double previousHeading = CoreMath.getHeading(leftInches, rightInches, leftOffset, rightOffset);

        OdometryPosition posChange = CoreMath.getOdometryPosition(leftChange, rightChange, frontBackChange, leftOffset, rightOffset, frontBackOffset, previousHeading);

        double absHeading = CoreMath.getHeading(newLeftInches, newRightInches, leftOffset, rightOffset);

        leftInches += leftChange;
        rightInches += rightChange;
        frontBackInches += frontBackChange;
        position = new OdometryPosition(position.getX() + posChange.getX(), position.getY() + posChange.getY(), absHeading, HeadingUnit.RADIANS);

        return position;
    }
}
