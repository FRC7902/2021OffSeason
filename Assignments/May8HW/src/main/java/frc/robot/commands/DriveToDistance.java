package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToDistance extends PIDCommand {
    public DriveToDistance(double targetDist, DriveSubsystem driveSubsystem) {
        super(new PIDController(15, 0, 0.25), driveSubsystem::getAvgEncoderDistance, targetDist, output -> {
            driveSubsystem.rawDrive(output, output);
        }, driveSubsystem);

        getController().setTolerance(0.00006, 0.001);
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
