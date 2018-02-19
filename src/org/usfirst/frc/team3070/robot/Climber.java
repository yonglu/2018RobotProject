package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Climber implements Pronstants {
	TalonSRX talC;
	DigitalInput limitSwitchSt1;
	DigitalInput limitSwitchSt2;
	DigitalInput limitSwitchSt3;
	DigitalInput limitSwitchSt4;

	/**
	 * Constructor
	 */
	public Climber() {
		talC = new TalonSRX(TALC_PORT);
	}

	/**
	 * Sets motor speed to 1 Make sure it's going the right way
	 */
	public void up() {
		talC.set(ControlMode.PercentOutput, 1);
	}

	/**
	 * Sets motor speed to -1
	 */
	public void down() {
		talC.set(ControlMode.PercentOutput, -1);
	}

	/**
	 * Stops the climber
	 */
	public void stop() {
		talC.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * this method is for the climbing/extending of the robot extender bit
	 */
	public void cTeleop(boolean up, boolean down) {
		// if [up] is pressed, it will extend as much as it can
		if (up) {
			up();
		}
		// else if [down] is pressed, it will retract as much as it can
		else if (down) {
			down();
		}
		// if none of the above are being pressed, the extendy bit wont be moved.
		else {
			stop();
		}
	}
	
	/**
	 * Keep this false probably
	 * 
	 * @param brake
	 *            True for Brake, False for Coast
	 */
	private void setNeutralMode(boolean brake) {
		if (brake) {
			talC.setNeutralMode(NeutralMode.Brake);
		} else {
			talC.setNeutralMode(NeutralMode.Coast);
		}
	}
}
