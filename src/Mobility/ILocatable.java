package Mobility;

import java.awt.*;

public interface ILocatable
{
    /**
     * Retrieves the current location of the object.
     *
     * @return The current location of the object as a Point.
     */
    public Point getLocation();
    /**
     * Sets the location of the object to the specified point.
     *
     * @param p The new location coordinates to set.
     * @return True if the location was set successfully, false otherwise.
     */
    public boolean setLocation(Point p);
}
