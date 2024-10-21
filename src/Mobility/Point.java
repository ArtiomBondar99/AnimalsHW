package Mobility;

public class Point implements Cloneable{
    private int x;
    private int y;
    /**
     * Constructs a Point object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(int x, int y)  {
        if(x >= 0 && y >= 0)
        {
            this.x = x;
            this.y = y;
        }
    }
    /**
     * Default constructor for creating an Point object.
     *
     */
    public Point()
    {
        this.x = 0;
        this.y = 0;
    }

//    @Override
//    public Object clone() {
//        try {
//            return super.clone();
//        }catch (CloneNotSupportedException e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    /**
     * Returns a string representation of the point in the format "Point (x,y)".
     *
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        return "Point (" + x + "," + y + ")";
    }
    /**
     * Compares this Point object to the specified object. The result is true if and only if the argument is not null
     * and is a Point object that has the same x and y coordinates as this Point.
     *
     * @param obj The object to compare this Point against.
     * @return True if the given object represents a Point with the same coordinates as this Point, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }
    /**
     * Returns the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public int getX() {return x;}
    /**
     * Returns the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public int getY() {return y;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
}
