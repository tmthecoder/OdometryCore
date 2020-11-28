## Odometry Core
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a library made to handle the core mathematical calculations for three wheel Odometry.

This library requires either a value in encoder ticks, paired with the counts per revolution per encoder, or an inch value for each encoder to be given

## Installation (Gradle)
In order to use this library, paste the following code under your ``allprojects/repositories``:

```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }  //Add this line
    }
}
``` 

Then place the following line under your ```dependencies```:
```
dependencies {
    implementation 'com.github.tmthecoder:OdometryCore:1.0.0'
}
```
## Installation (JAR)

Alternatively, you can use the provided JAR in the releases section under the latest released version

## Usage

**Library Initialization:**
```
OdometryCore.initialize(cpr, diameter, leftOffet, rightOffset, frontBackOffset);
```
The parameters seen are:
- cpr: The counts per revolution for the encoders you are using
- diameter: The diameter of the wheels on the encoder
- leftOffset: The offset of the left wheel from the center of the robot (only horizontal offset)
- rightOffset: The offset of the right wheel from the center of the robot (only horizontal offset)
- frontBackOffset: The offset of the front or back encoder wheel (only vertical offset)

**Position Tracking:**

After initializing, the robot's position can be found using the following code:
```               
EncoderPositions encoderPositions = new EncoderPositions(leftPosition, rightPosition, frontBackPosition);
OdometryPosition position = OdometryCore.getInstance().getCurrentPosition(encoderPositions);
```
The parameters seen are: 
- leftPosition: The left encoder's position in ticks
- rightPosition: The right encoder's position in ticks
- frontBackPosition: The front or back encoder's position in ticks

The position tracking code should be run on a loop with as small an interval as possible, and if possible on a separate thread

## Features and Bugs
Please file feature requests and bugs at the [issue tracker].

[issue tracker]: https://github.com/tmthecoder/OdometryCore/issues

## Licensing

OdometryCore is Licensed under the [MIT License] 

[MIT License]: https://github.com/tmthecoder/OdometryCore/blob/main/LICENSE
