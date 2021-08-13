package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.TransferSubsystem;

public class Shoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final TransferSubsystem m_transferSubsystem;
    private final StorageSubsystem m_storageSubsystem;

    public Shoot(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, TransferSubsystem transferSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        m_storageSubsystem = storageSubsystem;
        m_transferSubsystem = transferSubsystem;
        
        addRequirements(shooterSubsystem, storageSubsystem, transferSubsystem);
    }

    @Override
    public void execute() {
        m_shooterSubsystem.shoot();
        m_storageSubsystem.store();
        m_transferSubsystem.transfer();
    }

    @Override
    public void initialize() {
        m_shooterSubsystem.stap();
        m_storageSubsystem.stop();
        m_transferSubsystem.stopTransfer();
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stap();
        m_storageSubsystem.stop();
        m_transferSubsystem.stopTransfer();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
