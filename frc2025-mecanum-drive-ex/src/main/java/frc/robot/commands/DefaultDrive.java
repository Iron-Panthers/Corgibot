package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends Command {
    
    // Your suppliers
    private DoubleSupplier ySupplier;
    private DoubleSupplier xSupplier;

    // Drivebase subsystem
    private DriveSubsystem driveSubsystem;

    // Take in a subsystem and suppliers
    public DefaultDrive (DriveSubsystem driveSubsystem, DoubleSupplier ySupplier, DoubleSupplier xSupplier) {
        // instantiate drivebase and suppliers
        this.driveSubsystem = driveSubsystem;
        this.ySupplier = ySupplier;
        this.xSupplier = xSupplier;

        addRequirements(driveSubsystem);
    }


    @Override
    public void initialize() {
      // Anything that needs to be reset between commands

    }
  
    @Override
    public void execute() {

      // Get your subsystem and get it to drive
      // Hint: read from suppliers

      double y = ySupplier.getAsDouble();
      double x = xSupplier.getAsDouble();

      driveSubsystem.driveHEHEHEHE(x,y);

    }
  
    @Override
    public void end(boolean interrupted) {

      // Set your drive method to not move
    }
  
    // @Override
    // public boolean isFinished() {
    // //   return mTimer.get() >= mTimeS;
    // }


}
