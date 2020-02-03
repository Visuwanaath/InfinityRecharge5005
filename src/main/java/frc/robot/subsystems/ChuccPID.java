/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import java.util.ArrayList;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
public class ChuccPID extends PIDSubsystem {
  private Victor Chuccyboi1 = new Victor(2);
  private Victor Chuccyboi2 = new Victor(5);
  private Encoder encoder1 = new Encoder(0,1);
  ArrayList<Double> AverageValues = new ArrayList<Double>(10);
  double AverageValue;
  //1
  //private Victor Chuccyboi1 = new Victor(18);
  //private Victor Chuccyboi2 = new Victor(15);
  //private final SimpleMotorFeedforward m_shooterFeedforward =
  //new SimpleMotorFeedforward(0.2,0.5);
  /**
   * Creates a new ChuccPID.
   */
  public ChuccPID() {
    super(
        new PIDController(0.055, 0.5,0.005,0.05));
        //0.02,0.1
        encoder1.setDistancePerPulse(0.001);
        m_controller.setTolerance(0.1);

  }
  @Override
  public double getMeasurement() {
    System.out.println("Shooter Speed: " +-1* encoder1.getRate());
    SmartDashboard.putNumber("Shooter Current Speed",-1*encoder1.getRate());
    System.out.println("Distance " + encoder1.getDistance());
    if(AverageValues.isEmpty()){
      AverageValues.add(-1*encoder1.getRate());
    }else if(AverageValues.size() < 10){
      AverageValues.add(-1*encoder1.getRate());
    }else{
      AverageValues.remove(0);
      AverageValues.add(-1*encoder1.getRate());
    }
    for(int i = 0; i<AverageValues.size();i = i+1){
      AverageValue = AverageValue + AverageValues.get(i);
    }
    AverageValue = AverageValue/AverageValues.size();
    System.out.println("Average: " +AverageValue);
    return AverageValue;
  }
  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }
  @Override
  public void useOutput(double output, double setpoint) {
    System.out.println("Output: " +output);
    Chuccyboi1.set(12/130 * setpoint +output);
  }
  public void ChuccBall(double speed1, double speed2){
    Chuccyboi1.set(speed1);
    Chuccyboi2.set(speed2);
  }
}