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
  /**
   * Creates a new LineUpTarget.
   */
   //IMPORT LINE BELOW FOR ACCESSSING STUFF FROM LIMELIGHT
   //NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").getDouble(0);
  public LineUpTarget(Drivetrain subsystem) {
    m_Drivetrain = subsystem;
    addRequirements(m_Drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  } 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    EndNow = false;
    TargetDetected = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    STEER_K = 0.03;                    // how hard to turn toward the target
    DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(TargetDetected == 1){
      double OffsetX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      double OffsetY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      double AreaOfTarget = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
      if(OffsetX != 0){
      steer_cmd = OffsetX * STEER_K;
      drive_cmd = (DESIRED_TARGET_AREA - AreaOfTarget) * DRIVE_K;
      if (drive_cmd > MAX_DRIVE)
      {
        drive_cmd = MAX_DRIVE;
      }
      m_Drivetrain.ArcDrive(drive_cmd,steer_cmd);
      }else{
        EndNow = true;
        m_Drivetrain.Go(0, 0, 0);
      }
    }else if(TargetDetected == 0){
      EndNow = true;
      m_Drivetrain.Go(0, 0, 0);
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.Go(0, 0, 0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(EndNow){
      return true;
    }else{
      return false;
    }
  }
}