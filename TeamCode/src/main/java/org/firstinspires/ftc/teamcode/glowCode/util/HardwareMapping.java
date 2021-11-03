package org.firstinspires.ftc.teamcode.glowCode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.Arrays;
import java.util.List;

public class HardwareMapping {

    /* Public OpMode members. */
    public DcMotor carouselArm = null;
    //public DcMotor shooterLeft = null;
    //public DcMotor intake = null;
    //public DcMotor wobbleLift = null;
    //public Servo flicker = null;
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
        //shooterLeft = hwMap.get(DcMotorEx.class, "shooterLeft");
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
        //flicker = hwMap.get(Servo.class, "flicker");
        //ArmServo = hwMap.get(Servo.class, "ArmServo");
        //liftServo = hwMap.get(Servo.class, "liftServo");

        // set the digital channel to input.
        //touchSensor.setMode(DigitalChannel.Mode.INPUT);

        //flicker.setPosition(0);
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
    public void flickRings(int power) {
        carouselArm.setPower(power);
        ElapsedTime runtime2 = new ElapsedTime();
        while(runtime2.milliseconds() < 4000){
            if (runtime2.milliseconds() > 1000) {
                carouselArm.setPower(power);

            }
        }
    }
}