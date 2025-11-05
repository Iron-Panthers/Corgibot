package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;


public class NewDrive extends DefaultDrive {
    public int timeInMs;
    public int currentTime = 0;
    public int totalTimesRun; //1000 ms in a sec / 20 ms per execute
    public double speed;

    public NewDrive(DriveSubsystem driveSubsystem, DoubleSupplier timeInSec, DoubleSupplier speed) {
        super(driveSubsystem, timeInSec, speed);
        this.timeInMs = (int)(timeInSec.getAsDouble()*1000);
        this.totalTimesRun = timeInMs/20;
        this.speed = speed.getAsDouble();
    }

    @Override
    public void initialize() {
        currentTime = 0;
    }
  
    @Override
    public void execute() { //every 20 ms
        currentTime++;
        this.driveSubsystem.drive(speed, speed);;
    }
  
    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.drive(0, 0);
    }

@Override
    public boolean isFinished() {
        return currentTime >= totalTimesRun;
    }

}
