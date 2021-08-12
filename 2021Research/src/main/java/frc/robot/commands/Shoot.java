package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    public Shoot(ShooterSubsystem shooterSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        m_shooterSubsystem.shoot();
    }

    @Override
    public void initialize() {
        m_shooterSubsystem.stap();
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stap();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
