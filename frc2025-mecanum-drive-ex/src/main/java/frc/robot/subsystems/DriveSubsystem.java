package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

    private double FrontRightWheel;
    private double FrontLeftWheel;
    private double BackRightWheel;
    private double BackLeftWheel; 
    private double theta = 1;
    private double mag = 1;
    private double Ypower = 1;
    private double Xpower = 1;

    private ControlMode m_driveControlMode = ControlMode.PercentOutput;
    
        private double angle;
    
        public DriveSubsystem(TalonSRX mFrontLeftTalon, TalonSRX mRearLeftTalon, TalonSRX mFrontRightTalon, TalonSRX mRearRightTalon) {
            this.mFrontLeftTalon = mFrontLeftTalon;
            this.mRearLeftTalon = mRearLeftTalon;
            this.mFrontRightTalon = mFrontRightTalon;
            this.mRearRightTalon = mRearRightTalon;
    
            this.mRearLeftTalon.setInverted(true);
            this.mRearRightTalon.setInverted(true);
    
            this.BackLeftWheel = 0;
            this.BackRightWheel = 0;
            this.FrontLeftWheel= 0;
            this.FrontRightWheel = 0;
            gyro = new ADIS16470_IMU();
    
            
    
          
        }
    
    
        public DriveSubsystem() {
    
            this.mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
            this.mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
            this.mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
            this.mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);
            this.angle = 0;

        gyro = new ADIS16470_IMU();

       
        drivebaseTab.addDouble("BackLeftPower", () -> this.BackLeftWheel);
        drivebaseTab.addDouble("BackRightPower", () -> this.BackRightWheel);
        drivebaseTab.addDouble("FrontLeftPower", () -> this.FrontLeftWheel);
        drivebaseTab.addDouble("FrontRightPower", () -> this.BackRightWheel);
        
        drivebaseTab.addDouble("FrontRightSpeed", () -> mFrontRightTalon.getSelectedSensorVelocity());
        drivebaseTab.addDouble("FrontLeftSpeed", mFrontLeftTalon::getSelectedSensorVelocity);
        drivebaseTab.addDouble("RearRightSpeed", mRearRightTalon::getSelectedSensorVelocity);
        drivebaseTab.addDouble("RearLeftSpeed", mRearLeftTalon::getSelectedSensorVelocity);

        drivebaseTab.addDouble("FrontRightRatshower", () -> mFrontRightTalon.getSelectedSensorVelocity() / this.FrontRightWheel);
        drivebaseTab.addDouble("FrontLeftRatshower", () -> mFrontLeftTalon.getSelectedSensorVelocity() / this.FrontLeftWheel);
        drivebaseTab.addDouble("RearRightRatshower", () -> mRearRightTalon.getSelectedSensorVelocity() / this.BackRightWheel);
        drivebaseTab.addDouble("BackLeftRatshower", () -> mRearLeftTalon.getSelectedSensorVelocity() / this.BackLeftWheel);

        drivebaseTab.addDouble("Angle", () -> Math.toDegrees(this.angle));

      
    }
    public void setSpeed(double y, double x)
    {
     // Use the joystick X axis for lateral movement, Y axis for forward
     // movement, and Z axis for rotation.
         // mRobotDrive.driveCartesian(ySpeed, xSpeed, zRot, 0.0);
      
      
         
    }

    public void driveHEHEHEHE(double x, double y){
      this.angle = Math.toRadians(calcAngle(x, y));
      double magnetude = calcMag(x, y);
      BackLeftWheel = Math.sin(this.angle) * magnetude;
      BackRightWheel = Math.cos(this.angle) * magnetude;
      FrontLeftWheel = Math.cos(this.angle) * magnetude;
      FrontRightWheel = Math.sin(this.angle) * magnetude;
      


    }

    public static double calcAngle(double x, double y){
      double offset = -45;
      double angle = Math.toDegrees(Math.atan2(y,x)) + offset;
      return angle;
    }

    public static double calcMag(double x, double y){
      double magnetude = Math.sqrt(x*x + y*y);
      return magnetude;
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

       this.mFrontLeftTalon.set(ControlMode.PercentOutput, FrontLeftWheel);
       this.mRearLeftTalon.set(ControlMode.PercentOutput, BackLeftWheel);
       this.mFrontRightTalon.set(ControlMode.PercentOutput, FrontRightWheel);
       this.mRearRightTalon.set(ControlMode.PercentOutput, BackRightWheel);
   }
 }