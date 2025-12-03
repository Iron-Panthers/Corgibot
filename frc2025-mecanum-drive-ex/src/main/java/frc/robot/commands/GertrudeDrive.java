package frc.robot.commands;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class GertrudeDrive extends Command{

    public DriveSubsystem driveSubsystem;
    private double y;
    private double x;

    public GertrudeDrive(DriveSubsystem driveSubsystem, DoubleSupplier y, DoubleSupplier x) {
        addRequirements(driveSubsystem);
        this.driveSubsystem = driveSubsystem;
        this.y = y.getAsDouble();
        this.x = x.getAsDouble();
    }

    @Override
    public void initialize() {
        y = 0;
        x = 0;
    }
  
    @Override
    public void execute() { 
        y = Robot.getGertrude().getY();
        x = Robot.getGertrude().getX();
        driveSubsystem.setSpeed(y, x);
    }
  
    @Override
    public void end(boolean interrupted) {
    }

    //@Override
    //public boolean isFinished() {
    //}
}
