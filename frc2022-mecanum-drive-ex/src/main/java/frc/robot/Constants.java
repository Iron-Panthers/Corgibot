package frc.robot;

import java.util.List;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
public final class Constants {
    public final class Drive {
        public static final double m_deadband = 0.1;
         public class MotorPorts {
            public static final int kFrontLeftId = 0x03;
            public static final int kRearLeftId = 0x06;
            public static final int kFrontRightId = 0x01;
            public static final int kRearRightId = 0x07;
        }


    }

    
 
  
  public static final class Vision {
    
    public static record VisionSource(String name, Transform3d robotToCamera) {}

    public static final VisionSource VISION_SOURCE = new VisionSource(
                "backUpCam",
                new Transform3d(
                    new Translation3d(
                        -0.2796, // front/back
                        0.2286, // left/right
                        -0.2159 // up/down
                        ),
                    new Rotation3d(
                        0,
                        Math.toRadians(30), // angle up/down
                        Math.toRadians(180))));

    public static final int THREAD_SLEEP_DURATION_MS = 5;
  }


    
}
