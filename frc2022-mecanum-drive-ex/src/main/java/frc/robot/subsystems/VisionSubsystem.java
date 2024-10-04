package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

public class VisionSubsystem extends SubsystemBase {
    private PhotonCamera camera;

    public VisionSubsystem() {
        camera = new PhotonCamera("limelight");
    }

    public void periodic() {
        var results = camera.getLatestResult();
        if (results.hasTargets()) {
            List<PhotonTrackedTarget> targets = results.getTargets();
            PhotonTrackedTarget bestTarget = results.getBestTarget();
            
        }
    }
}