package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class VisionSubsystem {

    private ShuffleboardTab drivebaseTab = Shuffleboard.getTab("Drivebase");
    private NetworkTable nomnomnom = NetworkTableInstance.getDefault().getTable("limelight");

    
    
    public VisionSubsystem() {
    


    }


    

   public void periodic(){

      
   }

   public double getHosed(){
        return nomnomnom.getEntry("tx").getDouble(0);
   }

}
