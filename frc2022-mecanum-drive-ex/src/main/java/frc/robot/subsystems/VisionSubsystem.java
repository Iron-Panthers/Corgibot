import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
    private PhotonCamera camera;

    public VisionSubsystem() {
        camera = new PhotonCamera("limelight");
    }
}