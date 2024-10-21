package Graphics;

import Animals.Animal;
import OtherCompetitions.CourierTournament;
import OtherCompetitions.RegularTournament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A dialog for adding a competition to the competition frame.
 *
 * This dialog allows the user to select a competition type from a JComboBox and confirms their choice
 * by pressing the "Ok" button. It also provides a "Cancel" button to close the dialog without making a selection.
 */
public class AddCompetitionDialog extends JDialog {
    private JComboBox<String> competitions;
    private JComboBox<Integer> groupSizeComboBox; // ComboBox לבחירת גודל הקבוצה
    private JButton okButton;
    private JButton cancelButton;
    private JTextField groupNameField; // שדה להזנת שם הקבוצה
    private String type;
    private CompetitionFrame ref;
    private JPanel panelTable;
    private JTable table;
    private String regularOrCarrierType; // הוספנו את המשתנה כאן
    private DefaultTableModel tableModel;

    /**
     * Constructs an AddCompetitionDialog.
     *
     * @param ref the CompetitionFrame that this dialog will interact with.
     */
    public AddCompetitionDialog(CompetitionFrame ref)
    {
        this.ref = ref;

        setTitle("Add Competition");
        setSize(400, 300);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Group Name");
        tableModel.addColumn("Animal Name");
        tableModel.addColumn("Animal Type");


        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        String[] competitionNames = {"Terrestrial", "Water", "Air"};
        competitions = new JComboBox<>(competitionNames);

        // ComboBox לבחירת גודל הקבוצה
        groupSizeComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        groupSizeComboBox.setEnabled(false);

        JPanel competitionPanel = new JPanel();
        competitionPanel.add(new JLabel("Choose the Competition Name:"));
        competitionPanel.add(competitions);

        groupNameField = new JTextField(10);
        competitionPanel.add(new JLabel("Group Name:"));
        competitionPanel.add(groupNameField);

        JPanel panel = new JPanel();
        JRadioButton radioButton1 = new JRadioButton("Regular");
        JRadioButton radioButton2 = new JRadioButton("Courier");

        // Group the radio buttons so only one can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);

        // Add the radio buttons to the panel
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(new JLabel("Group Size:"));
        panel.add(groupSizeComboBox);

        add(panel, BorderLayout.NORTH);
        radioButton1.addActionListener(e -> groupSizeComboBox.setEnabled(false)); // Disable for regular competition
        radioButton2.addActionListener(e -> groupSizeComboBox.setEnabled(true));

        JPanel selectButton = new JPanel();
        okButton = new JButton("Refresh");
        okButton.addActionListener(e -> {
           type = (String) competitions.getSelectedItem();
            int groupSize = (int) groupSizeComboBox.getSelectedItem(); // Get selected group size

            String groupName = groupNameField.getText().trim();

            if (radioButton1.isSelected()) {
                //regular
               // type = (String) competitions.getSelectedItem();
                regularOrCarrierType = "Regular";
                ref.setRegularOrCarrierType(regularOrCarrierType);

                String[] columnNames = null;
                Object[][] data;
                int distanceNeeded = 0;
                switch (type){
                    case "Terrestrial":
                        data = ref.getRegularData(ref.terrestrialRegular);
                        columnNames = new String[]{"Add Animal to Group"};

                        ref.terrestrialRegularGroupName = groupName;
                        break;
                    case "Water":
                        data = ref.getRegularData(ref.waterRegular);
                        columnNames = new String[]{"Add Animal to Group"};

                        ref.waterRegularGroupName = groupName;
                        break;
                    case "Air":
                        data = ref.getRegularData(ref.airRegular);
                        columnNames = new String[]{"Add Animal to Group"};

                        ref.airRegularGroupName = groupName;
                        break;
                    default:
                        data = new Object[0][0];
                }

                tableModel = new DefaultTableModel(data, columnNames);//new
                table = new JTable(data, columnNames){
                    @Override
                    public JTableHeader getTableHeader() {
                        JTableHeader header = super.getTableHeader();
                        header.setDefaultRenderer(new ButtonHeaderRenderer(ref.getCurrentRegularList(type).size(),panelTable,table,ref,"Regular"));
                        return header;
                    }
                };
                JTableHeader header = table.getTableHeader();
                header.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        AddAnimalDialog animalDialog = new AddAnimalDialog(ref, regularOrCarrierType, 0,groupSize);
                        animalDialog.setVisible(true);
                    }
                });


                table.setBounds(30, 40, 200, 300);

                JScrollPane sp = new JScrollPane(table);
                panelTable.removeAll(); // Clear previous content
                panelTable.add(sp, BorderLayout.CENTER);
                panelTable.setPreferredSize(new Dimension(400, 200)); // גודל פאנל הטבלה
                table.setRowHeight(30); // גובה שורות
                table.getColumnModel().getColumn(0).setPreferredWidth(150); // רוחב עמודה 0
                panelTable.setVisible(true);
                panelTable.revalidate();
                panelTable.repaint();
                System.out.println("Table added successfully!");
                pack();

            } else if(radioButton2.isSelected()) {
                //currier
                // Column Names

                regularOrCarrierType = "Courier";
                ref.setRegularOrCarrierType(regularOrCarrierType);
                if (groupName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a group name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int numOfGroups;
                String[] columnNames = null;
                Object[][] data = null;

                type = (String) competitions.getSelectedItem();
                switch(type)
                {
                    case "Terrestrial":
                        if(ref.terrestrialCourier.size() == 5)
                        {
                            JOptionPane.showMessageDialog(ref, "The amount of Terrestrial groups is limited to 5", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        data = ref.getCourierData(ref.terrestrialCourier);
                        if(ref.terrestrialCourier.size() == 0)
                        {
                            columnNames = new String[]{"Add Animal to Group"};
                        }
                        else
                        {
                            numOfGroups = data[0].length + 1;
                            columnNames = new String[numOfGroups];
                        }
                        ref.terrestrialCourierGroupName = groupName;
                        break;

                    case "Water":
                        if(ref.waterCourier.size() == 4)
                        {
                            JOptionPane.showMessageDialog(ref, "The amount of Water groups is limited to 4", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        data = ref.getCourierData(ref.waterCourier);
                        if(ref.waterCourier.size() == 0)
                        {
                            columnNames = new String[]{"Add Animal to Group"};
                        }
                        else
                        {
                            numOfGroups = data[0].length + 1;
                            columnNames = new String[numOfGroups];
                        }
                        ref.waterCourierGroupName = groupName;
                        break;

                    case "Air":
                        data = ref.getCourierData(ref.airCourier);
                        if(ref.airCourier.size() == 0)
                        {
                            columnNames = new String[]{"Add Animal to Group"};
                        }
                        else
                        {
                            numOfGroups = data[0].length + 1;
                            columnNames = new String[numOfGroups];
                        }
                        ref.airCourierGroupName = groupName;
                        break;
                }

                tableModel = new DefaultTableModel(data, columnNames);
                table = new JTable(tableModel){
                    @Override
                    public JTableHeader getTableHeader() {
                        JTableHeader header = super.getTableHeader();
                        header.setDefaultRenderer(new ButtonHeaderRenderer(ref.getCurrentCourierList(type).size(),panelTable,table,ref,"Courier"));
                        return header;
                    }
                };

                JTableHeader header = table.getTableHeader();
                header.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        List<List<Animal>> ls = ref.getCurrentCourierList(type);
                        int col = table.columnAtPoint(e.getPoint());
                        if (ls.size() > col && ls.get(col).size() >= groupSize) {
                            JOptionPane.showMessageDialog(ref, "The group size is limited to " + groupSize + " animals.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (col == ls.size()) { // או כל עמודה אחרת שבה הכפתור נמצא
                            switch(type)
                            {
                                case "Terrestrial":
                                    if(ref.terrestrialCourier.size() == 5)
                                    {
                                        JOptionPane.showMessageDialog(ref, "The amount of Terrestrial groups is limited to 5", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;

                                case "Water":
                                    if(ref.waterCourier.size() == 4)
                                    {
                                        JOptionPane.showMessageDialog(ref, "The amount of Water groups is limited to 4", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;

                                case "Air":
                                    if(ref.airCourier.size() == 5)
                                    {
                                        JOptionPane.showMessageDialog(ref, "The amount of Air groups is limited to 5", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;
                            }

                            ls.add(new ArrayList<Animal>());
                        }
                        AddAnimalDialog animalDialog = new AddAnimalDialog(ref, regularOrCarrierType, col,groupSize);
                        animalDialog.setVisible(true);
                    }
                });


                table.setBounds(30, 40, 200, 300);

                JScrollPane sp = new JScrollPane(table);
                panelTable.removeAll(); // Clear previous content
                panelTable.add(sp, BorderLayout.CENTER);
                panelTable.setPreferredSize(new Dimension(400, 200)); // גודל פאנל הטבלה
                table.setRowHeight(30); // גובה שורות
                table.getColumnModel().getColumn(0).setPreferredWidth(150); // רוחב עמודה 0
                panelTable.setVisible(true);
                panelTable.revalidate();
                panelTable.repaint();
                System.out.println("Table added successfully!");
                pack();

            }else {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a competition type.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // מסיימים את הפונקציה כדי שהקוד למטה לא יתבצע
            }

            ref.setCounter(ref.getCounter() + 1);
            ref.setComp(type);




        });
        selectButton.add(okButton);


        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        selectButton.add(okButton);
        selectButton.add(cancelButton);

        JPanel x = new JPanel(new BorderLayout());
        x.add(competitionPanel, BorderLayout.CENTER);
        x.add(selectButton, BorderLayout.SOUTH);
        add(x,BorderLayout.CENTER);
//        add(competitionPanel, BorderLayout.CENTER);
//        add(selectButton, BorderLayout.SOUTH);

        panelTable = new JPanel(new BorderLayout());
        panelTable.setVisible(false);
        add(panelTable, BorderLayout.SOUTH); // This ensures the table panel appears below the ComboBox and Radio buttons
        setLocationRelativeTo(ref);

    }


    /**
     * Gets the selected competition name.
     *
     * @return the name of the selected competition.
     */
    public String getCompetitionName() {
        return type;
    }


    public String getRegularOrCarrierType() {
        return regularOrCarrierType;
    }

    public void setRegularOrCarrierType(String regularOrCarrierType) {
        this.regularOrCarrierType = regularOrCarrierType;
    }

    public DefaultTableModel getTableModel() {return tableModel;}

    public JTable getTable(){
        return table;
    }

}


class ButtonHeaderRenderer implements TableCellRenderer {
    private JTable table;
    private CompetitionFrame ref;
    private String regularOrCarrierType;
    private JPanel panelTable;
    private int size;

    public ButtonHeaderRenderer(int size,JPanel panelTable,JTable table,CompetitionFrame ref,String regularOrCarrierType) {
        this.size = size;
        this.panelTable = panelTable;
        this.table = table;
        this.ref=ref;
        this.regularOrCarrierType=regularOrCarrierType;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == size)
        {
            return new JButton("Create new group");
        }

        return new JButton("Add to Group " + (column+1));



//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button in column " + column + " clicked");
//                // You can add your specific action here for each button click
//                AddAnimalDialog animalDialog = new AddAnimalDialog(ref,regularOrCarrierType,column);
//                animalDialog.setVisible(true);
//                JScrollPane sp = new JScrollPane(table);
//                panelTable.removeAll(); // Clear previous content
//                panelTable.add(sp, BorderLayout.CENTER);
//                panelTable.revalidate();
//                panelTable.repaint();
//            }
//        });
        //return button;
    }
}


//class ButtonHeaderRenderer implements TableCellRenderer {
//    private JTable table;
//    private CompetitionFrame ref;
//    private String regularOrCarrierType;
//    private JButton button;
//
//    public ButtonHeaderRenderer(JTable table, CompetitionFrame ref, String regularOrCarrierType) {
//        this.table = table;
//        this.ref = ref;
//        this.regularOrCarrierType = regularOrCarrierType;
//        this.button = new JButton("+");
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open the AddAnimalDialog with the current competition type and column
//                AddAnimalDialog animalDialog = new AddAnimalDialog(ref, regularOrCarrierType, 0);
//                animalDialog.setVisible(true);
//            }
//        });
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        return button;
//    }
//}
//

