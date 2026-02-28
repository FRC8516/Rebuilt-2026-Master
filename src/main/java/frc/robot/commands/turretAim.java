package frc.robot.commands;

import com.ctre.phoenix6.controls.PositionVoltage;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;

public class turretAim extends Command {
  private final Turret m_turret;
  private final Vision m_Vision;
  private double m_wantedPos;
   private final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);
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
    //m_turret.setTurretPos(m_request.withPosition(m_turret.RotPos()));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Turret Valid Target", m_Vision.getTV());
    
    m_wantedPos = Math.toDegrees(Math.atan((m_Vision.getAvgTX()/m_Vision.getAvgTA())))/360;
    SmartDashboard.putNumber("Pos", m_wantedPos);
    SmartDashboard.putNumber("Requested Pos", m_turret.RotPos()+m_wantedPos);
    m_turret.setTurretPos(m_request.withPosition(m_turret.RotPos()+m_wantedPos));
    if (-0.5 < m_wantedPos && m_wantedPos < 0.5){
      m_turret.Feed();
    }else{
      m_turret.NoFeed();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.setTurretPos(m_request.withPosition(m_turret.RotPos()));
    m_turret.NoFeed();
    m_turret.CeaseFire();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
