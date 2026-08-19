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
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveForSeconds;
import frc.robot.commands.DriveToAngle;
import frc.robot.commands.GertrudeDrive;
import frc.robot.commands.NewDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.MyFirstRobot;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private DriveSubsystem driveSubsystem;
  private Joystick gertrude = new Joystick(1);
  public static Robot instance;

  @Override
  public void robotInit() {
    driveSubsystem = new DriveSubsystem();
    instance = this;
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
    GertrudeDrive instance = new GertrudeDrive(driveSubsystem, gertrude::getX, gertrude::getY);
    instance.schedule();

    MyFirstRobot.myFirstRobot();
  }

  public static void driveForward(double speed, double seconds) {
    instance.driveForward(speed, seconds);
  }

  public void driveForward(double speed, double seconds) {
    new InstantCommand(() -> new DriveForSeconds(driveSubsystem, speed, seconds)).schedule();
  }

  public static void turn(double speed, double angle) {
    instance.turn(speed, angle);
  }

  public void turn(double speed, double angle) {
    new InstantCommand(() -> new DriveToAngle(driveSubsystem, angle, speed)).schedule();
  }
}
