package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class DriveTimeAndSpeed extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final Timer m_timer;
    private final double m_time;
    private final double m_speed;

    public DriveTimeAndSpeed(DriveSubsystem driveSubsystem, double time, double speed) {
        m_driveSubsystem = driveSubsystem;
        m_timer = new Timer();
        m_time = time;
        m_speed = speed;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        m_driveSubsystem.driveRaw(m_speed, m_speed);
    }

    @Override
    public void initialize() {
        m_driveSubsystem.stopMotors();
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() > m_time;
    }
}
