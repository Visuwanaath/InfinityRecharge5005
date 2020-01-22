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
import frc.robot.subsystems.Gyro;
public class GyroLineUp extends CommandBase {
  private final Drivetrain m_Drivetrain;
  Double m_DesiredAngle;
  boolean End;
  boolean m_Reset = false;
  private final Gyro m_Gyro;
  /**
   * Creates a new GyroLineUp.
   */
  public GyroLineUp(Drivetrain subsystem, Gyro subsystemGyro,DoubleSupplier DesiredAngle,boolean ResetGyro) {
    m_Drivetrain = subsystem;
    m_DesiredAngle = DesiredAngle.getAsDouble();
    m_Gyro = subsystemGyro;
    m_Reset = ResetGyro;
    addRequirements(m_Drivetrain);
    addRequirements(m_Gyro);
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
      double CurrentAngle = m_Gyro.GetGyroAngle();
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
    }else{
      m_Gyro.ResetGyro();
      End = true;
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
