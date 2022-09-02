package Core;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Button {
	private ImageIcon _buttonSpriteOn, _buttonSpriteOff;
	private JLabel _buttonContainer = new JLabel();

	public Button(int x, int y, BufferedImage _buttonSpriteOn, BufferedImage _buttonSpriteOff) {
		set_buttonSpriteOn(_buttonSpriteOn);
		set_buttonSpriteOff(_buttonSpriteOff);

		_buttonContainer.setBounds(x, y, _buttonSpriteOff.getWidth(), _buttonSpriteOn.getHeight());
		_buttonContainer.setIcon(this._buttonSpriteOff);
		Main.frame.add(_buttonContainer);
	}

	public void drawButton(boolean _keyPress) {
		_buttonContainer.setVisible(false);
		if (_keyPress) {
			// _buttonSpriteOn.getGraphics().drawImage(_buttonSpriteOn, 0, 0, null);
			_buttonContainer.setIcon(_buttonSpriteOn);
		} else {
			// _buttonSpriteOff.getGraphics().drawImage(_buttonSpriteOff, 0, 0, null);
			_buttonContainer.setIcon(_buttonSpriteOff);
		}
		_buttonContainer.setVisible(true);
		// Main.frame.getContentPane().update(_buttonSpriteOn.getGraphics());
	}

	public ImageIcon get_buttonSpriteOn() {
		return _buttonSpriteOn;
	}

	public void set_buttonSpriteOn(BufferedImage _buttonSpriteOn) {
		this._buttonSpriteOn = new ImageIcon(_buttonSpriteOn);
	}

	public ImageIcon get_buttonSpriteOff() {
		return _buttonSpriteOff;
	}

	public void set_buttonSpriteOff(BufferedImage _buttonSpriteOff) {
		this._buttonSpriteOff = new ImageIcon(_buttonSpriteOff);
	}
}