package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class Turret extends SubsystemBase {
    /* Hardware */
    private final TalonFX m_TurretSpinMotor = new TalonFX(ManipulatorConstants.kTurretRotationMotor);
    private final TalonFX m_TurretAngleMotor = new TalonFX(ManipulatorConstants.kTurretAngleMotor);
    private final TalonFX m_TurretFiringMotor = new TalonFX(ManipulatorConstants.kTurretFiringMotor);
  /** Creates a new TurretSubsytem. */
  public Turret() {
    TalonFXConfiguration angleConfigs = new TalonFXConfiguration();
    TalonFXConfiguration firingConfigs = new TalonFXConfiguration();
    TalonFXConfiguration spinConfigs = new TalonFXConfiguration();
      //Set configurations  
      
      firingConfigs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      angleConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      spinConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

      spinConfigs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    m_TurretAngleMotor.getConfigurator().apply(angleConfigs);
    m_TurretFiringMotor.getConfigurator().apply(firingConfigs);
    m_TurretSpinMotor.getConfigurator().apply(spinConfigs);

  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command Home() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          
        });
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