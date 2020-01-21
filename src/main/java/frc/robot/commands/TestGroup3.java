/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Zucc;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Chucc;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestGroup3 extends SequentialCommandGroup {
  /**
   * Creates a new TestGroup3.
   */
  public TestGroup3(Zucc subsystem3,Feeder subsystem1,Chucc subsystem2) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ZuccBall(subsystem3, () -> 0.5).withTimeout(3),new FeedBall(subsystem1, () -> 1).withTimeout(3.5),new ChuccBall(subsystem2, true).withTimeout(0.1));
  }
}
