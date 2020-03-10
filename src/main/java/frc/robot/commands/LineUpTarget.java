/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class LineUpTarget extends CommandBase {
  private final Drivetrain m_Drivetrain;
  double TargetDetected;
  boolean EndNow;
  double RightMotorSpeed;
  double LeftMotorSpeed;
  double STEER_K;
  double DRIVE_K;
  double DESIRED_TARGET_AREA;
  double MAX_DRIVE;
  double steer_cmd;
  double drive_cmd;
  double Desired_distance;
  double actual_Distance;
  boolean m_DriveToDistance;
   //IMPORT LINE BELOW FOR ACCESSSING STUFF FROM LIMELIGHT
   //NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").getDouble(0);
  public LineUpTarget(Drivetrain subsystem,boolean DriveToDistance) {
    m_Drivetrain = subsystem;
    addRequirements(m_Drivetrain);
    m_DriveToDistance = DriveToDistance;
    //was 0.07
    STEER_K = 0.0725;                    // how hard to turn toward the target
    DRIVE_K = 0.07;                    // how hard to drive fwd toward the target
    //DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    MAX_DRIVE = 0.5;                   // Simple speed limit so we don't drive too fast
    Desired_distance = 84;
  } 
  @Override
  public void initialize() {
    EndNow = false;
    TargetDetected = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    SmartDashboard.putNumber("Desired Distance", Desired_distance);
  }
  @Override
  public void execute() {
    if(TargetDetected == 1){
      double OffsetX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      double OffsetY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      double MIN_DRIVE = 0.25;
      if(m_DriveToDistance == false){
        DRIVE_K = 0;
      }
      actual_Distance = GetDistance(18.2, 89, OffsetY, 27);
      SmartDashboard.putNumber("Actual Distance", actual_Distance);
      if(OffsetX != 0){
      steer_cmd = OffsetX * STEER_K;
      drive_cmd = (actual_Distance- Desired_distance) * DRIVE_K;
      if (drive_cmd > MAX_DRIVE)
      {
        drive_cmd = MAX_DRIVE;
      }
      m_Drivetrain.ArcDrive(drive_cmd,steer_cmd);
      }else{
        EndNow = true;
        m_Drivetrain.Go(0, 0, 0,false);
      }
    }else if(TargetDetected == 0){
      m_Drivetrain.Go(0, 0, 1,false);
    }
  }
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.Go(0, 0, 0,false);
  }
  public double GetDistance(double LimeHeight,double TargetHeight, double Angle,double LimelightAngle){
    Angle = Angle + LimelightAngle;
    Angle = Math.toRadians(Angle);
    double distance = (TargetHeight - LimeHeight) /(Math.tan(Angle));
    return distance;
  }
  @Override
  public boolean isFinished() {
    if(EndNow){
      return true;
    }else{
      return false;
    }
  }
}