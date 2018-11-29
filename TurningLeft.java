import lejos.robotics.subsumption.Behavior;

public class TurningLeft implements Behavior {

	@Override
	public boolean takeControl() { //turn if space to the right
		if (MazeSolver.moveEnd) { //resets the thing checking if it moved
			MazeSolver.moveEnd = false;
			return false;
		}
		return true;
	}

	@Override
	public void action() { //if activated, turn left
		MazeSolver.eye.pauseScanning();
		MazeSolver.pilot.getMovePilot().rotate(-90);
		MazeSolver.eye.resumeScanning();
		//May need a short delay here so scanner up-to-date
	}

	@Override
	public void suppress() { //look at Traverse
		
	} 
	
	
	
}