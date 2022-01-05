// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


// import pre-built WPILIB classes
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.command.Command;

// import subsystems
import frc.robot.subsystems.DriveTrainSubsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class RobotMap {
    // constanst properties
//    private final DefaultDrive m_defaultDrive;
    private final DriveTrainSubsystem m_drive = new DriveTrainSubsystem();
//    private final GyroTurn m_gyroTurn;
//    private static RobotContainer m_robotContainer;
//    private final Empty m_empty;
//    private final ReverseDrive m_reverseDrive;
//    private final ShooterSubsystem m_shoot;
//    private final Shoot m_shootCommand;
//    private final Convey m_conveyCommand;
//    private final IntakeSubsystem m_intake;
//    private final Intake m_intakeCommand;
}
