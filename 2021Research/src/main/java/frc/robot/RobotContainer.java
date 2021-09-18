// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveAndShoot;
import frc.robot.commands.DriveForwardAndTurn;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeStoreAndTransfer;
import frc.robot.commands.Shoot;
import frc.robot.commands.Start2Node2Datalink;
import frc.robot.commands.Start3Node3Datalink;
import frc.robot.commands.TrackItem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.TransferSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ElevatorSubsystem;

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
  // private final ArmSubsystem m_robotArm = new ArmSubsystem();
  // private final CameraSubsystem m_robotCam = new CameraSubsystem();
  // private final ElevatorSubsystem m_robotElevator = new ElevatorSubsystem();
  private final IntakeSubsystem m_robotIntake = new IntakeSubsystem();
  private final StorageSubsystem m_robotStorage = new StorageSubsystem();
  private final TransferSubsystem m_robotTransfer = new TransferSubsystem();
  private final ShooterSubsystem m_robotShoot = new ShooterSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveAndShoot driveAndShoot = new DriveAndShoot(m_robotDrive, m_robotIntake, m_robotStorage, m_robotTransfer, m_robotShoot);
  private final Shoot m_shoot = new Shoot(m_robotShoot, m_robotStorage, m_robotTransfer);
  private final IntakeStoreAndTransfer m_intake = new IntakeStoreAndTransfer(m_robotIntake, m_robotStorage);
  private final DriveForwardAndTurn driveForwardAndTurn = new DriveForwardAndTurn(m_robotDrive);
  private final Start3Node3Datalink start3Node3Datalink = new Start3Node3Datalink(m_robotDrive);
  private final Start2Node2Datalink start2Node2Datalink = new Start2Node2Datalink(m_robotDrive);
  // private final TrackItem trackItem = new TrackItem(m_robotDrive, m_robotCam);

  SendableChooser<Command> m_chooser = new SendableChooser<>();


  private static Joystick driverStick = new Joystick(0);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    m_robotDrive.setDefaultCommand(

      new RunCommand(

        () -> m_robotDrive.drive(driverStick.getRawAxis(1), driverStick.getRawAxis((RobotBase.isReal()) ? 4 : 0))  
        , m_robotDrive)
        
      //   () -> m_robotDrive.drive(-m_stick.getX(), m_stick.getY())
      // , m_robotDrive)

    
    );

    m_chooser.addOption("Drive and Shoot", driveAndShoot);
    m_chooser.addOption("Drive Forward and Turn", driveForwardAndTurn);
    m_chooser.addOption("Start3, Node3, Datalink", start3Node3Datalink);
    m_chooser.setDefaultOption("Start2, Node2, Datalink", start2Node2Datalink);
    // m_chooser.addOption("Track Item", trackItem);

    Shuffleboard.getTab("Autonomous").add(m_chooser);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(driverStick, Constants.RB)
      .whileHeld(m_shoot);

    new JoystickButton(driverStick, Constants.LB)
      .whileHeld(m_intake);

    // new JoystickButton(m_stick, 1)
    //   .whileHeld(() -> m_robotArm.setArmAnglePIDF(30))
    //   .whenReleased(()-> m_robotArm.setMotor(0));

    // new JoystickButton(m_stick, 2)
    //   .whenPressed(() -> m_robotDrive.resetOdometry(new Pose2d(5, 5, new Rotation2d())));


    // new JoystickButton(m_stick, 3)
    //   .whenPressed(() -> m_robotArm.setMotor(1))
    //   .whenReleased(() -> m_robotArm.setMotor(0));

    // new JoystickButton(m_stick, 4)
    //   .whenPressed(() -> m_robotArm.setMotor(-1))
    //   .whenReleased(() -> m_robotArm.setMotor(0));

    // new JoystickButton(m_stick, 5)
    //   .whenPressed(() -> m_robotElevator.raise(1.0))
    //   .whenReleased(() -> m_robotElevator.lower());
  }

  public DriveSubsystem getRobotDrive(){
    return m_robotDrive;
  }

  public Joystick getDriverJoystick(){
    return driverStick;
  }

  // public CameraSubsystem getRobotCam(){
  //   return m_robotCam;
  // }

  // public ElevatorSubsystem getRobotElevator() {
  //   return m_robotElevator;
  // }
  
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
