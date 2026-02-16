package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;

public class turretAim extends Command {
  private final Turret m_turret;
  private final Vision m_Vision;
  private double m_wantedPos;
  public turretAim(Vision TurretVision, Turret TurretSubsytem) {
    m_Vision = TurretVision;
    m_turret = TurretSubsytem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_turret.Fire();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Turret Valid Target", m_Vision.getTV());
    m_wantedPos = /*Math */0;
    m_turret.setTurretPos(/*Insert the math here*/0);
    if (-0.05 < m_wantedPos && m_wantedPos < 0.05){
      m_turret.Feed();
    }else{
      m_turret.NoFeed();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.NoFeed();
    m_turret.CeaseFire();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
