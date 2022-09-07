package Libraries;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinUser;

public class KeyboardHook implements Runnable {
	public static boolean upPressed, downPressed, rightPressed, leftPressed, spacePressed;
	private int[] lastKey = { 0, 1 };
	private WinUser.HHOOK hhk;

	// Hook callback function
	private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
		@Override
		public LRESULT callback(int nCode, WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
			// Output key value and key time
			if (nCode >= 0) {

				/*
				 * up:38 down:40 right:39 left:37 space:32
				 */

				if (event.vkCode == 38) {
					if (event.flags == 1) {
						upPressed = true;
					} else {
						upPressed = false;
					}
				}

				if (event.vkCode == 40) {
					if (event.flags == 1) {
						downPressed = true;
					} else {
						downPressed = false;
					}
				}

				if (event.vkCode == 39) {
					if (event.flags == 1) {
						rightPressed = true;
					} else {
						rightPressed = false;
					}
				}

				if (event.vkCode == 37) {
					if (event.flags == 1) {
						leftPressed = true;
					} else {
						leftPressed = false;
					}
				}

				if (event.vkCode == 32) {
					if (event.flags == 0) {
						spacePressed = true;
					} else {
						spacePressed = false;
					}
				}

				lastKey[1] = lastKey[0];
				lastKey[0] = event.vkCode;
			}
			return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
		}
	};

	public void run() {
		setHookOn();
	}

	// install hook
	public void setHookOn() {
		System.out.println("Hook On!");

		HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
		hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);

		int result;
		WinUser.MSG msg = new WinUser.MSG();
		while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
			if (result == -1) {
				setHookOff();
				break;
			} else {
				User32.INSTANCE.TranslateMessage(msg);
				User32.INSTANCE.DispatchMessage(msg);
			}
		}
	}

	// remove the hook and exit
	public void setHookOff() {
		System.out.println("Hook Off!");
		User32.INSTANCE.UnhookWindowsHookEx(hhk);
	}
}
