// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class DriveArcade extends CommandBase {
  /** Creates a new DriveArcade. */
  public DriveArcade() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("XBOX Controller Active!");
    RobotContainer.m_driveSubsystem.setXboxDrive();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rTrigger = RobotContainer.m_xboxDriverController.getRightTriggerAxis();
    double lTrigger = RobotContainer.m_xboxDriverController.getLeftTriggerAxis();

    if ( rTrigger > 0.2) {
      RobotContainer.m_driveSubsystem.PushDrive(rTrigger);
    }
    else if (lTrigger> 0.2) {
      RobotContainer.m_driveSubsystem.PushDrive(lTrigger*-1);
    }
    else { 
      double moveSpeed = RobotContainer.m_xboxDriverController.getLeftY();
      double rotateSpeed = RobotContainer.m_xboxDriverController.getRightX();
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
