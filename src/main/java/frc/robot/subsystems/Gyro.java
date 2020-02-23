/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends SubsystemBase {
  /**
   * Creates a new Gyro.
   */
  private ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();
  public Gyro() {
  }
  public double GetGyroAngle(){
    return m_gyro.getAngle();
  }
  public void ResetGyro(){
    m_gyro.reset();
  }
  @Override
  public void periodic() {
  }
}
