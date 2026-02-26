// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Talon FX Configuration Settings 
 *  -Values are used from the Phonenix Tuner
*/
public class CalibrationSettings {

    public static class ElevatorCalibrations {

        //All of the PID and Feedforward gains for the MotionMagic Motion profiler.
        public static final double kElevatorkG = 0.19;
        public static final double kElevatorkS = 0.25;
        public static final double kElevatorkV = 0.25;
        public static final double kElevatorkA = 0.01;
        public static final double kElevatorkP = 65;
        public static final double kElevatorkD = 1;
        public static final double kElevatorkI = 0;

        // Motion Magic Configs for the MotionMagicConfigs class for the Elevator
        public static final double kCruiseVelocity = 8.5;
        public static final double kMaxAccelerationMotionMagic = 20.0;
        public static final double kElevatorJerk = 50;
        public static final double kMaxLimit = 67.0;
    }
    
    public static class FlywheelCalibrations{
        //All of the PID and Feedforward gains for the MotionMagic Motion profiler.
        public static final double kElevatorkG = 0.19;
        public static final double kElevatorkS = 0.25;
        public static final double kElevatorkV = 0.25;
        public static final double kElevatorkA = 0.01;
        public static final double kElevatorkP = 65;
        public static final double kElevatorkD = 1;
        public static final double kElevatorkI = 0;

        // Motion Magic Configs for the MotionMagicConfigs class for the Elevator
        public static final double kCruiseVelocity = 8.5;
        public static final double kMaxAccelerationMotionMagic = 20.0;
        public static final double kElevatorJerk = 50;
        public static final double kMaxLimit = 67.0;
    }


    public static class ClimberCalibrations {
        //All of the PID and Feedforward gains for the MotionMagic Motion profiler.
        public static final double kClimberkG = 0.19;
        public static final double kClimberkS = 0.25;
        public static final double kClimberkV = 0.12;
        public static final double kClimberkA = 0.01;
        public static final double kClimberkP = 60;
        public static final double kClimberkD = 1;
        public static final double kClimberkI = 0;
    } 


}
