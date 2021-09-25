package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DatalinkSubsystem extends SubsystemBase {
    private WPI_VictorSPX datalinkMotor = new WPI_VictorSPX(Constants.DatalinkConstants.kDatalink);

    private String status;
    private boolean isDeployed;

    public DatalinkSubsystem() {
        isDeployed = false;
        status = "Off";
    }

    public void down() {
        if (!isDeployed) deploy();
        datalinkMotor.set(Constants.DatalinkConstants.kSpeed);
        status = "Going down";
    }

    public void up() {
        if (!isDeployed) deploy();
        datalinkMotor.set(-Constants.DatalinkConstants.kSpeed);
        status = "Going up";
    }

    public void stop() {
        datalinkMotor.stopMotor();;
        status = "Stop";
    }

    public void deploy() {
        if (isDeployed) return;
        isDeployed = true;
    }

    public void retract() {
        if (!isDeployed) return;
        isDeployed = false;
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("Datalink Status", (datalinkMotor.isAlive()) ? status : "Broken");
        SmartDashboard.putString("Datalink Deployment", isDeployed ? "Deployed" : "Retracted");
        SmartDashboard.putNumber("Datalink Motor", datalinkMotor.get());
    }
}