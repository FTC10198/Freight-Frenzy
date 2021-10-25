package org.firstinspires.ftc.teamcode.glowCode;
        import com.acmerobotics.roadrunner.geometry.Pose2d;
        import com.acmerobotics.roadrunner.trajectory.Trajectory;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
public class TestAuto extends LinearOpMode{
    private final org.firstinspires.ftc.teamcode.glowCode.util.HardwareMapping robot = new org.firstinspires.ftc.teamcode.glowCode.util.HardwareMapping();

    //SampleMecanumDrive drives = new SampleMecanumDrive(hardwareMap);
    //VuforiaStuff.skystonePos pos;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive drives = new org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive(hardwareMap);

        //pos = robot.vuforiaStuff.vuforiascan(false, false);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {

            // We want to start the bot at x: 10, y: -8, heading: 90 degrees
            //Pose2d startPose = new Pose2d(10, -8, Math.toRadians(90));

            //drives.setPoseEstimate(startPose);

            //Trajectory traj1 = drives.trajectoryBuilder(startPose)
            //      .forward(50)
            //      .strafeLeft(50)
            //      .back(50)
            //      .strafeRight(50)
            //      .build();

            //drives.followTrajectory(traj1);

//            drives.turn(90);
//            drives.turn(-90);
        }
    }
}