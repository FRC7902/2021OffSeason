/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StorageSubsystem extends SubsystemBase {
  // Controllers
  private PWMVictorSPX left = new PWMVictorSPX(Constants.StorageConstants.kLeft);
  private PWMVictorSPX right = new PWMVictorSPX(Constants.StorageConstants.kRight);

  private SpeedControllerGroup store;

  /**
   * Creates a new StorageSubsystem.
   */
  public StorageSubsystem() {
    // Set Inverse
    left.setInverted(true);
    // Bind
    store = new SpeedControllerGroup(left, right);
    // WTF such a useful function I was not told about?
    //transfer.set(ControlMode.VELOCITY, velocity);
  }

  /**
   * Store Motors Start
   */
  public void store() {
    store.set(Constants.StorageConstants.kSpeed);
  }

  /**
   * Stop
   */
  public void stop() {
    store.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Storage Motor", store.get());
  }
}
