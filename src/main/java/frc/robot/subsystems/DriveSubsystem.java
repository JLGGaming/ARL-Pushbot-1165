// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants.SparkMaxConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import frc.robot.Constants.TalonSRXConstants;


import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;



public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  
  CANSparkMax topLeft = new CANSparkMax(SparkMaxConstants.kLeftMotorTop, MotorType.kBrushed);
  CANSparkMax topRight = new CANSparkMax(SparkMaxConstants.kRightMotorTop, MotorType.kBrushed);
  
  WPI_TalonSRX frontLeft = new  WPI_TalonSRX (TalonSRXConstants.kLeftMotorFront);
  WPI_TalonSRX  backLeft = new  WPI_TalonSRX (TalonSRXConstants.kLeftMotorBack);
  WPI_TalonSRX  frontRight = new  WPI_TalonSRX (TalonSRXConstants.kRightMotorFront);
  WPI_TalonSRX  backRight = new  WPI_TalonSRX (TalonSRXConstants.kRightMotorBack);

  MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft, topLeft);
  MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight, topRight);

  MotorControllerGroup leftDriveMotors = new MotorControllerGroup(frontLeft, backLeft);
  MotorControllerGroup rightDriveMotors = new MotorControllerGroup(frontRight, backRight);
  
  MotorControllerGroup pushMotors = new MotorControllerGroup(topLeft, topRight);

  DifferentialDrive drive = new DifferentialDrive(leftDriveMotors, rightDriveMotors);



  public DriveSubsystem() {
    configMotors();
  }

  private void configMotors() {
    topLeft.setIdleMode(IdleMode.kCoast);
    topRight.setIdleMode(IdleMode.kCoast);
    topRight.setInverted(true);

    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void DriveArcade(double xSpeed, double ySpeed) {
    drive.arcadeDrive(xSpeed, ySpeed);
  }

  public void PushDrive (double speed) {
    leftDriveMotors.set(speed);
    rightDriveMotors.set(-speed);

    pushMotors.set(speed);
  }

  
  public void toggleBrakeMode() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
  }


  public void stopDrive() {
    leftMotors.set(0);
    rightMotors.set(0);
  }

}
