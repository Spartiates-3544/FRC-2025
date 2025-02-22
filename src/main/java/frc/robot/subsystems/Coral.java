package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Coral implements Subsystem {
     PWMSparkMax intakeCoral;

    public Coral() {
        intakeCoral = new PWMSparkMax(0);  
    }

    
    public void intake(double speed) {
        intakeCoral.set(-0.2);  
    }

    public void place(double speed) { 
        intakeCoral.set(0.2);

    }

    
    public void stop() {
        intakeCoral.stopMotor();  
    }
}
