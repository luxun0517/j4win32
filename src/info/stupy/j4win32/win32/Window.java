package info.stupy.j4win32.win32;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

/**
 * @ClassName: Window
 * @Description: Win32中操作窗口的类，这里使用了两种User32库，一种是Jna的，因为Jna的内容不够，所以还自己补充了一些在ownlib包中
 * @author vasquez
 * @contact liqianglau@outlook.com
 * @date 2014年12月28日 下午11:15:52
 */
public class Window
{

	private static User32 user32 = User32.INSTANCE;
	private static info.stupy.j4win32.win32.ownlib.User32 ownUser32 = info.stupy.j4win32.win32.ownlib.User32.INSTANCE;

	// 取窗口句柄
	public static HWND getHwndByTitle(String title)
	{
		return user32.FindWindow(null, title);
	}
	public static HWND getHwndByClassName(String className)
	{
		return user32.FindWindow(className, null);
	}
	
	// 取窗口矩形
	public static RECT getRect(HWND hwnd)
	{
		RECT r = new RECT();
		user32.GetWindowRect(hwnd, r);
		return r;
	}

	// 窗口截图
	public static BufferedImage getImage(HWND hwnd)
	{
		RECT r = Window.getRect(hwnd);
		Rectangle rg = new Rectangle(r.left, r.top, r.right - r.left, r.bottom - r.top);
		try
		{
			return new Robot().createScreenCapture(rg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	// 获取窗口标题
	public static String getWindowTitle(HWND hwnd)
	{
		char[] content = new char[1024];
		int length = user32.GetWindowText(hwnd, content, 1000);
		return new String(content, 0, length);
	}
	
	// 设置窗口标题
	public static int setWindowTitle(int hwnd, String title)
	{
		return ownUser32.SetWindowTextA(hwnd, title);
	}
	
	/**
	 * @Title: postMessage
	 * @Description: 将一条消息投递到指定窗口的消息队列。投递的消息会在Windows事件处理过程中得到处理。在那个时候，会随同投递的消息调用指定窗口的窗口函数。特别适合那些不需要立即处理的窗口消息的发送
	 * @param @param hwnd 接收消息的那个窗口的句柄。如设为HWND_BROADCAST，表示投递给系统中的所有顶级窗口。如设为零，表示投递一条线程消息（参考PostThreadMessage）
	 * @param @param msg 消息标识符
	 * @param @param wParam 具体由消息决定
	 * @param @param lParam 具体由消息决定
	 * @return int 如消息投递成功，则返回TRUE（非零）。会设置GetLastError
	 * @throws
	 */
	public static void postMessage(HWND hwnd, int msg, WPARAM wParam, LPARAM lParam)
	{
		user32.PostMessage(hwnd, msg, wParam, lParam);
	}
	
	/**
	 * @Title: sendMessage
	 * @Description: 调用一个窗口的窗口函数，将一条消息发给那个窗口。除非消息处理完毕，否则该函数不会返回。
	 * @param @param hwnd 要接收消息的那个窗口的句柄
	 * @param @param msg 消息的标识符
	 * @param @param wParam 具体取决于消息
	 * @param @param lParam 具体取决于消息
	 * @return int 由具体的消息决定 
	 * @throws
	 */
	public static int sendMessage(int hwnd, int msg, int wParam, int lParam)
	{
		return ownUser32.SendMessageA(hwnd, msg, wParam, lParam);
	}
}
