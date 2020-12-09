package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrive{
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;

    Telemetry telemetry;

    public MecanumDrive(HardwareMap hardwareMap, Telemetry telemetry){

        this.telemetry= telemetry;

        leftFront= hardwareMap.get(DcMotor.class, "leftFront");
        rightFront= hardwareMap.get(DcMotor.class, "rightFront");
        leftBack= hardwareMap.get(DcMotor.class,"leftBack");
        rightBack= hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void update(double forward, double strafe, double turn){

        double direction= Math.atan2(forward, strafe);
        double speed= Math.hypot(forward, strafe);

        double desiredFrontLeftSpeed= Math.sin((direction + Math.PI / 4) * speed) + turn;
        double desiredFrontRightSpeed= Math.sin((direction - Math.PI / 4) * speed) - turn;
        double desiredBackLeftSpeed= Math.sin((direction - Math.PI / 4) * speed) + turn;
        double desiredBackRightSpeed= Math.sin((direction + Math.PI / 4) * speed)- turn;

        double fact1= Math.max(Math.abs(desiredFrontLeftSpeed), Math.abs(desiredFrontRightSpeed));
        double fact2= Math.max(Math.abs(desiredBackLeftSpeed), Math.abs(desiredBackRightSpeed));
        double speedScaler= Math.max(fact1,fact2);

        if(speedScaler > 1){
            desiredFrontLeftSpeed /= speedScaler;
            desiredFrontRightSpeed /= speedScaler;
            desiredBackLeftSpeed /= speedScaler;
            desiredBackRightSpeed /= speedScaler;
        }

        leftFront.setPower(desiredFrontLeftSpeed);
        leftBack.setPower(desiredBackLeftSpeed);
        rightFront.setPower(desiredFrontRightSpeed);
        rightBack.setPower(desiredBackRightSpeed);


    }

}

