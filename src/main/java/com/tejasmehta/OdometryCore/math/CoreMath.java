/*
 * @author Tejas Mehta
 * Made on Wednesday, November 04, 2020
 * File Name: CoreMath
 * Package: com.tejasmehta.OdometryCore.math
 */
package com.tejasmehta.OdometryCore.math;


import com.tejasmehta.OdometryCore.localization.OdometryPosition;

public class CoreMath {

    /**
     * A method to get the current heading in radians of the robot given the following parameters
     * Sourced from the formula: Δθ = (ΔL - ΔR)/(leftOffset + rightOffset)
     *
     * @param leftChange  - The change in the left odometry wheel's movement (in inches)
     * @param rightChange - The change in the right odometry wheel's movement (in inches)
     * @param leftOffset  - The horizontal offset of the left wheel from the robot's center (in inches)
     * @param rightOffset - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @return - The radian value of the current heading
     */
    public static double getHeading(double leftChange, double rightChange, double leftOffset, double rightOffset) {
        return (leftChange - rightChange) / (leftOffset + rightOffset);
    }

    /**
     * A method to get the raw X coordinate (no conversion to the local plane yet) by utilizing the change in the front/back odometry wheel and its offset
     * Sourced from the formula 2sin(θ/2) * (ΔS/Δθ + forwardBackwardOffset)
     *
     * @param frontBackChange - The change in the front/back odometey wheel's movement (in inches)
     * @param frontBackOffset - The horizontal offset of the front/back odometry wheel from the robot's center (in inches)
     * @param headingChange   - The change in the heading from the previous measurement (in radians)
     * @return - The raw x coordinate (in inches) of the robot without the conversion to the local coordinate plane (relative to the robot)
     */
    public static double getRawXCoordinate(double frontBackChange, double frontBackOffset, double headingChange) {
        if (headingChange == 0) return frontBackChange;
        return 2 * Math.sin(headingChange / 2) * (frontBackChange / headingChange + frontBackOffset);
    }

    /**
     * A method to get the raw Y coordinate (no conversion to the local plane yet) via averaging the X coordinate results from both the right and left coordinate methods,
     * mainly for accuracy and consistency between both sides in case one side is a little off
     *
     * @param leftChange    - The change in the left odometry wheel's movement (in inches)
     * @param rightChange   - The change in the right odometry wheel's movement (in inches)
     * @param leftOffset    - The horizontal offset of the left odometry wheel from the robot's center (in inches)
     * @param rightOffset   - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @param headingChange - The change in the heading from the previous measurement (in radians)
     * @return - The average of the results of both the `getRawYCoordinateWithRightOnly()` and `getRawYCoordinateWithLeftOnly()` methods (in inches)
     */
    public static double getRawYCoordinate(double leftChange, double rightChange, double leftOffset, double rightOffset, double headingChange) {
        System.out.println("RAWYR: " + getRawYCoordinateWithRightOnly(rightChange, rightOffset, headingChange));
        System.out.println("RAWYL: " + getRawYCoordinateWithLeftOnly(leftChange, leftOffset, headingChange));
        return (getRawYCoordinateWithRightOnly(rightChange, rightOffset, headingChange) + getRawYCoordinateWithLeftOnly(leftChange, leftOffset, headingChange)) / 2;
    }

    /**
     * A method to get the raw Y coordinate (no conversion to the local plane yet) by utilizing only the change and offset of the right wheel
     * Sourced from the formula: 2sin(θ/2) * (ΔR/Δθ + rightOffset)
     *
     * @param rightChange   - The change in the right odometry wheel's movement (in inches)
     * @param rightOffset   - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @param headingChange - The change in heading from the previous measurement (in radians)
     * @return - The raw y coordinate (in inches) of the robot without conversion to the local coordinate plane (relative to the robot)
     */
    public static double getRawYCoordinateWithRightOnly(double rightChange, double rightOffset, double headingChange) {
        if (headingChange == 0) return rightChange;
        return 2 * Math.sin(headingChange / 2) * (rightChange / headingChange + rightOffset);
    }

    /**
     * A method to get the raw Y coordinate (no conversion to the local plane yet) by utilizing only the change and offset of the left wheel
     * Sourced from the formula: 2sin(θ/2) * (ΔL/Δθ - leftOffset)
     *
     * @param leftChange    - The change in the left odometry wheel's movement (in inches)
     * @param leftOffset    - The horizontal offset of the left odometry wheel from the robot's center (in inches)
     * @param headingChange - The change in heading from the previous measurement (in radians)
     * @return - The raw y coordinate (in inches) of the robot without conversion to the local coordinate plane (relative to the robot)
     */
    public static double getRawYCoordinateWithLeftOnly(double leftChange, double leftOffset, double headingChange) {
        if (headingChange == 0) return leftChange;
        return 2 * Math.sin(headingChange / 2) * (leftChange / headingChange - leftOffset);
    }

    /**
     * A method to take the changes in the left, right, and front/back odometry wheels, along with their respective offsets to create a polar coordinate
     * after getting their raw x & y coordinates (still not localized)
     *
     * @param leftChange      - The change in the left odometry wheel's movement (in inches)
     * @param rightChange     - The change in the right odometry wheel's movement (in inches)
     * @param frontBackChange - The change in the front/back odometey wheel's movement (in inches)
     * @param leftOffset      - The horizontal offset of the left odometry wheel from the robot's center (in inches)
     * @param rightOffset     - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @param frontBackOffset - The horizontal offset of the front/back odometry wheel from the robot's center (in inches)
     * @return - The Polar coordinate that coincides with the robot's relative coordinate
     */
    public static PolarCoordinate getPolarCoordinate(double leftChange, double rightChange, double frontBackChange, double leftOffset, double rightOffset, double frontBackOffset) {
        double heading = getHeading(leftChange, rightChange, leftOffset, rightOffset);
        double rawXCoordinate = getRawXCoordinate(frontBackChange, frontBackOffset, heading);
        double rawYCoordinate = getRawYCoordinate(leftChange, rightChange, leftOffset, rightOffset, heading);
        System.out.println("RAWX: " + rawXCoordinate);
        System.out.println("RAWY: " + rawYCoordinate);
        return getPolarCoordinate(rawXCoordinate, rawYCoordinate);
    }

    /**
     * A method to take both the raw X and Y coordinates and convert them to an equivalent polar cooordinate
     *
     * @param rawXCoordinate - The raw x coordinate that is still relative to the robot's position and not the local coordinate plane
     * @param rawYCoordinate - The raw y coordinate that is still relative to the robot's position and not the local coordinate plane
     * @return - The polar coordinate that is equivalent to the raw cartesian coordinates given
     */
    public static PolarCoordinate getPolarCoordinate(double rawXCoordinate, double rawYCoordinate) {
        return PolarCoordinate.fromCartesian(new CartesianCoordinate(rawXCoordinate, rawYCoordinate));
    }

    /**
     * A method to take the left and right encoders' new values, old values, as well as the change of the front/back encoder paired with
     * the offset of all 3 to calculate a localized Cartesian Coordinate after performing relative calculations
     *
     * @param currentLeftPosition   - The current reported left encoder;s position (in inches)
     * @param currentRightPosition  - The current reported right encoder's position (in inches)
     * @param currentFrontBackPosition - The current reported front/back encoder's position (in inches)
     * @param previousLeftPosition  - The previous reported position of the left encoder (in inches)
     * @param previousRightPosition - The previous reported position of the right encoder (in inches)
     * @param previousFrontBackPosition - The previous reported position of the front/back encoder (in inches)
     * @param leftOffset            - The horizontal offset of the left odometry wheel from the robot's center (in inches)
     * @param rightOffset           - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @param frontBackOffset       - The horizontal offset of the front/back odometry wheel from the robot's center (in inches)
     * @return - The converted Cartesian Coordinate on the local plane of the field
     */
    public static CartesianCoordinate getConvertedCoordinate(double currentLeftPosition, double currentRightPosition, double currentFrontBackPosition, double previousLeftPosition, double previousRightPosition, double previousFrontBackPosition, double leftOffset, double rightOffset, double frontBackOffset) {
        double leftChange = currentLeftPosition - previousLeftPosition;
        double rightChange = currentRightPosition - previousRightPosition;
        double frontBackChange = currentFrontBackPosition - previousFrontBackPosition;
        PolarCoordinate relativePolarCoordinate = getPolarCoordinate(leftChange, rightChange, frontBackChange, leftOffset, rightOffset, frontBackOffset);
        double xVal = currentFrontBackPosition;
        double yVal = (currentLeftPosition + currentRightPosition)/2;
        double tanVal = xVal == 0 ? 0 : yVal/xVal;
        return getConvertedCoordinate(relativePolarCoordinate, Math.atan(tanVal));
    }

    /**
     * A method to take the left and right encoders' new values, old values, as well as the change of the front/back encoder paired with
     * the offset of all 3 to calculate a localized Cartesian Coordinate after performing relative calculations
     *
     * @param leftChange   - The current reported left encoder;s position (in inches)
     * @param rightChange  - The current reported right encoder's position (in inches)
     * @param frontBackChange - The current reported front/back encoder's position (in inches)
     * @param leftOffset            - The horizontal offset of the left odometry wheel from the robot's center (in inches)
     * @param rightOffset           - The horizontal offset of the right odometry wheel from the robot's center (in inches)
     * @param frontBackOffset       - The horizontal offset of the front/back odometry wheel from the robot's center (in inches)
     * @return - The converted Cartesian Coordinate on the local plane of the field
     */
    public static CartesianCoordinate getConvertedCoordinate(double leftChange, double rightChange, double frontBackChange, double leftOffset, double rightOffset, double frontBackOffset) {
        PolarCoordinate relativePolarCoordinate = getPolarCoordinate(leftChange, rightChange, frontBackChange, leftOffset, rightOffset, frontBackOffset);
        double tanVal = frontBackChange == 0 ? 0 : ((leftChange+rightChange)/2)/frontBackChange;
        System.out.println("LEFTC: " + leftChange);
        System.out.println("RIGHTC: " + rightChange);
        CartesianCoordinate relativeCartesian = CartesianCoordinate.fromPolar(relativePolarCoordinate);
        double newAngle = Math.atan2(relativeCartesian.getY(), relativeCartesian.getX());
        return getConvertedCoordinate(relativePolarCoordinate, newAngle);
    }

    public static OdometryPosition getOdometryPosition(double leftChange, double rightChange, double frontBackChange, double leftOffset, double rightOffset, double frontBackOffset) {
        CartesianCoordinate localCoordinate = getConvertedCoordinate(leftChange, rightChange, frontBackChange, leftOffset, rightOffset, frontBackOffset);
        double heading = getHeading(leftChange, rightChange, leftOffset, rightOffset);
        System.out.println("LOCALX: " + localCoordinate.getX());
        System.out.println("LOCALY: " + localCoordinate.getY());
        System.out.println("LOCALH: " + heading);
        return new OdometryPosition(localCoordinate.getX(), localCoordinate.getY(), heading);
    }

    /**
     * A method to take a relative polar coordinate and an absolute heading to convert the polar coordinate to a localized Cartesian coordinate
     *
     * @param polarCoordinate - The Polar Coordinate of the relative coordinate
     * @param absoluteHeading - The absolute heading of the robot (in radians)
     * @return - The converted Cartesian Coordinate on the local plane of the field
     */
    public static CartesianCoordinate getConvertedCoordinate(PolarCoordinate polarCoordinate, double absoluteHeading) {
        System.out.println("HEADINGR: " + absoluteHeading);
        System.out.println("HEADINGD: " + Math.toDegrees(absoluteHeading));
        return CartesianCoordinate.fromPolar(new PolarCoordinate(polarCoordinate.getR(), absoluteHeading + Math.PI/2));
    }

}
