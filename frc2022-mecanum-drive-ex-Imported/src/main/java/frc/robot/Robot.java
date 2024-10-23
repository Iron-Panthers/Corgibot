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
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This is a demo program showing how to use Mecanum control with the
 * MecanumDrive class.
 */
public class Robot extends TimedRobot {
  /* Joystick Channels */
  private static final int kJoystickAChannel = 0;
  private static final int kJoystickALeftX = 0;
  private static final int kJoystickALeftY = 1;
  private static final int kJoystickARightX = 4;
  private static final int kJoystickARightY = 5;

  private DriveSubsystem mRobotDrive;
  private VisionSubsystem visionSubsystem;
  private CommandXboxController xController = new CommandXboxController(0);

  @Override
  public void robotInit() {

    visionSubsystem = new VisionSubsystem();
    mRobotDrive = new DriveSubsystem();

    // IMPORTANT! Create your default command in order to drive
    mRobotDrive.setDefaultCommand();

  }

  public void doSmartDashboardTelemetry() {
    // SmartDashboard.putNumber("m_stick.x", m_stick.getX());

    // System.out.println(skibidy);
    // SmartDashboard.putNumber("m_stick.y", m_stick.getY());
    // Faults frontLeftFaults = new Faults();
    // Faults frontRightFaults = new Faults();
    // Faults rearLeftFaults = new Faults();
    // Faults rearRightFaults = new Faults();
    // mFrontLeftTalon.getFaults(frontLeftFaults);
    // mFrontRightTalon.getFaults(frontRightFaults);
    // mRearLeftTalon.getFaults(rearLeftFaults);
    // mRearRightTalon.getFaults(rearRightFaults);
    // SmartDashboard.putString("frontLeft.faults", frontLeftFaults.toString());
    // SmartDashboard.putString("frontRight.faults", frontRightFaults.toString());
    // SmartDashboard.putString("rearLeft.faults", rearLeftFaults.toString());
    // SmartDashboard.putString("rearRight.faults", rearRightFaults.toString());
    // SmartDashboard.putNumber("frontLeft.encoder",
    // mFrontLeftTalon.getSensorCollection().getQuadraturePosition());
    // SmartDashboard.putNumber("frontRight.encoder",
    // mFrontRightTalon.getSensorCollection().getQuadraturePosition());
    // SmartDashboard.putNumber("rearLeft.encoder",
    // mRearLeftTalon.getSensorCollection().getQuadraturePosition());
    // SmartDashboard.putNumber("rearRight.encoder",
    // mRearRightTalon.getSensorCollection().getQuadraturePosition());
  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // always report telemetry
    doSmartDashboardTelemetry();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
  }

  // //public Command getAutonomousCommand() {
  // //}

  // @Override
  // public void autonomousInit() {
  // // m_auto_command = m_chooser.getSelected();
  // // // schedule the autonomous command
  // // if (m_auto_command != null) {
  // // m_auto_command.schedule();
  // // }
  // }

  // @Override
  // public void autonomousPeriodic() {}
}
