package info.stupy.j4win32;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class ImageButton extends JButton
{
	private static final long serialVersionUID = -6330005615069045806L;

	public ImageButton(String fileName, int x, int y, int width, int height)
	{
		super(new ImageIcon(ImageHelper.load(fileName)));
		setBorder(null);
		setBounds(x, y, width, height);
	}

}
