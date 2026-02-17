package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class Turret extends SubsystemBase {
    /* Hardware */
    private final TalonFX m_TurretSpinMotor = new TalonFX(ManipulatorConstants.kTurretRotationMotor);
   //private final TalonFX m_TurretAngleMotor = new TalonFX(ManipulatorConstants.kTurretAngleMotor);
    private final TalonFX m_TurretFiringMotor = new TalonFX(ManipulatorConstants.kTurretFiringMotor);
    private SparkFlex m_FeedMotor = new SparkFlex(ManipulatorConstants.kFeedMotor,MotorType.kBrushless);
    private SparkFlex m_Agitator = new SparkFlex(ManipulatorConstants.kFeedMotor,MotorType.kBrushless);
  /** Creates a new TurretSubsytem. */
  public Turret() {
   // TalonFXConfiguration angleConfigs = new TalonFXConfiguration();
    TalonFXConfiguration firingConfigs = new TalonFXConfiguration();
    TalonFXConfiguration spinConfigs = new TalonFXConfiguration();
      //Set configurations  
      
      firingConfigs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      //angleConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      spinConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

      spinConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    //m_TurretAngleMotor.getConfigurator().apply(angleConfigs);
    m_TurretFiringMotor.getConfigurator().apply(firingConfigs);
    m_TurretSpinMotor.getConfigurator().apply(spinConfigs);

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
    m_TurretFiringMotor.setVoltage(16);
  }
  public void CeaseFire(){
    m_TurretFiringMotor.stopMotor();
  }
  public void setTurretPos(ControlRequest Position){
      m_TurretSpinMotor.setControl(Position);
  }
}