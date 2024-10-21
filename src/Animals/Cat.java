package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cat extends TerrestrialAnimals
{
    private boolean castrated;
    private String type ;

    /**
     * Constructs a Cat object with the specified attributes.
     *
     * @param name         the name of the cat
     * @param gender       the gender of the cat
     * @param weight       the weight of the cat
     * @param speed        the speed of the cat
     * @param medals       the medals won by the cat
     * @param location     the location of the cat
     * @param id           the ID of the cat
     * @param orien        the orientation of the cat
     * @param maxEnergy    the maximum energy of the cat
     * @param energyPerMeter the energy consumed per meter for the cat
     * @param pan          the competition panel associated with the cat
     * @param img1         the image for the east orientation
     * @param img2         the image for the south orientation
     * @param img3         the image for the west orientation
     * @param img4         the image for the north orientation
     * @param totalDistance the total distance traveled by the cat
     * @param noLegs       the number of legs the cat has
     * @param castrated    whether the cat is castrated
     */
    public Cat(String name, Animal.Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,double totalDistance, int noLegs , boolean castrated)
    {
        super(name,gender,weight,speed,medals,location, id, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4,0.0,4);
        this.castrated=castrated;
    }

    /**
     * Default constructor for creating an Cat object.
     *
     */

    public Cat()
    {
        super();
        this.castrated=false;
    }
    /**
     * Returns the sound the cat makes.
     *
     * @return the sound of the cat
     */
    @Override
    protected String getSound()
    {
        return "Meow";
    }
    /**
     * Checks if the cat is castrated.
     *
     * @return true if the cat is castrated, false otherwise
     */
    public boolean isCastrated()
    {
        return castrated;
    }
    /**
     * Sets the castrated status of the cat.
     *
     * @param castrated the new castrated status
     * @return true if the status was set successfully
     */
    public boolean setCastrated(boolean castrated)
    {
        this.castrated = castrated;
        return true;
    }
    /**
     * Returns a string representation of the cat.
     *
     * @return a string representation of the cat
     */
    @Override
    public String toString()
    {

        return "Cat is:" +"Terrestrial " + super.toString() + "|is castrated: " + castrated + '}';
    }
    /**
     * Compares this cat to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this==obj)
            return true;
        if (!(obj instanceof Cat))
            return false;
        Cat cat = (Cat)obj;
        return this.castrated==cat.castrated && super.equals(cat);
    }





    /**
     * Returns the type of the animal.
     *
     * @return the type of the animal
     */
    @Override
    public String getType(){return "Cat";}


}
