package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
public class DriveSubsystem extends SubsystemBase {

    private TalonSRX mFrontLeftTalon;
    private TalonSRX mFrontRightTalon;
    // private PWMTalonSRX

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");

    // private ControlMode m_driveControlMode = ControlMode.PercentOutput;
    private PIDController rotController;
    private ADIS16470_IMU gyro;

    // Add modes (an enum)
    public DriveSubsystem() {
        mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
        mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);

        gyro = new ADIS16470_IMU();
    }

    public static double CalculateDirection(double x, double magnitude) {
        return Math.abs(x) < Constants.Drive.EPSILON ? 0 : Math.signum(x) * magnitude;
    }

    public void drive(double rightPower, double leftPower) {
        mFrontLeftTalon.set(TalonSRXControlMode.PercentOutput, leftPower);
        mFrontRightTalon.set(TalonSRXControlMode.PercentOutput, rightPower);
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public double getMotorPosition() {
        return mFrontLeftTalon.getSelectedSensorPosition();
    }

    public void resetMotorPosition() {
        mFrontLeftTalon.setSelectedSensorPosition(0);
    }

    public void resetGyro() {
        gyro.reset();
   }
}

