package Graphics;


import Mobility.Point;
/**
 * The {@code IMoveable} interface defines methods for moving an object
 * and retrieving its properties. Classes implementing this interface
 * should provide functionality for getting the object's name, speed,
 * and moving it to a specified point.
 */
public interface IMoveable {
    /**
     * Returns the name of the animal.
     *
     * @return A {@code String} representing the name of the animal.
     */
    public String getAnimaleName();

    /**
     * Returns the speed of the animal.
     *
     * @return A {@code double} representing the speed of the animal.
     */
    public double getSpeed();
    /**
     * Moves the animal to the specified point.
     * The implementation should handle the logic for moving the animal
     * and updating its position.
     *
     * @param p A {@code Point} representing the destination point.
     * @return {@code true} if the move was successful, {@code false} otherwise.
     */
    public boolean move(Point p);

}
