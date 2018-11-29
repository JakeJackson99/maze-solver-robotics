import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class Turning implements Behavior {

	@Override
	public boolean takeControl() { //turn if space to the right
		return !MazeSolver.moveEnd && (Scan.distanceRight > MazeSolver.RIGHT_DISTANCE_TO_TURN);
	}

	@Override
	public void action() { //if activated, turn right
		MazeSolver.moveEnd = true;
		Sound.beep();
		MazeSolver.eye.pauseScanning();
		MazeSolver.pilot.getMovePilot().rotate(90);
		MazeSolver.eye.resumeScanning();
		//Auto-move right should work but not tested
		MazeSolver.pilot.getMovePilot().travel(MazeSolver.TRAVEL_DISTANCE);
		//May need a short delay here so scanner up-to-date
	}

	@Override
	public void suppress() { //look at Traverse
		
	} 
	
	
	
}