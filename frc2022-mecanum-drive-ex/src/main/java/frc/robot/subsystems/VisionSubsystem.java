// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.Constants.Vision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;

public class VisionSubsystem {
  private class DuplicateTracker {
    private double lastTimeStamp;

    public boolean isDuplicate(PhotonPipelineResult frame) {
      boolean isDuplicate = frame.getTimestampSeconds() == lastTimeStamp;
      lastTimeStamp = frame.getTimestampSeconds();
      return isDuplicate;
    }
  }
 private double lastDetection = 0;

  private double tagX;
  private double tagY;
  private double tagZ;
  private double tagRoll;
  private double tagPitch;
  private double tagYaw;
  private final List<CameraEstimator> cameraEstimators = new ArrayList<>();
  record CameraEstimator(
      PhotonCamera camera, PhotonPoseEstimator estimator, DuplicateTracker duplicateTracker) {}
// change name for sure
      PhotonCamera camera = new PhotonCamera("photonvision");
  record MeasurementRow(
      double realX,
      double realY,
      int tags,
      double avgDistance,
      double ambiguity,
      double estX,
      double estY,
      double estTheta) {}

  public static record UnitDeviationParams(
      double distanceMultiplier, double eulerMultiplier, double minimum) {
    private double computeUnitDeviation(double averageDistance) {
      return Math.max(minimum, eulerMultiplier * Math.exp(averageDistance * distanceMultiplier));
    }
  }
 
  private void findVisionMeasurements() {
    for (CameraEstimator cameraEstimator : cameraEstimators) {
      PhotonPipelineResult frame = cameraEstimator.camera().getLatestResult();

      // determine if result should be ignored

      // FIXME good god why
      var optEstimation = cameraEstimator.estimator().update(frame);
      if (optEstimation.isEmpty()) continue;
      var estimation = optEstimation.get();

      double sumDistance = 0;
      for (var target : estimation.targetsUsed) {
        var t3d = target.getBestCameraToTarget();
        sumDistance +=
            Math.sqrt(Math.pow(t3d.getX(), 2) + Math.pow(t3d.getY(), 2) + Math.pow(t3d.getZ(), 2));
        tagX = t3d.getX();
        tagY = t3d.getY();
        tagZ = t3d.getZ();
        tagRoll = t3d.getRotation().getX();
        tagPitch = t3d.getRotation().getY();
        tagYaw = t3d.getRotation().getZ();
      }
      double avgDistance = sumDistance / estimation.targetsUsed.size();



        // Read in relevant data from the Camera
        boolean targetVisible = false;
        double targetYaw = 0.0;
        var results = camera.();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
                
            }
        }

      
    }
  }
}
