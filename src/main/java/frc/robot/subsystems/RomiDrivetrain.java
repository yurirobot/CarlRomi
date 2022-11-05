// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.RomiGyro;

public class RomiDrivetrain extends SubsystemBase {
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

  //Left motor is PWM channel 0 and right motor is PWM channel 1
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);

  //Sets up the left and right encoders
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  //Button is PWM channel 2
  DigitalInput button = new DigitalInput(2);

  //Set Timer
  Timer timer = new Timer();

  //Get Gyro
  private final RomiGyro m_gyro = new RomiGyro();

  //Sets up differential drive controller
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);



  
  /** Creates a new Drivetrain. */
  public RomiDrivetrain() {
    m_rightMotor.setInverted(true);

    //Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public void resetGyro() {
    m_gyro.reset();
  }

  public void arcadeDrive(double xaxisSpeed) {
    m_diffDrive.arcadeDrive(xaxisSpeed, 0);
  }
  
  public int getLeftEncoderCount() {
    return m_leftEncoder.get();
  }

  public int getRightEncoderCount() {
    return m_rightEncoder.get();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

  public double getGyro() {
    return m_gyro.getAngleZ();
  }


  
  public void stop() {
    arcadeDrive(0);
  }
  
  public void move() {
    //while(getAverageDistanceInch() >= 6) {
      m_leftMotor.set(0.5);
      m_rightMotor.set(0.5);
    }

  

  public void turnLeft() {
    
   // m_gyro.reset();
    //(m_gyro.getAngle() <= 90) {
      m_leftMotor.set(-0.5);
      m_rightMotor.set(0.5);
  
    
  }

    public void turnRight() {
      m_leftMotor.set(0.5);
      m_rightMotor.set(-0.5);
    }
  

  public void turnAround() {
    m_gyro.reset();
    while(m_gyro.getAngleX() <= 180) {
      m_leftMotor.set(-0.5);
      m_rightMotor.set(0.5);
    }
  
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
