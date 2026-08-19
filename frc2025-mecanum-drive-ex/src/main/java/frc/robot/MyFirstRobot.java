package frc.robot;
import frc.robot.Robot;

public static class MyFirstRobot {
    public static void myFirstRobot() {

        // Welcome to your first java program. You have two demands at your disposal:
        // Robot.driveForward(<speed>, <time to drive forward in seconds>);
        // Robot.turn(<speed>, <angle to turn by (note: can be negative)>);
        // Always remember to add a semicolon!

        // Write your commands here:
        Robot.driveForward(1, 1);
        Robot.turn(1, 90);
        Robot.driveForward(1, 1);
        
    }
}
