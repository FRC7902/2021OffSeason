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
        public static final int kLeftMotor1Port = 0;
        public static final int kLeftMotor2Port = 1;
        public static final int kRightMotor1Port = 2;
        public static final int kRightMotor2Port = 3;


        public static final int[] kLeftEncoderPorts = {0, 1};
        public static final int[] kRightEncoderPorts = {2, 3};

        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterMeters = 0.1524;
        public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;


        public static final double kTurnSpeed = 0.8;
        public static final double kSlowSpeed = 0.5;
    }


    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
    }

    public static final class ElevatorConstants {
        public static final int kMotorPort = 5;
        public static final int kEncoderAChannel = 6;
        public static final int kEncoderBChannel = 7;
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
}
