package Animals;

import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a water animal that can dive to certain depths.
 */
public class WaterAnimal extends Animal implements IWaterAnimal {
    static final int MAX_DIVE = -800;
    private double diveDept;
    private BufferedImage img ;

    /**
     * Constructs a new Water Animal with the specified attributes.
     *
     * @param name         The name of the water animal.
     * @param gender       The gender of the water animal (Male, Female, Hermaphrodite).
     * @param weight       The weight of the water animal in kilograms.
     * @param speed        The speed of the water animal in kilometers per hour.
     * @param medals       The medals awarded to the water animal.
     * @param location     The location coordinates of the water animal.
     * @param id           The ID of the water animal.
     * @param orien        The orientation of the water animal.
     * @param maxEnergy    The maximum energy of the water animal.
     * @param energyPerMeter The energy consumption per meter of movement.
     * @param pan          The competition panel where the animal is displayed.
     * @param img          The image representing the water animal.
     * @param totalDistance The total distance traveled by the water animal.
     * @param diveDept     The dive depth capability of the water animal.
     */
    public WaterAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id,Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img, double totalDistance, double diveDept)
    {
        super(name, gender, weight, speed, medals, location,id,orien,maxEnergy,energyPerMeter,pan,img,null,null,null);
        this.diveDept = diveDept;
        this.img = img;
    }



    @Override
    public String getType()
    {
        return super.getType();
    }

    /**
     * Constructs a new Water Animal with default attributes.
     *
     */
    public WaterAnimal()
    {
        super();
        this.setLocation(new Point(50,0));
        this.diveDept = 0.0;
        this.img = null;
    }

    /**
     * Returns a string representation of the water animal, focusing on its dive depth capability.
     *
     * @return A string representation of the water animal.
     */
    @Override
    public String toString() {
        return "Water Animal" + super.toString() + "|dive Dept=" + diveDept + '}';
    }
    /**
     * Compares this water animal to the specified object. The result is true if and only if the argument
     * is not null and is a WaterAnimal object that has the same dive depth capability as this water animal.
     *
     * @param obj The object to compare this water animal against.
     * @return True if the given object represents a WaterAnimal with the same dive depth capability, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof WaterAnimal))
            return false;
        WaterAnimal other = (WaterAnimal) obj;
        return this.diveDept==other.diveDept && super.equals(obj);
    }
    /**
     * Returns an empty string as water animals do not necessarily make a sound.
     *
     * @return An empty string.
     */
    @Override
    protected String getSound()
    {
        return "";
    }
    /**
     * Sets the dive depth capability of the water animal.
     *
     * @param diveDept The dive depth to set for the water animal.
     * @return True if the dive depth was set successfully, false otherwise.
     */
    public boolean setDiveDept(double diveDept)
    {
        if (diveDept < MAX_DIVE)
            return false;
        this.diveDept = diveDept;
        return true;
    }
    /**
     * Simulates the action of diving by reducing the dive depth of the water animal by a specified depth.
     *
     * @param depth The depth by which to dive.
     */
    @Override
    public void Dive(double depth)
    {
        if (this.diveDept - depth < MAX_DIVE)
        {
            this.diveDept = MAX_DIVE;
        }
        else {
            this.diveDept -= depth;
        }
    }
    /**
     * Gets the dive depth capability of the water animal.
     *
     * @return The dive depth capability of the water animal.
     */

    public double getDiveDept()
    {
        return diveDept;
    }



    /**
     * Loads images for the water animal from a file.
     *
     * @param fileName The name of the file to load images from.
     */
    @Override
    public void loadImages(String fileName){
        try {
            img = ImageIO.read(new File(fileName + "1.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image for air animal");
        }
    }




    /**
     * Returns the category of the animal.
     *
     * @return The category of the animal.
     */
    public String animalCategory() {
        return "Water";
    }



    /**
     * Returns an empty string as the animal name is not specified.
     *
     * @return An empty string.
     */
    @Override
    public String getAnimaleName() {
        return "";
    }

}
