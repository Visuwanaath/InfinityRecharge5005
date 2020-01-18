/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_Drivetrain = new Drivetrain();
  private final Spinner m_Spinner = new Spinner();
  //private final Zucc m_Zucc = new Zucc();
  private final Chucc m_Chucc = new Chucc();
  //private final LEDS m_Leds = new LEDS();
  private Joystick controller1 = new Joystick(0);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    m_Drivetrain.setDefaultCommand(
      new DefaultDrive(m_Drivetrain, () -> controller1.getRawAxis(3), () -> controller1.getRawAxis(2), () -> controller1.getRawAxis(0))
    );
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller1,4).whenPressed(new SpinForRotations(m_Spinner,() -> 30));
    new JoystickButton(controller1,3).whenPressed(new SpinUntilColor(m_Spinner));
    new JoystickButton(controller1,2).whileHeld(new LineUpTarget(m_Drivetrain,true));
    new JoystickButton(controller1,5).whenPressed(new ChuccBall(m_Chucc));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
