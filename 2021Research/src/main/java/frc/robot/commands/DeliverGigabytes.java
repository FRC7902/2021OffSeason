package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
public class DeliverGigabytes extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    public DeliverGigabytes(IntakeSubsystem intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.release();
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.stop();
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
