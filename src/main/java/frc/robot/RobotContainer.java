package frc.robot;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton LeverBras = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton reset = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton BaisserBras = new JoystickButton(driver, XboxController.Button.kA.value);
    // private final JoystickButton RamasserBallon = new JoystickButton(driver, XboxController.Button.kX.value);
    private final JoystickButton OuttakeBallon = new JoystickButton(driver, XboxController.Button.kB.value);
    private final JoystickButton OuttakeTube = new JoystickButton(driver, XboxController.Button.kRightBumper.value);


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Bras systemeBras = new Bras();
    private final RamasseurTube systemeTube = new RamasseurTube();

    private final RamasseurBallon systemeBallon = new RamasseurBallon();
    private final MonterBras commande_monterBras = new MonterBras(systemeBras);
    private final BaisserBras commande_BaisserBras = new BaisserBras(systemeBras);
    private final RamasserBallon commande_ramasserBallon = new RamasserBallon(systemeBallon);
    private final EjectBallon commande_outtake = new EjectBallon(systemeBallon);
    private final EjectTube commande_outtakeTube = new EjectTube(systemeTube);
    private SendableChooser<Command> autoChooser;



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> false
            )
        );

        SmartDashboard.putData(CommandScheduler.getInstance());
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData(autoChooser);
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        // LeverBras.onTrue(commande_monterBras);
        // LeverBras.onTrue(Commands.runOnce(() -> systemeBras.setPosition(0.10), systemeBras));
        // BaisserBras.onTrue(Commands.parallel(commande_BaisserBras, commande_ramasserBallon));
        BaisserBras.toggleOnTrue(Commands.parallel(Commands.runOnce(() -> systemeBras.setPosition(0.22), systemeBras), commande_ramasserBallon).andThen(Commands.runOnce(() -> systemeBras.setPosition(0.10), systemeBras)));
        // RamasserBallon.onTrue(commande_ramasserBallon);
        OuttakeBallon.toggleOnTrue(commande_outtake);
        OuttakeTube.toggleOnTrue(commande_outtakeTube);
        reset.onTrue(Commands.runOnce(() -> s_Swerve.setHeading(Rotation2d.fromDegrees(180))));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoChooser.getSelected();    }
}
