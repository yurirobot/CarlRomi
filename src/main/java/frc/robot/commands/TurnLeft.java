// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class TurnLeft extends CommandBase {
  private final RomiDrivetrain s_drivetrain;
  /** Creates a new TurnLeft. */
  public TurnLeft(RomiDrivetrain s_drivetrain) {
    this.s_drivetrain = s_drivetrain;
    addRequirements(s_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_drivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_drivetrain.turnLeft();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("DEBUG " + s_drivetrain.getGyro());
    return s_drivetrain.getGyro() <= -35 && s_drivetrain.getGyro() >= -60;
  }
}
