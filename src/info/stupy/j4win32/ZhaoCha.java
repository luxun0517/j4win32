package info.stupy.j4win32;

import info.stupy.j4win32.win32.Window;

import java.awt.image.BufferedImage;

import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class ZhaoCha
{
	// 模拟点击要用,所以直接给全局变量了

	public static int LEFT_X = 5;
	public static int RIGHT_X = 514;
	public static int Y = 190;
	public static int WIDTH = 500;
	public static int HEIGHT = 450;
	public static HWND hwnd;

	// 找茬
	public static BufferedImage exec()
	{

		// 取窗口句柄
		hwnd = Window.getHwndByTitle("大家来找茬");
		if (hwnd == null)
		{
			System.out.println("[失败]没有找到游戏窗口.");
			return null;
		}

		// 截图
		BufferedImage buffImg = Window.getImage(hwnd);
		if (buffImg == null)
		{
			System.out.println("[失败]截图失败.");
			return null;
		}

		// 图像操作
		MemoryImage mImg = new MemoryImage(buffImg);
		// 拿到游戏中,左右两幅图像
		MemoryImage left = mImg.copy(LEFT_X, Y, WIDTH, HEIGHT);
		MemoryImage right = mImg.copy(RIGHT_X, Y, WIDTH, HEIGHT);

		// 比较不同颜色值.并设置为红色(当然也可以给其它颜色)
		for (int j = 0; j < HEIGHT; j++)
		{
			for (int i = 0; i < WIDTH; i++)
			{
				if (left.getRGB(i, j) != right.getRGB(i, j))
				{
					left.setRGB(i, j, 0xff0000);
				}
			}
		}

		// 返回BufferedImage,用于显示到我们的swing窗体中
		return left.getBufferedImage();
	}

}
