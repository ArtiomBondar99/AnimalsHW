package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Alligator class represents an alligator that can both swim and move on land.
 * It extends the Animal class and implements the IReptile, ITerrestrialAnimal, and IWaterAnimal interfaces.
 */
public class Alligator extends Animal implements IReptile,ITerrestrialAnimal,IWaterAnimal
{
    private String AreaOfLiving;
    private WaterAnimal waterAnimal;
    private TerrestrialAnimals terrestrialAnimals;
    private String type ;

    /**
     * Constructor for creating an Alligator object with specific attributes.
     *
     * @param name          the name of the alligator
     * @param gender        the gender of the alligator
     * @param weight        the weight of the alligator
     * @param speed         the speed of the alligator
     * @param medals        the medals won by the alligator
     * @param location      the location of the alligator
     * @param id            the ID of the alligator
     * @param orien         the orientation of the alligator
     * @param maxEnergy     the maximum energy of the alligator
     * @param energyPerMeter the energy consumption per meter for the alligator
     * @param pan           the competition panel associated with the alligator
     * @param img1          the image of the alligator in state 1
     * @param img2          the image of the alligator in state 2
     * @param img3          the image of the alligator in state 3
     * @param img4          the image of the alligator in state 4
     * @param AreaOfLiving  the area where the alligator lives
     * @param diveDept      the depth the alligator can dive
     */
    public Alligator(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, String AreaOfLiving, double diveDept)
    {

        super(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,img2,img3,img4);

        this.AreaOfLiving = AreaOfLiving;
        this.waterAnimal = new  WaterAnimal(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,0.0,diveDept);
        this.terrestrialAnimals = new TerrestrialAnimals(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,img2,img3,img4,0.0,4);

    }

    /**
     * Default constructor for creating an Alligator object.
     *
     */
    public Alligator()
    {
        super();
        this.AreaOfLiving = "";
        this.waterAnimal = null;
        this.terrestrialAnimals = null;
    }

    @Override
    public void Dive(double depth)
    {
        waterAnimal.Dive(depth);
    }


    @Override
    public void NoLegs(int noLegs)
    {
       terrestrialAnimals.getNoLegs();
    }

    /**
     * Attempts to increase the speed of the object.
     * If the resulting speed exceeds the maximum speed limit,
     * the speed is set to the maximum speed.
     *
     * @param speed the amount to increase the current speed by
     * @return true if the speed was successfully increased without exceeding the maximum speed,
     *         false if the speed was set to the maximum speed
     */
    @Override
    public boolean speedUp(int speed)
    {
        if (getSpeed() + speed > MAX_SPEED)
        {
            setSpeed(MAX_SPEED);
            return false;
        }
        setSpeed(MAX_SPEED + speed);
        return true;
    }
    /**
     * Sets the speed of the object.
     * If the specified speed is less than zero, the speed is set to zero.
     *
     * @param speed the speed to set
     * @return true if the speed was set successfully, false if the speed was set to zero
     */
    protected boolean setSpeed(double speed)
    {
        if (speed<0)
        {
            this.setSpeed(0);
            return false;
        }
        this.setSpeed(speed);
        return true;
    }
    /**
     * Returns the sound the alligator makes.
     *
     * @return the sound of the alligator
     */
    @Override
    public String getSound()
    {
        return "Roar";
    }
    /**
     * Gets the area where the alligator lives.
     *
     * @return the area of living
     */



    /**
     * Compares this alligator to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Alligator))
            return false;
        Alligator other = (Alligator) obj;
        return AreaOfLiving.equals(other.AreaOfLiving) && super.equals(obj);
    }

    /**
     * Returns a string representation of the alligator.
     *
     * @return a string representation of the alligator
     */
    @Override
    public String toString()
    {

        return "Alligator is: " +super.toString() + "Area Of Living: " + AreaOfLiving+'}' ;
    }


    @Override
    public String getAnimaleName() {
        return "";
    }



    @Override
    public String getType(){return "Alligator";}

}
