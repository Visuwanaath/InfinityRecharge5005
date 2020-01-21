/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
public class Drivetrain extends SubsystemBase {
  private Spark rightMotor = new Spark(0);
  private Spark leftMotor = new Spark(1);
  public Drivetrain() {
  }
public void Go(double rightTrigger,double leftTrigger,double leftStickAxis){
  double triggerVal;
  triggerVal = (rightTrigger - leftTrigger);
  if(triggerVal != 0 ){
    leftMotor.set(triggerVal + leftStickAxis);
    rightMotor.set(triggerVal - leftStickAxis);
  }else{
    leftMotor.set(0.5*(triggerVal + leftStickAxis));
    rightMotor.set(0.5*(triggerVal - leftStickAxis));
  }
}
public void ArcDrive(double GoSpeed, double Turn){
  if(GoSpeed != 0 ){
    leftMotor.set(GoSpeed + Turn);
    rightMotor.set(GoSpeed - Turn);
  }else{
    //leftMotor.set(0.5*(GoSpeed + Turn));
    //rightMotor.set(0.5*(GoSpeed - Turn));
    leftMotor.set(GoSpeed + Turn);
    rightMotor.set(GoSpeed - Turn);
  }
}
@Override
public void periodic() {
}
}