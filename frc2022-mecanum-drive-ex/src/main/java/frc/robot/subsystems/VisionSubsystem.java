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
    private Pose2d currentRobotPose = new Pose2d();
    public VisionSubsystem() {
        camera = new PhotonCamera("limelight");
    }
   
  record CameraEstimator(
      PhotonCamera camera, PhotonPoseEstimator estimator) {}
      public void setRobotPose(Pose2d pose) {
        this.currentRobotPose = pose;
      }

      public void periodic() {
        var results = camera.getLatestResult();
        if (results.hasTargets()) {
            List<PhotonTrackedTarget> targets = results.getTargets();
            PhotonTrackedTarget bestTarget = results.getBestTarget();
            for (var target : results.getTargets()){
                targetYaw = target.getYaw();
                targetVisible = true;
                
              }  
            
            
        }
        
        if (targetVisible){
          

        }
    }
}