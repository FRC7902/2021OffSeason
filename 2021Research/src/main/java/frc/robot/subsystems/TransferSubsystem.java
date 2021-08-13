package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TransferSubsystem extends SubsystemBase {
    private PWMVictorSPX transfer = new  PWMVictorSPX(Constants.TransferConstants.kTransfer);

      /**
   * Transfer to Shooter 
   */
  public void transfer() {
    transfer.set(Constants.TransferConstants.kTransferSpeed);
  }

  /**
   * Terminate Transfer
   */
  public void stopTransfer() {
    transfer.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Transfer Motor", transfer.get());
  }
}
