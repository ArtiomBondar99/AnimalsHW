package Animals;
import Graphics.*;

import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Mobility.Point;
import Olympics.Medal;

import java.awt.image.BufferedImage;

/**
 * Represents a terrestrial animal, which is a type of animal that lives on land.
 */
public class TerrestrialAnimals extends Animal implements ITerrestrialAnimal
{
    private int noLegs;

    /**
     * Constructs a new Terrestrial Animal with the specified attributes.
     *
     * @param name         The name of the terrestrial animal.
     * @param gender       The gender of the terrestrial animal (Male, Female, Hermaphrodite).
     * @param weight       The weight of the terrestrial animal in kilograms.
     * @param speed        The speed of the terrestrial animal in kilometers per hour.
     * @param medals       The medals awarded to the terrestrial animal.
     * @param location     The location coordinates of the terrestrial animal.
     * @param id           The ID of the terrestrial animal.
     * @param orien        The orientation of the terrestrial animal.
     * @param maxEnergy    The maximum energy of the terrestrial animal.
     * @param energyPerMeter The energy consumption per meter of movement.
     * @param pan          The competition panel where the animal is displayed.
     * @param img1         The image representing the animal facing East.
     * @param img2         The image representing the animal facing South.
     * @param img3         The image representing the animal facing West.
     * @param img4         The image representing the animal facing North.
     * @param totalDistance The total distance traveled by the animal.
     * @param noLegs       The number of legs of the terrestrial animal.
     */
    public TerrestrialAnimals(String name, Gender gender, double weight, double speed, Medal[] medals, Point location,  int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,double totalDistance, int noLegs)
    {
        super(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,img2,img3,img4);
        this.noLegs = noLegs;
    }

    /**
     * Constructs a new Terrestrial Animal with default attributes.
     *
     */
    public TerrestrialAnimals()
    {
        super();
        this.setLocation(new Point(0,20));
        this.noLegs = 0;
    }
    /**
     * Returns a string representation of the terrestrial animal, focusing on its number of legs.
     *
     * @return A string representation of the terrestrial animal.
     */

    @Override
    public String toString() {
        return "Terrestrial Animals " + super.toString() + "|noLegs: " + noLegs + "}";
    }
    /**
     * Returns an empty string as terrestrial animals do not necessarily make a sound.
     *
     * @return An empty string.
     */
    @Override
    protected String getSound()
    {
        return "";
    }
    /**
     * Compares this terrestrial animal to the specified object. The result is true if and only if the argument
     * is not null and is a TerrestrialAnimals object that has the same number of legs as this terrestrial animal.
     *
     * @param obj The object to compare this terrestrial animal against.
     * @return True if the given object represents a TerrestrialAnimals with the same number of legs, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof TerrestrialAnimals))
            return false;
        TerrestrialAnimals other = (TerrestrialAnimals) obj;
        return noLegs == other.noLegs && super.equals(obj);
    }
    /**
     * Gets the number of legs of the terrestrial animal.
     *
     * @return The number of legs of the terrestrial animal.
     */

    public int getNoLegs()
    {
        return noLegs;
    }
    /**
     * Sets the number of legs of the terrestrial animal.
     *
     * @param noLegs The number of legs to set for the terrestrial animal.
     * @return True if the number of legs was set successfully, false otherwise.
     */


    public boolean setNoLegs(int noLegs)
    {
        if (noLegs < 0)
            return false;
        this.noLegs = noLegs;
        return true;
    }

    /**
     * Returns the type of the animal.
     *
     * @return The type of the animal.
     */
    @Override
    public String getType()
    {
        return super.getType();
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



    /**
     * Sets the number of legs of the terrestrial animal.
     *
     * @param noLegs The number of legs to set.
     */
    @Override
    public void NoLegs(int noLegs) {
        this.noLegs = noLegs;
    }


    /**
     * Returns the category of the animal.
     *
     * @return The category of the animal.
     */
    public String animalCategory() {
        return "Terrestrial";
    }


    /**
     * Loads images for the animal.
     *
     * @param fileName The name of the file to load images from.
     */
    @Override
    public void loadImages(String fileName){
        super.loadImages(fileName);
    }




}



