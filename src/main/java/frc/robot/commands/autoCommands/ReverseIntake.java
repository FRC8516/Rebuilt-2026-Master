// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import frc.robot.subsystems.FeedAndAgi;
import frc.robot.subsystems.FloorIntake;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ReverseIntake extends Command {
  
  private final FloorIntake m_Intake;
  private final FeedAndAgi m_Agitator;
  public ReverseIntake(FloorIntake floorIntake, FeedAndAgi agi) {
    m_Intake = floorIntake;
    m_Agitator = agi;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(floorIntake,agi);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Intake.output();
    m_Agitator.antiAgi();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.end(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

