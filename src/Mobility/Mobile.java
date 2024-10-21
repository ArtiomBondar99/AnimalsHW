package Mobility;

public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    public Mobile(Point location) {
        this.totalDistance = 0.0;
        this.location = location;
    }
    /**
     * Default constructor for creating an Mobile object.
     *
     */
    public Mobile()
    {
        this.totalDistance = 0.0;
        this.location = null;
    }
    /**
     * Adds the given distance to the total distance traveled by the mobile object.
     *
     * @param totalDistance The distance to add to the total distance traveled.
     */
    public void addTotalDistance(double totalDistance) {
        this.totalDistance += totalDistance;
    }
    /**
     * Calculates the Euclidean distance between the current location of the mobile object and the given point.
     *
     * @param location The point to calculate the distance to.
     * @return The Euclidean distance between the current location and the given point.
     */
    public double calcDistance(Point location) {
        return Math.sqrt(Math.pow(location.getX() - this.location.getX(), 2) + Math.pow(location.getY() - this.location.getY(), 2));
    }
    /**
     * Moves the mobile object to the specified location and updates the total distance traveled.
     *
     * @param p The new location to move the mobile object to.
     * @return The distance moved.
     */
   public boolean move(Point p){
       double distance = calcDistance(p);
       if (distance >0)
       {
           addTotalDistance(distance);
           setLocation(p);
           return true;
       }
       return false;
   }
    /**
     * Retrieves the current location of the mobile object.
     *
     * @return The current location of the mobile object as a Point.
     */
    @Override
    public Point getLocation() {
        return  new Point(location.getX(), location.getY());
    }
    /**
     * Sets the location of the mobile object to the specified point.
     *
     * @param p The new location coordinates to set.
     * @return True if the location was set successfully, false otherwise.
     */
    @Override
    public boolean setLocation(Point p) {
        this.location = p;
        return true;
    }
    /**
     * Compares this Mobile object to the specified object. The result is true if and only if the argument is not null
     * and is a Mobile object that has the same location and total distance as this object.
     *
     * @param obj The object to compare this Mobile against.
     * @return True if the given object represents a Mobile with the same location and total distance as this Mobile, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mobile)) return false;
        Mobile other = (Mobile) obj;
        return location.equals(other.location) && totalDistance == other.totalDistance;
    }
    /**
     * Returns a string representation of the Mobile object, including its current location and total distance traveled.
     *
     * @return A string representation of the Mobile object.
     */
    @Override
    public String toString()
    {
        return "location: " + location + "| totalDistance: " + totalDistance ;
    }

    public double getTotalDictance(){return totalDistance;}
}
