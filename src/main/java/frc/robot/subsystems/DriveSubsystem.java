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

import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants.TalonSRXConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  MotorControllerGroup leftDriveMotors = new MotorControllerGroup(frontLeft, backLeft, topLeft);
  MotorControllerGroup rightDriveMotors = new MotorControllerGroup(frontRight, backRight, topRight);
  
  MotorControllerGroup pushMotors = new MotorControllerGroup(topLeft, topRight);

  DifferentialDrive drive = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

  public String activeController = "Waiting";

  public DriveSubsystem() {
    configMotors();
    updateSmartDashboard(true);
  }

  private void configMotors() {
    topLeft.setIdleMode(IdleMode.kCoast);
    topRight.setIdleMode(IdleMode.kCoast);
    topRight.setInverted(true); //Will be tested 6/16/2023
    topLeft.setInverted(true); //Will be tested 6/16/2023

    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);

    frontLeft.setSafetyEnabled(true);
    frontRight.setSafetyEnabled(true);
    backLeft.setSafetyEnabled(true);
    backRight.setSafetyEnabled(true);
     
    topLeft.setOpenLoopRampRate(0.1);
    topRight.setClosedLoopRampRate(0.1);
    frontLeft.configOpenloopRamp(0.5);
    frontRight.configOpenloopRamp(0.5);
    backLeft.configOpenloopRamp(0.5);
    backRight.configOpenloopRamp(0.5);

    //Must Tune!
    topLeft.setSmartCurrentLimit(SparkMaxConstants.kPushingCurrentLimit, SparkMaxConstants.kFreeSpinCurrentLimit);
    topRight.setSmartCurrentLimit(SparkMaxConstants.kPushingCurrentLimit, SparkMaxConstants.kFreeSpinCurrentLimit);
    //Must Tune!
    frontLeft.configPeakCurrentLimit(TalonSRXConstants.kCurrentLimit, TalonSRXConstants.kCurrentLimitTimeout);
    frontRight.configPeakCurrentLimit(TalonSRXConstants.kCurrentLimit, TalonSRXConstants.kCurrentLimitTimeout);
    backLeft.configPeakCurrentLimit(TalonSRXConstants.kCurrentLimit, TalonSRXConstants.kCurrentLimitTimeout);
    backRight.configPeakCurrentLimit(TalonSRXConstants.kCurrentLimit, TalonSRXConstants.kCurrentLimitTimeout);

    System.out.println("Motors Configured!"); 
  }

  // public void setJoystickDrive(){
  //   activeController = "Joystick";
  //   updateSmartDashboard(true);
  // }

  public void DriveArcade(double xSpeed, double ySpeed) {
    drive.arcadeDrive(xSpeed, ySpeed);
    SmartDashboard.putNumber("xSpeed" , xSpeed); 
    SmartDashboard.putNumber("ySpeed" , ySpeed);
  }

  public void PushDrive (double speed) {
    leftDriveMotors.set(speed);
    rightDriveMotors.set(speed);
    topLeft.set(-speed);
    topRight.set(speed);


    SmartDashboard.putNumber("pSpeed" , speed);
  }


  public void DriveTank(double left, double right) {
    drive.tankDrive(left, right);
    SmartDashboard.putNumber("xSpeed" , left); 
  }


  public void toggleBrakeMode() { //Unused ATM
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
  }
 
  public void stopDrive() {
    leftMotors.set(0);
    rightMotors.set(0);
  }

  public void stopPushMotors() {
    pushMotors.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateSmartDashboard(false);
  }

  public void updateSmartDashboard(boolean firstRun) {
    if (firstRun) {
      SmartDashboard.putString("TalonSRX Current Limit", TalonSRXConstants.kCurrentLimit + " Amps");
      SmartDashboard.putString("TalonSRX Current Limit Timeout", TalonSRXConstants.kCurrentLimitTimeout + "ms");

      SmartDashboard.putString("SparkMAX Freespin Current Limit", SparkMaxConstants.kFreeSpinCurrentLimit+ " Amps");
      SmartDashboard.putString("SparkMAX Pushing Current Limit", SparkMaxConstants.kPushingCurrentLimit+ " Amps");

      SmartDashboard.putString("Active Robot", "Pushy Bot | KAREN");
    }

    SmartDashboard.putNumber("FL %", frontLeft.getMotorOutputPercent());
    SmartDashboard.putNumber("FL Volts", frontLeft.getMotorOutputVoltage());
    SmartDashboard.putNumber("FL Amps", frontLeft.getStatorCurrent());

    SmartDashboard.putNumber("FR %", frontRight.getMotorOutputPercent());
    SmartDashboard.putNumber("FR Volts", frontRight.getMotorOutputVoltage());
    SmartDashboard.putNumber("FR Amps", frontRight.getStatorCurrent());

    SmartDashboard.putNumber("BL %", backLeft.getMotorOutputPercent());
    SmartDashboard.putNumber("BL Volts", backLeft.getMotorOutputVoltage());
    SmartDashboard.putNumber("BL Amps", backLeft.getStatorCurrent());
    
    SmartDashboard.putNumber("BR %", backRight.getMotorOutputPercent());
    SmartDashboard.putNumber("BR Volts", backRight.getMotorOutputVoltage());
    SmartDashboard.putNumber("BR Amps", backRight.getStatorCurrent());

    SmartDashboard.putNumber("TL/FLYL %", topLeft.getAppliedOutput());
    SmartDashboard.putNumber("TL/FLYL Amps" , topLeft.getOutputCurrent());
    SmartDashboard.putString("FLYL Volts/TL IdleMode" , "" + topLeft.getIdleMode());

    SmartDashboard.putNumber("TR/FLYR %", topRight.getAppliedOutput());
    SmartDashboard.putNumber("TR/FLYR Amps" , topRight.getOutputCurrent());
    SmartDashboard.putString("FLYR Volts/TL IdleMode" , "" + topRight.getIdleMode());

    SmartDashboard.updateValues(); 
  }


  


  
 


}
