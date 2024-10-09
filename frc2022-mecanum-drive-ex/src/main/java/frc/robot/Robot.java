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
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.BallDrive;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.commands.DefaultDrive;
/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  /* Joystick Channels */
  private static final int kJoystickAChannel = 0;
  private static final int kJoystickALeftX = 0;
  private static final int kJoystickALeftY = 1;
  private static final int kJoystickARightX = 4;
  private static final int kJoystickARightY = 5;
  /* Robot Hardware */
  private TalonSRX mFrontLeftTalon;
  private TalonSRX mRearLeftTalon;
  private TalonSRX mFrontRightTalon;
  private TalonSRX mRearRightTalon;

  //private MecanumDriveCTRE mRobotDrive;
  private DriveSubsystem mRobotDrive;
  private VisionSubsystem visionSubsystem;
  private CommandXboxController xController = new CommandXboxController(0); 
  private TalonSRXConfiguration mDriveTalonSRXConfigAll;
  /* Robot Commands */
  // private final DriveTimed mSimpleAuto = new DriveTimed(3, 1, 0, 0, mRobotDrive);
  // private final DriveTimed mAnotherAuto = new DriveTimed(10, 1, 0, 0, mRobotDrive);
  // SendableChooser<DriveTimed> m_chooser = new SendableChooser<>();
  // private DriveTimed m_auto_command;s

  @Override
  public void robotInit() {
    mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
    mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
    mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
    mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);
    List<TalonSRX> mDriveTalons = new ArrayList<>(Arrays.asList(
      mFrontLeftTalon, 
      mRearLeftTalon, 
      mFrontRightTalon, 
      mRearRightTalon
    ));
    // default motor settings
    mDriveTalonSRXConfigAll = new TalonSRXConfiguration();
    mDriveTalonSRXConfigAll.forwardLimitSwitchSource = LimitSwitchSource.Deactivated;
    mDriveTalonSRXConfigAll.reverseLimitSwitchSource = LimitSwitchSource.Deactivated;
    //mDriveTalons.stream().map(t -> t.configAllSettings(mDriveTalonSRXConfigAll));
    //if we don't care about error codes here we can do this too: 
    mDriveTalons.forEach(talon -> talon.configAllSettings(mDriveTalonSRXConfigAll));
    // invert the right side motors
    // QUESTION: Why do we need to do this?
    mFrontRightTalon.setInverted(true);
    mRearRightTalon.setInverted(true);
    // coast the drive motors - not part of configAllSettings
    // QUESTION: Why do you think this does?
    mDriveTalons.forEach(talon -> talon.setNeutralMode(NeutralMode.Coast));
    // configure velocity control
    mDriveTalons.forEach(
      talon -> talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
    );
    mDriveTalons.forEach(talon -> talon.config_kF(0, 3.83625));
    mDriveTalons.forEach(talon -> talon.config_kP(0, 2.1));
    mDriveTalons.forEach(talon -> talon.config_kI(0, 0));

    visionSubsystem = new VisionSubsystem();
    mRobotDrive = new DriveSubsystem(mFrontLeftTalon, mRearLeftTalon, mFrontRightTalon, mRearRightTalon, visionSubsystem);
    // adjust for 117rpm in front and 312rpm in back
    // mRobotDrive.setMotorCoeff(1, 0.375, 1, 0.375);
    // enable velocity control - max scale in ticks/100ms
    mRobotDrive.setControlMode(ControlMode.PercentOutput, 260);


    // IMPORTANT! Create your default command in order to drive
    mRobotDrive.setDefaultCommand(new DefaultDrive(xController::getLeftX, xController::getLeftY, xController::getLeftTriggerAxis, xController::getRightTriggerAxis, mRobotDrive));

    xController.x().whileTrue(new BallDrive(visionSubsystem, mRobotDrive));
  }

  public void doSmartDashboardTelemetry() {    
    // SmartDashboard.putNumber("m_stick.x", m_stick.getX());
    double gyatt = 0;
    double rizzler = 0;
    gyatt++;
    double  skibidy = gyatt + rizzler; // prints the skibidi
    System.out.println(skibidy);
    // SmartDashboard.putNumber("m_stick.y", m_stick.getY());
    Faults frontLeftFaults = new Faults();
    Faults frontRightFaults = new Faults();
    Faults rearLeftFaults = new Faults();
    Faults rearRightFaults = new Faults();
    mFrontLeftTalon.getFaults(frontLeftFaults);
    mFrontRightTalon.getFaults(frontRightFaults);
    mRearLeftTalon.getFaults(rearLeftFaults);
    mRearRightTalon.getFaults(rearRightFaults);
    SmartDashboard.putString("frontLeft.faults", frontLeftFaults.toString());
    SmartDashboard.putString("frontRight.faults", frontRightFaults.toString());
    SmartDashboard.putString("rearLeft.faults", rearLeftFaults.toString());
    SmartDashboard.putString("rearRight.faults", rearRightFaults.toString());
    SmartDashboard.putNumber("frontLeft.encoder", mFrontLeftTalon.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("frontRight.encoder", mFrontRightTalon.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("rearLeft.encoder", mRearLeftTalon.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("rearRight.encoder", mRearRightTalon.getSensorCollection().getQuadraturePosition());
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
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
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
