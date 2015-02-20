package info.stupy.j4win32;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class ImageHelper {

	public static Image load(String fileName) 
	{
		try 
		{
			return ImageIO.read(ImageHelper.class.getResource(fileName));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

}
