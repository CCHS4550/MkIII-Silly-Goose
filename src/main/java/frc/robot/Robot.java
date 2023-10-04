// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ResourceBundle.Control;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Helpers.*;
import frc.robot.Helpers.Combo.*;
import frc.robot.Maps.*;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {

  //private DifferentialDrive driveTrain;

  CCSparkMax left1 = new CCSparkMax("front left", "fl", 4, MotorType.kBrushed, IdleMode.kCoast, false, -1);
  CCSparkMax left2 = new CCSparkMax("back left", "bl", 2, MotorType.kBrushed, IdleMode.kCoast, false, -1);
  MotorControllerGroup leftSide = new MotorControllerGroup(left1, left2);

  CCSparkMax right1 = new CCSparkMax("front right", "fr", 3, MotorType.kBrushed, IdleMode.kCoast, true, -1);
  CCSparkMax right2 = new CCSparkMax("back right", "br", 1, MotorType.kBrushed, IdleMode.kCoast, true, -1);
  MotorControllerGroup rightSide = new MotorControllerGroup(right1, right2);

  CCSparkMax spindex = new CCSparkMax("spindexer", "s", 5, MotorType.kBrushless, IdleMode.kCoast, false, -1);
  CCSparkMax pitch = new CCSparkMax("pitch", "p", 6, MotorType.kBrushless, IdleMode.kBrake, false, -1);
  CCSparkMax actuator = new CCSparkMax("linearactuator", "up", 7, MotorType.kBrushed, IdleMode.kBrake, true, -1);

  DifferentialDrive driveTrain = new DifferentialDrive(leftSide, rightSide);
  Solenoid shoot = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  @Override
  public void robotInit() {
    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    //driveTrain = new DifferentialDrive(leftSide, rightSide);
  }

  @Override
  public void robotPeriodic() {
    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    Timer.tick();
  }

  public void teleopInit(){
    //control.start();
  }

  public double decelTime = .5;
  public double decelTimeFast = 0.1;
  public double decelTimeSlow = 0.1;

  public double velocity = 0;

  public double deltaTime = 0.02;

  @Override
  public void teleopPeriodic() {
    normalController();
  }

  @Override
  public void testPeriodic(){
    left2.set(OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL));
  }

  // Trigger mic = new Trigger();
  // Timer control = new Timer(0.1);
  // double acceptedDelay = 0.25;
  // Combo combo = new Combo(acceptedDelay);

  // String forward = "UL UR";
  // String backward = "DL DR";
  // String left = "UL DL";
  // String right = "UR DR";
  // String clockwise = "UR DL";
  // String cclockwise = "UL DR";
  // String cannonUp = "M UL"; 
  // String cannonDown = "M UR";
  // String stop = "M M";
  // public void dkCombo(){
  //   boolean cont = false;
  //   if(OI.buttonDown(0, DKMap.UP_RIGHT)){
  //     combo.add(new Input(1, "UR"));
  //     control.start();
  //     cont = true;
  //   }
  //   if(OI.buttonDown(0, DKMap.UP_LEFT)){
  //     combo.add(new Input(4, "UL"));
  //     control.start();
  //     cont = true;
  //   }
  //   if(OI.buttonDown(0, DKMap.DOWN_RIGHT)){
  //     combo.add(new Input(2, "DR"));
  //     control.start();
  //     cont = true;
  //   }
  //   if(OI.buttonDown(0, DKMap.DOWN_LEFT)){
  //     combo.add(new Input(3, "DL"));
  //     control.start();
  //     cont = true;
  //   }
  //   // if(mic.trigger(!cont && OI.axis(0, DKMap.MIC) > DKMap.ZERO && control.triggered())){
  //   //   combo.add(new Input(5, "M"));
  //   // }

  //   if(OI.button(0, DKMap.START) || combo.contains(stop)) combo = new Combo(acceptedDelay);
  //   double vert = combo.containsLast(forward, backward, 1);
  //   double rot = combo.containsLast(clockwise, cclockwise, 1);
  //   drive(vert, rot);
  // }

  // void marioKart(){
  //   double turn = 0;
  //   if(Math.abs(OI.axis(1, 3)) > 0.2) turn = OI.axis(1, 3);

  //   double joystick = OI.button(1, WiimoteMap.TWO) ? 1 : (OI.button(1, WiimoteMap.ONE) ? -1 : 0);
  //   decelTime = OI.button(1, WiimoteMap.TWO) ? decelTimeFast : (OI.button(1, WiimoteMap.ONE) ? decelTimeFast : decelTimeSlow);
  //   if(joystick - velocity != 0 && decelTime != 0) velocity += (joystick - velocity) / Math.abs(joystick - velocity) * deltaTime / decelTime;

  //   // Use the joystick X axis for lateral movement, Y axis for forward
  //   // movement, and Z axis for rotation.
  //   drive(joystick, turn);
  // }

  // void wiimote(){
  //   double fwd = -OI.axis(0, WiimoteMap.DPAD_VERTICAL);
  //   if(fwd - velocity != 0 && decelTime != 0) velocity += (fwd - velocity) / Math.abs(fwd - velocity) * deltaTime / decelTime;
  //   double turn = -OI.axis(0, WiimoteMap.DPAD_HORIZONTAL);
  //   drive(-velocity, turn * .3); 
  //   //shoot.set(OI.button(0, WiimoteMap.A) && OI.button(0, WiimoteMap.B));
  // }

  

  // void balanceBoard(){
  //   drive(-OI.axis(0, 1), OI.axis(0, 0));
  // }

  // void cannonAdjWiiMote(){
  //   cannon.set(OI.button(1, WiimoteMap.ONE) ? 0.5 : (OI.button(1, WiimoteMap.TWO) ? -0.5 : 0));
  // }

  // void cannonAdjDDR(){
  //   cannon.set(OI.button(0, DDRMap.MINUS) ? 0.5 : (OI.button(0, DDRMap.PLUS) ? -0.5 : 0));
  // }

  // void DDR(){
  //   double y = OI.button(0, DDRMap.UP) ? 1 : OI.button(0, DDRMap.DOWN) ? -1 : 0;
  //   double turn = OI.button(0, DDRMap.LEFT) ? 1 : OI.button(0, DDRMap.RIGHT) ? -1 : 0;
  //   drive(y, turn);
  //   //shoot.set(OI.button(0, DDRMap.A) && OI.button(0, DDRMap.B));
  // }

  void normalController(){
    drive(
    OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL),
      OI.axis(0, ControlMap.R_JOYSTICK_HORIZONTAL));

    shoot.set((OI.axis(0, ControlMap.RT) + OI.axis(0, ControlMap.LT)) / 2 >= 0.9);

  
  spindex.set(OI.dPadAng(0) > 0 ? Math.cos(Math.toRadians((OI.dPadAng(0) + 270) % 360)) * 0.2 : 0);
   
 // if (OI.buttonDown(0, ControlMap.A_BUTTON) == true) {
   // pitch.set(-1);
//   } else if(OI.buttonDown(0, ControlMap.Y_BUTTON) == true) {
//    pitch.set(1);
//   } else {   
//    pitch.set(0);
//   }

  if (OI.button(0, ControlMap.A_BUTTON) == true) {
      pitch.set(OI.button(0, ControlMap.A_BUTTON) ? -0.03 : 0);
  } else if (OI.button(0, ControlMap.Y_BUTTON) == true) {
      pitch.set(OI.button(0, ControlMap.Y_BUTTON) ? 0.1 : 0);
  } else {
    pitch.set(0.02);
  }
    //actuator.set(OI.dPadAng(0) > -1 ? Math.sin(Math.toRadians((OI.dPadAng(0) + 90) % 360)) * 0.3 : 0);

    if(OI.dPad(0, ControlMap.DPAD_UP) == true) {
      actuator.set(0.5);
    } else if(OI.dPad(0,ControlMap.DPAD_DOWN) == true) {
      actuator.set(-0.4);
    } else {
      actuator.set(0);
    }
  }

  // void joycon(){
  //   double turn = OI.button(0, ControlMap.LB_BUTTON) ? -1 : (OI.button(0, ControlMap.RB_BUTTON) ? 1 : 0);
  //   double fwd = OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL);
  //   turn *= 0.65;
  //   fwd *= 1;
  //   drive(fwd, turn);
  // }


  void drive(double fwd, double turn){
    driveTrain.arcadeDrive(OI.normalize(fwd, -1, 1), OI.normalize(turn, -.5, .5));
  }

}
