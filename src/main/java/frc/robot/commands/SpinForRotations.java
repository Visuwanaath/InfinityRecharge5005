/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spinner;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class SpinForRotations extends CommandBase {
private final Spinner m_Spinner;
private final DoubleSupplier m_colorChangesToDo;
double ColorChanges = 0;
String LastColor = "";
String currentColor;
  /**
   * Creates a new SpinForRotations.
   */
  public SpinForRotations(Spinner subsytem, DoubleSupplier colorChangesToDo) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Spinner = subsytem;
    m_colorChangesToDo = colorChangesToDo;
    addRequirements(m_Spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ColorChanges = 0;
    LastColor = "";
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  currentColor = SmartDashboard.getString("Detected Color", "NoU");
  SmartDashboard.putString("detectedCurrentColor", currentColor);
  if(currentColor != "NoU"){
    if(currentColor.equals(LastColor) == false){
      ColorChanges = ColorChanges + 1;
      System.out.println("done");
      System.out.println(currentColor);
      System.out.println(LastColor);
      SmartDashboard.putNumber("ColorChanges", ColorChanges);
      LastColor = currentColor;
      m_Spinner.setArmSpeed(1);
    }
  }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Spinner.setArmSpeed(0);
  }

  @Override
  public boolean isFinished() {
    if(ColorChanges >= m_colorChangesToDo.getAsDouble()){
      return true;
    }else{
      return false;
    }
  }
}
