package frc.subsystems;

import frc.robot.Constants.Drive;
import frc.robot.subsystems.DriveSubsystem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class drivebaseTest {
    

    @Test
    public void goStraight(){
 
        assertEquals(45, DriveSubsystem.calcAngle(0,1));
        assertEquals(1, DriveSubsystem.calcMag(0,1));

        
    }

    @Test
    public void goUpAndLeft(){

        assertEquals(90, DriveSubsystem.calcAngle(-0.5,0.5));
        // assertEquals(1, DriveSubsystem.calcMag(-0.5,0.1));

        
    }

    @Test
    public void goLeft(){ 
        double gyroAngle = 135;
        assertEquals(135, DriveSubsystem.calcAngle(-1,0));
        assertEquals(1, DriveSubsystem.calcMag(-1,0));
    
    }
    @Test
    public void goRight(){
        assertEquals(-45, DriveSubsystem.calcAngle(1,0));
        assertEquals(1, DriveSubsystem.calcMag(1,0));
    
    }
    
}
