package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class BallDrive extends CommandBase {
    
    // Your suppliers
    private DoubleSupplier xSupplier;
    private DoubleSupplier ySupplier;
    private DoubleSupplier leftTrigger;
    private DoubleSupplier rightTrigger;

    // Drivebase subsystem
    private DriveSubsystem mDrive;
    private VisionSubsystem visionSubsystem;

    public BallDrive (VisionSubsystem visionSubsystem, DriveSubsystem mDrive) {
        
        this.visionSubsystem = visionSubsystem;
        this.mDrive = mDrive;
        addRequirements(mDrive);
    }


    @Override
    public void initialize() {
      // Anything that needs to be reset between commands
      //m_drive.resetEncoders();
    }
  
    @Override
    public void execute() {

      // Get yaw from the vision subsystem\
      double rot = Math.copySign(0.2, visionSubsystem.getAngle());
      double y = 0.6;
      double x = 0;
      mDrive.drive(x, y, rot);
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
