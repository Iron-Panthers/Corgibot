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

    private double m_frontLeftCoeff = 1;
    private double m_rearLeftCoeff = 1;
    private double m_frontRightCoeff = 1;
    private double m_rearRightCoeff = 1;

    private double FrontRightWheel = 1;
    private double FrontLeftWheel = 1;
    private double BackRightWheel = 1;
    private double BackLeftWheel = 1; 
    private double theta = 1;
    private double mag = 1;
    //
    private double Ypower = 1;
    private double Xpower = 1;

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    private ControlMode m_driveControlMode = ControlMode.PercentOutput;

    public DriveSubsystem( TalonSRX mFrontLeftTalon, TalonSRX mRearLeftTalon, TalonSRX mFrontRightTalon, TalonSRX mRearRightTalon) {
        this.mFrontLeftTalon = mFrontLeftTalon;
        this.mRearLeftTalon = mRearLeftTalon;
        this.mFrontRightTalon = mFrontRightTalon;
        this.mRearRightTalon = mRearRightTalon;
    }

   public void drive(DoubleSupplier ySpeed, DoubleSupplier xSpeed)
   {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
        // mRobotDrive.driveCartesian(ySpeed, xSpeed, zRot, 0.0);


        double y = ySpeed.getAsDouble();
        double x = xSpeed.getAsDouble();
        // theta = Math.atan(y / x);
        // mag = Math.sqrt(x * x + y * y);
        // Ypower = Math.sin(theta - 45) * mag;
        // Xpower = Math.cos(theta - 45) * mag;
        // Ypower = FrontLeftWheel = BackRightWheel;
        // Xpower = BackLeftWheel = FrontRightWheel;
        double FrontLeftWheel = x + y;
        double FrontRightWheel = y - x;
        double BackLeftWheel = y - x;
        double BackRightWheel = x + y;

        mFrontLeftTalon.set(m_driveControlMode, FrontLeftWheel);
        mFrontRightTalon.set(m_driveControlMode, FrontRightWheel);
        mRearLeftTalon.set(m_driveControlMode, BackLeftWheel);
        mRearRightTalon.set(m_driveControlMode, BackRightWheel);
   }
      public void setMotorCoeff(
        double frontLeftCoeff,
        double rearLeftCoeff,
        double frontRightCoeff,
        double rearRightCoeff) {
      m_frontLeftCoeff = frontLeftCoeff;
      m_rearLeftCoeff = rearLeftCoeff;
      m_frontRightCoeff = frontRightCoeff;
      m_rearRightCoeff = rearRightCoeff;
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

