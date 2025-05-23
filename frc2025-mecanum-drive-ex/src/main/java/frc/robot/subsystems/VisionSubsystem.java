// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private ShuffleboardTab visionTab = Shuffleboard.getTab("VisionTab");

  //Takes in the area percentage the ball takes up
  private double areaPer;
  private double xErr;
  public VisionSubsystem() {

    visionTab.addDouble("Area Percentage", () -> areaPer);
    visionTab.addDouble("Distance", () -> getDistance());
  }
  public double getDistance(){
    if (areaPer < 2){
      return 0.;
    }
    return 100-areaPer;
  }

  public double getRotation(){
    if(areaPer < 2){
      return 0.;
    }
    return xErr/20;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    areaPer = table.getEntry("ta").getDouble(0);
    xErr = table.getEntry("tx").getDouble(0);
  }
}
