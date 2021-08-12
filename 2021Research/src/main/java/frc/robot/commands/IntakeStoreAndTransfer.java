package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.TransferSubsystem;

public class IntakeStoreAndTransfer extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;
    private final StorageSubsystem m_storageSubsystem;
    private final TransferSubsystem m_transferSubsystem;

    public IntakeStoreAndTransfer(IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem, TransferSubsystem transferSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        m_storageSubsystem = storageSubsystem;
        m_transferSubsystem = transferSubsystem;

        addRequirements(intakeSubsystem, storageSubsystem, transferSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.suck();
        m_storageSubsystem.store();
        m_transferSubsystem.transfer();
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.stop();
        m_storageSubsystem.stop();
        m_transferSubsystem.stopTransfer();
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
        m_storageSubsystem.stop();
        m_transferSubsystem.stopTransfer();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
