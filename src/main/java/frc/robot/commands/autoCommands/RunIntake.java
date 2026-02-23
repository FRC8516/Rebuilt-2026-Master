// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import frc.robot.subsystems.FloorIntake;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class RunIntake extends Command {
  
  private final FloorIntake m_Intake;
 
  public RunIntake(FloorIntake floorIntake) {
    m_Intake = floorIntake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(floorIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Intake.intake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

