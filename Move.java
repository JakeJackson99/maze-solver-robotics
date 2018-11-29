import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Move implements Behavior {

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		if (Scan.distanceRight > MazeSolver.RIGHT_DISTANCE_TO_TURN) {
			Sound.beep();
			MazeSolver.eye.pauseScanning();
			MazeSolver.pilot.getMovePilot().rotate(90);
			MazeSolver.eye.resumeScanning();
			//Auto-move right should work but not tested
			MazeSolver.pilot.getMovePilot().travel(MazeSolver.TRAVEL_DISTANCE);
		} else if (Scan.distanceForward > MazeSolver.TRAVEL_DISTANCE) {
			Sound.twoBeeps();
			MazeSolver.pilot.getMovePilot().travel(MazeSolver.TRAVEL_DISTANCE);
		} else {
			MazeSolver.eye.pauseScanning();
			MazeSolver.pilot.getMovePilot().rotate(-90);
			MazeSolver.eye.resumeScanning();
		}
		Delay.msDelay(2000);
		
	}

	@Override
	public void suppress() {
		// TODO yes
		
	}

}
