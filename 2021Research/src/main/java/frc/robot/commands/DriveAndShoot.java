package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class DriveAndShoot extends SequentialCommandGroup {
    public DriveAndShoot(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem, TransferSubsystem transferSubsystem, ShooterSubsystem shooterSubsystem) {
        addCommands(
            new DriveTimeAndSpeed(driveSubsystem, 2, 0.5),
            new IntakeStoreAndTransfer(intakeSubsystem, storageSubsystem, transferSubsystem),
            new Shoot(shooterSubsystem),
            new DriveTimeAndSpeed(driveSubsystem, 2, -0.5)
        );
    }
}
