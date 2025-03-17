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

    private TalonSRX mFrontLeftTalon;
    private TalonSRX mRearLeftTalon;
    private TalonSRX mFrontRightTalon;
    private TalonSRX mRearRightTalon;
    // private PWMTalonSRX

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    // private ControlMode m_driveControlMode = ControlMode.PercentOutput;
    private PIDController rotController;
    private ADIS16470_IMU gyro;


    // Add modes (an enum)
   

    public DriveSubsystem() {
        mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
        mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
        mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
        mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);

        gyro = new ADIS16470_IMU();
    }

    // Take in x and y and rotation
    public void drive(double x, double y, double z){

        double joystickAngle = Math.atan2(y, x);
        double magnitude = Math.sqrt(x*x + y*y);
        
        // I changed it to cos cause it makes more sense to me, but mathematically the same
        double frontLeftPower =  Math.cos(Math.toRadians(joystickAngle - 45)) * magnitude; 
        double frontRightPower = Math.sin(Math.toRadians(joystickAngle - 45)) * magnitude; 
        double rearLeftPower = -frontRightPower; 
        double rearRightPower = -frontLeftPower; 


        // Use talon.set to send power
        mFrontLeftTalon.set(TalonSRXControlMode.PercentOutput, frontLeftPower);
        mFrontRightTalon.set(TalonSRXControlMode.PercentOutput, frontRightPower);
        mRearLeftTalon.set(TalonSRXControlMode.PercentOutput, rearLeftPower);
        mRearRightTalon.set(TalonSRXControlMode.PercentOutput, rearRightPower);
    }


   // Get the robot to turn to some degree
   public void rotateToAngle(double targetAngle) {}

    
}

