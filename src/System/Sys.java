//Artiom Bondar:332692730
//Gal Warsulker: 206493173
package System;
import Animals.*;
import Mobility.Point;
import Olympics.Medal;
import java.util.Objects;
import java.util.Scanner;
public class Sys
{
    /**
     * Main method to start the animal management system.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of animals do you want to create: ");
        int numberOfAnimals = sc.nextInt();
        sc.nextLine();

        Animal[] animals = new Animal[numberOfAnimals];
        for (int i = 0; i < numberOfAnimals; i++)
        {
            System.out.println("Enter type of animal: ");
            System.out.println("1) Enter Water Animal ");
            System.out.println("2) Enter Terrestrial Animal ");
            System.out.println("3) Enter Air Animal ");

            String typeChoice = sc.nextLine();

            switch (typeChoice)
            {
                case "1":// Water
                    animals[i] = createWaterAnimal(sc);
                    break;
                case "2"://Terrestrial
                    animals[i] = createTerrestrialAnimal(sc);
                    break;
                case "3"://Air
                    animals[i] = createAirAnimal(sc);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    i--;
                    break;
            }
        }
        while (true)
        {
            System.out.println("Choose an option:");
            System.out.println("1) Display information of all animals");
            System.out.println("2) Make all animals sound");
            System.out.println("3) Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    for (int i=0; i<numberOfAnimals; i++)
                    {
                        Animal animal = animals[i];
                        System.out.println(animal);
                    }
                    break;
                case 2:
                    for (int i=0; i<numberOfAnimals; i++)
                    {
                        Animal animal = animals[i];
                        animal.makeSound();
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    /**
     * Helper method to get gender input from user.
     *
     * @return The gender of the animal
     */

    private static Animal.Gender getGenderInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: 1)For male, 2)For female,3) For Hermaphrodite");
        int genderChoice = sc.nextInt();
        switch (genderChoice){
            case 1:
                return Animal.Gender.Male;
            case 2:
                return Animal.Gender.Female;
            case 3:
                return Animal.Gender.Hermaphrodite;
            default:
                throw new IllegalArgumentException("Invalid gender");
        }
    }
    /**
     * Helper method to get a double input from user.
     *
     * @param sc      Scanner object to read input
     * @param message Prompt message for user
     * @return Double value entered by user
     */
    private static double getSomeDoubleInput(Scanner sc, String message)
    {
        System.out.println(message);
        return sc.nextDouble();
    }

    private static int getSomeIntegerInput(Scanner sc, String message)
    {
        System.out.println(message);
        return sc.nextInt();
    }
    /**
     * Helper method to get a boolean input from user.
     *
     * @param sc      Scanner object to read input
     * @param message Prompt message for user
     * @return Boolean value entered by user
     */
    private static boolean getSomeBooleanInput(Scanner sc, String message)
    {
        System.out.println(message);
        return sc.nextBoolean();
    }
    /**
     * Helper method to get a string input from user.
     *
     * @param sc      Scanner object to read input
     * @param message Prompt message for user
     * @return String value entered by user
     */
    private static String getStringInput(Scanner sc, String message)
    {

        System.out.println(message);
        return sc.nextLine();
    }
    /**
     * Helper method to get water type input from user.
     *
     * @param sc Scanner object to read input
     * @return WaterType enum value chosen by user
     */
    private static Dolphin.WaterType getWaterTypeInput(Scanner sc)
    {
        System.out.print("Enter type of water: 1)Sea , 2)Sweet:");
        int waterTypeChoice = sc.nextInt();
        switch (waterTypeChoice)
        {
            case 1:
                return Dolphin.WaterType.Sea;
            case 2:
                return Dolphin.WaterType.Sweet;
            default:
                throw new IllegalArgumentException("Illegal waterType");
        }
    }
    /**
     * Helper method to get poisonous type input for snake from user.
     *
     * @param sc Scanner object to read input
     * @return Poisonous enum value chosen by user
     */
    private static Snake.Poisonous getIsPoisonous (Scanner sc)
    {
        System.out.print("Enter type of snake: 1)Venom , 2)Non Venom:");
        int typeChoice = sc.nextInt();
        switch (typeChoice)
        {
            case 1:
                return Snake.Poisonous.low;
            case 2:
                return Snake.Poisonous.medium;
            case 3:
                return Snake.Poisonous.high;
            default:
                throw new IllegalArgumentException("Illegal type");
        }
    }
    /**
     * Helper method to create a Water Animal based on user input.
     *
     * @param sc Scanner object to read input
     * @return Newly created Water Animal object
     */

    private static Animal createWaterAnimal(Scanner sc)
    {
        System.out.println("Enter the type of water animal: ");
        System.out.println("1) Whale");
        System.out.println("2) Dolphin");
        System.out.println("3) Alligator");
        String typeChoice = sc.nextLine();
       // sc.nextLine();
        switch (typeChoice)
        {
            case "1":
                return createWhale(sc);
            case "2":
                return createDolphin(sc);
            case "3":
                return createAlligator(sc);
            default:
                System.out.println("Illegal choice");
                return null;
        }
    }
    /**
     * Helper method to get a Medal object from user input.
     *
     * @param sc Scanner object to read input
     * @return Newly created Medal object
     */
    public static void getMedal(Scanner sc,Medal[] medals) {
        for (int i=0; i<medals.length; i++)
        {
            sc.nextLine();
            System.out.println("Enter the type of the medal: Type -> (bronze/silver/gold): ");
            Medal.Type medalType = Medal.Type.valueOf(sc.next());
            sc.nextLine();
            System.out.println("Enter the tournament: ");
            String tournament = sc.nextLine();
            System.out.println("Enter the year: ");
            int year = sc.nextInt();
            Medal.Type type = null;
            boolean flag = true;
            while (flag)
            {
                switch (medalType)
                {
                    case bronze:
                        type = Medal.Type.bronze;
                        flag = false;
                        break;
                    case silver:
                        type = Medal.Type.silver;
                        flag = false;
                        break;
                    case gold:
                        type = Medal.Type.gold;
                        flag = false;
                        break;
                    default:
                        System.out.println("Illegal choice");
                        //return null;
                }
            }
            medals[i] = new Medal(type,tournament,year);
        }
    }
    /**
     * Helper method to create a Whale based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Whale object
     */
    private static Animal createWhale(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the whale: ");
        double depthDive = getSomeDoubleInput(scanner, "Enter the depth dive of the whale: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the whale: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the whale: ");
        int id = getSomeIntegerInput(scanner,"Enter the id of whale:");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(50, 0);
        System.out.println("Enter type of food: ");
        String foodType = scanner.next();
        scanner.nextLine();
        return new Whale();
    }
    /**
     * Helper method to create a Dolphin based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Dolphin object
     */
    private static Animal createDolphin(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the dolphin: ");
        double depthDive = getSomeDoubleInput(scanner, "Enter the depth dive of the dolphin: ");
        scanner.nextLine();
        Dolphin.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the dolphin: ");
        scanner.nextLine();
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(50, 0);
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the dolphin: ");
        scanner.nextLine();
        Dolphin.WaterType waterType = getWaterTypeInput(scanner);

        return new Dolphin();
    }
    /**
     * Helper method to create an Alligator based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Alligator object
     */

    private static Animal createAlligator(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the alligator: ");
        double depthDive = getSomeDoubleInput(scanner, "Enter the depth dive of the alligator: ");
        scanner.nextLine();
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the alligator: ");
        scanner.nextLine();
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(50, 0);
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the alligator: ");
        scanner.nextLine();
        String areaOfLiving = getStringInput(scanner, "Enter the area of living: ");

        return new Alligator();
    }
    /**
     * Helper method to create a Dog based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Dog object
     */
    private static Animal createDog(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the dog: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the dog: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the dog: ");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(0, 20);
        System.out.println("Enter the breed of the dog: ");
        String breed = scanner.next();
        scanner.nextLine();
        return new Dog();
    }
    /**
     * Helper method to create a Cat based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Cat object
     */
    private static Animal createCat(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the cat: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the cat: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the cat: ");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(0, 20);
        boolean isCastrated = getSomeBooleanInput(scanner, "Is the cat castrated? (true/false): ");
        return new Cat();
    }
    /**
     * Helper method to create a Snake based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Snake object
     */
    private static Animal createSnake(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the snake: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the snake: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the snake: ");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(0, 20);
        double length = getSomeDoubleInput(scanner, "Enter the length of the snake: ");
        Snake.Poisonous poisonous = getIsPoisonous(scanner);
        return new Snake();
    }
    /**
     * Helper method to create an Eagle based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Eagle object
     */
    private static Animal createEagle(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the eagle: ");
        double wingspan = getSomeDoubleInput(scanner, "Enter the wingspan of the eagle: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the eagle: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the eagle: ");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(0, 100);
        double altitude = getSomeDoubleInput(scanner, "Enter the altitude of flight of the eagle: ");
        return new Eagle();
    }
    /**
     * Helper method to create a Pigeon based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Pigeon object
     */
    private static Animal createPigeon(Scanner scanner)
    {
        String name = getStringInput(scanner, "Enter the name of the pigeon: ");
        double wingspan = getSomeDoubleInput(scanner, "Enter the wingspan of the pigeon: ");
        Animal.Gender gender = getGenderInput();
        double weight = getSomeDoubleInput(scanner, "Enter the weight of the pigeon: ");
        double speed = getSomeDoubleInput(scanner, "Enter the speed of the pigeon: ");
        System.out.println("Enter the num of the medals: ");
        int num = scanner.nextInt();
        Medal[] medals = new Medal[num];
        getMedal(scanner,medals);
        Point location = new Point(0, 100);
        String family = getStringInput(scanner, "Enter the family of the pigeon: ");
        return new Pigeon();

    }
    /**
     * Helper method to create a Terrestrial Animal based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Terrestrial Animal object
     */
    private static Animal createTerrestrialAnimal(Scanner scanner)
    {
        System.out.println("Enter the type of terrestrial animal: ");
        System.out.println("1) Dog");
        System.out.println("2) Cat");
        System.out.println("3) Snake");
        int choice = scanner.nextInt();
        scanner.nextLine();
            switch (choice)
            {
                case 1:
                    return createDog(scanner);
                case 2:
                    return createCat(scanner);
                case 3:
                    return createSnake(scanner);
                default:
                    System.out.println("Invalid choice");
                    return null;
            }
    }
    /**
     * Helper method to create an Air Animal based on user input.
     *
     * @param scanner Scanner object to read input
     * @return Newly created Air Animal object
     */
    private static Animal createAirAnimal(Scanner scanner)
    {
        System.out.println("Enter the type of ari animal: ");
        System.out.println("1) Eagle");
        System.out.println("2) Pigeon");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 1:
                return createEagle(scanner);
            case 2:
                return createPigeon(scanner);
            default:
                System.out.println("Invalid choice");
                return null;
        }
    }
}
