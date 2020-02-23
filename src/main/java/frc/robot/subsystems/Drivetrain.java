/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
public class Drivetrain extends SubsystemBase {
  //private Spark rightMotor = new Spark(0);
  private Spark rightMotor = new Spark(Constants.DriveTrainRightMotor1PWM);
  private Spark rightMotor2 = new Spark(Constants.DriveTrainRightMotor2PWM);
  private Spark leftMotor = new Spark(Constants.DriveTrainLeftMotor1PWM);
  private Spark leftMotor2 = new Spark(Constants.DriveTrainLeftMotor2PWM);
  private DigitalInput SpinnerSwitch = new DigitalInput(4);
  private Boolean CutOffWithSwitch;
  public Drivetrain() {
  }
public void Go(double rightTrigger,double leftTrigger,double leftStickAxis,Boolean CutOffWithSwitch){
  double triggerVal;
  triggerVal = (rightTrigger - leftTrigger);
  if(CutOffWithSwitch){
    //replace true with SpinnerSwitch.get()
    if(SpinnerSwitch.get() == true){
      if(triggerVal != 0 ){
        leftMotor.set(triggerVal + leftStickAxis);
        leftMotor2.set(triggerVal + leftStickAxis);
        rightMotor.set(-1 * (triggerVal - leftStickAxis));
        rightMotor2.set(-1 * (triggerVal - leftStickAxis));
      }else{
        leftMotor.set(0.5*(triggerVal + leftStickAxis));
        leftMotor2.set(0.5*(triggerVal + leftStickAxis));
        rightMotor.set(-0.5*(triggerVal - leftStickAxis));
        rightMotor2.set(-0.5*(triggerVal - leftStickAxis));
      }
    }else{
      if(triggerVal < 0){
        leftMotor.set(0.5*(triggerVal + leftStickAxis));
        leftMotor2.set(0.5*(triggerVal + leftStickAxis));
        rightMotor.set(-0.5*(triggerVal - leftStickAxis));
        rightMotor2.set(-0.5*(triggerVal - leftStickAxis));
      }else{
        leftMotor.set(0);
        leftMotor2.set(0);
        rightMotor.set(0);
        rightMotor2.set(0);
      }
    }    
  }else{
    if(triggerVal != 0 ){
      leftMotor.set(triggerVal + leftStickAxis);
      leftMotor2.set(triggerVal + leftStickAxis);
      rightMotor.set(-1 * (triggerVal - leftStickAxis));
      rightMotor2.set(-1 * (triggerVal - leftStickAxis));
    }else{
      leftMotor.set(0.5*(triggerVal + leftStickAxis));
      leftMotor2.set(0.5*(triggerVal + leftStickAxis));
      rightMotor.set(-0.5*(triggerVal - leftStickAxis));
      rightMotor2.set(-0.5*(triggerVal - leftStickAxis));
    }
  }
}
public void ArcDrive(double GoSpeed, double Turn){
  if(GoSpeed != 0 ){
    leftMotor.set(GoSpeed + Turn);
    rightMotor.set(-1*(GoSpeed - Turn));
  }else{
    //leftMotor.set(0.5*(GoSpeed + Turn));
    //rightMotor.set(0.5*(GoSpeed - Turn));
    leftMotor.set(GoSpeed + Turn);
    rightMotor.set(-1*(GoSpeed - Turn));
  }
}
@Override
public void periodic() {
}
}