package Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Animals.*;
import Mobility.Point;

import static Animals.Animal.Orientation.*;

/**
 * A dialog window that allows users to add animals to a competition.
 * The dialog provides options to select the type of competition, choose the animal,
 * and specify details relevant to the selected animal.
 * It also updates the UI dynamically based on user selections.
 */
public class AddAnimalDialog extends JDialog
{
    private JComboBox<String> competitionType;//שומר על סוגי תחרויות
    private JComboBox<String> animals;
    private JButton addAnimalButton;
    private JTextField nameField; // שדה טקסט עבור שם החיה
    private JTextField lengthField; // שדה טקסט עבור שם החיה
    private JTextField speedField;
    private JTextField breedField;
    private JTextField wingspanField;
    private JPanel dynamicFieldsPanel; // פאנל לשדות הדינמיים
    private JTextField energyConField;
    private JTextField weightField;
    private JComboBox<String> waterTypeField;
    private JTextField diveDeptField;
    private JComboBox<String> genderField;
    private JComboBox<String> poisonousTypeField;
    private JTextField foodTypeField;
    private JTextField altitudeOfFlightField;
    private JTextField AreaLivingField;
    private JTextField castratedField;
    private JTextField familyField;
    private String selectedAnimal;// שדה ששומר על חיה
    private CompetitionFrame ref;
    private BufferedImage image;
    private String typeCompetition ;
    private int choice;
    private CompetitionPanel competitionPanel ;
    private String regularOrCarrierType;
    private int column;
    private int groupSize;
    //private DefaultTableModel tableModel; // tableModel שמועבר מ-AddCompetitionDialog
//    private static int counterWaterAnimal = 0;
//    private static int counterAirAnimal = 0;


    /**
     * Constructs an AddAnimalDialog with specified reference to CompetitionFrame and background panel.
     *
     * @param ref       the reference to the CompetitionFrame.
     */
    public AddAnimalDialog(CompetitionFrame ref, String regularOrCarrierType,int column, int groupSize)
    {
        this.groupSize = groupSize;
        this.ref = ref;
        this.column = column;
        this.typeCompetition =ref.getComp();
        this.competitionPanel =ref.getCompetitionPanel();
        this.regularOrCarrierType = regularOrCarrierType;

        //this.tableModel =null;

        setTitle("Add Animal");
        setSize(400, 300);
        setLayout(new GridLayout(10,2));

        competitionType = new JComboBox<>(new String[] {"Terrestrial", "Water", "Air"});
        JLabel animalTypeLabel = new JLabel("Select animal type:");
        animals = new JComboBox<>();
        animals.setEnabled(false);


        // Action Listener for competition type selection
        competitionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeCompetition = (String)competitionType.getSelectedItem();//חדש
                updateAnimalComboBox(); // Update animal options based on selected competition type
                animals.setEnabled(true); // הפיכת רכיב החיות לזמין

            }
        });
//

            add(new JLabel("Enter the Competition Type:"));
            add(competitionType);
            add(animalTypeLabel);
            add(animals);


            nameField = new JTextField();
            speedField = new JTextField();
            energyConField = new JTextField();
            genderField = new JComboBox<>(new String[] {"Male", "Female", "Hermaphrodite"});

            weightField = new JTextField();

            add(new JLabel("Enter Animal Name:"));
            add(nameField);
            add(new JLabel("Enter Animal Speed:"));
            add(speedField);
            add(new JLabel("Enter Animal Energy Consumption:"));
            add(energyConField);
            add(new JLabel("Enter Animal Gender:"));
            add(genderField);
            add(new JLabel("Enter Animal Weight:"));
            add(weightField);

            dynamicFieldsPanel = new JPanel();
            dynamicFieldsPanel.setLayout(new GridLayout(1,1));
            add(dynamicFieldsPanel);


            addAnimalButton = new JButton("Select");
            addAnimalButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (typeCompetition == null || typeCompetition.equals("Select Competition Type")) {
                            showErrorDialog("Please select a competition type first.");
                            return;
                        }
                        addAnimalToCompetition();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AddAnimalDialog.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(false); // Close the dialog
                }
            });
            add(addAnimalButton);





        animals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDynamicFields();
            }
        });
    }
    /**
     * Checks if the selected animal is valid for the specified competition type.
     *
     * @param selectedCompetition the competition type.
     * @param selectedAnimal      the animal to check.
     * @return true if the animal is valid for the competition type; false otherwise.
     */
    private boolean isValidAnimalForCompetition(String selectedCompetition, String selectedAnimal) {
        switch (selectedCompetition) {
            case "Terrestrial":
                return selectedAnimal.equals("Alligator") || selectedAnimal.equals("Snake") || selectedAnimal.equals("Cat") || selectedAnimal.equals("Dog");
            case "Water":
                return selectedAnimal.equals("Dolphin") || selectedAnimal.equals("Whale") || selectedAnimal.equals("Alligator");
            case "Air":
                return selectedAnimal.equals("Eagle") || selectedAnimal.equals("Pigeon");
            default:
                return false;
        }
    }

    /**
     * Displays an error dialog with the specified message.
     *
     * @param message the error message to display.
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        dispose(); // Close the dialog
    }


    /**
     * Updates the animal selection options based on the selected competition type.
     */
    private void updateAnimalComboBox() {
        if (typeCompetition == null || typeCompetition.equals("Select Competition Type")) {
            animals.setEnabled(false); // Disable animal selection
            return;
        }
        animals.removeAllItems(); // Clear existing items
        String selectedCompetition = (String) competitionType.getSelectedItem();

        switch (selectedCompetition) {

            case "Terrestrial":
                animals.addItem("Alligator");
                animals.addItem("Snake");
                animals.addItem("Cat");
                animals.addItem("Dog");
                break;
            case "Water":
                animals.addItem("Dolphin");
                animals.addItem("Whale");
                animals.addItem("Alligator");
                break;
            case "Air":
                animals.addItem("Eagle");
                animals.addItem("Pigeon");
                break;
            default:
                break;
        }
        animals.setEnabled(true); // הפעלת בחירת חיות לאחר שינוי סוג התחרות
    }



    /**
     * Updates the dynamic fields based on the selected animal.
     */
    private void updateDynamicFields() {
        String selectedAnimal = (String) animals.getSelectedItem();

        dynamicFieldsPanel.removeAll();

        if (selectedAnimal == null) {
            dynamicFieldsPanel.revalidate();
            dynamicFieldsPanel.repaint();
            return;
        }


        switch (selectedAnimal)
        {
            case "Dog":
                breedField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter Breed:"));
                dynamicFieldsPanel.add(breedField);
                break;

            case "Snake":
                poisonousTypeField = new JComboBox<>(new String[] {"low", "medium", "high"});
                lengthField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Poison Level:"));
                dynamicFieldsPanel.add(poisonousTypeField);
                dynamicFieldsPanel.add(new JLabel("Enter the length of snake"));
                dynamicFieldsPanel.add(lengthField);
                break;

            case "Cat":
                castratedField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Cat castrated : Print yes or no"));
                dynamicFieldsPanel.add(castratedField);
                break;

            case "Alligator":
                AreaLivingField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Area living"));
                dynamicFieldsPanel.add(AreaLivingField);

                diveDeptField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the drive dept"));
                dynamicFieldsPanel.add(diveDeptField);
                break;

            case "Dolphin":
                waterTypeField =new JComboBox<>(new String[] {"Sea", "Sweet"});
                dynamicFieldsPanel.add(new JLabel("Water Type:"));
                dynamicFieldsPanel.add(waterTypeField);

                diveDeptField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the drive dept"));
                dynamicFieldsPanel.add(diveDeptField);
                break;

            case "Whale":
                 foodTypeField = new JTextField();
                 dynamicFieldsPanel.add(new JLabel("Enter the food"));
                 dynamicFieldsPanel.add(foodTypeField);

                diveDeptField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the drive dept"));
                dynamicFieldsPanel.add(diveDeptField);
                 break;

            case "Eagle":
                altitudeOfFlightField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the altitude of flight"));
                dynamicFieldsPanel.add(altitudeOfFlightField);
                wingspanField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the wingspan"));
                dynamicFieldsPanel.add(wingspanField);
                break;

            case "Pigeon":
                familyField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter name of family"));
                dynamicFieldsPanel.add(familyField);

                wingspanField = new JTextField();
                dynamicFieldsPanel.add(new JLabel("Enter the wingspan"));
                dynamicFieldsPanel.add(wingspanField);
                break;
        }
        dynamicFieldsPanel.revalidate();
        dynamicFieldsPanel.repaint();
    }



    public String getSelectedAnimal() {
        return selectedAnimal;
    }




    /**
     * Adds the selected animal to the competition and updates the UI.
     *
     * @throws Exception if there is an error while adding the animal.
     */
    private void addAnimalToCompetition() {
        System.out.println("regularOrCarrierType: " + regularOrCarrierType);
        System.out.println("typeCompetition: " + typeCompetition);
        if(regularOrCarrierType == null || regularOrCarrierType.isEmpty())
        {
            showErrorDialog("Competition type is not specified or incorrect.");
            return;
        }

        String selectedAnimal = (String) animals.getSelectedItem();
        String selectedCompetition = (String) competitionType.getSelectedItem();
        String currentCompetitionType = ref.getComp(); // קבלת סוג התחרות הנוכחי



        if (typeCompetition == null || typeCompetition.isEmpty()) {
            showErrorDialog("Please select a competition type.");
            return;
        }


        if (!isValidAnimalForCompetition(typeCompetition, selectedAnimal)) {
            showErrorDialog("The selected animal is not suitable for " + typeCompetition + " competition.");
            return;
        }


        if (!selectedCompetition.equals(currentCompetitionType)) {
            showErrorDialog("The selected animal is not suitable for the current competition type.");
            return;
        }


        String name = nameField.getText();
        double speed = Double.parseDouble(speedField.getText());
        int energyConsumption = Integer.parseInt(energyConField.getText());
        String genderString = (String) genderField.getSelectedItem();
        Animal.Gender gender = null;


        switch(genderString)
        {
            case "Male":
                gender = Animal.Gender.Male;
                break;

            case "Female":
                gender = Animal.Gender.Female;
                break;

            case "Hermaphrodite":
                gender = Animal.Gender.Hermaphrodite;
                break;
        }

        double weight = Double.parseDouble(weightField.getText());
        Animal an = null;

        switch (selectedCompetition) {//selectedCompetition
            case "Terrestrial":
                int yOffsetTerrestrial = 15;
                int xOffsetTerrestrial = 0;
                Animal.Orientation ori = EAST;
                if(regularOrCarrierType != "Regular")
                {
                    int totalRaceLen = 1430 * 2 + 730*2;
                    int totalOffset = (totalRaceLen / groupSize) * ref.getCurrentCourierList("Terrestrial").get(column).size();
                    if(totalOffset < 1430)
                    {
                        //on top track
                        xOffsetTerrestrial = totalOffset;
                        ori = EAST;
                    }
                    else if(totalOffset < 1430 + 730){
                        //on right track
                        xOffsetTerrestrial = 1400;
                        yOffsetTerrestrial = totalOffset - 1430;
                        ori = SOUTH;
                    }
                    else if(totalOffset < 1430*2 + 730){
                        //on bottom track
                        yOffsetTerrestrial = 675;
                        xOffsetTerrestrial = 1430 - (totalOffset - 1430 - 675);
                        ori = WEST;

                    }
                    else {
                        //on left track
                        xOffsetTerrestrial = 0;
                        yOffsetTerrestrial = 765 - (totalOffset - 1430*2 - 675);
                        ori = NORTH;
                    }

                }


                if (selectedAnimal.equals("Alligator"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/AlligatorE.png"));
                        BufferedImage im2 = ImageIO.read(new File("src/Resourses/AlligatorS.png"));
                        BufferedImage im3 = ImageIO.read(new File("src/Resourses/AlligatorW.png"));
                        BufferedImage im4 = ImageIO.read(new File("src/Resourses/AlligatorN.png"));
                        String AreaLiving = AreaLivingField.getText();
                        an = new Alligator(name,gender,weight,speed,null,new Point(xOffsetTerrestrial,yOffsetTerrestrial),123, ori,100,energyConsumption,competitionPanel,im1,im2,im3,im4,AreaLiving,-600);
                        ref.setTerrestrialCounter(ref.getTerrestrialCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Terrestrial",regularOrCarrierType,column);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                else if (selectedAnimal.equals("Snake"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/SnakeE.png"));
                        BufferedImage im2 = ImageIO.read(new File("src/Resourses/SnakeS.png"));
                        BufferedImage im3 = ImageIO.read(new File("src/Resourses/SnakeW.png"));
                        BufferedImage im4 = ImageIO.read(new File("src/Resourses/SnakeN.png"));
                        Snake.Poisonous poisonous = Snake.Poisonous.valueOf((String) poisonousTypeField.getSelectedItem());
                        int lenght = Integer.parseInt(lengthField.getText());
                        an = new Snake(name,gender,weight,speed,null,new Point(xOffsetTerrestrial,yOffsetTerrestrial),123, ori,100,energyConsumption,competitionPanel,im1,im2,im3,im4,0.0, 0,lenght,poisonous);
                        ref.setTerrestrialCounter(ref.getTerrestrialCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Terrestrial",regularOrCarrierType,column);

                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                else if (selectedAnimal.equals("Dog"))
                {

                    try
                    {

                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/dogE.png"));
                        BufferedImage im2 = ImageIO.read(new File("src/Resourses/dogS.png"));
                        BufferedImage im3 = ImageIO.read(new File("src/Resourses/dogW.png"));
                        BufferedImage im4 = ImageIO.read(new File("src/Resourses/dogN.png"));
                        String breed = breedField.getText();
                        an = new Dog(name,gender,weight,speed,null,new Point(xOffsetTerrestrial,yOffsetTerrestrial),123, ori,100,energyConsumption,competitionPanel,im1,im2,im3,im4,4,0.0,breed);
                        ref.setTerrestrialCounter(ref.getTerrestrialCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Terrestrial",regularOrCarrierType,column);

                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                else if (selectedAnimal.equals("Cat"))
                {

                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/CatE.png"));
                        BufferedImage im2 = ImageIO.read(new File("src/Resourses/CatS.png"));
                        BufferedImage im3 = ImageIO.read(new File("src/Resourses/CatW.png"));
                        BufferedImage im4 = ImageIO.read(new File("src/Resourses/CatN.png"));
                        boolean castrated = Boolean.parseBoolean(castratedField.getText());
                        an = new Cat(name,gender,weight,speed,null,new Point(xOffsetTerrestrial,yOffsetTerrestrial),123, ori,100,energyConsumption,competitionPanel,im1,im2,im3,im4,0.0,4,castrated);
                        ref.setTerrestrialCounter(ref.getTerrestrialCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Terrestrial",regularOrCarrierType,column);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                break;
            case "Water":
                int yOffsetWater;
                int xOffsetWater = 140;
                if(regularOrCarrierType == "Regular")
                {
                    yOffsetWater = 100 + 160 * (ref.getCurrentRegularList("Water").size()-1); // קביעת המיקום לפי המסלול שנבחר
                }
                else
                {
                    yOffsetWater = 100 + 160 * (ref.getCurrentCourierList("Water").size()-1); // קביעת המיקום לפי המסלול שנבחר
                    xOffsetWater += (1220 / groupSize) * ref.getCurrentCourierList("Water").get(column).size();
                }


                if (selectedAnimal.equals("Alligator"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/AlligatorE.png"));
                        double diveDept = Double.parseDouble(diveDeptField.getText());
                        an = new Alligator(name,gender,weight,speed,null,new Point(xOffsetWater,yOffsetWater),123, EAST,100,energyConsumption,competitionPanel,im1,null,null,null,"Sea",-diveDept);
                        ref.setWaterCounter(ref.getWaterCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Water",regularOrCarrierType,column);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }

                }
                else if (selectedAnimal.equals("Whale"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/whale2.png"));

                        double diveDept = Double.parseDouble(diveDeptField.getText());
                        String foodType = foodTypeField.getText();
                        an = new Whale(name,gender,weight,speed,null,new Point(xOffsetWater,yOffsetWater),123, EAST,100,energyConsumption,competitionPanel,im1,diveDept,0.0,foodType);
                        ref.setWaterCounter(ref.getWaterCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Water",regularOrCarrierType,column);

                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                else if (selectedAnimal.equals("Dolphin"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/dolphin1.png"));
                        double diveDept = Double.parseDouble(diveDeptField.getText());
                        Dolphin.WaterType waterType = Dolphin.WaterType.valueOf((String) waterTypeField.getSelectedItem());
                        an = new Dolphin(name,gender,weight,speed,null,new Point(xOffsetWater,yOffsetWater),123, EAST,100,energyConsumption,competitionPanel,im1,0.0,diveDept,waterType);
                        ref.setWaterCounter(ref.getWaterCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Water",regularOrCarrierType,column);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                break;
            case "Air":
                int yOffsetAir = 25;
                int xOffsetAir = 0;
                if(regularOrCarrierType == "Regular")
                {
                    yOffsetAir += 160 * (ref.getCurrentRegularList("Air").size()); // קביעת המיקום לפי המסלול שנבחר
                }
                else
                {
                    yOffsetAir += 160 * (ref.getCurrentCourierList("Air").size()-1); // קביעת המיקום לפי המסלול שנבחר
                    xOffsetAir += (1550 / groupSize) * ref.getCurrentCourierList("Air").get(column).size();
                }

                if (selectedAnimal.equals("Eagle"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/eagle2.png"));
                        double altitudeOfFlight = Double.parseDouble(altitudeOfFlightField.getText());
                        double wingspan = Double.parseDouble(wingspanField.getText());
                        an = new Eagle(name,gender,weight,speed,null,new Point(xOffsetAir,yOffsetAir),123, EAST,100,energyConsumption,competitionPanel,im1,wingspan,altitudeOfFlight);
                        ref.setAirCounter(ref.getAirCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Air",regularOrCarrierType,column);

                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                else if (selectedAnimal.equals("Pigeon"))
                {
                    try
                    {
                        BufferedImage im1 = ImageIO.read(new File("src/Resourses/pigeon.png"));
                        double wingspan = Double.parseDouble(wingspanField.getText());
                        String family = familyField.getText();
                        an = new Pigeon(name,gender,weight,speed,null,new Point(xOffsetAir,yOffsetAir),123, EAST,100,energyConsumption,competitionPanel,im1,wingspan,family);
                        ref.setAirCounter(ref.getAirCounter() + 1);
                        ref.addAnimalToSpecificList(an,"Air",regularOrCarrierType,column);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: Unable to load background image.");
                        System.exit( 1);
                    }
                }
                break;
            default:
                break;
        }

        if (an != null ) {
            ref.addAnimalToList(an);
            Object[] newRow = {an.getName(), an.getType()}; // או כל נתון אחר שתרצה להציג
            //tableModel.addRow(newRow);
            ref.getAddCompetitionDialog().getTableModel().addRow(newRow);
            ref.getAddCompetitionDialog().getTable().revalidate();
            ref.getAddCompetitionDialog().getTable().repaint();

        } else {
            showErrorDialog("Error: Unable to add animal.");
        }

    }
    /**
     * Returns the preferred size of the panel based on the loaded image.
     *
     * This method overrides the default behavior to return a preferred size that matches the dimensions of
     * the loaded image. If no image has been loaded, it returns the default preferred size of the superclass.
     *
     * @return the preferred size of the panel, or the default preferred size if no image is loaded.
     */
    public Dimension getPreferredSize()
    {
        if (image != null)
            return new Dimension(image.getWidth(), image.getHeight());
        return super.getPreferredSize();
    }

}

