import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 * @author ivymayende , CS10, Spring 2022, PSET 6, filled in other methods
 * @author Delia Howley, CS10, Spring 2022, PSET 6, Filled in other methods
 */
public class Polyline implements Shape {
	protected ArrayList<Point> points;  //keeps track of all points in the polyline

	private Color color;    //color of the whole polyline

	/**
	 * //constructor to take in just a point and a color
	 * @param p    the single point being added
	 * @param color   color of polyline/point
	 */
	public Polyline(Point p, Color color){
		this.points = new ArrayList<Point>();
		this.color = color;
		points.add(p);
	}

	/**
	 * constructor to take in an array of points and color
	 */

	public Polyline(ArrayList<Point> pointlist, Color color){
		this.points = pointlist;
		this.color = color;

	}

	/**
	 * Method to move the polyline
	 * @param dx the change in x direction
	 * @param dy the change in y direction
	 */
	@Override
	public void moveBy(int dx, int dy) {
		//loop through all the points in the arraylist and change the x and y of each
		for(Point point: points){
			point.x += dx;
			point.y += dy;
		}
	}

	/**
	 * Method to get the current color of the polyline
	 * @return
	 */
	@Override
	public Color getColor() {

		return color;
	}

	/**
	 * Method to set the polyline color
	 * @param color The shape's color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
		System.out.println(" my color is" + color);

	}

	/**
	 * Method to check if the clicked region is within the polyline, using formula from Segment
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	public boolean contains(int x, int y) {

		for (int a = 0; a < points.size() -1 ; a+=2) {

			if(Segment.pointToSegmentDistance(x, y, (int) points.get(a).getX(), (int) points.get(a).getY(), (int) points.get(a + 1).getX(), (int) points.get(a + 1).getY()) <= 10){
				return true;
			}
		}
		return false;
	}
	/**
	 * Method to draw the dots in the polyline
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		//loop through each point in the list and draws a line connecting each in order
		g.setColor(color);
		for(int i = 0; i < points.size()-1; i ++){
			g.drawLine(points.get(i).x,points.get(i).y, points.get(i+1).x, points.get(i+1).y);
		}
	}

	/**
	 * Method to add a point to the arraylist of points
	 * @param p
	 */
	public void addPoint(Point p){

		points.add(p);
	}

	/**
	 *Method to generate a string containing polyline information
	 * @return
	 */
	@Override
	public String toString() {
		String polyline = "freehand ";

		for(Point point : points){
			polyline +=  point.x + " " + point.y+ " ";
		}
		return (polyline + color.getRGB());
	}
}
