package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends Command {

  public DefaultDrive() {

    // Take in a subsystem and add requirements
    addRequirements();
  }

  @Override
  public void initialize() {
    // Anything that needs to be reset between commands
    // m_drive.resetEncoders();
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

    // Set your drive method to not move
  }

  // @Override
  // public boolean isFinished() {
  // // return mTimer.get() >= mTimeS;
  // }

}
