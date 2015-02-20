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
	// ģ����Ҫ��,����ֱ�Ӹ�ȫ�ֱ�����

	public static int LEFT_X = 5;
	public static int RIGHT_X = 514;
	public static int Y = 190;
	public static int WIDTH = 500;
	public static int HEIGHT = 450;
	public static HWND hwnd;

	// �Ҳ�
	public static BufferedImage exec()
	{

		// ȡ���ھ��
		hwnd = Window.getHwndByTitle("������Ҳ�");
		if (hwnd == null)
		{
			System.out.println("[ʧ��]û���ҵ���Ϸ����.");
			return null;
		}

		// ��ͼ
		BufferedImage buffImg = Window.getImage(hwnd);
		if (buffImg == null)
		{
			System.out.println("[ʧ��]��ͼʧ��.");
			return null;
		}

		// ͼ�����
		MemoryImage mImg = new MemoryImage(buffImg);
		// �õ���Ϸ��,��������ͼ��
		MemoryImage left = mImg.copy(LEFT_X, Y, WIDTH, HEIGHT);
		MemoryImage right = mImg.copy(RIGHT_X, Y, WIDTH, HEIGHT);

		// �Ƚϲ�ͬ��ɫֵ.������Ϊ��ɫ(��ȻҲ���Ը�������ɫ)
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

		// ����BufferedImage,������ʾ�����ǵ�swing������
		return left.getBufferedImage();
	}

}
