package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A dialog for clearing an animal from the competition.
 *
 * This dialog allows the user to select an animal from a JComboBox and confirms their choice
 * before removing the animal from the competition by pressing the "Clear Animal" button.
 */
public class ClearAnimalDialog  extends JDialog {

    private CompetitionFrame ref;
    private JPanel background;
    private JComboBox<String> animalsComobo;
    private JButton clearAnimalButton;

    /**
     * Constructs a ClearAnimalDialog.
     *
     * @param ref the CompetitionFrame that this dialog will interact with.
     * @param background the JPanel where the animal details will be applied.
     */

    public ClearAnimalDialog(CompetitionFrame ref, JPanel background) {
        this.ref = ref;
        this.background = background;
        setTitle("Clear Animal");
        setSize(400, 300);
        setLayout(new GridLayout(1,2));

        animalsComobo = new JComboBox<>(ref.getAnimalNamesAndType());
        JLabel animalLabel = new JLabel("Select animal:");

        add(animalLabel);
        add(animalsComobo);



        clearAnimalButton = new JButton("Clear Animal");
        clearAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(ref,
                        "Are you sure you want to clear?",
                        "Clear Confirmation", JOptionPane.OK_OPTION);
                if (choice == JOptionPane.OK_OPTION)
                {
                    ref.Clear( (String) animalsComobo.getSelectedItem());
                }
                setVisible(false); // Close the dialog

            }
        });
        add(clearAnimalButton);
    }

}
