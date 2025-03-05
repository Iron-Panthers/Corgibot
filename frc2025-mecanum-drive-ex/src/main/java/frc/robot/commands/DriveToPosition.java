// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveToPosition extends DriveCommand {
  public DriveToPosition(DriveSubsystem mDrive, double target, double speed) {
    super(mDrive, target, speed);
  }

  @Override
  public void execute() {
    // Get difference between current position and target position
    double frontLeftPower = DriveSubsystem.CalculateDirection(subsystem.getMotorPosition() - target, speed); 
    double frontRightPower = frontLeftPower; 

    if (frontLeftPower == 0) {
      isFinished = true;
      subsystem.resetMotorPosition();
    }

    // Set motor powers
    subsystem.drive(frontRightPower, frontLeftPower);
  }
}
