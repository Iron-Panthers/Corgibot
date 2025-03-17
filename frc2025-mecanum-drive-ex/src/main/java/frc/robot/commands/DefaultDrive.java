package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends Command {
    
    // Your suppliers
   

    // Drivebase subsystem
    
    // Take in a subsystem and suppliers
    public DefaultDrive (DriveSubsystem mDrive) {

        // instantiate drivebase and suppliers
        
        addRequirements(mDrive);
    }


    @Override
    public void initialize() {
      // Anything that needs to be reset between commands
    }
  
    @Override
    public void execute() {

      // Get your subsystem and get it to drive
      // Hint: read from suppliers

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
