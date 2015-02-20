package info.stupy.j4win32.win32.ownlib;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public interface User32 extends StdCallLibrary
{
	User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);

	int SendMessageA(int a, int b, int c, int d);
	
	int SetWindowTextA(int hwnd, String title);
}
