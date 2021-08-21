// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 3;
        public static final int kLeftMotor2Port = 4;
        public static final int kRightMotor1Port = 8;
        public static final int kRightMotor2Port = 9;


        public static final int[] kLeftEncoderPorts = {0, 1, 2};
        public static final int[] kRightEncoderPorts = {3, 4, 5};

        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterMeters = 0.1524;
        public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;


        public static final double kTurnSpeed = 0.9;
        public static final double kSlowSpeed = 0.5;
    }


    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
    }

    public static final class ElevatorConstants {
        public static final int kMotorPort = 6;
        public static final int kEncoderAChannel = 8;
        public static final int kEncoderBChannel = 9;
        public static final int kJoystickPort = 0;

        public static final double kElevatorKp = 5.0;
        public static final double kElevatorGearing = 10.0;
        public static final double kElevatorDrumRadius = 0.0508;
        public static final double kCarriageMass = 4.0; // kg

        public static final double kMinElevatorHeight = 0.0;
        public static final double kMaxElevatorHeight = 1.27;

        // distance per pulse = (distance per revolution) / (pulses per revolution)
        //  = (Pi * D) / ppr
        public static final double kElevatorEncoderDistPerPulse =
            2.0 * Math.PI * kElevatorDrumRadius / 4096;
    }

    public static final class IntakeConstants {
        // Intake Controller
        public static final int kIntake = 5;
        // Solenoid
        public static final int kFrontSolenoid = 1,
                                kBackSolenoid = 2;
        // Speed
        public static final double kSpeed = 0.5;
    }

    public static final class StorageConstants {
        // Storage Controller
        public static final int kLeft = 2,
                                kRight = 7;
        // Speed
        public static final double kSpeed = 0.5;
    }

    public static final class ShooterConstants {
        // Shooting
        public static final int LS = 11,
                                RS = 12;
        // Speed
        public static final double kSpeed = 1;
    }

    public static final class TransferConstants {
        // The transfer wheel
        public static final int kTransfer = 10;
        // Transfer Speed (Should be equal or less than shooter speed)
        public static final double kTransferSpeed = ShooterConstants.kSpeed - 0.1;
    }

    /*
   * Other stuff
   */

    // Joystick USB Slot
    public static final int JOY = 0,
    OP = 1;

    // Joystick - Button
    public static final int A = 1,
                            B = 2,
                            X = 3,
                            Y = 4,
                            LB = 5, // Bumper
                            RB = 6, // Bumper
                            M = 7, // menu
                            S = 8, // start
                            LA = 9, // Press Left axis
                            RA = 10; // Press right axis

    // Joystick - Axis
    public static final int LX = 0,
                            LY = 1,
                            LT = 2, // Tigger
                            RT = 3, // Tigger
                            RX = 4,
                            RY = 5, 
                            PX = 6, // D-Pad
                            PY = 7; // D-Pad
}
