package Graphics;
import Animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
/**
 * A panel that provides controls for managing the competition, including options to add competitions, animals,
 * clear animals, feed animals, view information, and exit the application.
 */
public class CompetitionPanel extends JPanel {
    private JButton addCompetition;
    private JButton addAnimal;
    private JButton clear;
    private JButton eat;
    private JButton info;
    private JButton exit;
    private JButton play;
    private BufferedImage img = null;
    private String typeOfCompetition ;
    private CompetitionFrame ref;
    private String regularOrCarrierType;
    JPanel panelTable;



    /**
     * Constructs a CompetitionPanel with buttons for managing the competition.
     *
     * @param ref the reference to the CompetitionFrame
     * @param background the background panel for the competition
     */
    public CompetitionPanel(CompetitionFrame ref, JPanel background) {
        this.ref = ref;

        setLayout(new GridLayout(1, 4));
        addCompetition = new JButton("Add Competition");
        addCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddCompetitionDialog competitionDialog = new AddCompetitionDialog( ref);
                competitionDialog.setVisible(true);
                typeOfCompetition = competitionDialog.getCompetitionName();
                regularOrCarrierType = competitionDialog.getRegularOrCarrierType(); // עדכון של regularOrCarrierType
                ref.setRegularOrCarrierType(regularOrCarrierType);
                //ref.setUpTimer();



            }

        });


        addAnimal = new JButton("Scores");
        addAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame f = new JFrame("Competition Results: " + typeOfCompetition);
                f.setLayout(new GridLayout(8,2));
                f.setSize(500, 200);
                f.setVisible(true);
            }
        });


        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClearAnimalDialog clearAnimalDialog = new ClearAnimalDialog(ref, background);
                clearAnimalDialog.setVisible(true);
            }
        });

        play = new JButton("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //ref.setUpTimer();
               //ref.startRace();
                ref.startTornament();
            }
        });


        eat = new JButton("Eat");
        eat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Eat");
                f.setLayout(new GridLayout(3, 2));
                JComboBox<String> animalsComobo = new JComboBox<>(ref.getAnimalNamesAndType());
                JLabel animalLabel = new JLabel("Select animal:");

                f.add(animalLabel);
                f.add(animalsComobo);

                JLabel eatLabel = new JLabel("Enter amount to feed:");
                JTextField eatField = new JTextField();
                f.add(eatLabel);
                f.add(eatField);

                JButton feedAnimalButton = new JButton("Feed Animal");
                feedAnimalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedAnimalName = (String) animalsComobo.getSelectedItem();
                        if (selectedAnimalName != null && !eatField.getText().isEmpty()) {
                            try {
                                int amount = Integer.parseInt(eatField.getText());

                                for (Animal animal : ref.getAnimalList()) {
                                    if (animal.getNameAndType().equals(selectedAnimalName)) {
                                        ref.feedAnimalAndStartRace(animal, amount);
                                        break;
                                    }
                                }
                                f.setVisible(false);
                            } catch (NumberFormatException err) {
                                JOptionPane.showMessageDialog(f, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(f, "Please select an animal and enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                f.add(feedAnimalButton);

                // Frame Size
                f.setSize(500, 200);
                // Frame Visible = true
                f.setVisible(true);
            }
        });


        info = new JButton("Info");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Info");
                Object[][] data = ref.getData();


                // Column Names
                String[] columnNames = { "Animal", "Category", "Type","Speed", "Amount", "Distance", "Energy Consumption" };

                // Initializing the JTable
                JTable j = new JTable(data, columnNames);
                j.setBounds(30, 40, 200, 300);

                // adding it to JScrollPane
                JScrollPane sp = new JScrollPane(j);
                f.add(sp);
                // Frame Size
                f.setSize(500, 200);
                // Frame Visible = true
                f.setVisible(true);

            }
        });



        exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));


        add(addCompetition);
        add(addAnimal);
        add(clear);
        add(play);
        add(eat);
        add(info);
        add(exit);
        setVisible(true);

        panelTable = new JPanel(new BorderLayout());
        panelTable.setVisible(false);
        add(panelTable, BorderLayout.SOUTH);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        for (Animal animal: ref.getAnimalList())
        {
            animal.drawObject(g);
        }

    }

    public String getTypeOfCompetition() {
        return typeOfCompetition;
    }

    public void updateTable(JScrollPane newTableScrollPane) {
        panelTable.removeAll();
        panelTable.add(newTableScrollPane, BorderLayout.CENTER);
        panelTable.setVisible(true);
        panelTable.revalidate();
        panelTable.repaint();
    }


}
