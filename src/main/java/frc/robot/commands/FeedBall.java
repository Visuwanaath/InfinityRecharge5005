/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
public class FeedBall extends CommandBase {
  private final Feeder m_Feeder;
  DoubleSupplier m_speed;
  boolean m_cutoff;
  public FeedBall(Feeder subsystem, DoubleSupplier speed, boolean cutoff) {
    m_Feeder = subsystem;
    m_speed = speed;
    m_cutoff = cutoff;
    addRequirements(m_Feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Feeder.FeedBall(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(m_cutoff){
      m_Feeder.FeedBall(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}