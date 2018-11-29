import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Claw {
	private RegulatedMotor clawMotor;

	public Claw() {
		clawMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		clawMotor.setSpeed(200);
	}

	public void pickUp() { //maybe use rotateTo here to ensure dumb stuff doesn't happen?
		clawMotor.rotate(-500);
	}

	public void letGoo() {
		clawMotor.rotate(300);
	}

	public RegulatedMotor getMotor() {
		return clawMotor;
	}

	public void close() { 
		clawMotor.close();
	}
}
