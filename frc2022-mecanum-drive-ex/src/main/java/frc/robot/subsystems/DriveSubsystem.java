package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DriveSubsystem extends SubsystemBase {

    private TalonSRX mFrontLeftTalon;
    private TalonSRX mRearLeftTalon;
    private TalonSRX mFrontRightTalon;
    private TalonSRX mRearRightTalon;

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    private ControlMode m_driveControlMode = ControlMode.PercentOutput;

    public DriveSubsystem( TalonSRX mFrontLeftTalon, TalonSRX mRearLeftTalon, TalonSRX mFrontRightTalon, TalonSRX mRearRightTalon) {
        this.mFrontLeftTalon = mFrontLeftTalon;
        this.mRearLeftTalon = mRearLeftTalon;
        this.mFrontRightTalon = mFrontRightTalon;
        this.mRearRightTalon = mRearRightTalon;
    }

   public void drive(DoubleSupplier ySpeed, DoubleSupplier xSpeed, DoubleSupplier leftTwist, DoubleSupplier rightTwist)
   {
        // Use the joystick X axis for lateral movement, Y axis for forward
        // movement, and Z axis for rotation.
        // mRobotDrive.driveCartesian(ySpeed, xSpeed, zRot, 0.0);
        double twist = -Math.pow(leftTwist.getAsDouble(), 2) + Math.pow(rightTwist.getAsDouble(), 2);
        double y = Math.copySign(Math.pow(ySpeed.getAsDouble(), 2), ySpeed.getAsDouble());
        double x = Math.copySign(Math.pow(-xSpeed.getAsDouble(), 2), -xSpeed.getAsDouble());

        // Denominator isn't needed but can ensure all powers have the same ratio
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(twist), 1);
        double FrontLeftWheel = (y + x + twist) / denominator * 1.5;
        double BackLeftWheel = (y - x + twist) / denominator;
        double FrontRightWheel = (y - x - twist) / denominator * 1.5;
        double BackRightWheel = (y + x - twist) / denominator;

        mFrontLeftTalon.set(m_driveControlMode, FrontLeftWheel);
        mFrontRightTalon.set(m_driveControlMode, FrontRightWheel);
        mRearLeftTalon.set(m_driveControlMode, BackLeftWheel);
        mRearRightTalon.set(m_driveControlMode, BackRightWheel);
   }

      /**
   * Set control mode and velocity scale (opt)
   * 
   * @param controlMode control mode to use setting talon output
   * @param velocityScale velocity for full scale in ticks/100ms
   */
  public void setControlMode(ControlMode controlMode, double velocityScale) {
    m_driveControlMode = controlMode;
  }
}

