// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Climb extends Command {
  
  private final Climber m_Climber;
  private final boolean m_yes;
  public Climb(Climber climber, boolean yes) {
    m_Climber = climber;
    m_yes = yes;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_Climber.setPos(true);
    if(m_yes){
      m_Climber.testrun();
    }else{
      m_Climber.nontestrun();
    }
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Climber In Pos", m_Climber.inPos());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_Climber.setPos(false);
    m_Climber.stop();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

