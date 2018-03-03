package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Climber implements Pronstants {
	TalonSRX talC;
	DigitalInput limitSwitch;
	Servo ratchet;
	boolean locked = false;

	/**
	 * Constructor
	 */
	public Climber() {
		talC = new TalonSRX(TALC_PORT);
		ratchet = new Servo(0);
		// Want the motor to be locked in place when not receiving
		talC.setNeutralMode(NeutralMode.Brake);
	}

	public void lock() {
		ratchet.set(LOCKED_ANGLE);
	}

	/**
	 * Sets motor speed to 1 Make sure it's going the right way
	 */
	public void up() {
		talC.set(ControlMode.PercentOutput, CLIMB_SPEED);
	}

	/**
	 * Sets motor speed to -1
	 */
	public void down() {
		talC.set(ControlMode.PercentOutput, -CLIMB_SPEED);
	}

	/**
	 * Stops the climber
	 */
	public void stop() {
		talC.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * Teleop code, run in teleopPeriodic. Recommend directly passing getRawButton()
	 * 
	 * @param up
	 *            If the button for going up is pressed
	 * @param down
	 *            if the button for going down if pressed.
	 * @param lock
	 *            if the button for locking the rachet is pressed.
	 */
	public void cTeleop(boolean up, boolean down, boolean lock) {
		// if [up] is pressed, it will extend as much as it can
		if (up) {
			up();
		}
		// else if [down] is pressed, it will retract as much as it can
		else if (down) {
			down();
			//else if [lock] is pressed, it will lock the rachet;
		} else if(lock) {
			locked = true;
		}
		// if none of the above are being pressed, the extendy bit wont be moved, and
		// will lock in place.
		else {
			stop();
		}
		if(locked) {
			lock();
		}
	}
}
