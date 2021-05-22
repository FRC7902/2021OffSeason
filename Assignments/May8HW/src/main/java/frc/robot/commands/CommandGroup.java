package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class CommandGroup extends SequentialCommandGroup {
    public CommandGroup(DriveSubsystem driveSubsystem) {
        addCommands(
            new DriveToDistance(2, driveSubsystem),
            new TurnToAngle(180, driveSubsystem),
            new DriveToDistance(2, driveSubsystem),
            new TurnToAngle(180, driveSubsystem)
        );
    }
}
