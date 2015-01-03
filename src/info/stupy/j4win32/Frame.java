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
		// ���жϵ����λ����ͼ��λ�á�
		// -25 �� -90 ����˼�� �����ǻ����Լ��Ĵ����ϵ�������25,90������Ҫ���ȥ��
		// +LEFT_X �� +Y ����˼�� ��Ϸ������������Ϸ����ľ��룬ҲҪ���ȥ��
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
