import java.awt.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

/**
 * @author Ivy Mayende, CS10, Spring 2022, Pset 6
 * @author Delia Howley, CS10, Spring 2022, Pset 6
 */
public class Messages {
    Sketch sketch;            //Sketch class object
    String [] messages;        //stores the incoming messages

    /**
     * constructor
     * takes in message, splits it and stores components in array
     * @param message
     */
    public Messages(String message){
       this.messages = message.split(" ");
    }

    /**
     * assigns respective parts of the array to either commands,ID, shapes, co-ordinates or color
     * @param sketch
     */
    public void stringAAllocator(Sketch sketch){
       String inputCommand = messages[0].toLowerCase(Locale.ROOT); //assigning the command to be the first part of the string
       int inputID = parseInt(messages[1]); // assigning the ID
        Shape currshape = null;
        System.out.println("Command is: "+inputCommand);


       if(inputCommand.equals("add")){
           String shape = messages[2]; //variable to store the shape in the command

           //for ellipse, creates an Ellipse object and adds that to shape map
           if(shape.equals("ellipse")){
                int shape_x1 = parseInt(messages[3]);
                int shape_y1 = parseInt(messages[4]);
                int shape_x2 = parseInt(messages[5]);
                int shape_y2 = parseInt(messages[6]);
                Color color = new Color(parseInt(messages[7]));
                currshape = new Ellipse(shape_x1, shape_y1, shape_x2, shape_y2, color);

            }

           //for freehand, creates an arraylist of points (polypoints) and adds that to the shape map
            if(shape.equals("freehand")){
              ArrayList<Point> polypoints = new ArrayList<Point>();
              for(int i = 3; i <= messages.length - 2; i += 2){
                  int poly_x = parseInt(messages[i]);
                  int poly_y = parseInt(messages[i+1]);
                  Point point = new Point(poly_x, poly_y);
                  polypoints.add(point);
                }
              Color color = new Color(parseInt(messages[messages.length -1]));
              currshape = new Polyline(polypoints,color);
            }

            //for rectangle, creates a Rectangle object and adds that to shape map
            if(shape.equals("rectangle")){
                int shape_x1 = parseInt(messages[3]);
                int shape_y1 = parseInt(messages[4]);
                int shape_x2 = parseInt(messages[5]);
                int shape_y2 = parseInt(messages[6]);
                Color color = new Color(parseInt(messages[7]));
                currshape = new Rectangle(shape_x1, shape_y1, shape_x2, shape_y2, color);

            }
           //for Segment, creates a Segment object and adds that to shape map
            if(shape.equals("segment")){
                int shape_x1 = parseInt(messages[3]);
                int shape_y1 = parseInt(messages[4]);
                int shape_x2 = parseInt(messages[5]);
                int shape_y2 = parseInt(messages[6]);
                Color color = new Color(parseInt(messages[7]));
                currshape =  new Segment(shape_x1, shape_y1, shape_x2, shape_y2, color);

            }

           int ID = sketch.addShape(inputID, currshape);
           messages[1] = String.valueOf(ID);
             }

       //when command is delete, remove shape from shape map
       else if(inputCommand.equals("delete")){
            sketch.delete(inputID);
        }

        //when command is recolor, calls on sketch method to change color
       else if(inputCommand.equals( "recolor")){
           Color color = new Color(parseInt(messages[messages.length - 1]));
           sketch.recolor(inputID, color);

       }
        //if command is move by, then calls on respective sketch method to move shape
        else if(inputCommand.equals("moveby")){
            sketch.moveBy(inputID, parseInt(messages[2]), parseInt(messages[3]));
       }

        System.out.println("Current sketchmap: "+sketch.getSketchmap());

        }


    @Override
    /**
     * Method to generate a string containing sketch information
     */
    public String toString() {
        String message = "";
        for(String string : messages){
            message += string + " ";
        }

        return message;
    }
}


