
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class TurnToAngle extends PIDCommand {
  public TurnToAngle(double targetAngleDeg, DriveSubsystem driveSubsystem) {
    super(new PIDController(0.06, 0, 0.001), driveSubsystem::getHeading, targetAngleDeg, output -> {
      driveSubsystem.driveRaw(output, -output);
    }, driveSubsystem);

    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(0.001, 0.1);

  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
