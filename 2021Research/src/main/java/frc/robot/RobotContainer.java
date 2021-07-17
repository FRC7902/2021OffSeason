// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForwardAndTurn;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TrackItem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ArmSubsystem m_robotArm = new ArmSubsystem();
  private final CameraSubsystem m_robotCam = new CameraSubsystem();
  private final CameraSubsystem m_robotCamLimeGreen = new CameraSubsystem(28.0, 82.0, 44.0, 255.0, 71.0, 255.0);
  private final CameraSubsystem m_robotCamYellow = new CameraSubsystem(14.0287744055549, 43.822529503103, 58.09352773127796, 202.78156996587032, 44.33453383205606, 149.11263351961207);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveForwardAndTurn driveForwardAndTurn = new DriveForwardAndTurn(m_robotDrive);
  private final TrackItem trackItemLimeGreen = new TrackItem(m_robotDrive, m_robotCamLimeGreen);
  private final TrackItem trackItemYellow = new TrackItem(m_robotDrive, m_robotCamYellow);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  XboxController m_driverController = 
    new XboxController(0);

  private final Joystick m_stick = new Joystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    m_robotDrive.setDefaultCommand(

      new RunCommand(
        
        () -> m_robotDrive.drive(-m_stick.getX(), m_stick.getY())
      , m_robotDrive)

    
    );

    m_chooser.setDefaultOption("Drive Forward and Turn", driveForwardAndTurn);
    m_chooser.addOption("Track Item Lime Green", trackItemLimeGreen);
    m_chooser.addOption("Track Item Yellow", trackItemYellow);

    Shuffleboard.getTab("Autonomous").add(m_chooser);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_stick, 1)
      .whileHeld(() -> m_robotArm.setArmAnglePIDF(30))
      .whenReleased(()-> m_robotArm.setMotor(0));

    new JoystickButton(m_stick, 2)
      .whenPressed(() -> m_robotDrive.resetOdometry(new Pose2d(5, 5, new Rotation2d())));


    new JoystickButton(m_stick, 3)
      .whenPressed(() -> m_robotArm.setMotor(1))
      .whenReleased(() -> m_robotArm.setMotor(0));

    new JoystickButton(m_stick, 4)
      .whenPressed(() -> m_robotArm.setMotor(-1))
      .whenReleased(() -> m_robotArm.setMotor(0));
  }

  public DriveSubsystem getRobotDrive(){
    return m_robotDrive;
  }

  public CameraSubsystem getRobotCam(){
    return m_robotCam;
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return m_chooser.getSelected();
  }
}
