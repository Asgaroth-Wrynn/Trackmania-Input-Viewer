package Core;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Libraries.KeyboardHook;
import Libraries.Sprites;
import Libraries.Timer;




/*
 * Trackmania Input Viewer is being developed by Asgaroth Wrynn (https://github.com/Asgaroth-Wrynn)
 * 
 * this application may be used by anyone
 * 
 * this application may be modified or reused in a reused in a distinct project, whole or partially, as long as the original author is credited
 */





public class Main {
	public static void main(String[] args) {
		Sprites.SpritesLoad();
		display();

		while (true) {
			nextStep();
		}
	}

	private static void nextStep() {
		Timer.beginTimer();
		Update.updateDisplay();
		Timer.waitToMaintainConstantFPS();
	}

	public static JFrame frame;

	private static void display() {
		Image appIcon = null;
		try {
			appIcon = ImageIO.read(Main.class.getResource("/Sprites/Icon-Public.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.setTitle("Trackmania Inputs Viewer");
		frame.setSize(DisplaySettings.WIDTH + 16, DisplaySettings.HIGTH + 39);
		frame.setResizable(false);
		frame.setIconImage(appIcon);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);

		KeyboardHook kbhook = new KeyboardHook();
		new Thread(kbhook).start();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				kbhook.setHookOff();
			}
		});
	}
}