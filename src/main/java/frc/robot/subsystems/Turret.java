package frc.robot.subsystems;


import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.core.CoreCANcoder;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CalibrationSettings;
import frc.robot.Constants.ManipulatorConstants;

public class Turret extends SubsystemBase {
    /* Hardware */
    private final TalonFX m_TurretSpinMotor = new TalonFX(ManipulatorConstants.kTurretRotationMotor);
   //private final TalonFX m_TurretAngleMotor = new TalonFX(ManipulatorConstants.kTurretAngleMotor);
    private final TalonFX m_TurretFiringMotor = new TalonFX(ManipulatorConstants.kTurretFiringMotor);
   
      /* Keep a brake request so we can disable the motor */
      private final NeutralOut m_Coast = new NeutralOut();
  /** Creates a new TurretSubsytem. */
  public Turret() {
   // TalonFXConfiguration angleConfigs = new TalonFXConfiguration();
    TalonFXConfiguration firingConfigs = new TalonFXConfiguration();
    TalonFXConfiguration spinConfigs = new TalonFXConfiguration().withFeedback(new FeedbackConfigs().withRemoteCANcoder(new CoreCANcoder(29)));
      
      //Set configurations
      firingConfigs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      firingConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
            //angleConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      spinConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      spinConfigs.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
      spinConfigs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = -0;
      spinConfigs.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
      spinConfigs.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 3;
      spinConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
      spinConfigs.withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimit(75));
      Slot0Configs slot0 = firingConfigs.Slot0;
    slot0.kS = CalibrationSettings.ElevatorCalibrations.kElevatorkS;   // Add 0.25 V output to overcome static friction
    slot0.kV = CalibrationSettings.ElevatorCalibrations.kElevatorkV;   // A velocity target of 1 rps results in 0.12 V output
    slot0.kA = CalibrationSettings.ElevatorCalibrations.kElevatorkA;   // An acceleration of 1 rps/s requires 0.01 V output
    slot0.kP = CalibrationSettings.ElevatorCalibrations.kElevatorkP;   // An error of 1 rps results in 0.11 V output
    slot0.kI = CalibrationSettings.ElevatorCalibrations.kElevatorkI;   // no output for integrated error
    slot0.kD = CalibrationSettings.ElevatorCalibrations.kElevatorkD;
    Slot0Configs slot0Configs = spinConfigs.Slot0;
    slot0Configs.kP = 3.5; // An error of 1 rotation results in 2 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity of 1 rps results in 0.1 V output
    //m_TurretAngleMotor.getConfigurator().apply(angleConfigs);
    m_TurretFiringMotor.getConfigurator().apply(firingConfigs);
    m_TurretSpinMotor.getConfigurator().apply(spinConfigs);
    }
  
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  
  public void Fire(){
    m_TurretFiringMotor.setControl(new VelocityTorqueCurrentFOC(200).withSlot(0));
  }
  public void CeaseFire(){
    m_TurretFiringMotor.setControl(m_Coast);
  }
  public void setTurretPos(ControlRequest Position){
      m_TurretSpinMotor.setControl(Position);
  }
  //backup commands if limelight fails
  public void turnRight(){
    m_TurretSpinMotor.setVoltage(1.5);
  }
  public void turnLeft(){
    m_TurretSpinMotor.setVoltage(-1.5);
  }
  public void stopTurn(){
    m_TurretSpinMotor.stopMotor();
  }
  public double RotPos(){
    return m_TurretSpinMotor.getPosition().getValueAsDouble();
  }
  
}