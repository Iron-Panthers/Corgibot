package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
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
  private ADIS16470_IMU gyro;
  private Modes mode;

  public enum Modes {
    TURNING,
    DEFAULT
  }

  public DriveSubsystem() {
    mFrontLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_LEFT_PORT);
    mRearLeftTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_LEFT_PORT);
    mFrontRightTalon = new TalonSRX(Constants.Drive.MotorPorts.FRONT_RIGHT_PORT);
    mRearRightTalon = new TalonSRX(Constants.Drive.MotorPorts.BACK_RIGHT_PORT);

    mFrontLeftTalon.setInverted(true);
    mRearLeftTalon.setInverted(true);

    gyro = new ADIS16470_IMU();
  }

  public void drive(double x, double y, double rot) {

  }

  @Override
  public void periodic() {
    mFrontLeftTalon.set(TalonSRXControlMode.PercentOutput, -1);
    mRearRightTalon.set(TalonSRXControlMode.PercentOutput, 1);
    mFrontRightTalon.set(TalonSRXControlMode.PercentOutput, 1);
    mRearLeftTalon.set(TalonSRXControlMode.PercentOutput, -1);

  }

  // public void setDefaultDrive() {
  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'setDefaultDrive'");
  // }
}
