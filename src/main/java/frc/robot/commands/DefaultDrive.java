/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;
public class DefaultDrive extends CommandBase {
  private final Drivetrain m_Drivetrain;
  private final DoubleSupplier m_rightTrigger;
  private final DoubleSupplier m_leftTrigger;
  private final DoubleSupplier m_leftStickAxis;
  private final BooleanSupplier m_CutoffSpinnerSwitchThing;
  /**
   * Creates a new DefaultDrive.
   */
  public DefaultDrive(Drivetrain subsystem,DoubleSupplier rightTrigger, DoubleSupplier leftTrigger, DoubleSupplier leftStickAxis,BooleanSupplier CutoffSpinnerSwitchThing) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drivetrain = subsystem;
    m_rightTrigger = rightTrigger;
    m_leftTrigger = leftTrigger;
    m_leftStickAxis = leftStickAxis;
    m_CutoffSpinnerSwitchThing = CutoffSpinnerSwitchThing;
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Drivetrain.Go(m_rightTrigger.getAsDouble(), m_leftTrigger.getAsDouble(), m_leftStickAxis.getAsDouble(),m_CutoffSpinnerSwitchThing.getAsBoolean());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.Go(0,0,0,false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
