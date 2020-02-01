/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
public class Chucc extends SubsystemBase {
  //private Victor Chuccyboi1 = new Victor(2);
  //private Victor Chuccyboi2 = new Victor(5);
  //private Encoder encoder1 = new Encoder(0,1);
  //private Encoder encoder2 = new Encoder(2,3);
  public Chucc() {
  }
  public void ChuccBall(double speed1, double speed2){
    //Chuccyboi1.set(speed1);
    //Chuccyboi2.set(-speed2);
  }
  public double GetRot1(){
    //encoder1.setDistancePerPulse(0.01);
    //return encoder1.getRate();
    return 1;
  }
  public double GetRot2(){
    //encoder2.setDistancePerPulse(0.01);
    //return encoder2.getRate();
    return 1;
  }
  @Override
  public void periodic() {
  }
}