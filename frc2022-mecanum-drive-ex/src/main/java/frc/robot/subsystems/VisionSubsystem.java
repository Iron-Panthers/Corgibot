package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class VisionSubsystem extends SubsystemBase {
    private PhotonCamera camera;
    boolean targetVisible = false;
    double targetYaw = 0.0;
    double distanceToTarget=0.0;
    public VisionSubsystem() {
        camera = new PhotonCamera("limelight");
    }
    public double getAngle(){
       var results = camera.getLatestResult();
      if (results.hasTargets()) {
              return results.getBestTarget().getYaw();
        }
      return 0;
    }
    
}