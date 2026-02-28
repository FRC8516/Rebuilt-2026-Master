// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FeedAndAgi;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Test extends Command {
  
  private final FloorIntake m_Intake;
  private final Turret m_Turret;
 private final boolean m_unstuck;
 private final FeedAndAgi m_feed;
  public Test(Turret turret, FloorIntake floorIntake, FeedAndAgi feed, boolean unstuck) {
    m_Intake = floorIntake;
    m_Turret = turret;
    m_unstuck = unstuck;
    m_feed = feed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(floorIntake, turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_unstuck){
      m_Turret.Fire();
    }else{
      m_feed.Feed();  
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(m_unstuck){
      m_Turret.CeaseFire();
    }else{
      m_feed.NoFeed();
    }
      
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

