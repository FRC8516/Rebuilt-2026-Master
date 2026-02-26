// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Unstuck extends Command {
  private final Turret m_Turret;
 
  public Unstuck(Turret turret) {
    m_Turret = turret;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Turret.unstuck();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
    m_Turret.NoFeed();
    m_Turret.CeaseFire();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

