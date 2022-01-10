package org.firstinspires.ftc.teamcode.glowCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import org.firstinspires.ftc.teamcode.glowCode.VuforiaStuff;


@Autonomous
public class newAuto extends LinearOpMode{
    private final HardwareMapping robot = new HardwareMapping();

    //SampleMecanumDrive drives = new SampleMecanumDrive(hardwareMap);
    //VuforiaStuff.elementPos pos;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive drives = new org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive(hardwareMap);

        //pos = robot.vuforiaStuff.vuforiascan(true, false);
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {



           /* // We want to start the bot at x: 10, y: -8, heading: 90 degrees
            Pose2d startPose = new Pose2d(10, -8, Math.toRadians(90));

            drives.setPoseEstimate(startPose);

            Trajectory traj1 = drives.trajectoryBuilder(startPose)
                  .forward(50)
                  .build();
            Trajectory traj2 = drives.trajectoryBuilder(traj1.end())
                  .strafeLeft(50)
                  .build();
            Trajectory traj3 = drives.trajectoryBuilder(traj2.end())
                  .back(50)
                  .build();
            Trajectory traj4 = drives.trajectoryBuilder(traj3.end()\
                  .strafeRight(50)
                  .build();

            drives.followTrajectory(traj1);
            drives.followTrajectory(traj2);
            drives.followTrajectory(traj3);
            drives.followTrajectory(traj4);

            drives.turn(90);
            drives.turn(-90);

            */
            //robot.driveAtDirection(0,2200,0.3);
            //robot.driveAtDirection(0, 2200, 0.3);
            //robot.driveAtDirection(270, 200, .3);
            //pick up duck
            robot.clawArm.setPower(0.3);
            sleep(700);
            robot.driveAtDirection(0, 700, .3);
            robot.claw.setPosition(1);
            sleep(100);
            robot.clawArm.setPower(0.3);
            sleep(100);
            robot.claw.setPosition(0);



        }
    }
}