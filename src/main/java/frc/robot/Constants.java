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
        //public static final int CANdleID = 27;
    }

      public static final class ManipulatorConstants {
        //Intake Motors - Kraken X60
        public static final int kIntakeMotor = 31;
        //Turret Rotation Motor - Kraken X44
        public static final int kTurretRotationMotor = 26;
        //Turret Angle Motor - 
        public static final int kTurretAngleMotor = 1;
        //Turret Firing Motor - 
        public static final int kTurretFiringMotor = 25;
        //Climber Motor - 
        public static final int kClimberMotor = 22;
        //Turret Feed Motor -
        public static final int kFeedMotor = 30;
        //Agitator feed motor
        public static final int kAgitatorMotor = 32;
        // Smaller feed motor wheel
        public static final int kAltFeedMotor = 34;
        //Turret Feed Voltage (-16 thru 16)
        public static final double kFeedVoltage = -5;
        public static final double kAgitatorVoltage = 15;
        //Intake Speed in voltage
                        /* CHANGE */
        public static final double kIntakeVoltage = -3;
        //Turret Rotation time out
                        /* CHANGE */
        public static final double kTurretRotationTimeout = 1;
        //Firing time out
                        /* CHANGE */
        public static final double kShooterWraistTime = 1;
    }
    public static final class ClimberPos{
        public static final String Home = "Down";
        public static final String Climb = "Up";
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

    public static final class kVision {
        public static final String FrontLimelight = "front";
            public static final double fForwardOffset = 0.1234;
            public static final double fSideOffset = 0.1234;
            public static final double fHeightOffset = 0.1234;
            public static final double fRollOffset = 0.1234;
            public static final double fPitchOffset = 0.1234;
            public static final int fYawOffset = 0;
        public static final String RearLimelight = "rear";
            public static final double rForwardOffset = 0.1234;
            public static final double rSideOffset = 0.1234;
            public static final double rHeightOffset = 0.1234;
            public static final double rRollOffset = 0.1234;
            public static final double rPitchOffset = 0.1234;
            public static final int rYawOffset = 0;
        public static final String TurretLimelight = "limelight-turret";
             
    }

}
