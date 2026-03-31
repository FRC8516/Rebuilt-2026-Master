# REBUILT™ v2026.2.1

FRC 8516 Wired Up

## Description

### Drive Train:  

* Drivetrain composed of four SDS MK4 L3 Billet Modules,  
each configured with two Kraken X60,  
CTRE Can Encoder Through Bore Encoder as the absolute turning encoder.

Actuators: 
* Falcon 500 - Climber 
* Kraken X60 - Drivetrain, Flywheel  
* Kraken X44 - Turret, Feed  
* SparkFLEX - Intake, Agitator  
* Pigeon 2 - Accelerometer  

## Prerequisites
* WPI 2026.2.1
* Phoenix 6 v26.1.1 - Adds features that are required for swerve and mechanism
* Rev 6 v26.0.4 - Includes APIs for firmware features Flex Spark Maxs
* Pathplanner v26.1.2 - Autonomous path following API

## Configuration

> It is possible that this project will not work for your robot right out of the box.
> Various things like the CAN IDs, PID gains, chassis configuration, etc. 
> must be determined for your own robot!

These values can be adjusted in the `Constants.java` file.
Motor configuration is in the `CalibrationSettings.java` file.

## Updated During Season

-Added Autos, Fine tuning the motion during the LSR Event.  
-Added CTRE License for controls of Karken, Falcon 500, Pigeon 2.0, Encoders. 
-Added Second LimeLight 4.0 Camera with auto steering commands for climbing during auto. 
-Tuned climber, flywheel and intake. 
