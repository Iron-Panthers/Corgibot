// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForSeconds;
import frc.robot.commands.DriveToAngle;
import frc.robot.commands.DriveToLimelight;
import frc.robot.commands.GertrudeDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private DriveSubsystem driveSubsystem;
  private VisionSubsystem visionSubsystem;

  private static Joystick gertrude = new Joystick(1);
    public static Joystick getGertrude() {
      return gertrude;
    }

  @Override
  public void robotInit() {
    driveSubsystem = new DriveSubsystem();
    visionSubsystem = new VisionSubsystem();
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
    JoystickButton button2 = new JoystickButton(gertrude, 2);
    JoystickButton button3 = new JoystickButton(gertrude, 3);
    JoystickButton button4 = new JoystickButton(gertrude, 4);
    SequentialCommandGroup seqCommandGroup = new SequentialCommandGroup(
      new InstantCommand(() -> new DriveForSeconds(driveSubsystem, 0.1, 5).schedule()),
      new WaitUntilCommand(() -> button4.getAsBoolean()),
      new InstantCommand(() -> System.out.println("Hi!")),
      new WaitUntilCommand(6.7),
      new InstantCommand(() -> System.out.println("Hello"))
    );

    ParallelCommandGroup parCommandGroup = new ParallelCommandGroup(
      new InstantCommand(() -> new DriveToAngle(driveSubsystem, 90, 0.1).schedule()),
      new InstantCommand(() -> new DriveForSeconds(driveSubsystem, 0.1, 5).schedule())

    );

    button2.onTrue(seqCommandGroup);
    button3.onTrue(parCommandGroup);

  }

  @Override
  public void teleopPeriodic() {
    // GertrudeDrive instance = new GertrudeDrive(driveSubsystem, gertrude::getY, gertrude::getX);
    // instance.schedule();
    //driveSubsystem.setDefaultCommand(new DriveToLimelight(driveSubsystem, visionSubsystem)); 
    //new SequentialCommandGroup(
      //new DriveToAngle(driveSubsystem, 359, 1),
      //new WaitUntilCommand(() -> true),
      //new InstantCommand(() -> System.out.println("hello")),
      //new DriveForSeconds (driveSubsystem, 1, 20)
      //).schedule();
  }

}
