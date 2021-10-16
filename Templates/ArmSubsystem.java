// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

  //Encoder
  private final Encoder m_encoder = new Encoder(6, 7);

  //Gearbox 
  private final DCMotor m_armGearbox = DCMotor.getVex775Pro(2);

  //Simulation stuff
  private final SingleJointedArmSim m_armSim = 
    new SingleJointedArmSim(
      m_armGearbox,
      100, 
      SingleJointedArmSim.estimateMOI(1, 6.0), 
      1, 
      Units.degreesToRadians(-90), 
      Units.degreesToRadians(180), 
      6.0, 
      true,
      VecBuilder.fill(Units.degreesToRadians(0)));
  private final EncoderSim m_encoderSim = new EncoderSim(m_encoder);


  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {

    //Set the encoder pulse
    m_encoder.setDistancePerPulse(2*Math.PI/4096.0);
    m_encoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {


    //Connect the motor to the arm simulator
    m_armSim.setInput(m_motor.get() * RobotController.getBatteryVoltage());  


    //Simulation stuff
    m_armSim.update(0.02);
    m_encoderSim.setDistance(m_armSim.getAngleRads());
    RoboRioSim.setVInVoltage(
      BatterySim.calculateDefaultBatteryLoadedVoltage(m_armSim.getCurrentDrawAmps())
    );

    //Update the arm angle value on SmartDashboard
    SmartDashboard.putNumber("Arm Angle", Units.radiansToDegrees(m_armSim.getAngleRads()));
  }
}
