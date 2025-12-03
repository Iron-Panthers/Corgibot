// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForSeconds;
import frc.robot.commands.DriveToAngle;
import frc.robot.commands.GertrudeDrive;
import frc.robot.subsystems.DriveSubsystem;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private DriveSubsystem driveSubsystem;
  private static Joystick gertrude = new Joystick(0);

  public static Joystick getGertrude() {
    return gertrude;
  }

  @Override
  public void robotInit() {
    driveSubsystem = new DriveSubsystem();
  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveSubsystem.setDefaultCommand(new GertrudeDrive(driveSubsystem));
    JoystickButton backwardButton = new JoystickButton(gertrude, 2);
    JoystickButton forwardButton = new JoystickButton(gertrude, 3);
    JoystickButton leftButton = new JoystickButton(gertrude, 4);
    JoystickButton rightButton = new JoystickButton(gertrude, 5);

    forwardButton.onTrue(new InstantCommand(
      () -> new SequentialCommandGroup(
        new DriveForSeconds(driveSubsystem, 1.0, 1.0),
        new ParallelCommandGroup(
          new WaitUntilCommand(() -> leftButton.getAsBoolean()),
          new DriveToAngle(driveSubsystem, 270.0, 1.0),
          new WaitUntilCommand(() -> rightButton.getAsBoolean()),
          new DriveToAngle(driveSubsystem, 90.0, 1.0)
      )).schedule()));

    backwardButton.onTrue(new InstantCommand(() -> new SequentialCommandGroup(
      new DriveForSeconds(driveSubsystem, 1.0, 0.1),
      new WaitUntilCommand(0.2),
      new DriveToAngle(driveSubsystem, 180.0, 1.0),
      new WaitUntilCommand(() -> forwardButton.getAsBoolean()),
      new DriveForSeconds(driveSubsystem, 1.0, 1.0)
    ).schedule()));
  }
}
