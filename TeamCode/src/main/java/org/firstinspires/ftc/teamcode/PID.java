package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class PID extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftFront=hardwareMap.get(DcMotor.class,"leftFront");
        DcMotor rightFront=hardwareMap.get(DcMotor.class,"rightFront");
        DcMotor leftBack=hardwareMap.get(DcMotor.class,"leftBack");
        DcMotor rightBack=hardwareMap.get(DcMotor.class,"rightBack");

        double setpoint=1500;
         leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
         leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

         leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

         waitForStart();
         while(opModeIsActive()){
             double average= (leftFront.getCurrentPosition()+rightFront.getCurrentPosition()+
                     leftBack.getCurrentPosition()+rightBack.getCurrentPosition())/4.0;
             double error= setpoint-average;
             double kP=0.015;
             telemetry.addData("position",average);
             telemetry.update();

             double output= error*kP;
              leftFront.setPower(output);
              rightFront.setPower(output);
              leftBack.setPower(output);
              rightBack.setPower(output);
         }





    }
}

