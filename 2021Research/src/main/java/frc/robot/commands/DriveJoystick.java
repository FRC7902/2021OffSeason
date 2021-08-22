// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveJoystick extends CommandBase {
  private final DriveSubsystem m_driveSubsystem;
  private final Joystick m_driverJoystick;
  /** Creates a new DriveJoystick. */
  public DriveJoystick(DriveSubsystem driveSubsystem, Joystick driverJoystick) {
    m_driverJoystick = driverJoystick;
    m_driveSubsystem = driveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(driveSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveSubsystem.stopMotors();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
