package org.firstinspires.ftc.teamcode.glowCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class NonCameraAuto extends LinearOpMode {
    private final HardwareMapping robot = new HardwareMapping();

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

                robot.turnLeft(5, 0.1);
                //robot.driveAtDirection(180, 2200, 0.3);
                //robot.driveAtDirection(250, 400, 0.1);
                //robot.driveAtDirection(200, 2200, 0.05);
                //robot.moveToPosition(1000, 0.1);


                /*robot.clawArm.setPower(0.3);
                sleep(1000);
                robot.driveAtDirection(0, 850, .1);

                 */
                /*
                robot.claw.setPosition(1);
                sleep(100);
                robot.claw.setPosition(0);
                robot.clawArm.setPower(-0.3);
                sleep(300);
                robot.driveAtDirection(180, 2200, 0.3);
                robot.driveAtDirection(270, 2200, 0.3);
                robot.carouselArm.setPower(0.3);
                sleep(500);
                robot.leftRear.setPower(0.3);
                sleep(300);
                robot.rightRear.setPower(-0.3);
                sleep(3000);
                robot.driveAtDirection(0, 5000, 0.3);

                 */
            }
        }
    }
