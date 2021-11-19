package org.firstinspires.ftc.teamcode.glowCode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.Arrays;
import java.util.List;

public class HardwareMapping {

    /* Public OpMode members. */
    public DcMotor carouselArm = null;
    public DcMotor clawArm = null;
    //public DcMotor intake = null;
    //public DcMotor wobbleLift = null;
    public Servo claw = null;
    //public Servo ArmServo = null;
    //public Servo liftServo = null;

    //public VuforiaStuff vuforiaStuff;
    //private VuforiaLocalizer vuforia;
    //private static final String VUFORIA_KEY = "Ac5oJT3/////AAABmaqjC8BtVEdCqJ9KEctcjrJjRRHPEsjVpJWvESCluMdAjHDqmXj1hS15xQ/ZC19fWDvWS+synqHGRqZmXH+i3WbN2DElVFCs667s5+7HrgrsLzKbbqTtHbaKah5ZHKj8Oh5JGQf+2+glYSNej8y5tcU72ThLn/yWFirimoywB7MCwbvsyBNEr+QHMwSMrf6lqVxFBR+EF+XRAXRCoADqVupPYsngQsb7GsCW7tnfavRhonHStOqq4co+pqqrRrpFOKgXGzzXUzrlDafduiU4UulaCZXf6NzIAuO3e2rVv39LZW6hom6L/sYatfWHgmgOjR3a6/+EKonhIj6LhGSmMaFvDAoeTiMObtQFvy2IbfKh";

    /* local OpMode members. */
    public HardwareMap hwMap = null;
    private final ElapsedTime period = new ElapsedTime();

/* Copied out of SampleMecanumDrive*/
    public DcMotorEx leftFront, leftRear, rightRear, rightFront;
    public List<DcMotorEx> motors;
    public BNO055IMU imu;
/*End Copied out of SampleMecanumDrive*/

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

/*Copied out of SampleMecanumDrive*/
        // TODO: adjust the names of the following hardware devices to match your configuration
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        // TODO: if your hub is mounted vertically, remap the IMU axes so that the z-axis points
        // upward (normal to the floor) using a command like the following:
        // BNO055IMUUtil.remapAxes(imu, AxesOrder.XYZ, AxesSigns.NPN);

        leftFront = hwMap.get(DcMotorEx.class, "leftFront");
        leftRear = hwMap.get(DcMotorEx.class, "leftRear");
        rightRear = hwMap.get(DcMotorEx.class, "rightRear");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");

        motors = Arrays.asList(leftFront, leftRear, rightRear, rightFront);
/*End Copied out of SampleMecanumDrive*/

        // Define and Initialize Motors
        carouselArm = hwMap.get(DcMotorEx.class, "carouselArm");
        clawArm = hwMap.get(DcMotorEx.class, "clawArm");
        //intake = hwMap.get(DcMotorEx.class, "intake");
        //wobbleLift = hwMap.get(DcMotorEx.class, "wobbleLift");


        // Set all motors to zero power
        //shooterRight.setPower(0);
        //shooterLeft.setPower(0);
        //intake.setPower(0);
        //wobbleLift.setPower(0);
        //sonia test
        //wobbleLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //wobbleLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //wobbleLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define and initialize ALL installed servos.
        claw = hwMap.get(Servo.class, "claw");
        //ArmServo = hwMap.get(Servo.class, "ArmServo");
        //liftServo = hwMap.get(Servo.class, "liftServo");

        // set the digital channel to input.
        //touchSensor.setMode(DigitalChannel.Mode.INPUT);

        claw.setPosition(0);
        //ArmServo.setPosition(0.80);
        //liftServo.setPosition(0.05);
        /*clampServo.setPosition(0.35);*/

        WebcamName webcamName;
        webcamName = hwMap.get(WebcamName.class, "Webcam");


        //vuforia things
        //int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        //VuforiaLocalizer.Parameters parametersV = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //parametersV.vuforiaLicenseKey = VUFORIA_KEY;
        //parametersV.cameraName = webcamName;
        //  Instantiate the Vuforia engine
        //vuforia = ClassFactory.getInstance().createVuforia(parametersV);
        //vuforiaStuff = new VuforiaStuff(vuforia);
    }

    /*public void armOut(double armDistance, double armPower) {
        double armPosition = wobbleLift.getCurrentPosition();
        while (Math.abs(armPosition - wobbleLift.getCurrentPosition()) < armDistance) {
            wobbleLift.setPower(armPower);

        }
        wobbleLift.setPower(0);
    }*/
  /*  public void flickRings(int power) {
        carouselArm.setPower(power);
        ElapsedTime runtime2 = new ElapsedTime();
        while(runtime2.milliseconds() < 4000){
            if (runtime2.milliseconds() > 1000) {
                carouselArm.setPower(power);

            }
        }
    }
   */
    public void driveAtDirection(double AngleIn, double driveDistance, double motorPower) {
        double LeftYMotorFix = -1;
        double LeftXMotorFix = -1;
        double RightXMotorFix = -1;

        double frontLeftStart = leftRear.getCurrentPosition();
        double frontRightStart = rightRear.getCurrentPosition();

        double startingHeading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;;
        //double startingHeading = 0;

        while (Math.sqrt(Math.pow((leftRear.getCurrentPosition() - frontLeftStart),2)+Math.pow((rightRear.getCurrentPosition()-frontRightStart),2)) < driveDistance) {

            double LeftY;
            double LeftX;
            double RightX = 0;

            LeftX = Math.sin(Math.toRadians(AngleIn))*LeftXMotorFix*motorPower;
            LeftY = Math.cos(Math.toRadians(AngleIn))*LeftYMotorFix*motorPower;

            double currentHeading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            double correctionPower = 0;
            if (Math.abs(currentHeading-startingHeading)<.2){
//                correctionPower = 0;
                correctionPower = 0;
            }else if (Math.abs(currentHeading-startingHeading)<1) {
//                correctionPower = 0.005;
                correctionPower = motorPower*.15;
            }else if (Math.abs(currentHeading-startingHeading)<2.5) {
//                correctionPower = 0.01;
                correctionPower = motorPower*.2;
            }else if (Math.abs(currentHeading-startingHeading)<5) {
//                correctionPower = 0.015;
                correctionPower = motorPower*.25;
            }else if (Math.abs(currentHeading-startingHeading)<10) {
//                correctionPower = 0.02;
                correctionPower = motorPower*.3;
            }else if (Math.abs(currentHeading-startingHeading)<15) {
//                correctionPower = 0.03;
                correctionPower = motorPower*.35;
            }else if (Math.abs(currentHeading-startingHeading)<20) {
//                correctionPower = 0.04;
                correctionPower = motorPower*.4;
            }else if (Math.abs(currentHeading-startingHeading)>=20) {
//                correctionPower = 0.05;
                correctionPower = motorPower*.45;
            }


            if (startingHeading > currentHeading){
                RightX = -correctionPower*RightXMotorFix;
            }else if (startingHeading < currentHeading){
                RightX = correctionPower*RightXMotorFix;
            }else if (startingHeading == currentHeading){
                RightX = 0;
            }


            double FrontLeftPrep = -LeftY - LeftX - RightX;
            double FrontRightPrep = LeftY - LeftX - RightX;
            double BackRightPrep = LeftY + LeftX - RightX;
            double BackLeftPrep = -LeftY + LeftX - RightX;

            // clip the right/left values so that the values never exceed +/- 1
            double FrontRight = Range.clip(FrontRightPrep, -1, 1);
            double FrontLeft = Range.clip(FrontLeftPrep, -1, 1);
            double BackLeft = Range.clip(BackLeftPrep, -1, 1);
            double BackRight = Range.clip(BackRightPrep, -1, 1);

            // write the values to the motors
            rightFront.setPower(FrontRight);
            leftFront.setPower(FrontLeft);
            leftRear.setPower(BackLeft);
            rightRear.setPower(BackRight);
        }

        rightFront.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);
    }

}