package info.stupy.j4win32;

import java.awt.AWTException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame
{
	private static final long serialVersionUID = 2998107863635690131L;

	public Frame()
	{
		setSize(550, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setContentPane(new Panel());
		new FrameMoveHelper(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				waigua(e);
			}
		});
	}

	protected void waigua(MouseEvent e)
	{
		// 先判断点击的位置是图像位置。
		// -25 和 -90 的意思是 ，我们画在自己的窗体上的坐标是25,90。所以要算回去。
		// +LEFT_X 和 +Y 的意思是 游戏区距离整个游戏窗体的距离，也要算回去。
		if (e.getX() >= 25 && e.getX() <= 25 + ZhaoCha.WIDTH && e.getY() >= 90 && e.getY() <= 90 + ZhaoCha.HEIGHT)
		{
			//Mouse.click(ZhaoCha.hwnd, e.getX() - 25 + ZhaoCha.LEFT_X, e.getY() - 90 + ZhaoCha.Y);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, AWTException, InterruptedException
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Frame().setVisible(true);
			}
		});
	}
}
