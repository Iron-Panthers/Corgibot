package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToAngle extends Command {
    public DriveSubsystem driveSubsystem;
    public double finalAngle;
    public double speed;
    public double currentAngle;

    public DriveToAngle(DriveSubsystem driveSubsystem, double angle, double speed) {
        addRequirements(driveSubsystem);
        this.driveSubsystem = driveSubsystem;
        this.finalAngle = angle;
        this.speed = speed;
    }

    @Override
    public void initialize() {
        this.driveSubsystem.gyro.reset();
    }
  
    @Override
    public void execute() { 
        currentAngle = this.driveSubsystem.gyro.getAngle();
        this.driveSubsystem.drive(speed, speed*-1);;
    }
  
    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(finalAngle-currentAngle) <= 1;
    }

}

