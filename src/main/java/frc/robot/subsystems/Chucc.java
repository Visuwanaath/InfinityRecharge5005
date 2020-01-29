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
  private Spark Chuccyboi1 = new Spark(4);
  private Spark Chuccyboi2 = new Spark(5);
  private Encoder encoder1 = new Encoder(0,1);
  public Chucc() {
  }
  public void ChuccBall(double speed1, double speed2){
    Chuccyboi1.set(speed1);
    Chuccyboi2.set(-speed2);
  }
  @Override
  public void periodic() {
    System.out.println("Shooter Speed: " + encoder1.getRate());
    System.out.println("Distance " + encoder1.getDistance());
  }
}