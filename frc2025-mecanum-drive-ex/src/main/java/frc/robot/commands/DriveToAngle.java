package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToAngle extends DefaultDrive {
    public double finalAngle;
    public double speed;
    public double currentAngle;

    public DriveToAngle(DriveSubsystem driveSubsystem, DoubleSupplier angle, DoubleSupplier speed) {
        super(driveSubsystem, angle, speed);
        this.finalAngle = angle.getAsDouble();
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

