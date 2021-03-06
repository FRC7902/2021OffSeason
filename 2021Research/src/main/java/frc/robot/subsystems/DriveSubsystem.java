// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Position;

public class DriveSubsystem extends SubsystemBase {



  //SpeedController Groups
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(new WPI_VictorSPX(Constants.DriveConstants.kLeftMotor1Port), new WPI_VictorSPX(Constants.DriveConstants.kLeftMotor2Port));
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(new WPI_VictorSPX(Constants.DriveConstants.kRightMotor1Port), new WPI_VictorSPX(Constants.DriveConstants.kRightMotor2Port));


  //Encoders
  private final Encoder m_leftEncoder = new Encoder(Constants.DriveConstants.kLeftEncoderPorts[0], Constants.DriveConstants.kLeftEncoderPorts[1], Constants.DriveConstants.kLeftEncoderPorts[2]);
  private final Encoder m_rightEncoder = new Encoder(Constants.DriveConstants.kRightEncoderPorts[0], Constants.DriveConstants.kRightEncoderPorts[1], Constants.DriveConstants.kRightEncoderPorts[2]);

  //Gyro
  private final AnalogGyro m_gyro = new AnalogGyro(1);

  //Drive
  private DifferentialDrive m_drive;


  //Simulation Stuff
  private final DifferentialDriveOdometry m_odometry;
  private EncoderSim m_leftEncoderSim;
  private EncoderSim m_rightEncoderSim;
  private Field2d m_fieldSim;
  private AnalogGyroSim m_gyroSim;
  public DifferentialDrivetrainSim m_driveTrainSim;


  private Position currentPos = Position.RED3;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    //Set Encoder pulses
    m_leftEncoder.setDistancePerPulse(Constants.DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(Constants.DriveConstants.kEncoderDistancePerPulse);
    resetEncoders();
    
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()), new Pose2d(15.1, 3.4, new Rotation2d(Math.PI))); 
    // Position 2: 15.1, 3.4
    // Position 3: 15.1, 2.2
    

    if(!RobotBase.isReal()){
      //Set up robot simulation
      m_driveTrainSim = DifferentialDrivetrainSim.createKitbotSim(
      KitbotMotor.kDualCIMPerSide, 
      KitbotGearing.k10p71, 
      KitbotWheelSize.SixInch, null);
      m_fieldSim = new Field2d();
      SmartDashboard.putData("Field", m_fieldSim);

      //Connect the simulators with their counterparts
      m_leftEncoderSim = new EncoderSim(m_leftEncoder);
      m_rightEncoderSim = new EncoderSim(m_rightEncoder);
      m_gyroSim = new AnalogGyroSim(m_gyro);
    }


    
    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    m_leftMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //update the position of the robot


    SmartDashboard.putNumber("Enc Distance", getAvgEncoderDistance());
    SmartDashboard.putNumber("Gyro Heading", getHeading());


  }




  @Override
  public void simulationPeriodic() {

    //connect the motors to update the drivetrain
    m_driveTrainSim.setInputs(
      m_leftMotors.get() * RobotController.getBatteryVoltage(), 
      m_rightMotors.get() * RobotController.getBatteryVoltage()
    );

    //Run and update simulation
    m_driveTrainSim.update(0.02);
    m_leftEncoderSim.setDistance(m_driveTrainSim.getLeftPositionMeters());
    m_leftEncoderSim.setRate(m_driveTrainSim.getLeftVelocityMetersPerSecond());
    m_rightEncoderSim.setDistance(m_driveTrainSim.getRightPositionMeters());
    m_rightEncoderSim.setRate(m_driveTrainSim.getRightVelocityMetersPerSecond());
    m_gyroSim.setAngle(-m_driveTrainSim.getHeading().getDegrees());

    m_odometry.update( 
      Rotation2d.fromDegrees(getHeading()),
      m_leftEncoder.getDistance(),
      m_rightEncoder.getDistance()
    );
    m_fieldSim.setRobotPose(getPose());


  }



  //Get the current draw from the drivetrain
  public double getDrawnCurrentAmps(){
    return m_driveTrainSim.getCurrentDrawAmps();
  }

  //Get the position of the robot
  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }


  //get the wheel speeds
  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  //reset the robots position
  public void resetOdometry(Pose2d pose, double rad){
    resetEncoders();
    m_driveTrainSim.setPose(pose);
    m_odometry.resetPosition(pose, new Rotation2d(rad));


  }

  public void refreshOdometry(){
    Pose2d pose = getPose();
    resetEncoders();
    m_driveTrainSim.setPose(pose);
    m_odometry.resetPosition(pose, new Rotation2d(-pose.getRotation().getRadians())); 
  }


  //drive the robot using arcade drive
  public void arcadeDrive(double fwd, double rot){
    m_drive.arcadeDrive(fwd, rot);
  }


  //stop the motors
  public void stopMotors(){
    // m_leftMotors.stopMotor();
    // m_rightMotors.stopMotor();
    m_leftMotors.set(0);
    m_rightMotors.set(0);

  }


  //drive the robot
  public void drive(double y, double x){
    // if(y == 0 && x == 0){
    //   return;
    // }
    // m_leftMotors.set(-y + x * Constants.DriveConstants.kTurnSpeed);
    // m_rightMotors.set(-y-x * Constants.DriveConstants.kTurnSpeed);
    // m_drive.arcadeDrive(x, -y);
    if(RobotBase.isSimulation()){
      y=-y;
      x=-x;
    }

    x*=0.75;
    
    m_drive.arcadeDrive(x, y);
    

    // driveRaw(x, y);

  }

  //set the motors to a specific speed
  public void driveRaw(double left, double right){
    m_drive.arcadeDrive(0, left, false);
    // m_leftMotors.set(left);
    // m_rightMotors.set(right);

  }

  //reset the encoders
  public void resetEncoders(){
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }



  //get the robot distance
  public double getAvgEncoderDistance(){
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }


  //return left encoder
  public Encoder getLeftEncoder(){
    return m_leftEncoder;
  }

  //return right encoder
  public Encoder getRightEncoder(){
    return m_rightEncoder;
  }

  public void checkIfPosition(Position pos){
    if(currentPos != pos){
      switch (pos){
        case RED1:
          setPosition(0.9, 6.5, 0);
          break;
        case RED2:
          setPosition(0.9, 3.4, 0);
          break;
        case RED3:
          setPosition(0.9, 2.2, 0);
          break;
        case BLUE1:
          setPosition(15.1, 6.5, Math.PI);
          break;
        case BLUE2:
          setPosition(15.1, 3.4, Math.PI);
          break;
        case BLUE3:
          setPosition(15.1, 2.2, Math.PI);
          break;

      }
      currentPos = pos;
    }
  }

  public void setPosition(double x, double y, double rad){
    
    
    resetOdometry(new Pose2d(x, y, new Rotation2d()), rad);
  }

  

  //reset the gyro
  public void zeroHeading(){
    m_gyro.reset();
  }

  //get the heading angle from the gyro
  public double getHeading(){
    return Math.IEEEremainder(m_gyro.getAngle(), 360);
  }
}
