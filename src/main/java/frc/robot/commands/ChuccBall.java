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
public class ChuccBall extends CommandBase {
  private final Chucc m_Chucc;
  double TargetDetected;
  double Distance;
  public ChuccBall(Chucc subsystem){
    // Use addRequirements() here to declare subsystem dependencies.
    m_Chucc = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TargetDetected = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(TargetDetected == 1){
      double OffsetY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      Distance= GetDistance(20, 88, OffsetY, 35);
      //Find Relation between distance and speed and implement here
      m_Chucc.ChuccBall(0.7, 0.7);
    }else{
      m_Chucc.ChuccBall(0.5,0.5);
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
    return false;
  }
}