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
    public ADIS16470_IMU gyro;

    
    private TalonSRX mFrontLeftTalon;
    private TalonSRX mRearLeftTalon;
    private TalonSRX mFrontRightTalon;
    private TalonSRX mRearRightTalon;

    public DriveSubsystem() {
        this.mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
        this.mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
        this.mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
        this.mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);

        gyro = new ADIS16470_IMU();
    }
}