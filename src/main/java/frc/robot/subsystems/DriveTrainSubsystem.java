// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// imports pre-built WPILIB classes
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;

// import vendors dependency
import com.analog.adis16448.frc.ADIS16448_IMU;


public class DriveTrainSubsystem extends Subsystem {

  // declare properties for DriveTrainSubsystem
  public static DriveTrainSubsystem m_drive;
  private double rspeed = 0;
  private double lspeed = 0;
  private final double delta = 0.05;
  private final double maxSpeed = 0.8;
  private double kP = 0.0075;


  // declare motors
  Victor m_frontRight = new Victor(1); //added motor 1 and 2 but we should group them in speed controller groups and call them right and left side them put them into differnetial drive
  Victor m_backLeft = new Victor(2);
  Victor m_backRight = new Victor(3);
  Victor m_frontLeft = new Victor(4);

  // declare motors groups
  SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);
  SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_backRight);


  DifferentialDrive m_ddrive = new DifferentialDrive(m_left, m_right);

  // default constructor
  public DriveTrainSubsystem() {}

  /**
   * @Description: Static method to get an instance of DriveTrainSubsystems
   *
   * @return DriveTrainSubsystem instance
   */
  public static DriveTrainSubsystem getInstance(){
    if (m_drive == null){
      m_drive = new DriveTrainSubsystem();
    }
    return m_drive;
  }

  /**
   * @Description: Method to drive the robot using controlled speed from the user input(Joystick)
   *
   * @param leftSpeed
   * @param rightSpeed
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_ddrive.tankDrive(leftSpeed, rightSpeed);
  }


  /**
   * @Description: Will ask Diego further more about this method
   *
   * @param leftTarget
   * @param rightTarget
   */
  public void trapDrive(double leftTarget, double rightTarget) {
    double leftSpeed, rightSpeed;

    leftTarget *= maxSpeed;
    rightTarget *= maxSpeed;
    if(leftTarget < lspeed) {
      leftSpeed = lspeed - delta;
    }
    else{
      leftSpeed = leftTarget;
    }
    if(rightTarget < rspeed){
      rightSpeed = rspeed - delta;
    }
    else if(rightTarget > rspeed) {
      rightSpeed = rspeed + delta;
    }
    else{
      rightSpeed = rightTarget;
    }

    lspeed = leftSpeed;
    rspeed = rightSpeed;

    m_ddrive.tankDrive(leftSpeed,rightSpeed);
  }

  /**
   * @Description: Will ask Diego further more about this method
   *
   * @param leftTarget
   * @param rightTarget
   */
  public void gyroTrapDrive(double leftTarget, double rightTarget) {
    // double error = 0;
    double error = -getGyro().getRate();
    double leftSpeed, rightSpeed;
    leftTarget *= maxSpeed;
    rightTarget *= maxSpeed;
    if(leftTarget < lspeed) {
      leftSpeed = lspeed - delta;
    }
    else{
      leftSpeed = leftTarget;
    }
    if(rightTarget < rspeed){
      rightSpeed = rspeed - delta;
    }
    else if(rightTarget > rspeed) {
      rightSpeed = rspeed + delta;
    }
    else{
      rightSpeed = rightTarget;
    }

    lspeed = leftSpeed;
    rspeed = rightSpeed;

    m_ddrive.tankDrive(leftSpeed + kP * error,rightSpeed - kP * error);
  }

  /**
   * Description: Will ask Diego further more about this method
   *
   * @param leftSpeed
   * @param rightSpeed
   */
  public void gyroDrive(double leftSpeed ,double rightSpeed) {
    double error = -getGyro().getRate();
    String str = Double.toString(error);
    System.out.println(str);
    m_ddrive.tankDrive(leftSpeed + kP * error, rightSpeed - kP * error);
  }

  /**
   * Description: Method to utilize gyro in turning functionality
   */
  public void gyroTurnFunc() {
    double error = 90 - getGyro().getAngle();
    String str = Double.toString(error);
    System.out.println(str);
    m_drive.tankDrive(kP * error, kP * -error);
  }

  /**
   * @Description: Static method to return gyro instance
   *
   * @return gyro instance
   */
  public static ADIS16448_IMU getGyro() {
    ADIS16448_IMU gyro = new ADIS16448_IMU();
    return gyro;
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
