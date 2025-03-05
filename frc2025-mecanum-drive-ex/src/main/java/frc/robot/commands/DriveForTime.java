// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveForTime extends DriveCommand {
  private int time;
  private int executeTimes = 0;

  public DriveForTime(DriveSubsystem mDrive, double target, double speed) { // Here target represents the time in seconds to drive for
    super(mDrive, target, speed);
    executeTimes = (int)Math.floor(target * 50);
  }

  @Override
  public void execute() {
    // Get difference between current position and target position
    double frontLeftPower = speed; 
    double frontRightPower = speed; 

    if (time++ >= executeTimes) {
      isFinished = true;
      subsystem.resetMotorPosition();
    }

    // Set motor powers
    subsystem.drive(frontRightPower, frontLeftPower);
  }
}
