package Libraries;

import java.time.Instant;
import java.time.Duration;

import Core.DisplaySettings;

public class Timer {

	private static Instant startOfLoop;
	
	public static void waitToMaintainConstantFPS() {
		int durationLoopMs = (int) Duration.between(startOfLoop, Instant.now()).toMillis();
		int timeToPauseMs = DisplaySettings.MS_PER_FRAME - durationLoopMs;

		if (timeToPauseMs > 0) {
			try {
				Thread.sleep(timeToPauseMs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean print = false;
			if (print) {
				System.out.println("Warning, your main loop took " + durationLoopMs + "ms to run (max for "
						+ DisplaySettings.FRAME_PER_SECONDS + "fps is " + DisplaySettings.MS_PER_FRAME + ")");
			}
		}
	}

	/*
	 * Initialize the timer.
	 */
	public static void beginTimer() {
		startOfLoop = Instant.now();
	}
}