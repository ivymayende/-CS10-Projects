import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Handles communication to/from the server for the editor
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author Chris Bailey-Kellogg; overall structure substantially revised Winter 2014
 * @author Travis Peters, Dartmouth CS 10, Winter 2015; remove EditorCommunicatorStandalone (use echo server for testing)
 * @author Ivy Mayende, CS10, Spring 2022, PSET 6, expanded on run and helper methods
 * @author Delia Howley, CS10, Spring 2022, PSET 6, expanded on run and helper methods
 */
public class EditorCommunicator extends Thread {
	private PrintWriter out;		// to server
	private BufferedReader in;		// from server
	protected Editor editor;		// handling communication for

	/**
	 *
	 * Constructor => Establishes connection and in/out pair
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		System.out.println("connecting to " + serverIP + "...");
		try {
			Socket sock = new Socket(serverIP, 4242); //creating the socket
			out = new PrintWriter(sock.getOutputStream(), true); //instantiate printwriter
			in = new BufferedReader(new InputStreamReader(sock.getInputStream())); //instantiate bufferedreader
			System.out.println("...connected");
		}
		catch (IOException e) {
			System.err.println("couldn't connect");
			System.exit(-1);
		}
	}

	/**
	 * Sends message to the server
	 */
	public void send(String msg) {

		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the server
	 */
	public void run() {

		try {
			// Handle messages
			// TODO: YOUR CODE HERE
			String line;            //stores the input from the server
			Messages serverMessage; //message object
			while ((line = in.readLine()) != null) { //while message not null
				System.out.println(line); //printing out the message
				serverMessage = new Messages(line); //creating a new message object from input
				serverMessage.stringAAllocator(editor.getSketch()); //calling on method to assign parts of the string to methods
				editor.repaint();
			}
			out.close();
			in.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			//occurs when server finally hangs up
			System.out.println("server hung up");
		}
	}

	// Send editor requests to the server
	// TODO: YOUR CODE HERE

	/**
	 * Method to send delete requests to server
	 * @param ID the ID of the respective shape
	 */
	public void requestDelete(int ID){

		send("delete " + ID);
	}

	/**
	 * Method to send move by requests to server
	 * @param ID the ID of the respective shape
	 * @param dx how much the shape is moving in x-direction
	 * @param dy how much shape is moving in y-directopn
	 */
	public void requestMoveBy(int ID, int dx, int dy){

		send("moveBy "+ ID + " " + dx + " " + dy);
	}

	/**
	 * Method to send add requests to server
	 * @param shape the respective shape being added
	 */
	public void requestAdd(Shape shape){
		System.out.println(" sending");
		send("add -1 " + shape);
	}

	/**
	 * Method to send recoloring requests to server
	 * @param ID the ID of the respective shape
	 * @param color the new color the respective shape is being changed to
	 */
	public void requestRecolor(int ID, Color color){
		send("recolor " +ID + " " + color.getRGB());
	}

}
