package Graphics;
/**
 * Interface representing the behavior of an animal that can consume energy.
 */
public interface IAnimal {
    /**
     * Makes the animal consume a specified amount of energy.
     *
     * @param energy the amount of energy to be consumed
     * @return {@code true} if the energy consumption was successful, {@code false} otherwise
     */
    public boolean eat(int energy);
}
