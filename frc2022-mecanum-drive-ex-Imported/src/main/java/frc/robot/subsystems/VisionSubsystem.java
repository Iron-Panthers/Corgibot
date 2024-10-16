package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.util.protobuf.ProtobufSerializable;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;


public class VisionSubsystem extends SubsystemBase {
    private PhotonCamera camera;
    boolean targetVisible = false;
    double targetYaw = 0.0;
    double distanceToTarget=0.0;
    public VisionSubsystem() {
        camera = new PhotonCamera("limelight");
    }
    public double getAngle(){
       PhotonPipelineResult result = camera.getLatestResult();
       try {
        return result.getBestTarget().getYaw();
       } catch (Exception e) {
        return 0;
       }
     
    }
    
}