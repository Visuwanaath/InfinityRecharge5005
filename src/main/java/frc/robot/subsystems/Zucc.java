/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
public class Zucc extends SubsystemBase {
  private Victor Zuccyboi1 = new Victor(4);
  /**
   * Creates a new Zucc.
   */
  public Zucc() {

  }
  public void ZuccBall(double speed){
    Zuccyboi1.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
