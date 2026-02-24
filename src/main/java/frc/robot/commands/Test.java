// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Test extends Command {
  
  private final FloorIntake m_Intake;
  private final Turret m_Turret;
 private final boolean m_unstuck;
  public Test(Turret turret, FloorIntake floorIntake, boolean unstuck) {
    m_Intake = floorIntake;
    m_Turret = turret;
    m_unstuck = unstuck;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(floorIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    
      //m_Intake.intake();
      //m_Turret.Fire();
      m_Turret.Feed();  
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_Intake.stop();
    m_Turret.NoFeed();
    //m_Turret.CeaseFire();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

