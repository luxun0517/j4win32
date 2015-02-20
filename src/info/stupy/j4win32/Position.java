package info.stupy.j4win32;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class Position
{

	private int x;
	private int y;

	public Position()
	{
	}

	public void setXy(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

}
