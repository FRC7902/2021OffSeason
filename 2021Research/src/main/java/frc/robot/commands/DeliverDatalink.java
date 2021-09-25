package frc.robot.commands;

import frc.robot.subsystems.DatalinkSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DeliverDatalink extends CommandBase {
    private final DatalinkSubsystem m_datalinkSubsystem;

    public DeliverDatalink(DatalinkSubsystem datalinkSubsystem) {
        m_datalinkSubsystem = datalinkSubsystem;

        addRequirements(datalinkSubsystem);
    }

    @Override
    public void execute() {
        m_datalinkSubsystem.down();
    }

    @Override
    public void initialize() {
        m_datalinkSubsystem.stop();
    }

    @Override
    public void end(boolean interrupted) {
        m_datalinkSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
