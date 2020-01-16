/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Spinner;
public class SpinUntilColor extends CommandBase {
  private final Spinner m_Spinner;
  String gameData;
  String currentColor;
  Boolean End;
  /**
   * Creates a new SpinUntilColor.
   */
  public SpinUntilColor(Spinner subsytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Spinner = subsytem;
    addRequirements(m_Spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    System.out.println(gameData);
    End = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  double spinSpeed = 0.5;
  currentColor = SmartDashboard.getString("Detected Color", "NoU");
  if(currentColor.equals("NoU") == false){
    System.out.println(currentColor);
    if(gameData.equals("B")){
      if(currentColor.equals("Blue") == false){
        m_Spinner.setArmSpeed(spinSpeed);
      }else{
        End = true;
        m_Spinner.setArmSpeed(0);
      }
    }else if(gameData.equals("G")){
      if(currentColor.equals("Green") == false){
        m_Spinner.setArmSpeed(spinSpeed);
      }else{
        End = true;
        m_Spinner.setArmSpeed(0);
      }
    }else if(gameData.equals("R")){
      System.out.println("testworked");
      if(currentColor.equals("Red") == false){
        m_Spinner.setArmSpeed(spinSpeed);
      }else{
        End = true;
        m_Spinner.setArmSpeed(0);
      }
    }else if(gameData.equals("Y")){
      if(currentColor.equals("Yellow") == false){
        m_Spinner.setArmSpeed(spinSpeed);
      }else{
        End = true;
        m_Spinner.setArmSpeed(0);
      }
    }else{
      End = true;
      m_Spinner.setArmSpeed(0);
    }
  }else{
    End = true;
    m_Spinner.setArmSpeed(0);
  }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Spinner.setArmSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(End){
      return true;
    }else{
      return false;
    }
  }
}
