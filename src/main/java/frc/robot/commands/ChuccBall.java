/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chucc;
import frc.robot.subsystems.ChuccPID;
import frc.robot.subsystems.ChuccPID2;
import edu.wpi.first.networktables.NetworkTableInstance;
public class ChuccBall extends CommandBase {
  private final Chucc m_Chucc;
  double TargetDetected;
  double Distance;
  boolean m_Cutoff;
  private final ChuccPID m_ChuccPID;
  private final ChuccPID2 m_ChuccPID2;
  public ChuccBall(Chucc subsystem, boolean Cutoff,ChuccPID ChuccPIDSubsystem,ChuccPID2 ChuccPID2subsystem){
    // Use addRequirements() here to declare subsystem dependencies.
    m_Cutoff = Cutoff;
    m_Chucc = subsystem;
    m_ChuccPID = ChuccPIDSubsystem;
    m_ChuccPID2 = ChuccPID2subsystem;
    addRequirements(m_ChuccPID);
    addRequirements(m_Chucc);
    addRequirements(m_ChuccPID2);
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
      m_ChuccPID.enable();
      m_ChuccPID.setSetpoint(80);

      m_ChuccPID2.enable();
      m_ChuccPID2.setSetpoint(80);
    }else{
      m_ChuccPID.enable();
      m_ChuccPID.setSetpoint(80);
      
      m_ChuccPID2.enable();
      m_ChuccPID2.setSetpoint(80);
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
    if(m_Cutoff){
      //m_Chucc.ChuccBall(0, 0);
      m_ChuccPID.disable();
      m_ChuccPID2.disable();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}