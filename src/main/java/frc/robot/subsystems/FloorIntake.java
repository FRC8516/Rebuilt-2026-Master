package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class FloorIntake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private TalonFX m_IntakeMotor = new TalonFX(ManipulatorConstants.kIntakeMotor);
  public FloorIntake() {
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public void intake(){
    
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
}
