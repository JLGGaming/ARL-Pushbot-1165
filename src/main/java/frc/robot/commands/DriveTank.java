// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class DriveTank extends CommandBase {
  /** Creates a new DriveTank. */
  public double leftSpeed;
  public double rightSpeed;
  public double driveTime;

  public boolean end;

  private Timer timer;


  public DriveTank() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);

  }

  public DriveTank(double left, double right) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
    leftSpeed = left;
    rightSpeed = right;
    // driveTime = time;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // timer = new Timer();
    // timer.reset();
    // timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
      RobotContainer.m_driveSubsystem.DriveTank(leftSpeed, rightSpeed);
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_driveSubsystem.DriveTank(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
