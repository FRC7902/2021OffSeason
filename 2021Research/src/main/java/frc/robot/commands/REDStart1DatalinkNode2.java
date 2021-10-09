package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DatalinkSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class REDStart1DatalinkNode2 extends SequentialCommandGroup {
    public REDStart1DatalinkNode2(DriveSubsystem driveSubsystem, DatalinkSubsystem datalinkSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new DriveNoSum(1, driveSubsystem),
            new TurnToAngle(-90, driveSubsystem),
            new DriveNoSum(1.1, driveSubsystem),
            new TurnToAngle(180, driveSubsystem),
            new DriveNoSum(4.4, driveSubsystem),
            new DeliverDatalink(datalinkSubsystem).withTimeout(3),
            new DriveNoSum(-4, driveSubsystem),
            new TurnToAngle(-90, driveSubsystem),
            new DriveNoSum(0.5, driveSubsystem),
            new TurnToAngle(0, driveSubsystem),
            new DriveNoSum(1.2, driveSubsystem),
            new DeliverGigabytes(intakeSubsystem).withTimeout(3)
        );
    }
}
