package View;

import javax.swing.*;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachineView extends JPanel {

    private VendingMachine vendingMachine;
    private JButton backButton;
    private JTextField totalAmountField;
    private double totalAmount = 0.0;

    public VendingMachineView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        setLayout(new BorderLayout());

        // Panel 1: Uneditable Text Field
        JPanel displayAmountPanel = createTotalAmountPanel();
        displayAmountPanel.setBorder(BorderFactory.createTitledBorder("Uneditable Text Field"));
        add(displayAmountPanel, BorderLayout.NORTH);

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
        JPanel centerButtonPanel = createCenterButtonPanel();
        JPanel blackPanel = new JPanel();
        backButton = new JButton("Cancel"); // Use the class-level backButton variable
        blackPanel.add(centerButtonPanel);
        blackPanel.add(backButton); // Add the backButton to the blackPanel
        add(blackPanel, BorderLayout.SOUTH);

    }

    private JPanel createCenterButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align the button
        JButton centerButton = new JButton("Special Ice Cream");
        panel.add(centerButton);
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
            String itemName = vendingMachine.getItemSlot().get(i - 1).getDesignatedItem().getName() + i;
            JButton button = new JButton(itemName);
            button.addActionListener(new ButtonActionListener());
            panel.add(button);
        }

        return panel;
    }

    // private JPanel createPanelWithButtons() {
    // JPanel panel = new JPanel();
    // panel.setLayout(new GridLayout(3, 3));

    // for (int i = 0; i < vendingMachine.getItemSlot().size(); i++) {
    // System.out.print("hi");
    // SlotModel slot = vendingMachine.getItemSlot().get(i);
    // JButton button;
    // if (slot.getDesignatedItem().getName() != null) {
    // button = new JButton(slot.getDesignatedItem().getName()); // Use the itemname
    // as the button text
    // } else {
    // button = new JButton(String.valueOf(i));
    // }
    // button.addActionListener(new ButtonActionListener());
    // panel.add(button);
    // }

    // return panel;
    // }

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

    private class MoneyButtonActionListener implements ActionListener {
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
                // update Total amount
                totalAmount += denomination;
                System.out.println("HELLO  " + vendingMachine.getItemSlot().get(4).getDesignatedItem().getName());
                // If "Add Item" is clicked, show another JOptionPane to inform the user
                JOptionPane.showMessageDialog(null, "Item added with denomination: " + buttonText, "Item Added",
                        JOptionPane.INFORMATION_MESSAGE);
                totalAmountField.setText("Total Amount: PHP " + String.format("%.2f", totalAmount));

            }
        }
    }

    private JPanel createTotalAmountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        totalAmountField = new JTextField("Total Amount: PHP 0.00", 15);
        totalAmountField.setEditable(false);
        panel.add(totalAmountField);

        return panel;
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button click action here
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            int index = Integer.parseInt(buttonText) - 1;

            // Check if the index is within the bounds of the list
            if (index >= 0 && index < vendingMachine.getItemSlot().size()) {
                // Customize the JOptionPane options
                Object[] options = { "Buy", "Cancel" };
                int choice = JOptionPane.showOptionDialog(null,
                        "Button clicked: " + buttonText,
                        "Button Clicked",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice == JOptionPane.YES_OPTION) {
                    // Process the purchase
                    ItemModel item = vendingMachine.getItemSlot().get(index).getDesignatedItem();
                    if (item != null) {
                        double itemPrice = item.getPrice();
                        if (totalAmount >= itemPrice) {
                            totalAmount -= itemPrice;
                            totalAmountField.setText("Total Amount: PHP " + String.format("%.2f", totalAmount));
                            // Update the button text to show that the item is sold
                            button.setText(buttonText + " (Sold)");
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient funds. Please insert more money.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Item is not available in the vending machine
                        JOptionPane.showMessageDialog(null, "Item not available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public VendingMachine getVendingMachine() {
        return this.vendingMachine;
    }

}
