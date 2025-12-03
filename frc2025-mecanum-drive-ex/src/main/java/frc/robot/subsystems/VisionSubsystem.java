package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  //Takes in the area percentage the ball takes up
  private double areaPer;
  private double xErr;
  private double yErr;

  public double getArea() {
    return areaPer;
  }

  public double getX() {
    return xErr;
  }

  public double getY() {
    return yErr;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    areaPer = table.getEntry("ta").getDouble(0);
    
    yErr = table.getEntry("ty").getDouble(0);
    xErr = table.getEntry("tx").getDouble(0);
  }
}