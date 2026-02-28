// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.subsystems.FeedAndAgi;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Unstuck extends Command {
  private final FeedAndAgi m_feed;
 
  public Unstuck(FeedAndAgi feed) {
    m_feed = feed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_feed.unstuck();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
    m_feed.NoFeed();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

