/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
public class Spinner extends SubsystemBase {
  private Spark spinnerArm = new Spark(Constants.SpinnerArmPWM);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  public static final Color kBlueTarget = ColorMatch.makeColor(0.206, 0.460, 0.329);
  public static final Color kGreenTarget = ColorMatch.makeColor(0.196, 0.557, 0.246);
  public static final Color kRedTarget = ColorMatch.makeColor(0.291260, 0.496094, 0.212646);
  public static final Color kYellowTarget = ColorMatch.makeColor(0.304199, 0.507080, 0.188721);
  private Color detectedColor;
  private String colorString;
  ColorMatchResult match;
  public Spinner() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.setConfidenceThreshold(0.95);
  }
  public void setArmSpeed(double speed){
    spinnerArm.set(speed);
  }
  public String getColorString(){
    detectedColor = m_colorSensor.getColor();
    match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }
  public double[] getColorRGBVals(){
    double[] toReturn = new double[4];
    detectedColor = m_colorSensor.getColor();
    match = m_colorMatcher.matchClosestColor(detectedColor);
    toReturn[0] = detectedColor.red;
    toReturn[1] = detectedColor.green;
    toReturn[2] = detectedColor.blue;
    toReturn[3] = match.confidence;
    return toReturn;
  }
  @Override
  public void periodic() {
  }
}