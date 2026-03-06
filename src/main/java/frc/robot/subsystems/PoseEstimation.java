package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LimelightHelpers.RawFiducial;

public class PoseEstimation extends SubsystemBase {
  private RawFiducial[] fiducials;
  private String m_limelightName;
  private boolean throttled = false;

  public PoseEstimation(String limelightName, double forward, double side, double up, double roll, double pitch, double yaw) {
    m_limelightName = limelightName;
    LimelightHelpers.setCameraPose_RobotSpace(
        limelightName,
        forward, 
        side,
        up,
        roll,
        pitch,
        yaw);
    LimelightHelpers.SetFiducialIDFiltersOverride(limelightName, new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32});
  }
  public void toggleThrottle(){
    if (throttled == false) {
        LimelightHelpers.SetThrottle(m_limelightName, 200);
        throttled = true;
    }else if(throttled == true){
        LimelightHelpers.SetThrottle(m_limelightName, 0);
        throttled = false;
    }
    
  }
  public static class NoSuchTargetException extends RuntimeException {
    public NoSuchTargetException(String message) {
      super(message);
    }
  }


  @Override
  public void periodic() {
    fiducials = LimelightHelpers.getRawFiducials(m_limelightName);
  }
  public RawFiducial getClosestFiducial() {
    if (fiducials == null || fiducials.length == 0) {
        throw new NoSuchTargetException("No fiducials found.");
    }

    RawFiducial closest = fiducials[0];
    double minDistance = closest.ta;

    for (RawFiducial fiducial : fiducials) {
        if (fiducial.ta > minDistance) {
            closest = fiducial;
            minDistance = fiducial.ta;
        }
    }

    return closest;
  }

  public RawFiducial getFiducialWithId(int id) {
  
    for (RawFiducial fiducial : fiducials) {
        if (fiducial.id == id) {
            return fiducial;
        }
    }
    throw new NoSuchTargetException("Can't find ID: " + id);
  }

public RawFiducial getFiducialWithId(int id, boolean verbose) {
  StringBuilder availableIds = new StringBuilder();

  for (RawFiducial fiducial : fiducials) {
      if (availableIds.length() > 0) {
          availableIds.append(", ");
      } //Error reporting
      availableIds.append(fiducial.id);
      
      if (fiducial.id == id) {
          return fiducial;
      }
  }
  throw new NoSuchTargetException("Cannot find: " + id + ". IN view:: " + availableIds.toString());
  }

  public double getTX(){
    return LimelightHelpers.getTX(m_limelightName);
  }
  public double getTY(){
    return LimelightHelpers.getTY(m_limelightName);
  }
  public double getTA(){
    return LimelightHelpers.getTA(m_limelightName);
  }
  public boolean getTV(){
    return LimelightHelpers.getTV(m_limelightName);
  }
  public double getClosestTX(){
    return getClosestFiducial().txnc;
  }
  public double getClosestTY(){
    return getClosestFiducial().tync;
  }
  public double getClosestTA(){
    return getClosestFiducial().ta;
  }
}