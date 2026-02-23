// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;


import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.*;
import frc.robot.commands.intake;
import frc.robot.commands.turretAim;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;
import frc.robot.generated.TunerConstants;
import frc.robot.commands.Test;
import frc.robot.commands.Climb;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  public Orchestra m_Orchestra = new Orchestra();
  private final SendableChooser<Command> m_autoChooser;
  
  private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
  private double MaxAngularRate = RotationsPerSecond.of(0.50).in(RadiansPerSecond); // 1/2 of a rotation per second
                                                                                    // max angular velocity

  // Setting up bindings for necessary control of the swerve drive platform 
  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
          .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
          .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  
  private final Telemetry logger = new Telemetry(MaxSpeed);
 
  private final CommandXboxController joystick = new CommandXboxController(
          Constants.OIConstants.kDriverControllerPort);
  private final CommandXboxController operator = new CommandXboxController(
          Constants.OIConstants.kOperatorControllerPort);
  private final FloorIntake m_FloorIntake = new FloorIntake();
  private final Turret m_Turret = new Turret();
  private final Climber m_Climber = new Climber();
  private final Vision m_FrontVision = new Vision(kVision.FrontLimelight, kVision.fForwardOffset, kVision.fSideOffset, kVision.fHeightOffset, kVision.fRollOffset, kVision.fPitchOffset, kVision.fYawOffset);
  private final Vision m_RearVision = new Vision(kVision.RearLimelight, kVision.rForwardOffset, kVision.rSideOffset, kVision.rHeightOffset, kVision.rRollOffset, kVision.rPitchOffset, kVision.rYawOffset);
  private final Vision m_turretVision = new Vision(kVision.TurretLimelight);
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
  //Commands
  private final turretAim m_TurretAim = new turretAim(m_turretVision, m_Turret);
  private final intake m_intake = new intake(m_FloorIntake);
  private final Test test = new Test(m_Turret,m_FloorIntake,false);
  private final Test unstuck = new Test(m_Turret,m_FloorIntake,true);
  private final Climb m_climb = new Climb(m_Climber);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    addMotorsToOrchestra();
    NamedCommands.registerCommand("Climb",m_climb);
    // Configure the trigger bindings
    m_autoChooser = AutoBuilder.buildAutoChooser();
    configureBindings();
    SmartDashboard.putData("Auto Chooser", m_autoChooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
     
    drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() -> drive
                // Drive forward with negative Y (forward)
                .withVelocityX(-MathUtil.applyDeadband(joystick.getLeftY()/1.50, OIConstants.kDriveDeadband) * MaxSpeed)
                // Drive left with negative X (left)
                .withVelocityY(-MathUtil.applyDeadband(joystick.getLeftX()/1.50, OIConstants.kDriveDeadband) * MaxSpeed) 
                // Drive counterclockwise with negative X (left)
                .withRotationalRate(-MathUtil.applyDeadband(joystick.getRightX()/1.05, OIConstants.kDriveDeadband) * MaxAngularRate)
            ));
    joystick.leftTrigger().whileTrue(m_intake);
    joystick.rightTrigger().whileTrue(m_TurretAim);//this aims and fires the turret
    joystick.a().whileTrue(test);
    joystick.b().whileTrue(unstuck);
     joystick.start().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));
    joystick.x().whileTrue(m_climb);

  }
  public void Throttle(){
    m_FrontVision.toggleThrottle();
    m_RearVision.toggleThrottle();
  }

  private void addMotorsToOrchestra(){

    m_Orchestra.addInstrument(drivetrain.getModule(0).getDriveMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(0).getSteerMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(1).getDriveMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(1).getSteerMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(2).getDriveMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(2).getSteerMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(3).getDriveMotor());
    m_Orchestra.addInstrument(drivetrain.getModule(3).getSteerMotor());
    m_Orchestra.addInstrument(m_Climber.getMotor());
    //m_Orchestra.addInstrument(m_Turret.getMotorA());
    m_Orchestra.addInstrument(m_Turret.getMotorS());
    m_Orchestra.addInstrument(m_Turret.getMotorF());
  }

  public Command getAutonomousCommand() {
      return m_autoChooser.getSelected();
  }
}
