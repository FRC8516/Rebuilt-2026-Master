package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class Agitator extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
   
    private final SparkFlex m_Agitator = new SparkFlex(ManipulatorConstants.kAgitatorMotor,MotorType.kBrushless);
   
  public Agitator() {
    //Need to apply configs to motor, Unknown how much change is needed
    SparkBaseConfig configs = new SparkFlexConfig();
      //Set configurations  
      
    //Set configurations  
    configs.inverted(false);
    configs.idleMode(IdleMode.kCoast);
    configs.smartCurrentLimit(80);
    m_Agitator.configureAsync(configs, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Agitate(){
    for(var i = 0; i>100; i++){
    //if (i==90){m_Agitator.set(15);}
      SmartDashboard.putNumber("wasteing time", i);
    }
    m_Agitator.set(5);
  }
  public void instantAgitate(){
    m_Agitator.set(5);
  }
  public void antiAgi(){
    m_Agitator.set(-5);
  }
  public void stop(){
    m_Agitator.stopMotor();
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
