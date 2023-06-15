// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.RobotContainer;
public class DriveArcadeJoystick extends CommandBase {
  /** Creates a new DriveArcade. */
  public DriveArcadeJoystick() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double slider = RobotContainer.m_joystickDriverController.getThrottle(); 
    double rotateSpeed = RobotContainer.m_joystickDriverController.getY();
    if (slider < 0.8) {
      RobotContainer.m_driveSubsystem.PushDrive(rotateSpeed);
    }
    else {
      double moveSpeed = RobotContainer.m_joystickDriverController.getZ();
      RobotContainer.m_driveSubsystem.DriveArcade(rotateSpeed, moveSpeed);
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
