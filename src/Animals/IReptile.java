package Animals;

import jdk.dynalink.beans.StaticClass;

public interface IReptile
{
    static final int MAX_SPEED = 5 ;
    /**
     * Speeds up the reptile by the given amount.
     *
     * @param speed the amount to speed up the reptile
     * @return true if the speed was increased successfully, false otherwise
     */
    public boolean speedUp(int speed);

}
