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
public class MoveForTime extends CommandBase {
  private final Drivetrain m_Drivetrain;
  DoubleSupplier m_speed;
  DoubleSupplier m_turn;
  /**
   * Creates a new MoveForTime.
   */
  public MoveForTime(Drivetrain subsytem, DoubleSupplier speed, DoubleSupplier turn) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_speed = speed;
    m_turn = turn;
    m_Drivetrain = subsytem;
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Drivetrain.ArcDrive(m_speed.getAsDouble(),m_turn.getAsDouble());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.ArcDrive(0,0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
