// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  /** Creates a new TurnToAngle. */
  public TurnToAngle(double targetAngleDeg, DriveSubsystem driveSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(0.06, 0, 0.001), 
        // This should return the measurement
        driveSubsystem::getHeading,
        // This should return the setpoint (can also be a constant)
        targetAngleDeg,
        // This uses the output
        output -> {
          // Use the output here
          driveSubsystem.driveRaw(output, -output);
        }, driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController()
        .setTolerance(0.001, 0.1);

  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
