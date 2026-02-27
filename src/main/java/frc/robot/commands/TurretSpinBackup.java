package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Turret;


public class TurretSpinBackup extends Command {
  private final Turret m_turret;
  private final boolean turnleft;
  public TurretSpinBackup(Turret TurretSubsytem, boolean turnDir) {
    turnleft = turnDir;
    m_turret = TurretSubsytem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(turnleft){
      m_turret.turnLeft();
    }else{
      m_turret.turnRight();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.stopTurn();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
