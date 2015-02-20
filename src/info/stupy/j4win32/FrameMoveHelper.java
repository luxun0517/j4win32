package info.stupy.j4win32;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

/**
 * @author Vasquez
 * @contact liqianglau@outlook.com
 */
public class FrameMoveHelper {

	private Position start;
	private boolean isPressed;

	public FrameMoveHelper(final JFrame frame) {
		
		start = new Position();
		
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isPressed = true;
				start.setXy(e.getX(), e.getY());
			}

			public void mouseReleased(MouseEvent e) {
				isPressed = false;
			}
		});

		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isPressed) {
					Point point = frame.getLocation();
					frame.setLocation(point.x + e.getX() - start.getX(),
							point.y + e.getY() - start.getY());
				}
			}
		});
	}

}
