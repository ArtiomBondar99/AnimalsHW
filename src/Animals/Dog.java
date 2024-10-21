package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dog extends TerrestrialAnimals
{
    private String breed;
    private String type ;

    /**
     * Constructs a Dog object with the specified attributes.
     *
     * @param name         the name of the dog
     * @param gender       the gender of the dog
     * @param weight       the weight of the dog
     * @param speed        the speed of the dog
     * @param medals       the medals won by the dog
     * @param location     the location of the dog
     * @param id           the ID of the dog
     * @param orien        the orientation of the dog
     * @param maxEnergy    the maximum energy of the dog
     * @param energyPerMeter the energy consumed per meter for the dog
     * @param pan          the competition panel associated with the dog
     * @param img1         the image for the east orientation
     * @param img2         the image for the south orientation
     * @param img3         the image for the west orientation
     * @param img4         the image for the north orientation
     * @param noLegs       the number of legs the dog has
     * @param totalDistance the total distance traveled by the dog
     * @param breed        the breed of the dog
     */
    public Dog (String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,int noLegs, double totalDistance, String breed)
    {
        super(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1, img2,img3,img4,0.0,4);
        this.breed = breed;
    }


    /**
     * Default constructor for creating an Dog object.
     *
     */

    public Dog()
    {
        super();
        this.breed = "Boxer";
    }
    /**
     * Returns the sound the dog makes.
     *
     * @return the sound of the dog
     */

    @Override
    protected String getSound()
    {
        return "Woof Woof";
    }

    /**
     * Returns a string representation of the dog.
     *
     * @return a string representation of the dog
     */
    @Override
    public String toString()
    {
        return "Dog is: " +"Terretrial " + super.toString() + "breed=" + breed + '}';
    }
    /**
     * Compares this dog to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if (!(obj instanceof Dog))
            return false;
        Dog other = (Dog) obj;
        return breed.equals(other.breed) && super.equals(other);
    }


    /**
     * Returns the type of the animal.
     *
     * @return the type of the animal
     */
    @Override
    public String getType(){return "Dog";}



}
