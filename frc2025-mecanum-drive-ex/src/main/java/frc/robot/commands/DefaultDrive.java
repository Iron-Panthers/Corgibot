package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends Command {
    // Drivebase subsystem
    public DriveSubsystem driveSubsystem;
    public double speed;
    public double timeInSeconds;

    // Take in a subsystem and suppliers
    public DefaultDrive (DriveSubsystem driveSubsystem, double speed, double timeInSeconds) {
        // instantiate drivebase and suppliers
        this.driveSubsystem = driveSubsystem;
        this.speed = speed;
        this.timeInSeconds = timeInSeconds;

        addRequirements(driveSubsystem);
    }


    @Override
    public void initialize() {
      // Anything that needs to be reset between commands

    }
  
    @Override
    public void execute() {
      timeInSeconds = timeInSeconds - 0.02;
      driveSubsystem.drive(speed, speed);
    }
  
    @Override
    public void end(boolean interrupted) {
      driveSubsystem.drive(0.0, 0.0);
    }
  
    @Override
    public boolean isFinished() {
      if (timeInSeconds <= 0) {
        return true;
      }
      else {
        return false;
      }
    }
}
