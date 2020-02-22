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
public class Zucc extends SubsystemBase {
  private Spark Zuccyboi1 = new Spark(Constants.LoaderPWM);
  /**
   * Creates a new Zucc.
   */
  public Zucc() {
  }
  public void ZuccBall(double speed){
    Zuccyboi1.set(speed);
  }
}
