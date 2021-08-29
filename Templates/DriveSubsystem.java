// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    // Encoders
  private final Encoder m_leftEncoder = new Encoder(0, 1, 2);
  private final Encoder m_rightEncoder = new Encoder(3, 4, 5);

  // Gyro
  private final AnalogGyro m_gyro = new AnalogGyro(1);

  // Simulation Stuff
  private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
      Rotation2d.fromDegrees(getHeading()), new Pose2d(4, 5, new Rotation2d()));;
  private EncoderSim m_leftEncoderSim;
  private EncoderSim m_rightEncoderSim;
  private Field2d m_fieldSim;
  private AnalogGyroSim m_gyroSim;
  public DifferentialDrivetrainSim m_driveTrainSim;


  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Set Encoder pulses
    m_leftEncoder.setDistancePerPulse((0.1524 * Math.PI) / (double) 1024);
    m_rightEncoder.setDistancePerPulse((0.1524 * Math.PI) / (double) 1024);
    m_leftEncoder.reset();
    m_rightEncoder.reset();

    if (!RobotBase.isReal()) {
      // Set up robot simulation
      m_driveTrainSim = DifferentialDrivetrainSim.createKitbotSim(KitbotMotor.kDualCIMPerSide, KitbotGearing.k10p71,
          KitbotWheelSize.SixInch, null);
      m_fieldSim = new Field2d();
      SmartDashboard.putData("Field", m_fieldSim);

      // Connect the simulators with their counterparts
      m_leftEncoderSim = new EncoderSim(m_leftEncoder);
      m_rightEncoderSim = new EncoderSim(m_rightEncoder);
      m_gyroSim = new AnalogGyroSim(m_gyro);
    }
  }

  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360);
  }

  // Get the position of the robot
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic(){
    // connect the motors to update the drivetrain
    m_driveTrainSim.setInputs(m_leftMotors.get() * RobotController.getBatteryVoltage(),
        m_rightMotors.get() * RobotController.getBatteryVoltage());

    // Run and update simulation
    m_driveTrainSim.update(0.02);
    m_leftEncoderSim.setDistance(m_driveTrainSim.getLeftPositionMeters());
    m_leftEncoderSim.setRate(m_driveTrainSim.getLeftVelocityMetersPerSecond());
    m_rightEncoderSim.setDistance(m_driveTrainSim.getRightPositionMeters());
    m_rightEncoderSim.setRate(m_driveTrainSim.getRightVelocityMetersPerSecond());
    m_gyroSim.setAngle(-m_driveTrainSim.getHeading().getDegrees());

    m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
    m_fieldSim.setRobotPose(getPose());
  }
}
