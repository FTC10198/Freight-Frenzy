package org.firstinspires.ftc.teamcode.glowCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

//    Robot wheel mapping:
//            X FRONT X
//          X           X
//        X  FL       FR  X
//                X
//               XXX
//                X
//        X  BL       BR  X
//          X           X
//            X       X
//        */
@TeleOp
//@Disabled
public class TestTeleOp extends OpMode {
    private final org.firstinspires.ftc.teamcode.glowCode.util.HardwareMapping robot = new org.firstinspires.ftc.teamcode.glowCode.util.HardwareMapping();

    double slow = 1;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {

        if (gamepad1.x) {
            if (slow == 1) {
                slow = .6;
            } else if (slow == .6) {
                slow = 1;
            }
        }

        // left stick controls direction
        // right stick X controls rotation
        double gamepad1LeftY = (gamepad1.left_stick_y) * slow;
        double gamepad1LeftX = -gamepad1.left_stick_x * slow;
        double gamepad1RightX = -gamepad1.right_stick_x * .7 * slow;

        // holonomic formulas
        double FrontLeftPrep = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double FrontRightPrep = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double BackRightPrep = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        double BackLeftPrep = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // clip the right/left values so that the values never exceed +/- 1
        FrontRightPrep = Range.clip(FrontRightPrep, -1, 1);
        FrontLeftPrep = Range.clip(FrontLeftPrep, -1, 1);
        BackLeftPrep = Range.clip(BackLeftPrep, -1, 1);
        BackRightPrep = Range.clip(BackRightPrep, -1, 1);

        double FrontLeft = Math.pow(FrontLeftPrep, 3);
        double FrontRight = Math.pow(FrontRightPrep, 3);
        double BackLeft = Math.pow(BackLeftPrep, 3);
        double BackRight = Math.pow(BackRightPrep, 3);

        // write the values to the motors
        robot.rightFront.setPower(FrontRight);
        robot.leftFront.setPower(FrontLeft);
        robot.leftRear.setPower(BackLeft);
        robot.rightRear.setPower(BackRight);

        /* Telemetry for debugging  */
        telemetry.addData("Joy XL YL XR", String.format("%.2f", gamepad1LeftX) + " " +
                String.format("%.2f", gamepad1LeftY) + " " + String.format("%.2f", gamepad1RightX));
        telemetry.update();
    }
}