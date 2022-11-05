// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class TurnRight extends CommandBase {
  private final RomiDrivetrain s_drivetrain;
  /** Creates a new TurnRight. */
  public TurnRight(RomiDrivetrain s_drivetrain) {
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
    s_drivetrain.turnRight();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return s_drivetrain.getGyro() <= 270;
    return s_drivetrain.getGyro() >= 55 && s_drivetrain.getGyro() <= 80;
  }
}
