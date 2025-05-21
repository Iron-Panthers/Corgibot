package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ShonnaWay extends Command {
    
    // Your suppliers

    // Drivebase subsystem
    private DriveSubsystem driveSubsystem;
    private VisionSubsystem visionSubsystem;

    // Take in a subsystem and suppliers
    public ShonnaWay (DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
        // instantiate drivebase and suppliers
        this.driveSubsystem = driveSubsystem;
        this.visionSubsystem = visionSubsystem;

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
      driveSubsystem.shonnaWay(visionSubsystem.getDistance());
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
