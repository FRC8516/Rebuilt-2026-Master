package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.AudioConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CalibrationSettings;
import frc.robot.Constants.ClimberPos;
import frc.robot.Constants.ManipulatorConstants;

public class Climber extends SubsystemBase {
  private TalonFX m_ClimberMotor = new TalonFX(ManipulatorConstants.kClimberMotor);
  StatusSignal<Angle> aCurrentPosition;
      final double Climb = 2.5; 
      final double Home = 0; 
  //local setpoint for moving to position by magic motion
      private double setPoint;
      private double backUp;
      private String Key;
      /* Keep a brake request so we can disable the motor */
      private final NeutralOut m_brake = new NeutralOut();
      //private double scale = 360;
      private final PositionVoltage m_mmReq = new PositionVoltage(0).withSlot(0);

  public Climber() {
    
    TalonFXConfiguration config = new TalonFXConfiguration().withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimitEnable(true));
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;
    config.Feedback.SensorToMechanismRatio = 100;
    config.CurrentLimits.StatorCurrentLimit = 90;
    Slot0Configs slot0 = config.Slot0;
    slot0.GravityType = GravityTypeValue.Elevator_Static;
    slot0.kP = CalibrationSettings.ElevatorCalibrations.kElevatorkP;   // An error of 1 rps results in 0.11 V output
    slot0.kI = CalibrationSettings.ElevatorCalibrations.kElevatorkI;   // no output for integrated error
    slot0.kD = CalibrationSettings.ElevatorCalibrations.kElevatorkD;

    m_ClimberMotor.getConfigurator().apply(config);
   // m_ClimberMotor.setPosition(0);
  }
  
  public void setPos(boolean dir){
    if (dir){
      Key = ClimberPos.Climb;
      backUp = Climb;
    }else{
      Key = ClimberPos.Home; 
      backUp = Home;
    }
    //gets the current value
	  setPoint = getPreferencesDouble(Key, backUp);
    //sets the new position to the motor controller.
	  this.MoveToPosition(setPoint);
  }
  public void MoveToPosition(double pos){
    m_ClimberMotor.setControl(m_mmReq.withPosition(pos).withSlot(0));
  }
  public void stop(){
    m_ClimberMotor.stopMotor();
  }
  public void testrun(){
    m_ClimberMotor.setVoltage(-6);
  }
  public void nontestrun(){
    m_ClimberMotor.setVoltage(6);
  }
  public Boolean inPos() {
   double dError = aCurrentPosition.getValueAsDouble() - setPoint;
   //Returns the check to see if the elevator is in position
   if ((dError < 0.05) || (dError > -0.05)) {
     return true;
   } else {
     return false;
   }
  }
  @Override
  public void periodic() {
    aCurrentPosition = m_ClimberMotor.getPosition();
    SmartDashboard.putNumber("Climer Pos", aCurrentPosition.getValueAsDouble());
    
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  private double getPreferencesDouble(String key, double backup) {
      if (!Preferences.containsKey(key)) {
        Preferences.initDouble(key, backup);
        Preferences.setDouble(key, backup);
      }
      return Preferences.getDouble(key, backup);
  }
}