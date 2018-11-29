import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 *
 */
public class Eye {

	private final int SCAN_SPEED = 740; // scanning speed
	private RegulatedMotor neck; // it's the motor for the Ultrasonic Sensor
	private EV3UltrasonicSensor eye; // The Ultrasonic sensor
	private SampleProvider eyeSensor; // Sample provider
	private Thread scanThread;
	private boolean shouldPauseThread;
	private Position currentPosition;

	/**
	 * Creates a new Eye constructor
	 */
	public Eye() {
		shouldPauseThread = false;
		currentPosition = Position.FORWARD;
		neck = new EV3MediumRegulatedMotor(MotorPort.C);
		neck.setSpeed(SCAN_SPEED);
		eye = new EV3UltrasonicSensor(SensorPort.S1);
		eyeSensor = eye.getDistanceMode();
		scanThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					if (!shouldPauseThread)
						scanEnvironment();

				}

			}
		});
	}

	/**
	 * Move the neck to a specified position
	 * 
	 * @param toGoPosition
	 */
	public void moveToPosition(Position toGoPosition) {
		int rotation = 0;
		switch (toGoPosition) {
		case FORWARD:
			rotation = 0;
			break;
		case RIGHT:
			rotation = 90;
			break;
		default:
			break;
		}
		currentPosition = toGoPosition;
		neck.rotateTo(rotation);
	}

	/**
	 * Return the distance value from the eye
	 * 
	 * @return
	 */
	public float getSensorValue() {
		float[] sample = new float[1];
		eyeSensor.fetchSample(sample, 0);
		return sample[0] * 100;
	}

	/**
	 * Starts a new thread, scanning continuously for the Scan distance values
	 */
	public void startScanningThread() {
		scanThread.setDaemon(true);
		scanThread.start();
	}

	public void pauseScanning() {
		shouldPauseThread = true;
	}

	public void resumeScanning() {
		shouldPauseThread = false;
	}

	/**
	 * Scans the environment once
	 */
	public void scanEnvironment() {
		if (currentPosition == Position.RIGHT) {
			Scan.distanceRight = getSensorValue();
		} else {
			Scan.distanceForward = getSensorValue();
		}
		moveToPosition(Position.FORWARD);
		Scan.distanceForward = getSensorValue();
		moveToPosition(Position.RIGHT);
		Scan.distanceRight = getSensorValue();

	}

	/**
	 * Close the sensor and motor
	 */
	public void close() {
		eye.close();
		neck.close();
	}

	/**
	 * Enumerator specifying the move position
	 * 
	 * @author ZFAC196
	 *
	 */
	enum Position {
		FORWARD, RIGHT
	}
}

/**
 * TODO later
 */
class EyeBehaviour implements Behavior {

	@Override
	public boolean takeControl() {

		return false;
	}

	@Override
	public void action() {

	}

	@Override
	public void suppress() {

	}

}

/**
 * Scan class, contains the updated distance of the sensor
 */
class Scan {
	static float distanceRight;
	static float distanceForward;
}