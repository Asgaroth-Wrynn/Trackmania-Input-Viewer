package Core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Stream;

import javax.swing.JFrame;

public class DisplaySettings {
	public static final int FRAME_PER_SECONDS = 60;
	public static final int MS_PER_FRAME = 1000 / FRAME_PER_SECONDS;
	public static final int WIDTH = 416;
	public static final int HIGTH = 280;
	
	
	static Properties settings;
	static int[] oldDisplayScreenDefinition = {1920,1080};
	static int displayScreen = 0;
	static int[] positionOnScreen = {744,352};
	static int[] relativePosition = new int[2];
	
	static boolean brokenDSettings;
	static boolean brokenSettings;
	
	
	static void LoadSettings() {
		brokenDSettings = false;
		brokenSettings = false;
		
		Properties defaultSettings = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(new File(System.getProperty("user.dir") + "/Ressources/Settings/DefaultSettings.properties"));
			defaultSettings.load(in);
			
			settings = new Properties(defaultSettings);
		} catch (IOException e) {
			brokenDSettings = true;
			
			File newSettings = new File(System.getProperty("user.dir") + "/Ressources/Settings/DefaultSettings.properties");
			try {
				newSettings.createNewFile();
				
				in = new FileInputStream(new File(System.getProperty("user.dir") + "/Ressources/Settings/DefaultSettings.properties"));
				defaultSettings.load(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			in = new FileInputStream(new File(System.getProperty("user.dir") + "/Ressources/Settings/Settings.properties"));
			settings.load(in);
			
			String rawODSD = settings.getProperty("oldDisplayScreenDefinition");
			String rawDScreen = settings.getProperty("displayScreen");
			String rawPOScreen = settings.getProperty("positionOnScreen");
			
			if (rawODSD != null) {
				oldDisplayScreenDefinition = Stream.of(rawODSD.split(",")).mapToInt(Integer::parseInt).toArray();
			}
			if (rawDScreen != null) {
				displayScreen = Integer.parseInt(rawDScreen);
			}
			if (rawPOScreen != null) {
				positionOnScreen = Stream.of(rawPOScreen.split(",")).mapToInt(Integer::parseInt).toArray();
			}
			
			in.close();
		} catch (IOException e) {
			brokenSettings = true;
			
			File newSettings = new File(System.getProperty("user.dir") + "/Ressources/Settings/Settings.properties");
			try {
				newSettings.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	
	static void SaveSettings(Point framePos) {
		positionOnScreen[0] = framePos.x;
		positionOnScreen[1] = framePos.y;
		
		if (brokenDSettings && brokenSettings) {
			settings = new Properties();
		}
		
		settings.setProperty("oldDisplayScreenDefinition", oldDisplayScreenDefinition[0] + "," + oldDisplayScreenDefinition[1]);
		settings.setProperty("displayScreen", displayScreen + "");
		settings.setProperty("positionOnScreen", positionOnScreen[0] + "," + positionOnScreen[1]);
		
		FileOutputStream out;
		
		if (brokenDSettings) {
			try {				
				out = new FileOutputStream(new File(System.getProperty("user.dir") + "/Ressources/Settings/DefaultSettings.properties"));
				settings.store(out, "---repaired default settings---");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			out = new FileOutputStream(new File(System.getProperty("user.dir") + "/Ressources/Settings/Settings.properties"));
			settings.store(out, "---saved settings---");
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	static Point getRelativePosition(GraphicsDevice[] gDevice, int screenIndex, int frameY) {
		relativePosition[0] = (int) (gDevice[screenIndex].getDefaultConfiguration().getBounds().x
		+ positionOnScreen[0] / (float) (oldDisplayScreenDefinition[0]) * gDevice[screenIndex].getDisplayMode().getWidth());
		
		relativePosition[1] = (int) (frameY
		+ positionOnScreen[1] / (float) (oldDisplayScreenDefinition[1]) * gDevice[screenIndex].getDisplayMode().getHeight());
		
		oldDisplayScreenDefinition[0] = gDevice[screenIndex].getDisplayMode().getWidth();
		oldDisplayScreenDefinition[1] = gDevice[screenIndex].getDisplayMode().getHeight();
		
		return new Point(relativePosition[0], relativePosition[1]);
	}
	
	public static void setOpeningPos(JFrame frame) {
		GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gDevice = gEnvironment.getScreenDevices();
		
		if (displayScreen > -1 && displayScreen < gDevice.length) {
			frame.setLocation(getRelativePosition(gDevice, displayScreen, frame.getY()));
		} else if (gDevice.length > 0) {
			frame.setLocation(getRelativePosition(gDevice, 0, frame.getY()));
		} else {
			throw new RuntimeException("No Screen Found");
		}
	}
}