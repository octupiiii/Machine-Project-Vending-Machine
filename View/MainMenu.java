package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.VMController;

public class MainMenu extends JPanel {

    private JButton createButton;
    private JButton testButton;
    private JButton exitButton;

    public MainMenu() {

        createButton = new JButton("Create Vending Machine");
        createButton.setPreferredSize(new Dimension(278, 40));

        testButton = new JButton("Test Vending Machine");
        testButton.setPreferredSize(new Dimension(278, 40));

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(278, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(createButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        add(testButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(exitButton, gridBagConstraints);

    }

    public void createVM(ActionListener actionListener) {
        createButton.addActionListener(actionListener); // This sets up the ActionListener to be triggered on button
                                                        // click
    }

    public void testVM(ActionListener actionListener) {
        testButton.addActionListener(actionListener);
    }

    public void exit(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

}
