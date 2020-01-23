/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class ChuccPID extends PIDSubsystem {
  private Spark Chuccyboi1 = new Spark(2);
  private Spark Chuccyboi2 = new Spark(7);
  private DutyCycleEncoder encoder1 = new DutyCycleEncoder(1);
  private DutyCycleEncoder encoder2 = new DutyCycleEncoder(2);
  //private final SimpleMotorFeedforward m_shooterFeedforward =
  //new SimpleMotorFeedforward(0.2,0.5);
  /**
   * Creates a new ChuccPID.
   */
  public ChuccPID() {
    super(
        // The PIDController used by the subsystem
        //get values from shuffleboard
        new PIDController(0, 0, 0));
  }
  @Override
  public double getMeasurement() {
    return encoder1.getFrequency();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }
  @Override
  public void useOutput(double output, double setpoint) {
    Chuccyboi1.set(output);
  }
  public void ChuccBall(double speed1, double speed2){
    Chuccyboi1.set(speed1);
    Chuccyboi2.set(speed2);
  }
}