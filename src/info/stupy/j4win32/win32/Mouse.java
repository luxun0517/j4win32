package info.stupy.j4win32.win32;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;

public class Mouse
{

	private static User32 user32 = User32.INSTANCE;

	/**
	 * @Title: click
	 * @Description: 在指定窗口中发送鼠标点击
	 * @param @param hwnd 指定的窗口句柄
	 * @param @param x 在窗口中的x坐标
	 * @param @param y 在窗口中的y坐标
	 * @return void 
	 * @throws
	 */
	public static void click(HWND hwnd, int x, int y)
	{
		int v = x + (y << 16);
		user32.PostMessage(hwnd, 513, new WPARAM(1L), new LPARAM(v));
		user32.PostMessage(hwnd, 514, new WPARAM(0L), new LPARAM(v));
	}

}
