package Libraries;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
	public static BufferedImage upOff, upOn, downOff, downOn, rightOff, rightOn, leftOff, leftOn;

	public static BufferedImage emptyFill;

	public static void SpritesLoad() {
		try {
			upOff = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Up-Off.png"));
		} catch (IOException eUf) {
			// TODO Auto-generated catch block
			eUf.printStackTrace();
		}

		try {
			upOn = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Up-On.png"));
		} catch (IOException eUn) {
			// TODO Auto-generated catch block
			eUn.printStackTrace();
		}

		try {
			downOff = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Down-Off.png"));
		} catch (IOException eDf) {
			// TODO Auto-generated catch block
			eDf.printStackTrace();
		}

		try {
			downOn = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Down-On.png"));
		} catch (IOException eDn) {
			// TODO Auto-generated catch block
			eDn.printStackTrace();
		}

		try {
			rightOff = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Right-Off.png"));
		} catch (IOException eRf) {
			// TODO Auto-generated catch block
			eRf.printStackTrace();
		}

		try {
			rightOn = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Right-On.png"));
		} catch (IOException eRn) {
			// TODO Auto-generated catch block
			eRn.printStackTrace();
		}

		try {
			leftOff = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Left-Off.png"));
		} catch (IOException eLf) {
			// TODO Auto-generated catch block
			eLf.printStackTrace();
		}

		try {
			leftOn = ImageIO.read(Sprites.class.getResource("/Sprites/Keyboard-Arrows---Left-On.png"));
		} catch (IOException eLn) {
			// TODO Auto-generated catch block
			eLn.printStackTrace();
		}

		try {
			emptyFill = ImageIO.read(Sprites.class.getResource("/Sprites/empty-fill.png"));
		} catch (IOException eLn) {
			// TODO Auto-generated catch block
			eLn.printStackTrace();
		}
	}
}