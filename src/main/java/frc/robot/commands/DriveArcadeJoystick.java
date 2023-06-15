// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveArcadeJoystick extends CommandBase {
  /** Creates a new DriveArcade. */
  public DriveArcadeJoystick() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Joystick Controller Active!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double slider = RobotContainer.m_joystickDriverController.getThrottle(); 
    double moveSpeed = RobotContainer.m_joystickDriverController.getY()*-1;
    if (slider < 0.8) {
      RobotContainer.m_driveSubsystem.PushDrive(moveSpeed);
    }
    else {
      double rotateSpeed = RobotContainer.m_joystickDriverController.getZ();
      RobotContainer.m_driveSubsystem.DriveArcade(moveSpeed, rotateSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_driveSubsystem.stopDrive();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
