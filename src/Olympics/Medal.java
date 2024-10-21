package Olympics;

public class Medal {
    /**
     * Enum defining the type of the Medal: GOLD, SILVER, BRONZE.
     */
    public enum Type{gold,silver,bronze}
    private Type type;
    private String tournament;
    private int year;

    /**
     * Constructor to initialize a Medal object.
     *
     * @param type       The type of the Medal (gold, silver, bronze)
     * @param tournament The name of the tournament where the Medal was awarded
     * @param year       The year when the Medal was awarded
     */
    public Medal(Type type, String tournament, int year)
    {
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }
    /**
     * Default constructor for creating an Medal object.
     *
     */
    public Medal()
    {
        this.type = Type.gold;
        this.tournament = "Unknown";
        this.year = 2000;
    }
    /**
     * Getter method for the type of the Medal.
     *
     * @return The type of the Medal (gold, silver, bronze)
     */
    public int getMedals()
    {
        return new Medal(type, tournament, year).getMedals();
    }
    /**
     * Getter method for the name of the tournament.
     *
     * @return The name of the tournament where the Medal was awarded
     */

    /**
     * Returns a string representation of the Medal object.
     *
     * @return A string representation of the Medal object in the format:
     *         "Medal Info: type = [type], tournament = [tournament], year = [year]"
     */
    @Override
    public String toString()
    {
        String toReturn = "Medal Info: type : " ;
        switch(type)
        {
            case bronze:
                toReturn += "bronze ";
                break;

            case silver:
                toReturn += "silver ";
                break;

            case gold:
                toReturn += "gold ";
                break;
        }
        toReturn += " |tournament :" + tournament + " |year : " + year;
        return toReturn;
    }
    /**
     * Checks if this Medal object is equal to another object.
     *
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (!(obj instanceof Medal)) return false;
        Medal other = (Medal) obj;
        return other.type.equals(type) && other.tournament.equals(tournament) && other.year == year;
    }
}
