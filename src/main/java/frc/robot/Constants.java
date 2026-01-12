// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
        public static final double kDriveDeadband = 0.35;  //@0.05
        public static final int CANdleID = 27;
        public static final int CANRangeID = 21;
        public static final double CANRangeDetectRange = 0.1; //Set to half total distance from the top of coral chute
    }

      public static final class ManipulatorConstants {
        //Intake Motors - Kraken X60
        public static final int kIntakeMotor = 25;
        //Claw Wraist Motor - Falcon 500
        public static final int kIntakeWraistMotor = 24;
        //Elevator Motor - Falcon 500
        public static final int kElevatorMotorLeft = 22;
        public static final int kElevatorMotorRight = 23;
        //Intake feed time
        public static final double kIntakeFeedTime = 3.0;
        //Intake Speed in voltage
        public static final double kIntakeVoltage = 6.5;
        //Position Intake Wraist time out
        public static final double kIntakeWraistTime = 0.10;
        //Shooter Wraist time out
        public static final double kShooterWraistTime = 1.0;
        //Climber
        public static final int kClimberMotor = 26;
    }

    //Led lights
    public static final class LedLights {
        public static final String Yellow = "Yellow";
        public static final String Purple = "Purple";
        public static final String Red = "Red";
        public static final String Blue = "Blue";
        public static final String Green = "Green";
        public static final String Orange = "Orange";
    }
    
    //Claw Positions 
    public static final class ClawPositions {
        public static final String HomePosition = "HomePosition";
        public static final String ScoringPosition = "ScoringPosition";
        public static final String TransferPosition = "TransferPostion";
        public static final String AlgaePosition = "AlgaePositon";
        public static final String AlgaeScoringPosition = "AlgaeScoringPositon";
        public static final String LoadingPosition = "LoadingPosition";
    }

    //Elevator Positions
    public static final class ElevatorPositions {
        public static final String HomePosition = "HomePosition";
        public static final String L1Position = "L1Position";
        public static final String L2Position = "L2Position";
        public static final String L3Position = "L3Position";
        public static final String L4Position = "L4Position";
        public static final String LoadingPosition = "LoadingPosition";
    }
    public static final class VisionConstants {
        public static final String LIMELIGHT_NAME = "limelight-front";
    }

}
