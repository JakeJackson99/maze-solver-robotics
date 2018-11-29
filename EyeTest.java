import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class EyeTest {

	public static void main(String[] args) {
		final Eye eye = new Eye();
		eye.startScanningThread();

		while (Button.ENTER.isUp()) {
			LCD.drawString("FW:" + Scan.distanceForward, 0, 0);
			LCD.drawString("R:" + Scan.distanceRight, 0, 1);
			Delay.msDelay(100);
		}

	}

}
