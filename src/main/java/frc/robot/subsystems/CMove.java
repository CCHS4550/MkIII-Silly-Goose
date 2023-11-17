package frc.robot.subsystems;

import frc.robot.Helpers.CCSparkMax;
import frc.robot.Helpers.OI;
import frc.robot.Maps.ControlMap;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CMove extends SubsystemBase{

  CCSparkMax spindex = new CCSparkMax("spindexer", "s", 5, MotorType.kBrushless, IdleMode.kCoast, false, -1);
  CCSparkMax pitch = new CCSparkMax("pitch", "p", 6, MotorType.kBrushless, IdleMode.kBrake, false, -1);
  CCSparkMax actuator = new CCSparkMax("linearactuator", "up", 7, MotorType.kBrushed, IdleMode.kBrake, true, -1);
  

  public void spinmove(){
    spindex.set(OI.dPadAng(0) > 0 ? Math.cos(Math.toRadians((OI.dPadAng(0) + 270) % 360)) * 0.2 : 0);
  }

  public void setYaw(){
    if (OI.button(0, ControlMap.A_BUTTON) == true) {
        pitch.set(OI.button(0, ControlMap.A_BUTTON) ? -0.1 : 0);
    } else if (OI.button(0, ControlMap.Y_BUTTON) == true) {
        pitch.set(OI.button(0, ControlMap.Y_BUTTON) ? 0.05 : 0);
    } else {
      pitch.set(0);
    } 
  }

  public void setActuator(){
    if(OI.dPad(0, ControlMap.DPAD_UP) == true) {
        actuator.set(0.5);
      } else if(OI.dPad(0,ControlMap.DPAD_DOWN) == true) {
        actuator.set(-0.4);
      } else {
        actuator.set(0);
      }
  }

}
