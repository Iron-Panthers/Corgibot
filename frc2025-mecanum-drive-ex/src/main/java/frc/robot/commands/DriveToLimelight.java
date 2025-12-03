package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class DriveToLimelight extends Command{
    public DriveSubsystem driveSubsystem;
    public VisionSubsystem visionSubsystem;
    private double y;
    private double x;

    // Take in a subsystem and suppliers
    public DriveToLimelight (DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
        // instantiate drivebase and suppliers
        this.driveSubsystem = driveSubsystem;
        this.visionSubsystem = visionSubsystem;
        addRequirements(driveSubsystem);
    }


    @Override
    public void initialize() {

    }
  
    @Override
    public void execute() {
        this.y = visionSubsystem.getY();
        this.x = visionSubsystem.getX();
        driveSubsystem.setSpeed(y, x);
    }
  
    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.drive(0, 0);
    }
  
    //@Override
    //public boolean isFinished() {

    //}

}
