package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.VisionSubsystem;
public class DriveSubsystem extends SubsystemBase {
  private final VisionSubsystem visionSubsystem;
    private TalonSRX mFrontLeftTalon;
    private TalonSRX mRearLeftTalon;
    private TalonSRX mFrontRightTalon;
    private TalonSRX mRearRightTalon;
    // private PWMTalonSRX

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    // private ControlMode m_driveControlMode = ControlMode.PercentOutput;
    private PIDController rotController;
    private ADIS16470_IMU gyro;
    private Modes mode;
    private double targetAngle;
    private double xSpeed;
    private double ySpeed;
    private double rot;

    public enum Modes {
      TURNING, 
      DEFAULT
    }

    public DriveSubsystem(VisionSubsystem visionSubsystem) {
        mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
        mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
        mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
        mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);
        this.visionSubsystem = visionSubsystem;

        rotController = new PIDController(0.01, 0, 0);
        rotController.setSetpoint(0);
        mFrontLeftTalon.setInverted(true);
        mRearLeftTalon.setInverted(true);

        gyro = new ADIS16470_IMU();
    }

   public void drive(double x, double y, double rot)
   {
        // Use the joystick X axis for lateral movement, Y axis for forward
        // movement, and Z axis for rotation.
        // mRobotDrive.driveCartesian(ySpeed, xSpeed, zRot, 0.0);

        // Denominator isn't needed but can ensure all powers have the same ratio
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        double FrontLeftWheel = (y + x + rot) / denominator * 0.5;
        double BackLeftWheel = (y - x + rot) / denominator * 0.5;
        double FrontRightWheel = (y - x - rot) / denominator;
        double BackRightWheel = (y + x - rot) / denominator * 0.5;

        mFrontLeftTalon.set(TalonSRXControlMode.PercentOutput, FrontLeftWheel);
        mFrontRightTalon.set(TalonSRXControlMode.PercentOutput, FrontRightWheel);
        mRearLeftTalon.set(TalonSRXControlMode.PercentOutput, BackLeftWheel);
        mRearRightTalon.set(TalonSRXControlMode.PercentOutput, BackRightWheel);
   }

   public void rotateToAngle(double targetAngle) {
    this.targetAngle = targetAngle;
    
    double power = rotController.calculate(targetAngle);
    drive(0, 0, power);
   }

      /**
   * Set control mode and velocity scale (opt)
   * 
   * @param controlMode control mode to use setting talon output
   * @param velocityScale velocity for full scale in ticks/100ms
   */
  // public void setControlMode(ControlMode controlMode, double velocityScale) {
  //   m_driveControlMode = controlMode;
  // }

  // @Override
  // public void periodic() {
  //   if (targetAngle > Constants.Drive.ANGLE_ERROR) {
  //     rotateToAngle(targetAngle);
  //   } else {
  //     drive(xSpeed, ySpeed, rot);
  //   }
  // }
}

