package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.MathUtil;
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
        double twist = -leftTwist.getAsDouble() + rightTwist.getAsDouble();
        double y = ySpeed.getAsDouble();
        double x = -xSpeed.getAsDouble();
        double FrontLeftWheel = x + y + twist;
        double FrontRightWheel = y - x - twist;
        double BackLeftWheel = y - x + twist;
        double BackRightWheel = x + y - twist;

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

