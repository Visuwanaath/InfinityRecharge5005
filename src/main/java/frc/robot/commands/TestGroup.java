/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Chucc;
import frc.robot.subsystems.ChuccPID;
import frc.robot.subsystems.ChuccPID2;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Zucc;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestGroup extends SequentialCommandGroup {
  public TestGroup(Drivetrain subsystemDrivetrain,Feeder subsystemFeeder,Chucc subsystemChucc,Gyro subsystemGyro, Zucc subsystemZucc,ChuccPID subsystemChuccPID,ChuccPID2 subsystemChuccPID2) {
    super(
    new MoveForTime(subsystemDrivetrain, () ->-0.3,() -> 0).withTimeout(0.5),
    new GyroLineUp(subsystemDrivetrain,subsystemGyro,() -> 0,true),
    new LineUpTarget(subsystemDrivetrain,true).withTimeout(3),
    new ChuccBall(subsystemChucc,false,subsystemChuccPID,subsystemChuccPID2).withTimeout(0.5),
    new FeedBall(subsystemFeeder, () -> 1).withTimeout(3.5),
    new ChuccBall(subsystemChucc, true,subsystemChuccPID,subsystemChuccPID2).withTimeout(0.1),
    new GyroLineUp(subsystemDrivetrain,subsystemGyro,() -> 0,false).withTimeout(5),
    new ZuccBall(subsystemZucc,()-> 1,false).withTimeout(0.1),
    new MoveForTime(subsystemDrivetrain, () ->-0.3,() -> 0).withTimeout(0.5),
    new ZuccBall(subsystemZucc,()-> 1,true).withTimeout(3),
    new MoveForTime(subsystemDrivetrain, () ->0.3,() -> 0).withTimeout(0.7),
    new LineUpTarget(subsystemDrivetrain,true).withTimeout(3),
    new ChuccBall(subsystemChucc,false,subsystemChuccPID,subsystemChuccPID2).withTimeout(0.5),
    new FeedBall(subsystemFeeder, () -> 1).withTimeout(3.5),
    new ChuccBall(subsystemChucc,true,subsystemChuccPID,subsystemChuccPID2).withTimeout(0.1)
    );
  }
}