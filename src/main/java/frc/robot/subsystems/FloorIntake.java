package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class FloorIntake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private SparkFlex m_IntakeMotor = new SparkFlex(ManipulatorConstants.kIntakeMotor,MotorType.kBrushless);
  public FloorIntake() {
    //Need to apply configs to motor, Unknown how much change is needed\
    SparkBaseConfig configs = new SparkFlexConfig();
      //Set configurations  
      
    configs.inverted(false);
    configs.idleMode(IdleMode.kBrake);
    m_IntakeMotor.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void intake(){
    m_IntakeMotor.setVoltage(ManipulatorConstants.kIntakeVoltage);
  }
  public void output(){
    m_IntakeMotor.setVoltage(-ManipulatorConstants.kIntakeVoltage);
    
  }
  public void stop(){
    m_IntakeMotor.stopMotor();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
