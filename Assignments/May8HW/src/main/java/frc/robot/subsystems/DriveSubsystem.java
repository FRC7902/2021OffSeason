// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  // Encoders
  private final Encoder m_leftEncoder = new Encoder(Constants.DriveConstants.kLeftEncoderPorts[0],
      Constants.DriveConstants.kLeftEncoderPorts[1]);
  private final Encoder m_rightEncoder = new Encoder(Constants.DriveConstants.kRightEncoderPorts[0],
      Constants.DriveConstants.kRightEncoderPorts[1]);

  // Gyro
  private final AnalogGyro m_gyro = new AnalogGyro(1);

  // Drive
  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // Simulation Stuff
  private final DifferentialDriveOdometry m_odometry;
  private EncoderSim m_leftEncoderSim;
  private EncoderSim m_rightEncoderSim;
  private Field2d m_fieldSim;
  private AnalogGyroSim m_gyroSim;
  public DifferentialDrivetrainSim m_driveTrainSim;

  public DriveSubsystem() {
    // Set Encoder pulses
    m_leftEncoder.setDistancePerPulse(Constants.DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(Constants.DriveConstants.kEncoderDistancePerPulse);
    resetEncoders();

    // Set up robot simulation
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()),
        new Pose2d(4, 5, new Rotation2d()));
    m_driveTrainSim = DifferentialDrivetrainSim.createKitbotSim(KitbotMotor.kDualCIMPerSide, KitbotGearing.k10p71,
        KitbotWheelSize.SixInch, null);
    m_fieldSim = new Field2d();
    SmartDashboard.putData("Field", m_fieldSim);

    // Connect the simulators with their counterparts
    m_leftEncoderSim = new EncoderSim(m_leftEncoder);
    m_rightEncoderSim = new EncoderSim(m_rightEncoder);
    m_gyroSim = new AnalogGyroSim(m_gyro);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
    m_fieldSim.setRobotPose(getPose());

    SmartDashboard.putNumber("Enc Distance", getAvgEncoderDistance());
    SmartDashboard.putNumber("Gyro Heading", getHeading());
  }

  @Override
  public void simulationPeriodic() {
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
  }

  // Get the current draw from the drivetrain
  public double getDrawnCurrentAmps() {
    return m_driveTrainSim.getCurrentDrawAmps();
  }

  // Get the position of the robot
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  // get the wheel speeds
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  // reset the robots position
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_driveTrainSim.setPose(pose);
    m_odometry.resetPosition(pose, new Rotation2d());

  }


  // reset the encoders
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  // get the robot distance
  public double getAvgEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  // return left encoder
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  // return right encoder
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  // reset the gyro
  public void zeroHeading() {
    m_gyro.reset();
  }

  // get the heading angle from the gyro
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360);
  }
}
