import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 * @author Ivy Mayende, CS10, Spring 2022, PSET 6, completed the other methods
 * @author Delia Howley, CS10, Spring 2022, PSET 6, completed the other methods
 *  */

public class Rectangle implements Shape {
	// TODO: YOUR CODE HERE
	private int x1, y1, x2, y2;		// upper left and lower right
	private Color color;            //color of rectangle

	/**
	 *Constructor when only two points are given
	 * @param x1
	 * @param y1
	 * @param color
	 */
	public Rectangle(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}

	/**
	 * Constructor when all four points are given
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2);
		this.color = color;
	}

	/**
	 * Method to move rectangle
	 * @param dx
	 * @param dy
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
	}

	/**
	 * Method to get color of current rectangle
	 * @return
	 */
	@Override
	public Color getColor() {

		return color;
	}

	/**
	 * Method to assign the points given to respective corners of the rectangle
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * Method to set the color of the rectangle
	 * @param color The shape's color
	 */
	@Override
	public void setColor(Color color) {

		this.color = color;
	}

	/**
	 * Method to check whether cursor is within the rectangle
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	public boolean contains(int x, int y) {
		return (x>= x1 && x<=x2 && y>= y1 && y <=y2);
	}

	/**
	 * Method to draw the rectangle using a respective color
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2-x1, y2-y1);

	}

	/**
	 * Method to return a string containing rectangle information
	 * @return
	 */
	public String toString() {
		return "rectangle "+x1+" "+y1+" "+x2+" "+y2+" "+color.getRGB();
	}
}
