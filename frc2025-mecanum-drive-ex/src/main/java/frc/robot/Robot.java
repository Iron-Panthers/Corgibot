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
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveToAngle;
import frc.robot.commands.NewDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WheelSubsystem;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
 

  private DriveSubsystem driveSubsystem;
  private WheelSubsystem wheelSubsystem;
  private CommandXboxController xController = new CommandXboxController(0);

  private Joystick gertrude = new Joystick(1); 

  @Override
  public void robotInit() {

    driveSubsystem = new DriveSubsystem();
    wheelSubsystem = new WheelSubsystem();


    // IMPORTANT! Create your default command in order to drive
  }

  public void setSpeed(double speed) {
    speed = Constants.ShowcaseSubsystem.MOTOR_1_SPEED;
  }

  public void doSmartDashboardTelemetry() {    
    // If sad, ask Brandon
  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // always report telemetry
    doSmartDashboardTelemetry();
  }

  @Override
  public void teleopInit() {
    DefaultDrive instance = new NewDrive(driveSubsystem, () -> 5, () -> 20);
    instance.schedule();
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.

    // driveSubsystem.setDefaultCommand(new DefaultDrive(driveSubsystem, gertrude::getY, gertrude::getX));
    //wheelSubsystem.setDefaultCommand(new DefaultDrive(wheelSubsystem, gertrude::getY, gertrude::getX));

  }

  // //public Command getAutonomousCommand() {
  // //}

  // @Override
  // public void autonomousInit() {
  //   // m_auto_command = m_chooser.getSelected();
  //   // // schedule the autonomous command
  //   // if (m_auto_command != null) {
  //   //   m_auto_command.schedule();
  //   // }
  // }

  // @Override
  // public void autonomousPeriodic() {}
}
