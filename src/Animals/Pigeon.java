package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.image.BufferedImage;

public class Pigeon extends AirAnimal
{
    private String family;
    private String type ;

    /**
     * Constructs a new Pigeon with the specified attributes.
     *
     * @param name      The name of the pigeon.
     * @param gender    The gender of the pigeon (Male, Female, Hermaphrodite).
     * @param weight    The weight of the pigeon in kilograms.
     * @param speed     The speed of the pigeon in kilometers per hour.
     * @param medals    The medals awarded to the pigeon.
     * @param location  The location coordinates of the pigeon.
     * @param wingspan  The wingspan of the pigeon in meters.
     * @param family    The family of the pigeon.
     */
    public Pigeon(String name, Gender gender, double weight, double speed, Medal[] medals, Point location,  int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1,double wingspan, String family)
    {
        super(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,0.0,wingspan);
        this.family = family;
    }


    /**
     * Default constructor for creating an Pigeon object.
     *
     */
    public Pigeon()
    {
        super();
        this.family = "Pigeon";
    }
    /**
     * Returns the sound that the pigeon makes.
     *
     * @return The sound of the pigeon.
     */
    @Override
    public String getSound()
    {
        return "Arr-rar-rar-rar-raah";
    }
    /**
     * Gets the family of the pigeon.
     *
     * @return The family of the pigeon.
     */
    public String getFamily(){return family;}
    /**
     * Sets the family of the pigeon.
     *
     * @param family The family to set for the pigeon.
     * @return True if the family was set successfully, false otherwise.
     */
    public boolean setFamily(String family)
    {
        if (family==null)
            return false;
        this.family = family;
        return true;
    }
    /**
     * Returns a string representation of the pigeon.
     *
     * @return A string representation of the pigeon.
     */
    @Override
    public String toString()
    {

        return "Pigeon is " + super.toString() + "family " + family+ '}' ;
    }
    /**
     * Compares this pigeon to the specified object. The result is true if and only if the argument
     * is not null and is a Pigeon object that has the same family as this pigeon.
     *
     * @param obj The object to compare this pigeon against.
     * @return True if the given object represents a Pigeon with the same family, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this==obj)
            return true;
        if (!(obj instanceof Pigeon))
            return false;
        Pigeon other = (Pigeon)obj;
        return family.equals(other.family) && super.equals(other);
    }


    @Override
    public String getType(){return "Pigeon";}

}
