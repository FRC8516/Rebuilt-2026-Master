package frc.robot.subsystems;

import com.ctre.phoenix6.configs.AudioConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CalibrationSettings;
import frc.robot.Constants.ManipulatorConstants;

public class Turret extends SubsystemBase {
    /* Hardware */
    private final TalonFX m_TurretSpinMotor = new TalonFX(ManipulatorConstants.kTurretRotationMotor);
   //private final TalonFX m_TurretAngleMotor = new TalonFX(ManipulatorConstants.kTurretAngleMotor);
    private final TalonFX m_TurretFiringMotor = new TalonFX(ManipulatorConstants.kTurretFiringMotor);
    private final SparkFlex m_FeedMotor = new SparkFlex(ManipulatorConstants.kFeedMotor,MotorType.kBrushless);
    private final SparkFlex m_Agitator = new SparkFlex(ManipulatorConstants.kAgitatorMotor,MotorType.kBrushless);
      /* Keep a brake request so we can disable the motor */
      private final NeutralOut m_Coast = new NeutralOut();
  /** Creates a new TurretSubsytem. */
  public Turret() {
   // TalonFXConfiguration angleConfigs = new TalonFXConfiguration();
    TalonFXConfiguration firingConfigs = new TalonFXConfiguration().withAudio(new AudioConfigs().withAllowMusicDurDisable(true));
    TalonFXConfiguration spinConfigs = new TalonFXConfiguration().withAudio(new AudioConfigs().withAllowMusicDurDisable(true));
    SparkBaseConfig configs = new SparkFlexConfig();
    //Set configurations  
    configs.inverted(false);
    configs.idleMode(IdleMode.kCoast);

      //Set configurations
      firingConfigs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      firingConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
            //angleConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      spinConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

      spinConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
      Slot0Configs slot0 = firingConfigs.Slot0;
    slot0.kS = CalibrationSettings.ElevatorCalibrations.kElevatorkS;   // Add 0.25 V output to overcome static friction
    slot0.kV = CalibrationSettings.ElevatorCalibrations.kElevatorkV;   // A velocity target of 1 rps results in 0.12 V output
    slot0.kA = CalibrationSettings.ElevatorCalibrations.kElevatorkA;   // An acceleration of 1 rps/s requires 0.01 V output
    slot0.kP = CalibrationSettings.ElevatorCalibrations.kElevatorkP;   // An error of 1 rps results in 0.11 V output
    slot0.kI = CalibrationSettings.ElevatorCalibrations.kElevatorkI;   // no output for integrated error
    slot0.kD = CalibrationSettings.ElevatorCalibrations.kElevatorkD;
    Slot0Configs slot0Configs = spinConfigs.Slot0;
    slot0Configs.kP = 2; // An error of 1 rotation results in 2 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity of 1 rps results in 0.1 V output
    //m_TurretAngleMotor.getConfigurator().apply(angleConfigs);
    m_TurretFiringMotor.getConfigurator().apply(firingConfigs);
    m_TurretSpinMotor.getConfigurator().apply(spinConfigs);
    m_FeedMotor.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    m_Agitator.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }
  /*
  public TalonFX getMotorA(){
    return m_TurretAngleMotor;
  }
  */
  public TalonFX getMotorF(){
    return m_TurretFiringMotor;
  }
  public TalonFX getMotorS(){
    return m_TurretSpinMotor;
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

  public void Feed(){
    m_FeedMotor.setVoltage(ManipulatorConstants.kFeedVoltage);
    m_Agitator.setVoltage(ManipulatorConstants.kAgitatorVoltage);
  }
  public void NoFeed(){
    m_FeedMotor.stopMotor();
    m_Agitator.stopMotor();
  }
  public void Fire(){
    m_TurretFiringMotor.setControl(new VelocityDutyCycle(200).withSlot(0));
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
  public void unstuck(){
    m_FeedMotor.setVoltage(-ManipulatorConstants.kFeedVoltage);
    m_Agitator.setVoltage(-ManipulatorConstants.kAgitatorVoltage);
  }
}