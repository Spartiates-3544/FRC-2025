package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;

public class Climber extends SubsystemBase {

    private TalonFX moteurClimber;
    private PIDController pidClimber;
    

    public Climber() {

        moteurClimber = new TalonFX(19);
        pidClimber = new PIDController(1, 0, 0);
        pidClimber.setSetpoint(0);

    }

    public void setPositionClimber(double PositionClimber){
        pidClimber.setSetpoint(PositionClimber);

    }

    public void setVitesseClimb(double vitesseClimb){
        moteurClimber.set(vitesseClimb);

    }

    public void periodic() {
        // setVitesseClimb(pidClimber.calculate(moteurClimber.getPosition().getValueAsDouble()));
    }



}
