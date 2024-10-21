//Artiom Bondar: 332692730
//Gal Warsulker 206493173
package Graphics;
import Animals.Alligator;
import Animals.Animal;
import Animals.Snake;
import Animals.*;
import Mobility.Point;
import OtherCompetitions.CourierTournament;
import OtherCompetitions.RegularTournament;
import OtherCompetitions.Tournament;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static Animals.Animal.Gender.Male;

/**
 * The main frame for the competition application.
 *
 * This frame provides the interface for managing competitions, including adding, clearing, and feeding animals.
 * It also contains menu options for exiting and displaying help information.
 */
public class CompetitionFrame extends JFrame {

    private BufferedImage img;
    public JLayeredPane lpane;
    private JPanel backgroundPanel;


    private List<Animal> animalList;
    public List<List<Animal>> terrestrialCourier;
    public String terrestrialCourierGroupName;
    public List<List<Animal>> waterCourier;
    public String waterCourierGroupName;
    public List<List<Animal>> airCourier;
    public String airCourierGroupName;
    public List<Animal> terrestrialRegular;
    public String terrestrialRegularGroupName;
    public List<Animal> waterRegular;
    public String waterRegularGroupName;
    public List<Animal> airRegular;
    public String airRegularGroupName;
    private int counter = 0;
    private int TerrestrialCounter = 0;
    private int WaterCounter = 0;
    private int AirCounter = 0;
    private String currentCompetitionType ;
    private CompetitionPanel competitionPanel;
    private Timer timer;
    private String regularOrCarrierType;
    private AddCompetitionDialog addCompetitionDialog;
    private boolean isPLaying = false;
    private List<Tournament> tournaments;


    /**
     * Constructs a CompetitionFrame with a background image and menu options.
     * Initializes the layered pane, background panel, and menu bar.
     */
    public CompetitionFrame() {
        super("Competition");
//        terrestrialCourier = null;
//        waterCourier= null;
//        airCourier= null;
//        terrestrialRegular= null;
//        waterRegular= null;
//        airRegular= null;

//        int numberOfGroups = 5;
        terrestrialCourier = new ArrayList<>();
        waterCourier = new ArrayList<>();
        airCourier = new ArrayList<>();
        terrestrialRegular = new ArrayList<>();
        waterRegular = new ArrayList<>();
        airRegular = new ArrayList<>();
        tournaments = new ArrayList<>();
        addCompetitionDialog = new AddCompetitionDialog(this);



        try {
            img = ImageIO.read(new File("src/Resourses/competitionBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
            System.exit( 1);
        }
        setLayout(null);
        timer=null;

        lpane = new JLayeredPane(); // Initialize lpane here
        lpane.setPreferredSize(new Dimension(800, 800));

        backgroundPanel= new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                for (Animal animal : animalList) {
                    animal.drawObject(g);
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 800, 800);
        lpane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CompetitionFrame.this, "HomeWork 2" + "\n GUI");
            }
        });


        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(CompetitionFrame.this, "Home Work 2 \n GUI ", "Message", JOptionPane.OK_OPTION);
                System.exit(0);
            }
        });
        menuBar.add(fileMenu);
        fileMenu.add(exitMenuItem);
        menuBar.add(helpMenu);
        helpMenu.add(helpMenuItem);
        this.setJMenuBar(menuBar);



        competitionPanel = new CompetitionPanel(CompetitionFrame.this, backgroundPanel);
        add(competitionPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1550, 825);
        setVisible(true);

        animalList = new ArrayList<Animal>();
    }

    public Object[][] getCourierData(List<List<Animal>> AnimalCourier)
    {
        if (AnimalCourier == null || AnimalCourier.isEmpty()) {
            return new Object[0][0];
        }
        int rows = AnimalCourier.size();
        int cols = AnimalCourier.stream().mapToInt(List::size).max().orElse(0);

        // Initialize the 2D array
        Object[][] array = new Object[cols][rows];

        // Fill the array
        for (int i = 0; i < rows; i++) {
            List<Animal> group = AnimalCourier.get(i);
            for (int j = 0; j < group.size(); j++) {
                array[j][i] = group.get(j).toString().split(" ")[0];  // Place the Animal object or specific data (like name, type) into the array
            }
        }
        return array;
    }

    public Animal[][] getCourierAnimalData(List<List<Animal>> AnimalCourier)
    {
        if (AnimalCourier == null || AnimalCourier.isEmpty()) {
            return new Animal[0][0];
        }
        int rows = AnimalCourier.size();
        int cols = AnimalCourier.stream().mapToInt(List::size).max().orElse(0);

        // Initialize the 2D array
        Animal[][] array = new Animal[rows][cols];

        // Fill the array
        for (int i = 0; i < rows; i++) {
            List<Animal> group = AnimalCourier.get(i);
            for (int j = 0; j < group.size(); j++) {
                array[i][j] = group.get(j);  // Place the Animal object or specific data (like name, type) into the array
            }
        }
        return array;
    }

    public Object[][] getRegularData(List<Animal> animalRegular) {
        if (animalRegular == null) {
            return new Object[0][0];
        }
        Object[][] data = new Object[animalRegular.size()][1];
        for (int i = 0; i < animalRegular.size(); i++) {
            data[i][0] = animalRegular.get(i).toString().split(" ")[0];  // או כל מידע אחר שתרצה להציג בטבלה
        }
        return data;
    }

    public Animal[][] getRegularAnimalData(List<Animal> animalRegular) {
        if (animalRegular == null) {
            return new Animal[0][0];
        }
        Animal[][] data = new Animal[animalRegular.size()][1];
        for (int i = 0; i < animalRegular.size(); i++) {
            data[i][0] = animalRegular.get(i);  // או כל מידע אחר שתרצה להציג בטבלה
        }
        return data;
    }

    public void setUpTimer(){
        if (timer != null){
            timer.stop();
        }
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimal();
            }
        });
        if(isPLaying)
        {
            timer.start();
        }
    }

    public void startTornament()
    {
        if(terrestrialCourier.size() != 0)
        {
            CourierTournament ct = new CourierTournament( getCourierAnimalData(terrestrialCourier),terrestrialCourierGroupName,this);
            addTournament(ct);
        }

        if(waterCourier.size() != 0)
        {
            CourierTournament ct = new CourierTournament( getCourierAnimalData(waterCourier),waterCourierGroupName,this);
            addTournament(ct);
        }

        if(airCourier.size() != 0)
        {
            CourierTournament ct = new CourierTournament( getCourierAnimalData(airCourier),airCourierGroupName,this);
            addTournament(ct);
        }

        if(terrestrialRegular.size() != 0)
        {
            RegularTournament rt = new RegularTournament(getRegularAnimalData(terrestrialRegular),terrestrialRegularGroupName,1430,this);
            addTournament(rt);
        }

        if(waterRegular.size() != 0)
        {
            RegularTournament rt = new RegularTournament( getRegularAnimalData(waterRegular),waterRegularGroupName,1220,this);
            addTournament(rt);
        }

        if(airRegular.size() != 0)
        {
            RegularTournament rt = new RegularTournament(getRegularAnimalData(airRegular),airRegularGroupName,1550,this);
            addTournament(rt);
        }
    }


    public void startRace()
    {
        System.out.println("startRace called");
        isPLaying = true;
        if (timer != null)
        {
            timer.start();
        }
    }

    public void stopRace()
    {
        isPLaying = false;
        if (timer != null)
        {
            timer.stop();
        }
    }

    private void updateAnimal() {
        boolean anyAnimalMoved = false;
        for (Animal animal : animalList) {
            if (animal != null) {
                // לפני התנועה, נוודא שהאנרגיה מספיקה
                System.out.println(animal.getName() + " energy before move: " + animal.getcurrentEnergy());
                boolean moved = animal.move(animal.getLocation());
                if (moved) {
                    anyAnimalMoved = true;
                } else {
                    System.out.println(animal.getName() + " has stopped moving due to lack of energy.");
                }
            }
        }

        if (!anyAnimalMoved) {
            System.out.println("All animals have stopped. Stopping the race.");
            stopRace(); // עצירת המרוץ אם אף חיה לא זזה
        }

        revalidate();
        repaint();
    }

//    private void updateAnimal()
//    {
//        for(int i=0; i<animalList.size(); i++){
//            Animal animal = animalList.get(i);
//            if (animal.getcurrentEnergy()>0){
//                animal.move(animal.getLocation());
//            }
//            else
//            {
//                System.out.println(animal.getName() + " has run out of energy and stopped.");
//            }
//        }
//        revalidate();
//        repaint();;
//    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * Gets the current counter value.
     *
     * @return the current counter value
     */
    public int getCounter(){return counter;}

    /**
     * Gets the current competition type.
     *
     * @return the current competition type
     */
    public String getComp(){return currentCompetitionType ;}

    /**
     * Sets the current competition type.
     *
     * @param comp the competition type to set
     * @return the set competition type
     */
    public String setComp(String comp){
        this.currentCompetitionType = comp;
        return comp;
    }

    /**
     * Sets the counter value.
     *
     * @param counter the new counter value
     */
    public void setCounter(int counter){this.counter = counter;}

    public int getWaterCounter()
    { return WaterCounter;}

    public void setWaterCounter(int counter)
    { WaterCounter = counter;}

    public int getAirCounter()
    { return AirCounter;}

    public void setAirCounter(int counter)
    { AirCounter = counter;}


    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }
    public int getTerrestrialCounter()
    { return TerrestrialCounter;}

    public void setTerrestrialCounter(int counter)
    { TerrestrialCounter = counter;}



    /**
     * Adds an animal and its associated panel to the lists.
     *
     * @param an the animal to add
    // * @param jp the panel associated with the animal
     */

    public void addAnimalToList(Animal an)
    {
        animalList.add(an);
        revalidate();
        repaint();;
    }

    /**
     * Gets the names of all animals in the list.
     *
     * @return an array of animal names
     */
    public String[] getAnimalNamesAndType()
    {
        String[] animalNames = new String[animalList.size()];
        for (int i = 0; i < animalList.size(); i++)
        {
            animalNames[i] = animalList.get(i).getName() + " the "+ (animalList.get(i).toString()).split(" ")[0];
        }
        return animalNames;
    }

    /**
     * Clears the specified animal from the competition.
     *
     * @param animalType the type of animal to clear
     */
    public void Clear(String animalType)
    {
        if (!animalList.isEmpty())
        {
            for(int i = 0; i< animalList.size(); i++)
            {
                if((animalList.get(i).getNameAndType().equals(animalType))){
                    animalList.remove(i);
                }

            }
        }
    }

    /**
     * Feeds the specified animal with the given amount of food.
     *
     * @param animalType the type of animal to feed
     * @param feedAmount the amount of food to give
     */

    public void Feed(String animalType,int feedAmount)
    {
        for (int i = 0; i < animalList.size(); i++)
        {
            Animal animal = animalList.get(i);
            if (animal.getName().equals(animalType))
            {
                animal.eat(feedAmount);
                System.out.println(animal.getName() + " has been fed and can now continue running.");
            }
        }
    }


    /**
     * Gets the data of all animals in a 2D array format for table display.
     *
     * @return a 2D array containing animal data
     */
    public Object[][] getData()
    {
        Object[][] data = new Object[animalList.size()][7];
        for (int i = 0; i < animalList.size(); i++)
        {
            data[i][0] = animalList.get(i).getName();
            data[i][1] = animalList.get(i).animalCategory();
            data[i][2]= animalList.get(i).getType();
            data[i][3]= animalList.get(i).getSpeed();
            data[i][4]= animalList.get(i).getcurrentEnergy();
            data[i][5]= animalList.get(i).getTotalDictance();
            data[i][6]= animalList.get(i).getTotalConsumption();

        }

        return data;
    }

    public List<List<Animal>> getCurrentCourierList(String type) {
        switch(type) {
            case "Terrestrial":
                return terrestrialCourier;
            case "Water":
                return waterCourier;
            case "Air":
                return airCourier;
            default:
                return new ArrayList<>();
        }
    }



    public CompetitionPanel getCompetitionPanel(){return competitionPanel;}
    /**
     * Main method to launch the CompetitionFrame application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
         new CompetitionFrame();
    }

//
//    public void addAnimalToSpecificList(Animal an, String animalType, String regularOrCarrierType,int column) {
//
//        if(regularOrCarrierType.equals("Regular"))
//        {
//            switch(animalType)
//            {
//                case "Terrestrial":
//                    terrestrialRegular.add(an);
//                    break;
//
//                case "Water":
//                    waterRegular.add(an);
//                    break;
//
//                case "Air":
//                    airRegular.add(an);
//                    break;
//            }
//        }
//        else {
//            switch(animalType)
//            {
//                case "Terrestrial":
//                    terrestrialCourier.get(column).add(an);
//                    break;
//
//                case "Water":
//                    waterCourier.get(column).add(an);
//                    break;
//
//                case "Air":
//                    airCourier.get(column).add(an);
//                    break;
//            }
//        }
//
//    }

    public void addAnimalToSpecificList(Animal an, String animalType, String regularOrCarrierType, int column)
    {
        if (regularOrCarrierType.equals("Regular")) {
            switch (animalType) {
                case "Terrestrial":
                    terrestrialRegular.add(an);
                    break;
                case "Water":
                    waterRegular.add(an);
                    break;
                case "Air":
                    airRegular.add(an);
                    break;
            }
        }else {
            switch (animalType) {
                case "Terrestrial":
                    if(terrestrialCourier.size()<= column)
                    {
                        terrestrialCourier.add(new ArrayList<>());
                    }
                    terrestrialCourier.get(column).add(an);
                    break;
                case "Water":
                    if (waterCourier.size()<= column){
                        waterCourier.add(new ArrayList<>());
                    }
                    waterCourier.get(column).add(an);
                    break;
                case "Air":
                    if (airCourier.size()<= column){
                        airCourier.add(new ArrayList<>());
                    }
                    airCourier.get(column).add(an);
                    break;
            }
        }
    }


        public void setRegularOrCarrierType(String regularOrCarrierType) {
        this.regularOrCarrierType = regularOrCarrierType;
    }

    public String getRegularOrCarrierType() {
        return regularOrCarrierType;
    }
    public AddCompetitionDialog getAddCompetitionDialog() {
        return addCompetitionDialog;
    }

    public void feedAnimalAndStartRace(Animal animal, int foodAmount){
        animal.eat(foodAmount);
        if (animal.getcurrentEnergy() > 0 && (timer == null || !timer.isRunning()))
        {
            startRace();
        }
    }

    public List<Animal> getCurrentRegularList(String type) {
        switch(type) {
            case "Terrestrial":
                return terrestrialRegular;
            case "Water":
                return waterRegular;
            case "Air":
                return airRegular;
            default:
                return new ArrayList<>();
        }
    }
}
