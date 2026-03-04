package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class FeedAndAgi extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
   private final SparkFlex m_FeedMotor = new SparkFlex(ManipulatorConstants.kFeedMotor,MotorType.kBrushless);
    private final SparkFlex m_Agitator = new SparkFlex(ManipulatorConstants.kAgitatorMotor,MotorType.kBrushless);
    private final TalonFX m_AltFeed = new TalonFX(ManipulatorConstants.kAltFeedMotor);
  public FeedAndAgi() {
    //Need to apply configs to motor, Unknown how much change is needed\
    SparkBaseConfig configs = new SparkFlexConfig();
      //Set configurations  
      
    //Set configurations  
    configs.inverted(false);
    configs.idleMode(IdleMode.kCoast);
    m_Agitator.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    m_FeedMotor.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Feed(){
    m_FeedMotor.set(ManipulatorConstants.kFeedVoltage);
    m_Agitator.set(ManipulatorConstants.kAgitatorVoltage);
    m_AltFeed.set(ManipulatorConstants.kFeedVoltage);
  }
  public void NoFeed(){
    m_FeedMotor.stopMotor();
    m_Agitator.stopMotor();
    m_AltFeed.stopMotor();
  }
  public void Agitate(){
    m_Agitator.set(15);
  }
  public void antiAgi(){
    m_Agitator.set(-15);
  }
  public void unstuck(){
    m_FeedMotor.setVoltage(-ManipulatorConstants.kFeedVoltage);
    m_Agitator.setVoltage(-ManipulatorConstants.kAgitatorVoltage);
    m_AltFeed.setVoltage(-ManipulatorConstants.kFeedVoltage);
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
