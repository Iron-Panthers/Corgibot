package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.MathUtil;

public class DriveSubsystem extends SubsystemBase {

    private TalonSRX mFrontLeftTalon;
    private TalonSRX mRearLeftTalon;
    private TalonSRX mFrontRightTalon;
    private TalonSRX mRearRightTalon;

    private double m_frontLeftCoeff = 1;
    private double m_rearLeftCoeff = 1;
    private double m_frontRightCoeff = 1;
    private double m_rearRightCoeff = 1;

    private double FrontLeftWheel = 1;
    private double FrontRightWheel = 1;
    private double BackLeftWheel = 1;
    private double BackRightWheel = 1;

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

        if (Math.abs(y) > Math.abs(x)){ // If the robot wants to go forward or backward. Each wheel moves either forward or backward, and the back wheels' power is reduced a bit to match the front wheels.
          FrontLeftWheel = y;
          FrontRightWheel = y;
          BackLeftWheel = y * 0.57;
          BackRightWheel = y * 0.57;
        }
        if (x > 0){ // If the robot wants to move right (Front Right and Back Left go backward, while Back Right and Front Left go forward)
          FrontLeftWheel = y;
          FrontRightWheel = y * -1;
          BackLeftWheel = y * -1;
          BackRightWheel = y;
        }
        if (x < 0){ // If the robot wants to move right (Front Right and Back Left go backward, while Back Right and Front Left go forward)
          FrontLeftWheel = y * -1;
          FrontRightWheel = y;
          BackLeftWheel = y;
          BackRightWheel = y * -1;
        }

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

