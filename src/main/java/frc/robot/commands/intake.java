// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FeedAndAgi;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class intake extends Command {
  
  private final FloorIntake m_Intake;
 private final Turret m_Turret;
 private final FeedAndAgi m_feed;
  public intake(FloorIntake floorIntake, Turret turret, FeedAndAgi feed) {
    m_Intake = floorIntake;
    m_Turret = turret;
    m_feed = feed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(floorIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
        m_Intake.intake();
        m_feed.Agitate();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Intake.stop();
    m_feed.NoFeed();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

