import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ivy Mayende, CS10, Spring 2022, Pset 6
 * @author Delia Howley, CS10, Spring 2022, Pset 6
 *
 */
public class Sketch {
    static Map<Integer, Shape> sketchmap; //hashmap to keep all the shapes created
    private  Integer id = 0;      //stores shape ID

    /**
     *  plain constructor
     */
    public Sketch(){

        this.sketchmap = new TreeMap<>();
    }

    /**
     *  Constructor to add the shape without a new ID
     * @param shape
     */
    public synchronized int addShape(int inputID, Shape shape){
        System.out.println("this is add shape " +inputID);
        //putting the shape as the value
        if(inputID != -1){
            sketchmap.put(inputID  , shape);
        }

        else{
            sketchmap.put(id , shape);
            id +=1;
        }

        //incrementing the ID
        return id -1;
    }

    /**
     * Constructor to add a shape at a specific ID
     * @param ID
     * @param shape
     */
    public synchronized void addSpecificShape(Integer ID, Shape shape){
        sketchmap.put(ID, shape);
    }

    /**
     * Method to get the Shape ID
     * @param x
     * @param y
     * @return
     */
    public synchronized int getID(int x, int y) {
       for(int value :sketchmap.keySet()){
           if(sketchmap.get(value).contains(x,y)){
               return value;
           }
       }
       return -1;
    }

    /**
     * method to check whether the cursor is contained within the sketch
     * @param x The x-co ordinate of the cursor
     * @param y The y- co ordinate of the cursor
     * @return
     */
    public synchronized int contains(int x, int y){
        for(int shapeID: sketchmap.keySet()){
            if(sketchmap.get(shapeID).contains(x, y)){
                return shapeID;
            }
        }
        return -1;
    }

    /**
     * Method to retrieve the current sketchmap
     * @return
     */
    public static synchronized Map<Integer, Shape> getSketchmap() {
        return sketchmap;
    }

    /**
     * Method to delete shape from shapemap
     * @param ID
     */
    public synchronized void delete(Integer ID){
        //Checking if the map actually contains the ID being removed
        if(sketchmap.containsKey(ID)){
            //actual deletion
            sketchmap.remove(ID);
        }
        else{
            System.out.println("Shape isn't even there");
        }
    }

    /**
     * Method to move specific shape in sketchmap
     * @param ID   The ID of the specific shape being moved
     * @param dx   x-degree of movement
     * @param dy    y -degree of movement
     */
    public synchronized void moveBy(int ID, int dx, int dy){
        if(sketchmap.containsKey(ID)){
            sketchmap.get(ID).moveBy(dx, dy);
        }
    }

    /**
     * Method to recolor specific shape in sketchmap
     * @param ID
     * @param color
     */
    public synchronized void recolor(int ID, Color color){
        if(sketchmap.containsKey(ID)){ //checking if shape is in the map
            sketchmap.get(ID).setColor(color);
        }
    }

    /**
     * Method to draw shapes within the map
     * @param g
     */
    public synchronized void draw(Graphics g){
        //loop over all IDS
        for(int i : sketchmap.keySet()){
            //Get the shape
            Shape shape = sketchmap.get(i);
            //call draw on them
            shape.draw(g);
        }
    }

}
