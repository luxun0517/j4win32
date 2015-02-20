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
 * @Description: Win32�в������ڵ��࣬����ʹ��������User32�⣬һ����Jna�ģ���ΪJna�����ݲ��������Ի��Լ�������һЩ��ownlib����
 * @author vasquez
 * @contact liqianglau@outlook.com
 * @date 2014��12��28�� ����11:15:52
 */
public class Window
{

	private static User32 user32 = User32.INSTANCE;
	private static info.stupy.j4win32.win32.ownlib.User32 ownUser32 = info.stupy.j4win32.win32.ownlib.User32.INSTANCE;

	// ȡ���ھ��
	public static HWND getHwndByTitle(String title)
	{
		return user32.FindWindow(null, title);
	}
	public static HWND getHwndByClassName(String className)
	{
		return user32.FindWindow(className, null);
	}
	
	// ȡ���ھ���
	public static RECT getRect(HWND hwnd)
	{
		RECT r = new RECT();
		user32.GetWindowRect(hwnd, r);
		return r;
	}

	// ���ڽ�ͼ
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
	
	// ��ȡ���ڱ���
	public static String getWindowTitle(HWND hwnd)
	{
		char[] content = new char[1024];
		int length = user32.GetWindowText(hwnd, content, 1000);
		return new String(content, 0, length);
	}
	
	// ���ô��ڱ���
	public static int setWindowTitle(int hwnd, String title)
	{
		return ownUser32.SetWindowTextA(hwnd, title);
	}
	
	/**
	 * @Title: postMessage
	 * @Description: ��һ����ϢͶ�ݵ�ָ�����ڵ���Ϣ���С�Ͷ�ݵ���Ϣ����Windows�¼���������еõ��������Ǹ�ʱ�򣬻���ͬͶ�ݵ���Ϣ����ָ�����ڵĴ��ں������ر��ʺ���Щ����Ҫ��������Ĵ�����Ϣ�ķ���
	 * @param @param hwnd ������Ϣ���Ǹ����ڵľ��������ΪHWND_BROADCAST����ʾͶ�ݸ�ϵͳ�е����ж������ڡ�����Ϊ�㣬��ʾͶ��һ���߳���Ϣ���ο�PostThreadMessage��
	 * @param @param msg ��Ϣ��ʶ��
	 * @param @param wParam ��������Ϣ����
	 * @param @param lParam ��������Ϣ����
	 * @return int ����ϢͶ�ݳɹ����򷵻�TRUE�����㣩��������GetLastError
	 * @throws
	 */
	public static void postMessage(HWND hwnd, int msg, WPARAM wParam, LPARAM lParam)
	{
		user32.PostMessage(hwnd, msg, wParam, lParam);
	}
	
	/**
	 * @Title: sendMessage
	 * @Description: ����һ�����ڵĴ��ں�������һ����Ϣ�����Ǹ����ڡ�������Ϣ������ϣ�����ú������᷵�ء�
	 * @param @param hwnd Ҫ������Ϣ���Ǹ����ڵľ��
	 * @param @param msg ��Ϣ�ı�ʶ��
	 * @param @param wParam ����ȡ������Ϣ
	 * @param @param lParam ����ȡ������Ϣ
	 * @return int �ɾ������Ϣ���� 
	 * @throws
	 */
	public static int sendMessage(int hwnd, int msg, int wParam, int lParam)
	{
		return ownUser32.SendMessageA(hwnd, msg, wParam, lParam);
	}
}
