// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ExtendClimber extends Command {
  
  private final Climber m_Climber;
 
  public ExtendClimber(Climber climber) {
    m_Climber = climber;
    addRequirements(climber);
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Climber.setPos(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (this.isFinished()){
      this.end(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_Climber.inPos();
  }
}

