package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class DriveForwardAndTurn extends SequentialCommandGroup {
  public DriveForwardAndTurn(DriveSubsystem driveSubsystem) {
    addCommands(

        new DriveToDistance(5, driveSubsystem), new TurnToAngle(-45, driveSubsystem)

    );
  }
}