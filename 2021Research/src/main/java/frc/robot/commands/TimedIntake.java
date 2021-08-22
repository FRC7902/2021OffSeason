// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class TimedIntake extends CommandBase {
  private final IntakeSubsystem m_intakeSubsystem;
  private final StorageSubsystem m_storageSubsystem;

  private final double m_duration;

  private final Timer m_timer;
  /** Creates a new TimedIntake. */
  public TimedIntake(IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem, double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intakeSubsystem = intakeSubsystem;
    m_storageSubsystem = storageSubsystem;

    addRequirements(intakeSubsystem, storageSubsystem);

    m_timer = new Timer();
    m_duration = duration;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_timer.get() < m_duration){
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
