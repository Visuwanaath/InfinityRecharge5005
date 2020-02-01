/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chucc;
import edu.wpi.first.networktables.NetworkTableInstance;
public class GetShooterToSpeed extends CommandBase {
  private final Chucc m_Chucc;
  double TargetDetected;
  double Distance;
  double voltage1;
  double voltage2;
  boolean End1 = false;
  boolean End2 = false;
  public GetShooterToSpeed(Chucc subsystem) {
    m_Chucc = subsystem;
    addRequirements(m_Chucc);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TargetDetected = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    voltage1 = 0.0;
    voltage2 = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(TargetDetected == 1){
      double OffsetY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      Distance= GetDistance(20, 88, OffsetY, 35);
      //Find Relation between distance and speed and implement here
      double SpeedToShootAt = 50;
      double leniency = 2;
      double minSpeed = SpeedToShootAt - leniency;
      double maxSpeed = SpeedToShootAt + leniency;
      //Get voltage for Motor1
      if(m_Chucc.GetRot1() < maxSpeed){
        if(m_Chucc.GetRot1() < minSpeed){
          voltage1 = voltage1 + 0.03;
        }else{
          End1 = true;
        }
      }else{
        voltage1 = voltage1 - 0.03;
      }
      //Get voltage for Motor2
      if(m_Chucc.GetRot2() < maxSpeed){
        if(m_Chucc.GetRot2() < minSpeed){
          voltage2 = voltage2 + 0.03;
        }else{
          End1 = true;
        }
      }else{
        voltage2 = voltage2 - 0.03;
      }
      m_Chucc.ChuccBall(voltage1,voltage2);
    }else{
     End1 = true;
     End2 = true; 
    }
  }
  public double GetDistance(double LimeHeight,double TargetHeight, double Angle,double LimelightAngle){
    Angle = Angle + LimelightAngle;
    Angle = Math.toRadians(Angle);
    double distance = (TargetHeight - LimeHeight) /(Math.tan(Angle));
    return distance;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(End1){
      if(End2){
        return true;
      }
      return false;
    }else{
      return false;
    }
  }
}
