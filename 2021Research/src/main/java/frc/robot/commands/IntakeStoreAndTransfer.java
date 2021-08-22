package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class IntakeStoreAndTransfer extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;
    private final StorageSubsystem m_storageSubsystem;

    public IntakeStoreAndTransfer(IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        m_storageSubsystem = storageSubsystem;

        addRequirements(intakeSubsystem, storageSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.suck();
        m_storageSubsystem.store();
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.stop();
        m_storageSubsystem.stop();
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
        m_storageSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
