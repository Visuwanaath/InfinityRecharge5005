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
public class ChuccPID2 extends PIDSubsystem {
  private Victor Chuccyboi2 = new Victor(5);
  private Encoder encoder2 = new Encoder(2,3);
  ArrayList<Double> AverageValues = new ArrayList<Double>(5);
  double AverageValue;
  /**
   * Creates a new ChuccPID2.
   */
  public ChuccPID2() {
    super(      
      new PIDController(0.055, 0.5,0.005,0.05));
      encoder2.setDistancePerPulse(0.001);
      m_controller.setTolerance(0.1);
  }
  @Override
  public double getMeasurement() {
    System.out.println("Shooter Speed: " +-1* encoder2.getRate());
    SmartDashboard.putNumber("Shooter Current Speed",-1*encoder2.getRate());
    System.out.println("Distance " + encoder2.getDistance());
    AverageValue = 0;
    if(AverageValues.isEmpty()){
      AverageValues.add(-1*encoder2.getRate());
    }else if(AverageValues.size() < 5){
      AverageValues.add(-1*encoder2.getRate());
    }else{
      AverageValues.remove(0);
      AverageValues.add(-1*encoder2.getRate());
    }
    for(int i = 0; i<AverageValues.size();i = i+1){
      AverageValue = AverageValue + AverageValues.get(i);
    }
    AverageValue = AverageValue/AverageValues.size();
    System.out.println("Average: " +AverageValue);
    return AverageValue;
  }
  @Override
  public void useOutput(double output, double setpoint) {
    System.out.println("Output: " +output);
    Chuccyboi2.set(12/130 * setpoint +output);
  }
}
