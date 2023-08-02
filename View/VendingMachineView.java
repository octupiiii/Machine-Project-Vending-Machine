package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachineView extends JPanel {

    private JButton backButton;

    public VendingMachineView() {
        setLayout(new BorderLayout());

        // Panel 1: Uneditable Text Field
        JPanel uneditableTextPanel = createUneditableTextPanel();
        uneditableTextPanel.setBorder(BorderFactory.createTitledBorder("Uneditable Text Field"));
        add(uneditableTextPanel, BorderLayout.NORTH);

        // Panel 2: Main Panel (for Buttons)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel 2.1: Add buttons
        JPanel panel1 = createPanelWithButtons();
        panel1.setBorder(BorderFactory.createTitledBorder("Choose a Product"));
        mainPanel.add(panel1, BorderLayout.CENTER);

        // Panel 2.2: Money buttons
        JPanel panel2 = createMoneyButtons();
        panel2.setBorder(BorderFactory.createTitledBorder("Insert Money"));
        mainPanel.add(panel2, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Panel 3: Black Panel
        JPanel blackPanel = new JPanel();
        backButton = new JButton("Cancel"); // Use the class-level backButton variable
        blackPanel.add(backButton); // Add the backButton to the blackPanel
        add(blackPanel, BorderLayout.SOUTH);
    }

    private JPanel createUneditableTextPanel() {
        JPanel panel = new JPanel();
        JTextField textField = new JTextField("This is an uneditable text field", 20);
        textField.setEditable(false);
        panel.add(textField);
        return panel;
    }

    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    private JPanel createPanelWithButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Add 9 buttons to the panel
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("Button " + i);
            button.addActionListener(new ButtonActionListener());
            panel.add(button);
        }

        return panel;
    }

    private JPanel createMoneyButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5));

        // Add 10 money buttons to the panel with specific denominations
        double[] denominations = { 0.25, 1, 5, 10, 20, 50, 100, 200, 500, 1000 };
        for (double denomination : denominations) {
            String buttonText = String.format("$%.2f", denomination);
            JButton button = new JButton(buttonText);
            button.addActionListener(new MoneyButtonActionListener(denomination));
            panel.add(button);
        }

        return panel;
    }

    private static class MoneyButtonActionListener implements ActionListener {
        private final double denomination;

        public MoneyButtonActionListener(double denomination) {
            this.denomination = denomination;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle money button click action here
            String buttonText = String.format("$%.2f", denomination);

            // Customize the JOptionPane options
            Object[] options = { "Add Item", "Close" };
            int choice = JOptionPane.showOptionDialog(null,
                    "Money button clicked: " + buttonText,
                    "Money Button Clicked",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                // If "Add Item" is clicked, show another JOptionPane to inform the user
                JOptionPane.showMessageDialog(null, "Item added with denomination: " + buttonText, "Item Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button click action here
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            // Customize the JOptionPane options
            Object[] options = { "Add Item", "Close" };
            int choice = JOptionPane.showOptionDialog(null,
                    "Button clicked: " + buttonText,
                    "Button Clicked",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                // If "Add Item" is clicked, show another JOptionPane to inform the user
                JOptionPane.showMessageDialog(null, "Item added: " + buttonText, "Item Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
