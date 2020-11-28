package com.tejasmehta.OdometryCore.localization;

/****
 * @author Tejas Mehta
 * Made on Wednesday, November 11, 2020
 * File Name: EncoderPositions
 * A class to unify all three encoder positions used in the core calculations
 */
public class EncoderPositions {
    private final double leftPosition;
    private final double rightPosition;
    private final double frontBackPosition;

    /**
     * The constructor for the encoder positions class
     * @param leftPosition - The left encoder's position in ticks
     * @param rightPosition - The right encoder's position in ticks
     * @param frontBackPosition - The Front or back encoder's position in ticks
     */
    public EncoderPositions(double leftPosition, double rightPosition, double frontBackPosition) {
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
        this.frontBackPosition = frontBackPosition;
    }

    /**
     * A method to get the left encoder's position
     * @return - The left encoder's position (in ticks)
     */
    public double getLeftPosition() {
        return leftPosition;
    }

    /**
     * A method to get the right encoder's position
     * @return - The right encoder's position (in ticks)
     */
    public double getRightPosition() {
        return rightPosition;
    }

    /**
     * A method to get the front or back encoder's position
     * @return - The front or back encoder's position (in ticks)
     */
    public double getFrontBackPosition() {
        return frontBackPosition;
    }
}
