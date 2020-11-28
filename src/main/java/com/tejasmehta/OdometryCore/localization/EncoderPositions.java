/****
 * Made by Tejas Mehta
 * Made on Wednesday, November 11, 2020
 * File Name: EncoderTicks
 * Package: com.tejasmehta.OdometryCore.localization
 */
package com.tejasmehta.OdometryCore.localization;

public class EncoderPositions {
    private final double leftPosition;
    private final double rightPosition;
    private final double frontBackPosition;

    /**
     * A wrapper for encoder positions
     * @param leftPosition
     * @param rightPosition
     * @param frontBackPosition
     */
    public EncoderPositions(double leftPosition, double rightPosition, double frontBackPosition) {
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
        this.frontBackPosition = frontBackPosition;
    }


    public double getLeftPosition() {
        return leftPosition;
    }

    public double getRightPosition() {
        return rightPosition;
    }

    public double getFrontBackPosition() {
        return frontBackPosition;
    }
}
