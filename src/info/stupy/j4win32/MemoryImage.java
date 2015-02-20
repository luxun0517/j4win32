package info.stupy.j4win32;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @ClassName: MemoryImage
 * @Description: 图片处理类
 * @author Vasquez
 * @contact liqianglau@outlook.com
 * @date 2014年12月28日 下午2:23:01
 * 
 */
public class MemoryImage
{

	private BufferedImage buffImg;

	/**
	 * 通过宽高 创建图像
	 * 
	 * @param width
	 * @param height
	 */
	public MemoryImage(int width, int height)
	{
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * 通过BufferedImage对象 创建图像
	 * 
	 * @param buffImg
	 */
	public MemoryImage(BufferedImage buffImg)
	{
		this.buffImg = buffImg;
	}

	/**
	 * 通过文件 创建图像
	 * 
	 * @param fileName
	 */
	public MemoryImage(String fileName)
	{
		read(fileName);
	}

	/**
	 * 载入图像
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean read(String fileName)
	{
		try
		{
			buffImg = ImageIO.read(new File(fileName));
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 置图像某点颜色值
	 * 
	 * @param x
	 * @param y
	 * @param rgb
	 */
	public void setRGB(int x, int y, int rgb)
	{
		buffImg.setRGB(x, y, rgb);
	}

	/**
	 * 取图像某点颜色值
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getRGB(int x, int y)
	{
		return buffImg.getRGB(x, y);
	}

	/**
	 * 取图像宽度
	 * 
	 * @return
	 */
	public int getWidth()
	{
		return buffImg.getWidth();
	}

	/**
	 * 取图像高度
	 * 
	 * @return
	 */
	public int getHeight()
	{
		return buffImg.getHeight();
	}

	/**
	 * 写出文件
	 * 
	 * @param fileName 文件名
	 * @return
	 */
	public boolean write(String fileName)
	{
		try
		{
			return ImageIO.write(buffImg, fileName.substring(fileName.lastIndexOf('.') + 1), new File(fileName));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 拷贝图像
	 * 
	 * @return
	 */
	public MemoryImage copy()
	{
		return new MemoryImage(buffImg);
	}

	/**
	 * 拷贝图像矩形区到新图像中
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return 新图像
	 */
	public MemoryImage copy(int x, int y, int width, int height)
	{
		MemoryImage mImg = new MemoryImage(width, height);
		for (int j = 0; j < height; j++)
		{
			for (int i = 0; i < width; i++)
			{
				mImg.setRGB(i, j, buffImg.getRGB(x + i, y + j));
			}
		}
		return mImg;
	}

	/**
	 * 拷贝图像矩形区到另一个图像的指定位置
	 * 
	 * @param dest 目标图像
	 * @param dx 目标x
	 * @param dy 目标y
	 * @param sx 源x
	 * @param sy 源y
	 * @param width 拷贝的宽度
	 * @param height 拷贝的高度
	 * @return
	 */
	public boolean copy(MemoryImage dest, int dx, int dy, int sx, int sy, int width, int height)
	{
		try
		{
			for (int j = 0; j < height; j++)
			{
				for (int i = 0; i < width; i++)
				{
					dest.setRGB(dx + i, dy + j, buffImg.getRGB(sx + i, sy + j));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 拷贝图像矩形区,并写出文件
	 * 
	 * @param fileName 文件名
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean copyToFile(String fileName, int x, int y, int width, int height)
	{
		MemoryImage mImg = new MemoryImage(width, height);
		try
		{
			for (int j = 0; j < height; j++)
			{
				for (int i = 0; i < width; i++)
				{
					mImg.setRGB(i, j, buffImg.getRGB(x + i, y + j));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return mImg.write(fileName);
	}

	/**
	 * 比较两图是否相同
	 * 
	 * @param dest 目标图像
	 * @return
	 */
	public boolean equals(MemoryImage dest)
	{
		if (dest.getWidth() != buffImg.getWidth() || dest.getHeight() != buffImg.getHeight())
		{
			return false;
		}
		int width = dest.getWidth();
		int height = dest.getHeight();
		for (int j = 0; j < height; j++)
		{
			for (int i = 0; i < width; i++)
			{
				if (dest.getRGB(i, j) != buffImg.getRGB(i, j))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 比较两图的某个矩形区是否相同
	 * 
	 * @param dest 目标图像
	 * @param dx 目标x
	 * @param dy 目标y
	 * @param sx 源x
	 * @param sy 源y
	 * @param width 比较的宽度
	 * @param height 比较的高度
	 * @return
	 */
	public boolean equals(MemoryImage dest, int dx, int dy, int sx, int sy, int width, int height)
	{
		try
		{
			for (int j = 0; j < height; j++)
			{
				for (int i = 0; i < width; i++)
				{
					if (dest.getRGB(i + dx, j + dy) != buffImg.getRGB(i + sx, j + sy))
					{
						return false;
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 大图找小图
	 * 
	 * @param subImg 小图
	 * @return 找到返回顶点坐标,失败返回NULL
	 */
	public Point find(MemoryImage subImg)
	{
		int subWidth = subImg.getWidth();
		int subHeight = subImg.getHeight();
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		for (int j = 0; j < height; j++)
		{
			for (int i = 0; i < width; i++)
			{
				if (equals(subImg, 0, 0, i, j, subWidth, subHeight))
				{
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	/**
	 * 取回BufferedImage对象
	 * 
	 * @return
	 */
	public BufferedImage getBufferedImage()
	{
		return buffImg;
	}
}
