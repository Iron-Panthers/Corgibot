package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

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
public class DriveSubsystem extends SubsystemBase {


    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    // private ControlMode m_driveControlMode = ControlMode.PercentOutput;
    private PIDController rotController;
    private ADIS16470_IMU gyro;

    
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
    private double Ypower = 1;
    private double Xpower = 1;

    private ControlMode m_driveControlMode = ControlMode.PercentOutput;

    public DriveSubsystem( TalonSRX mFrontLeftTalon, TalonSRX mRearLeftTalon, TalonSRX mFrontRightTalon, TalonSRX mRearRightTalon) {
        this.mFrontLeftTalon = mFrontLeftTalon;
        this.mRearLeftTalon = mRearLeftTalon;
        this.mFrontRightTalon = mFrontRightTalon;
        this.mRearRightTalon = mRearRightTalon;
        gyro = new ADIS16470_IMU();
    }


    public DriveSubsystem() {
        this.mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
        this.mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
        this.mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
        this.mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);

        gyro = new ADIS16470_IMU();
    }
    public void setSpeed(DoubleSupplier ySpeed, DoubleSupplier xSpeed)
    {
     // Use the joystick X axis for lateral movement, Y axis for forward
     // movement, and Z axis for rotation.
         // mRobotDrive.driveCartesian(ySpeed, xSpeed, zRot, 0.0);
 
 
         double y = ySpeed.getAsDouble();
         double x = xSpeed.getAsDouble();
        
         theta = Math.atan2(y / x);
         mag = Math.sqrt(x * x + y * y);

         Ypower = Math.sin(theta - 45) * mag;
         Xpower = Math.cos(theta - 45) * mag;

         FrontLeftWheel = Ypower;
         BackRightWheel = Ypower;
         BackLeftWheel = Xpower;
         FrontRightWheel = Xpower;
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


   public void periodic(){
        mFrontLeftTalon.set(m_driveControlMode, FrontLeftWheel);
        mFrontRightTalon.set(m_driveControlMode, FrontRightWheel);
        mRearLeftTalon.set(m_driveControlMode, BackLeftWheel);
        mRearRightTalon.set(m_driveControlMode, BackRightWheel);
   }
 }