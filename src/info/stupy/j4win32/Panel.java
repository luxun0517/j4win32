package info.stupy.j4win32;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class Panel extends JPanel
{
	private static final long serialVersionUID = 686655302860830134L;
	private Image bg;
	private Image game;
	private ImageButton iBtn;

	public Panel()
	{
		setLayout(null);
		setSize(550, 600);
		bg = ImageHelper.load("/images/bg.png");
		iBtn = new ImageButton("/images/btn.png", 0, 535, 550, 65);
		iBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				iBtn_mouseClicked(e);
			}
		});
		add(iBtn);
	}

	protected void iBtn_mouseClicked(MouseEvent e)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				game = ZhaoCha.exec();
				System.out.println("ok");
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(bg, 0, 0, null);
		if (game != null)
		{
			g.drawImage(game, 25, 90, null);
		}
	}

}
