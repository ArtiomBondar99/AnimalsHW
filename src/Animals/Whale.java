package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.image.BufferedImage;
/**
 * Represents a whale, a type of water animal with specific attributes.
 */
public class Whale extends WaterAnimal
{
    private String foodType;
    private BufferedImage whaleImg;
    private String type ;

    /**
     * Constructs a new Whale with the specified attributes.
     *
     * @param name         The name of the whale.
     * @param gender       The gender of the whale (Male, Female, Hermaphrodite).
     * @param weight       The weight of the whale in kilograms.
     * @param speed        The speed of the whale in kilometers per hour.
     * @param medals       The medals awarded to the whale.
     * @param location     The location coordinates of the whale.
     * @param id           The ID of the whale.
     * @param orien        The orientation of the whale.
     * @param maxEnergy    The maximum energy of the whale.
     * @param energyPerMeter The energy consumption per meter of movement.
     * @param pan          The competition panel where the whale is displayed.
     * @param img1         The image representing the whale.
     * @param diveDept     The dive depth capability of the whale.
     * @param totalDistance The total distance traveled by the whale.
     * @param foodType     The type of food that the whale consumes.
     */
    public Whale(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, double diveDept,double totalDistance, String foodType)
    {
        super(name, gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,0.0,diveDept);
        this.foodType = foodType;
    }
    /**
     * Default constructor for creating an Whale object.
     *
     */
    public Whale()
    {
        super();
        this.foodType = "Unknown";
    }


    @Override
    public String getType(){return "Whale";}
    /**
     * Returns the sound produced by the whale.
     *
     * @return The sound "Splash".
     */
    @Override
    public String getSound()
    {
        return "Splash";
    }
    /**
     * Retrieves the type of food that the whale consumes.
     *
     * @return The food type of the whale.
     */
    public String getFoodType() {return foodType;}
    /**
     * Sets the type of food that the whale consumes.
     *
     * @param foodType The food type to set for the whale.
     * @return True if the food type was set successfully, false otherwise.
     */
    public boolean setFoodType(String foodType)
    {
        if (foodType == null)
            return false;
        this.foodType = foodType;
        return true;
    }
    /**
     * Compares this whale to the specified object. The result is true if and only if the argument
     * is not null and is a Whale object that has the same food type as this whale.
     *
     * @param obj The object to compare this whale against.
     * @return True if the given object represents a Whale with the same food type, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Whale))
            return false;
        Whale other = (Whale) obj;
        return foodType.equals(other.foodType);
    }
    /**
     * Returns a string representation of the whale, focusing on its attributes including food type.
     *
     * @return A string representation of the whale.
     */
    @Override
    public String toString() {
        return "Whale is: " + super.toString() + "|foodType: " + foodType + '}';
    }
}
