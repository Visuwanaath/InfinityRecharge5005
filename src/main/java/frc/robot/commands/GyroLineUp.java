/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
public class GyroLineUp extends CommandBase {
  private final Drivetrain m_Drivetrain;
  Double m_DesiredAngle;
	private static SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
  private ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();
  boolean m_Reset;
  boolean End;
  /**
   * Creates a new GyroLineUp.
   */
  public GyroLineUp(Drivetrain subsystem, DoubleSupplier DesiredAngle) {
    m_Drivetrain = subsystem;
    m_DesiredAngle = DesiredAngle.getAsDouble();
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    End = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_Reset == false){
      double kP= 0.07;
      double CurrentAngle = m_gyro.getAngle();
      double turningValue = (m_DesiredAngle - CurrentAngle) * kP;
      if(turningValue > 0.5){
        turningValue = 0.5;
      }else if(turningValue < -0.5){
        turningValue = 0.5;
      }
      m_Drivetrain.ArcDrive(0,turningValue);
      System.out.println("Angle = " + CurrentAngle);
      double Leniency = 5;
      double DesiredWithLeniencyMax = m_DesiredAngle + Leniency;
      double DesiredWithLeniencyMin = m_DesiredAngle - Leniency;
      if(CurrentAngle < DesiredWithLeniencyMax){
        if(CurrentAngle > DesiredWithLeniencyMin){
          System.out.println("Ending.");
          End = true;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.ArcDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(End){
      return true;
    }else{
      return false;
    }
  }
}
