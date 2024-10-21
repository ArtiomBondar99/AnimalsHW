package Animals;
import Graphics.CompetitionPanel;
import Graphics.IAnimal;
import Graphics.IMoveable;
import Mobility.Point;
import Olympics.Medal;

import java.awt.image.BufferedImage;

public class Dolphin extends WaterAnimal
{
    /**
 * Enum representing the type of water the dolphin lives in.
 */
    public enum WaterType {Sea, Sweet}

    private WaterType waterType;
    private String type ;

    /**
     * Constructs a Dolphin object with the specified attributes.
     *
     * @param name        the name of the dolphin
     * @param gender      the gender of the dolphin
     * @param weight      the weight of the dolphin
     * @param speed       the speed of the dolphin
     * @param medals      the medals won by the dolphin
     * @param location    the location of the dolphin
     * @param id          the ID of the dolphin
     * @param orien       the orientation of the dolphin
     * @param maxEnergy   the maximum energy of the dolphin
     * @param energyPerMeter the energy consumed per meter for the dolphin
     * @param pan         the competition panel associated with the dolphin
     * @param img1        the image for the dolphin
     * @param totalDistance the total distance traveled by the dolphin
     * @param diveDept    the depth the dolphin can dive
     * @param waterType   the type of water the dolphin lives in
     */

    public Dolphin(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1,double totalDistance, double diveDept, WaterType waterType)  {
        super(name, gender, weight, speed, medals,location,id,orien,maxEnergy,energyPerMeter,pan,img1,0.0,diveDept );
        this.waterType = waterType;
    }

    /**
     * Default constructor for creating an Dolphin object.
     *
     */
    public Dolphin()
    {
        super();
        this.waterType = WaterType.Sea;
    }
    /**
     * Returns the sound the dolphin makes.
     *
     * @return the sound of the dolphin
     */
    @Override
    public String getSound() {
        return "Click-click";
    }
    /**
     * Returns the type of water the dolphin lives in.
     *
     * @return the water type
     */
    public String getWaterType() {
        return waterType.toString();
    }
    /**
     * Sets the type of water the dolphin lives in.
     *
     * @param WaterType the new water type
     * @return true if the water type was set successfully, false otherwise
     */
    public boolean setWaterType(WaterType WaterType) {
        if (WaterType == null)
            return false;
        this.waterType = WaterType;
        return true;
    }


    @Override
    public String getType(){return "Dolphin";}

    /**
     * Returns a string representation of the dolphin.
     *
     * @return a string representation of the dolphin
     */
    @Override
    public String toString()
    {
        return "Dolphin is: "+ super.toString() + "|waterType: " + waterType + '}';
    }
    /**
     * Compares this dolphin to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Dolphin))
            return false;
        Dolphin dolphin = (Dolphin) obj;
        return waterType.equals(dolphin.waterType) && super.equals(dolphin);
    }

}



