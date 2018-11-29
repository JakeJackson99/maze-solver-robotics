import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class MazeSolver {

	static public Pilot pilot;
	static public Eye eye;
	final static public float RIGHT_DISTANCE_TO_TURN = 40; //around 15-20ish
	final static public float TRAVEL_DISTANCE = 40;
	
	static public boolean moveEnd = false; //checks if moved on this turn
	
	public static void main(String[] args) {
		pilot = new Pilot();
		eye = new Eye();
		eye.startScanningThread();
		
		Behavior b[] = {new Turning(), new Traverse(), new TurningLeft()};
		Arbitrator a = new Arbitrator(b);
		a.go();
		
		while (Button.ENTER.isUp()) {
			LCD.drawString("FW:" + Scan.distanceForward, 0, 0);
			LCD.drawString("R:" + Scan.distanceRight, 0, 1);
			Delay.msDelay(100);
		}
		
		//moving around depends on moveEnd and blocking call from .travel
		
		//add pick block
		//add release block
		//emergency stop if wall
		//adjust movement to stay centred
	}

}