package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Snake extends TerrestrialAnimals implements IReptile
{
    /**
 * Enum representing the poisonous status of the snake.
 */
    public enum Poisonous{low,medium, high}
    private double length;
    private Poisonous poisonous;
    private String type ;

    /**
     * Constructs a new Snake with the specified attributes.
     *
     * @param name      The name of the snake.
     * @param gender    The gender of the snake (Male, Female, Hermaphrodite).
     * @param weight    The weight of the snake in kilograms.
     * @param speed     The speed of the snake in kilometers per hour.
     * @param medals    The medals awarded to the snake.
     * @param location  The location coordinates of the snake.
     * @param length    The length of the snake in meters.
     * @param poisonous The poisonous status of the snake (Venom, nonVenom).
     * @throws Exception If an error occurs during initialization.
     */
    public Snake(String name, Gender gender, double weight, double speed, Medal[] medals, Point location,int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,double totalDistance, int noLegs, double length, Poisonous poisonous)
    {
        super(name,gender,weight,speed,medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,img2,img3,img4,0.0,0);
        this.length = length;
        this.poisonous = poisonous;
    }

    /**
     * Default constructor for creating an Snake object.
     *
     */
    public Snake()
    {
        super();
        this.length = 1.0;
        this.poisonous = Poisonous.medium;
    }
    /**
     * Returns the sound that the snake makes.
     *
     * @return The sound of the snake.
     */


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


    @Override
    public String getType(){return "Snake";}

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

    @Override
    protected String getSound()
    {
        return "ssssssss";
    }
    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake in meters.
     */
    public double getLength()
    {
        return length;
    }
    /**
     * Gets the poisonous status of the snake.
     *
     * @return The poisonous status of the snake (Venom, nonVenom).
     */
    public Poisonous getPoisonous()
    {
        return poisonous;
    }
    /**
     * Sets the poisonous status of the snake.
     *
     * @param poisonous The poisonous status to set for the snake.
     * @return True if the poisonous status was set successfully, false otherwise.
     */
    public boolean setPoisonous(Poisonous poisonous)
    {
        if(poisonous == null)
            return false;
        this.poisonous = poisonous;
        return true;
    }
    /**
     * Returns a string representation of the snake.
     *
     * @return A string representation of the snake.
     */

    @Override
    public String toString()
    {
        return "Snake is:" + "Terretrial" + super.toString() + "|length:" + length + "|poisonous:" + poisonous +'}';
    }
    /**
     * Compares this snake to the specified object. The result is true if and only if the argument
     * is not null and is a Snake object that has the same poisonous status and length as this snake.
     *
     * @param obj The object to compare this snake against.
     * @return True if the given object represents a Snake with the same poisonous status and length, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if(!(obj instanceof Snake))
            return false;
        Snake other = (Snake) obj;
        return poisonous.equals(other.poisonous) && length == other.length && super.equals(other);
    }

    @Override
    public void drawObject (Graphics g){super.drawObject(g);}

}
