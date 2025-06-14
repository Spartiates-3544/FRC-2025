package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.RobotConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSTalonFXSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class Swerve {
        public static final int pigeonID = 1;

        public static final COTSTalonFXSwerveConstants chosenModule =  
        COTSTalonFXSwerveConstants.SDS.MK4i.Falcon500(COTSTalonFXSwerveConstants.SDS.MK4i.driveRatios.L3);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(29.0); 
        public static final double wheelBase = Units.inchesToMeters(18.5); 
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        // public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
        //     new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
        //     new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0),
        //     new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
        //     new Translation2d(wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final InvertedValue angleMotorInvert = chosenModule.angleMotorInvert;
        public static final InvertedValue driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final SensorDirectionValue cancoderInvert = chosenModule.cancoderInvert;

        /* Swerve Current Limiting */
        public static final int angleCurrentLimit = 25;
        public static final int angleCurrentThreshold = 40;
        public static final double angleCurrentThresholdTime = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveCurrentLimit = 35;
        public static final int driveCurrentThreshold = 60;
        public static final double driveCurrentThresholdTime = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.1;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.07; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values From SYSID */
        public static final double driveKS = 0.10; //TODO: This must be tuned to specific robot
        public static final double driveKV = 1.51;
        public static final double driveKA = 0.27;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 1; 
        /** Radians per Second */
        public static final double maxAngularVelocity = 1.8;



        /* Neutral Modes */
        public static final NeutralModeValue angleNeutralMode = NeutralModeValue.Coast;
        public static final NeutralModeValue driveNeutralMode = NeutralModeValue.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 1 */
        public static final class Mod0 { 
            public static final int driveMotorID = 12;
            public static final int angleMotorID = 11;
            public static final int canCoderID = 1;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(160-180);//-17.5
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 2 */
        public static final class Mod1 { 
            public static final int driveMotorID = 22;
            public static final int angleMotorID = 21;
            public static final int canCoderID = 2;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(249);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 4 */
        public static final class Mod2 { 
            public static final int driveMotorID = 42;
            public static final int angleMotorID = 41;
            public static final int canCoderID = 4;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(140); // 142.128
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 5 */
        public static final class Mod3 { 
            public static final int driveMotorID = 52;
            public static final int angleMotorID = 51;
            public static final int canCoderID = 5;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(123)
            ; //124.38
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

//    public static final HolonomicPathFollowerConfig holonomicPathFollower = new HolonomicPathFollowerConfig(new PIDConstants(kPXController, 0, 0),
//          new PIDConstants(kPThetaController, 0, 0),
//          kMaxSpeedMetersPerSecond,
//          Units.inchesToMeters(15),
//          new ReplanningConfig());
//     }

    public static final class ArmConstants {
        public static final double kP = 2.;
        // public static final double PIDTolerance = 0.05;
    }

    public static final class AutoConstants {
        // public static final double kMaxSpeedMetersPerSecond = 1;
        // public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;
        // public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        // public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        // public static final double kPXController = 1;
        // public static final double kPYController = 1;
        // public static final double kPThetaController = 1;

        //FL, FR, BL, BR
        // public static final Translation2d[] moduleOffsets = {           
        //     new Translation2d(Constants.Swerve.wheelBase / 2.0, Constants.Swerve.trackWidth / 2.0),
        //     new Translation2d(Constants.Swerve.wheelBase / 2.0, -Constants.Swerve.trackWidth / 2.0),
        //     new Translation2d(-Constants.Swerve.wheelBase / 2.0, Constants.Swerve.trackWidth / 2.0),
        //     new Translation2d(-Constants.Swerve.wheelBase / 2.0, -Constants.Swerve.trackWidth / 2.0)};

        public static final Translation2d[] moduleOffsets = {           
            new Translation2d(Constants.Swerve.wheelBase / 2.0, Constants.Swerve.trackWidth / 2.0),
            new Translation2d(Constants.Swerve.wheelBase / 2.0, -Constants.Swerve.trackWidth / 2.0),
            new Translation2d(-Constants.Swerve.wheelBase / 2.0, Constants.Swerve.trackWidth / 2.0),
            new Translation2d(-Constants.Swerve.wheelBase / 2.0, -Constants.Swerve.trackWidth / 2.0)};

        public static void dimensions() {
            for (Translation2d translation2d : moduleOffsets) {
                System.out.println(translation2d.getX());
                System.out.println( translation2d.getY());
            }
            
        }
        public static final DCMotor motorConfig = DCMotor.getFalcon500(1);
        public static final ModuleConfig moduleConfig = new ModuleConfig(0.0508, 5, 1, motorConfig, 6.12, 40, 1);
        public static final RobotConfig config = new RobotConfig(45, 2.81854, moduleConfig, moduleOffsets);
        
        public static final double translationkP = 2;
        public static final double rotationkP = 4.1;

        /* Constraint for the motion profilied robot angle controller */
        // public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        //     new TrapezoidProfile.Constraints(
        //         kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }
}
