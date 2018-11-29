import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class Traverse implements Behavior {

	@Override
	public boolean takeControl() { //turn if space to the right and not turning right
		return !MazeSolver.moveEnd && (Scan.distanceForward > MazeSolver.TRAVEL_DISTANCE);
	}

	@Override
	public void action() { //if activated, turn right
		MazeSolver.moveEnd = true;
		Sound.twoBeeps();
		MazeSolver.pilot.getMovePilot().travel(MazeSolver.TRAVEL_DISTANCE);
		//May need a short delay here so scanner up-to-date
	}

	@Override
	public void suppress() {
		//stop the bot somehow to help with reacting to close wall
		//also in the other turn things
	} 
	
	
	
}