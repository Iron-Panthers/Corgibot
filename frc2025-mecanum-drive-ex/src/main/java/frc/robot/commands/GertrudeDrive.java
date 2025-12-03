
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class GertrudeDrive extends Command {
    public DriveSubsystem driveSubsystem;
    private Joystick gertrude = Robot.getGertrude();
    private double XPower;
    private double YPower;

    public GertrudeDrive(DriveSubsystem driveSubsystem) {
        addRequirements(driveSubsystem);
        this.driveSubsystem = driveSubsystem;
    }

    @Override
    public void initialize() {
        this.driveSubsystem.gyro.reset();
    }

    @Override
    public void execute() {
        XPower = gertrude.getX();
        YPower = gertrude.getY();
        this.driveSubsystem.setSpeed(XPower, YPower);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.drive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
