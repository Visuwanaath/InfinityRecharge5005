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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
public class ChuccPID extends PIDSubsystem {
  private Victor Chuccyboi1 = new Victor(4);
  private Victor Chuccyboi2 = new Victor(5);
  private Encoder encoder1 = new Encoder(0,1);
  //private final SimpleMotorFeedforward m_shooterFeedforward =
  //new SimpleMotorFeedforward(0.2,0.5);
  /**
   * Creates a new ChuccPID.
   */
  public ChuccPID() {
    super(
        // The PIDController used by the subsystem
        //get values from shuffleboard
        new PIDController(1, 0, 0));
  }
  @Override
  public double getMeasurement() {
    return encoder1.getRate();
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
  }
}