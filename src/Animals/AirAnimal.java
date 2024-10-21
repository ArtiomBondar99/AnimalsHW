package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AirAnimal extends Animal
{
    /**
     * The AirAnimal class represents an animal that can fly.
     * It extends the Animal class and adds specific attributes related to flying animals, such as wingspan.
     * This class also handles the loading and drawing of the animal's image.
     */
    private double wingspan;
    private BufferedImage img;

    /**
     * Constructor for creating an AirAnimal object with specific attributes.
     *
     * @param name          the name of the animal
     * @param gender        the gender of the animal
     * @param weight        the weight of the animal
     * @param speed         the speed of the animal
     * @param medals        the medals won by the animal
     * @param location      the location of the animal
     * @param id            the ID of the animal
     * @param orien         the orientation of the animal
     * @param maxEnergy     the maximum energy of the animal
     * @param energyPerMeter the energy consumption per meter for the animal
     * @param pan           the competition panel associated with the animal
     * @param img           the image of the animal
     * @param totalDistance the total distance traveled by the animal
     * @param wingspan      the wingspan of the animal
     */

    public AirAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id,Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img,double totalDistance, double wingspan)
    {
        super(name, gender, weight, speed, medals, location,id,orien,maxEnergy,energyPerMeter,pan,img,null,null,null);
        this.wingspan = wingspan;
        this.img = img;
    }

    /**
     * Default constructor for creating an AirAnimal object.
     *
     */

    public AirAnimal()
    {
        super();
        this.setLocation(new Point(0,100));
        this.wingspan = 0.0;
        this.img = null;
    }


    @Override
    public String getType()
    {
        return super.getType();
    }

    @Override
    protected String getSound() {
        return "";
    }


    /**
     * Compares this AirAnimal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof AirAnimal))
            return false;
        AirAnimal airAnimal = (AirAnimal) obj;
        return wingspan == airAnimal.wingspan && super.equals(obj);
    }
    /**
     * Returns a string representation of the AirAnimal.
     *
     * @return a string representation of the AirAnimal
     */

    @Override
    public String toString()
    {
        return "Air Animal " + super.toString() + "|wingspan=" + wingspan + '}';

    }

    @Override
    public String getAnimaleName() {
        return "";
    }

//    @Override
//    public boolean move(Point p) {
//        double distance = calcDistance(p);
//        if (distance > 0 && distance <= currentEnergy / energyPerMeter) {
//            addTotalDistance(distance);
//            currentEnergy -= distance * energyPerMeter;
//            setLocation(p);
//
//            // Air animals might move in any direction
//            if (p.getX() > getLocation().getX()) {
//                setOrien(Orientation.EAST);
//            } else if (p.getX() < getLocation().getX()) {
//                setOrien(Orientation.WEST);
//            } else if (p.getY() > getLocation().getY()) {
//                setOrien(Orientation.SOUTH);
//            } else if (p.getY() < getLocation().getY()) {
//                setOrien(Orientation.NORTH);
//            }
//
//            // Trigger panel repaint
//            getPanel().repaint();
//            return true;
//        }
//        return false;
//    }


    /**
     * Loads the image for the AirAnimal from the given file name.
     *
     * @param fileName the file name of the image to load
     */
    @Override
    public void loadImages(String fileName) {
        try {
            img = ImageIO.read(new File(fileName + "1.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image for air animal");
        }
    }


    public String animalCategory() {
        return "Air";
    }
}
