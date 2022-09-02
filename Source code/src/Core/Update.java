package Core;

import Libraries.KeyboardHook;
import Libraries.Sprites;

public class Update {
	private static Button up = new Button(144, 8, Sprites.upOn, Sprites.upOff);
	private static Button down = new Button(144, 144, Sprites.downOn, Sprites.downOff);
	private static Button right = new Button(280, 144, Sprites.rightOn, Sprites.rightOff);
	private static Button left = new Button(8, 144, Sprites.leftOn, Sprites.leftOff);
	@SuppressWarnings("unused")
	private static Button fill = new Button(8, 8, Sprites.emptyFill, Sprites.emptyFill);

	public static boolean[] keyPress = new boolean[4];

	public static void updateDisplay() {
		inputs = keyCheck();

		if (keyChange) {
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i] != null) {
					inputs[i].drawButton(keyPress[i]);
				}
			}
		}
	}

	public static boolean keyChange = false;

	static Button[] inputs = new Button[4];

	public static Button[] keyCheck() {
		inputs = new Button[4];
		keyChange = false;

		if (KeyboardHook.upPressed) {
			keyPress[0] = true;
			keyChange = true;

			inputs[0] = up;
		} else if (!KeyboardHook.upPressed) {
			keyPress[0] = false;
			keyChange = true;

			inputs[0] = up;
		}

		if (KeyboardHook.downPressed || KeyboardHook.spacePressed) {
			keyPress[1] = true;
			keyChange = true;

			inputs[1] = down;
		} else if (!(KeyboardHook.downPressed || KeyboardHook.spacePressed)) {
			keyPress[1] = false;
			keyChange = true;

			inputs[1] = down;
		}

		if (KeyboardHook.rightPressed) {
			keyPress[2] = true;
			keyChange = true;

			inputs[2] = right;
		} else if (!KeyboardHook.rightPressed) {
			keyPress[2] = false;
			keyChange = true;

			inputs[2] = right;
		}

		if (KeyboardHook.leftPressed) {
			keyPress[3] = true;
			keyChange = true;

			inputs[3] = left;
		} else if (!KeyboardHook.leftPressed) {
			keyPress[3] = false;
			keyChange = true;

			inputs[3] = left;
		}

		return inputs;
	}
}