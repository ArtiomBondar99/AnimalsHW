package Animals;

import Graphics.*;
import Mobility.ILocatable;
import Mobility.Mobile;
import Mobility.Point;
import Olympics.Medal;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 * Abstract class representing an animal with various attributes and behaviors.
 */
public abstract class Animal extends Mobile implements ILocatable, IMoveable, IDrawable, IAnimal, Cloneable
{
    public enum Gender { Male, Female, Hermaphrodite };
    public enum Orientation { NORTH, EAST, SOUTH, WEST };
    private String name;
    private Gender gender;
    private double weight;
    private double speed;
    private Medal[] medals;
    private int size ;
    private int id;
    protected Point loc;
    private IMoveable iMoveable;
    private IAnimal iAnimal;
    private Cloneable cloneable;
    private Orientation orien;
    public int currentEnergy;
    private int maxEnergy;
    public int energyPerMeter;
    private int energyConsumption;
    private CompetitionPanel pan;
    private BufferedImage img1, img2, img3, img4;
    /**
     * Constructs an Animal object with the specified attributes.
     *
     * @param name         the name of the animal
     * @param gender       the gender of the animal
     * @param weight       the weight of the animal
     * @param speed        the speed of the animal
     * @param medals       the medals won by the animal
     * @param location     the location of the animal
     * @param size         the size of the animal
     * @param id           the ID of the animal
     * @param orien        the orientation of the animal
     * @param maxEnergy    the maximum energy of the animal
     * @param energyPerMeter the energy consumed per meter
     * @param pan          the competition panel associated with the animal
     * @param img1         the image representing the animal facing east
     * @param img2         the image representing the animal facing south
     * @param img3         the image representing the animal facing west
     * @param img4         the image representing the animal facing north
     */


    /**
     * Default constructor for Animal, initializing with default values.
     */
    public Animal(String name, Gender gender, double weight, double speed, Medal[] medals, Point location, int id, Orientation orien, int maxEnergy, int energyPerMeter
            , CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4)
    {
        super(location);
        currentEnergy = maxEnergy;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        if (medals == null)
            this.medals = null;
        else
        {
            this.medals = new Medal[medals.length];
            for (int i = 0; i < medals.length; i++)
                this.medals[i] = medals[i];
        }

        this.size = 65;
        this.id = id;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter=energyPerMeter;
        this.loc=location;
        this.orien=orien;
        this.pan = pan;
        this.img1=img1;
        this.img2=img2;
        this.img3=img3;
        this.img4=img4;
    }

    @Override
    public String toString()
    {
        return "{ name:" +name + "|gender:" + gender + "|weight:" + weight + "|speed:"+speed+ "|medals:"+ Arrays.toString(getMedals()) +"|"+ "size:"+ size+ "|" +"loc:"+ loc + "|" + "orien:" + orien + "|" +"maxEnergy:" + maxEnergy + "|" +", energyPerMeter:" + energyPerMeter + "|" + " pan: " + pan+ "|" + " img1:" + img1 + "|" + " img2:" + img2+ "|" + " img3:" + img3+ "|" + "img4:" + img4 + "}";
    }

    @Override
    public double getSpeed() {
        return speed;
    }


    /**
     * Default constructor for Animal, initializing with default values.
     */
    public Animal()
    {
        super();
        this.name = "Unknown";
        this.gender = Gender.Male;
        this.weight = 0;
        this.speed = 0.0;
        this.medals = null;
        this.size = 0;
        this.id = 0;
        this.loc = null;
        this.orien= null;
        this.maxEnergy = 0;
        this.energyPerMeter = 0;
        this.pan = null;
        this.img1 = null;
        this.img2 = null;
        this.img3 = null;
        this.img4 = null;
        this.iMoveable = null;
        this.iAnimal = null;
        currentEnergy = 0;
//        this.cloneable = null;
    }


    public String animalCategory() {
        return "Animal";
    }


    /**
     * Draws the animal on the given graphics context.
     *
     * @param g the graphics context
     */
    @Override
    public void drawObject (Graphics g)
    {
        if(orien==Orientation.EAST)  // animal move to the east side
            g.drawImage(img1, loc.getX(), loc.getY()-size/10, size*2, size, pan);

        else if(orien==Orientation.SOUTH) // animal move to the south side
            g.drawImage(img2, loc.getX(), loc.getY()-size/10, size, size, pan);
        else if(orien==Orientation.WEST) // animal move to the west side
            g.drawImage(img3, loc.getX(), loc.getY()-size/10, size*2, size, pan);
        else if(orien==Orientation.NORTH) // animal move to the north side
            g.drawImage(img4, loc.getX()-size/2, loc.getY()-size/10, size, size*2, pan);
    }


    public String getType()
    {
        return " ";
    }



    /**
     * Loads images for the animal based on a file name prefix.
     *
     * @param fileName the prefix of the file names for the images
     */
    @Override
    public void loadImages(String fileName) {
        try {
            img1 = ImageIO.read(new File(fileName + "1.png"));
            img2 = ImageIO.read(new File(fileName + "2.png"));
            img3 = ImageIO.read(new File(fileName + "3.png"));
            img4 = ImageIO.read(new File(fileName + "4.png"));
        } catch (IOException e) {
            System.out.println("Cannot load images");
        }
    }



    /**
     * Creates a clone of this animal.
     *
     * @return a clone of the animal
     * @throws CloneNotSupportedException if the clone operation is not supported
     */
    public Object clone() throws CloneNotSupportedException {
        Animal clone = (Animal) super.clone();
        return clone;
    }



    /**
     * Constructs an Animal object with the specified attributes.
     *
     * @param name     the name of the animal
     * @param gender   the gender of the animal
     * @param weight   the weight of the animal
     * @param speed    the speed of the animal
     * @param medals   the medals won by the animal
     * @param location the location of the animal
     */


    /**
     * Default constructor for Animal, initializing with default values.
     *
     */

    /**
     * Gets the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName(){ return name;}

    /**
     * Gets the name of the animal.
     *
     * @return the name of the animal
     */
    public String getNameAndType(){ return name + " the " + toString().split(" ")[0];}

    /**
     * Gets the gender of the animal.
     *
     * @return the gender of the animal
     */
    public Gender getGender(){ return gender;}
    /**
     * Gets the weight of the animal.
     *
     * @return the weight of the animal
     */
    public double getWeight(){ return weight;}

    /**
     * Gets the medals won by the animal.
     *
     * @return the medals won by the animal
     */
    public Medal[] getMedals(){ return medals;}

    /**
     * Returns the sound the animal makes.
     *
     * @return the sound of the animal
     */
    protected abstract String getSound();
    /**
     * Makes the animal produce its sound.
     */
    public void makeSound()
    {
        System.out.println("Animal " + name + " said " + getSound());
    }
    /**
     * Compares this animal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Animal))
            return false;
        Animal other = (Animal) obj;
        return name.equals(other.name) && gender.equals(other.gender) && weight == other.weight && speed==other.speed && medals.equals(other.medals) && size== other.size && id == other.id
                && orien.equals(other.orien)&& maxEnergy==other.maxEnergy && energyPerMeter==other.energyPerMeter&& pan.equals(other.pan)&& super.equals(obj);
    }
    /**
     * Updates the animal's energy based on consumption.
     *
     * @param energy the amount of energy to be consumed
     * @return true if the operation was successful, false otherwise
     */

    @Override
    public boolean eat(int energy)
    {
        energyConsumption += energy;

        if(currentEnergy + energy > maxEnergy)
        {
            currentEnergy = maxEnergy;
            return true;
        }
        currentEnergy += energy;
        return true;
    }


    @Override
    public Point getLocation()
    {
        return loc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Sets the location of the animal.
     *
     * @param p the new location of the animal
     * @return true if the location was set successfully, false otherwise
     */
    @Override
    public boolean setLocation(Point p)
    {
        if(p != null)
        {
            loc = p;
            return true;
        }
        return false;
    }

    public int getMaxEnergy()
    {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy)
    {
        if (maxEnergy >= 0)
            this.maxEnergy = maxEnergy;
    }


    /**
     * Gets the orientation of the animal.
     *
     * @return the orientation of the animal
     */
    public Orientation getOrien() {return orien;}

    public void setOrien(Orientation orien){this.orien = orien;}



    /**
     * Gets the competition panel associated with the animal.
     *
     * @return the competition panel
     */
    public CompetitionPanel getPanel(){return pan;}

//    public void setPanel(JPanel pan) {
//        this.pan = pan;
//    }


    /**
     * Gets the image representing the animal facing east.
     *
     * @return the image for the east orientation
     */
    public BufferedImage getImg1(){return img1;}

    /**
     * Gets the image representing the animal facing south.
     *
     * @return the image for the south orientation
     */


    public BufferedImage getImg2(){return img2;}
    /**
     * Gets the image representing the animal facing west.
     *
     * @return the image for the west orientation
     */

    public BufferedImage getImg3(){return img3;}

    /**
     * Gets the image representing the animal facing north.
     *
     * @return the image for the north orientation
     */

    public BufferedImage getImg4(){return img4;}

    /**
     * Sets the amount of energy consumed per meter.
     *
     * @param energyPerMeter the new energy per meter value
     */




    public void setEnergyPerMeter(int energyPerMeter)
    {
        if (energyPerMeter >= 0) {
            this.energyPerMeter = energyPerMeter;
        }
    }


    public int getEnergyPerMeter(){return energyPerMeter;}

    /**
     * Moves the animal to a new location if there is enough energy.
     *
     * @param p the new location
     * @return true if the move was successful, false otherwise
     */


    @Override
    public boolean move(Point p) {
        if (currentEnergy <= 0) {
            System.out.println(getName() + " has no energy left and cannot move.");
            return false; // החיה לא זזה כי אין לה אנרגיה
        }

        Point pos = getLocation();
        int speed = (int) getSpeed();
        int panelWidth = pan.getWidth();
        int panelHeight = pan.getHeight();


        // עדכון מיקום החיה
        if (orien == Orientation.EAST) {
            pos.setX(pos.getX() + speed);
            if (pos.getX() >= panelWidth - 130) {
                pos.setX(panelWidth - 130);
                orien = Orientation.SOUTH;
            }
        } else if (orien == Orientation.SOUTH) {
            pos.setY(pos.getY() + speed);
            if (pos.getY() >= panelHeight + 650) {
                pos.setY(panelHeight + 650);
                orien = Orientation.WEST;
            }
        } else if (orien == Orientation.WEST) {
            pos.setX(pos.getX() - speed);
            if (pos.getX() <= 35) {
                pos.setX(35);
                orien = Orientation.NORTH;
            }
        } else if (orien == Orientation.NORTH) {
            pos.setY(pos.getY() - speed);
            if (pos.getY() <= 10) {
                pos.setY(10);
                orien = Orientation.EAST;
            }
        }

        currentEnergy -= energyPerMeter;
        System.out.println(getName() + " new energy after move: " + currentEnergy);

        if (currentEnergy <= 0) {
            currentEnergy = 0;
            return false; // החיה עוצרת בגלל חוסר אנרגיה
        }

        setLocation(pos);
        return true;
    }

    /*
    @Override
    public boolean move(Point p)
    {
        if (pan == null || pan.getTypeOfCompetition() == null) {
            System.out.println("Error: pan or competition type is null");

            return false; // או לזרוק חריגה אם מתאים
        }
        switch (pan.getTypeOfCompetition()){
            case "Air":
            case "Water":{
                int x =(int)(getLocation().getX()+getSpeed());
                int y =(int)(getLocation().getY());
                if (eat(-(int) (getEnergyPerMeter()*getSpeed())) && (x+size*2) <= pan.getWidth()){
                    return super.move(new Point(x,y));
                }
                return false;
            }
            case "Terrestrial":
                switch (getOrien()){
                    case EAST -> {
                        int x =(int)(getLocation().getX()+getSpeed());
                        int y =(int)(getLocation().getY());
                        if (eat(-(int) (getEnergyPerMeter()*getSpeed()))){
                            if((x+getSize()*2) >= pan.getWidth()){
                                setOrien(Orientation.SOUTH);
                                x=pan.getWidth()-getSize();
                            }
                            return super.move(new Point(x,y));
                        }
                        return false;
                    }
                    case SOUTH -> {
                        int x =getLocation().getX();
                        int y =(int)(getLocation().getY()+getSpeed());
                        if (eat(-(int) (getEnergyPerMeter()*getSpeed()))){
                            if (y+getSize() >= pan.getHeight()){
                                setOrien(Orientation.WEST);
                                y=pan.getHeight()-getSize();
                            }
                            return super.move(new Point(x,y));
                        }
                        return false;
                    }
                    case WEST -> {
                        int x = (int)(getLocation().getX()- getSpeed());
                        int y =(int)(getLocation().getY());
                        if (eat(-(int) (getEnergyPerMeter()*getSpeed()))){
                            if(x-getSize()*2 <= 0){
                                setOrien(Orientation.NORTH);
                                x = 0;
                            }
                            return super.move(new Point(x,y));
                        }
                        return false;
                    }
                    case NORTH -> {
                        int x = (getLocation().getX());
                        int y =(int)(getLocation().getY()-getSpeed());
                        if (eat(-(int) (getEnergyPerMeter()*getSpeed()))){
                            if (y-getSize() <= 0){
                                y=0;
                            }
                            return super.move(new Point(x,y));
                        }
                        return false;
                    }
                }
        }
        return false;
    }

*/



    /**
     * Gets the total energy consumption of the animal.
     *
     * @return the total energy consumption
     */

    public int getTotalConsumption() {
        return energyConsumption;
    }

    /**
     * Gets the current energy of the animal.
     *
     * @return the current energy
     */

    public int getcurrentEnergy(){return currentEnergy;}



}
