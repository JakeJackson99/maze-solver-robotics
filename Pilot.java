import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;

/**
 * <p>
 * <b>Pilot</b> class defines a quicker declaration of the <b>MovePilot</b> and
 * related objects such as <b>PoseProvider</b> and <b>Navigator</b>
 *
 * @see MovePilot
 * @see PoseProvider
 * @see Navigator
 */
public class Pilot {

	static float WHEEL_DIAMETER = 81.6f;
	static float AXLE_OFFSET = 72.0f; // MCREA125
	// static float AXLE_OFFSET = 72.0f; //Windsor
	static float ANGULAR_SPEED = 1020;
	static float LINEAR_SPEED = 1020;

	// define regulated motors from ports
	RegulatedMotor mL;
	RegulatedMotor mR;

	// defining the wheels with each motor and a new chassis
	Wheel wheelLeft;
	Wheel wheelRight;
	Chassis chassis;

	// defining a pilot
	MovePilot movePilot;

	PoseProvider poseProvider;
	PoseProvider altPoseProvider;
	Navigator navigator;

	public Pilot() {

		mL = new EV3LargeRegulatedMotor(MotorPort.D);
		mR = new EV3LargeRegulatedMotor(MotorPort.A);

		wheelLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_OFFSET);
		wheelRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_OFFSET);
		chassis = new WheeledChassis(new Wheel[] { wheelLeft, wheelRight }, WheeledChassis.TYPE_DIFFERENTIAL);

		movePilot = new MovePilot(chassis);
		movePilot.setAngularSpeed(ANGULAR_SPEED);
		movePilot.setLinearSpeed(LINEAR_SPEED);

		poseProvider = new OdometryPoseProvider(movePilot);

		navigator = new Navigator(movePilot, poseProvider);

	}

	public void update() {
		wheelLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_OFFSET);
		wheelRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_OFFSET);
		chassis = new WheeledChassis(new Wheel[] { wheelLeft, wheelRight }, WheeledChassis.TYPE_DIFFERENTIAL);

		movePilot = new MovePilot(chassis);

		poseProvider = new OdometryPoseProvider(movePilot);
		navigator = new Navigator(movePilot, poseProvider);
	}

	public void setMotorsSpeed(int speed) {
		mL.setSpeed(speed);
		mR.setSpeed(speed);
	}

	public void setMotorsSpeed(int speedLeftMotor, int speedRightMotor) {
		mL.setSpeed(speedLeftMotor);
		mR.setSpeed(speedRightMotor);
	}

	public MovePilot getMovePilot() {
		return movePilot;
	}

	public void addToOffset(float value) {
		AXLE_OFFSET += value;
		update();
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public void closeMotors() {
		mL.close();
		mR.close();
	}
}
