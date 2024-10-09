package frc.robot;

public class Constants {
    public class Drive {
        public static final double DEADBAND = 0.1;
         public class MotorPorts {
            public static final int FRONT_LEFT_PORT = 0x03;
            public static final int BACK_LEFT_PORT = 0x06;
            public static final int FRONT_RIGHT_PORT = 0x01;
            public static final int BACK_RIGHT_PORT = 0x07;
        }
        public static final int ANGLE_ERROR = 2;
    }
    public class Camera {
        public static final int FOV = 60;
        // Camera IP: 10.50.26.17:5800
    }
}
