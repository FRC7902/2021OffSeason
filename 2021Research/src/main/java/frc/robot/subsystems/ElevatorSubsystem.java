package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final DCMotor m_elevatorGearbox;

    // Standard classes for controlling our elevator
    private final PIDController m_controller;
    private final Encoder m_encoder;
    private final PWMSparkMax m_motor;
    private final Joystick m_joystick;

    // Simulation classes help us simulate what's going on, including gravity.
    private final ElevatorSim m_elevatorSim;
    private final EncoderSim m_encoderSim ;

    public ElevatorSubsystem() {
        m_elevatorGearbox = DCMotor.getVex775Pro(4);
        m_controller = new PIDController(ElevatorConstants.kElevatorKp, 0, 0);
        m_encoder = new Encoder(ElevatorConstants.kEncoderAChannel, ElevatorConstants.kEncoderBChannel);
        m_motor = new PWMSparkMax(ElevatorConstants.kMotorPort);
        m_joystick = new Joystick(ElevatorConstants.kJoystickPort);
        m_elevatorSim = new ElevatorSim(
            m_elevatorGearbox,
            ElevatorConstants.kElevatorGearing,
            ElevatorConstants.kCarriageMass,
            ElevatorConstants. kElevatorDrumRadius,
            ElevatorConstants.kMinElevatorHeight,
            ElevatorConstants.kMaxElevatorHeight,
            VecBuilder.fill(0.01));
        m_encoderSim = new EncoderSim(m_encoder);

        SmartDashboard.putNumber("Elevator Height", 0);
    }

    public void setup() {
        m_encoder.setDistancePerPulse(ElevatorConstants.kElevatorEncoderDistPerPulse);
    }

    @Override
    public void simulationPeriodic() {
        // In this method, we update our simulation of what our elevator is doing
        // First, we set our "inputs" (voltages)
        m_elevatorSim.setInput(m_motor.get() * RobotController.getBatteryVoltage());

        // Next, we update it. The standard loop time is 20ms.
        m_elevatorSim.update(0.020);

        // Finally, we set our simulated encoder's readings and simulated battery voltage
        m_encoderSim.setDistance(m_elevatorSim.getPositionMeters());
        // SimBattery estimates loaded battery voltages
        RoboRioSim.setVInVoltage(
            BatterySim.calculateDefaultBatteryLoadedVoltage(m_elevatorSim.getCurrentDrawAmps()));

        SmartDashboard.putNumber("Elevator Height", m_elevatorSim.getPositionMeters());
    }

    public void raise(double metres) {
        double pidOutput = m_controller.calculate(m_encoder.getDistance(), metres);
        m_motor.setVoltage(pidOutput);
    }

    public void lower() {
        m_motor.set(0.0);
    }

    public void disable() {
        // This just makes sure that our simulation code knows that the motor's off.
        m_motor.set(0.0);
    }
}
