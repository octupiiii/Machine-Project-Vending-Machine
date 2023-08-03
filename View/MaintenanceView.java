package View;

import javax.swing.*;

import Model.*;
import Controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MaintenanceView extends JPanel {

    private VendingMachine vendingMachine;
    private JButton backButton;
    private JTextField totalAmountField;
    private double totalAmount = 0.0;
    private ItemModel[] tempSlots;

    public MaintenanceView(VendingMachine vendingMachine) {
        setLayout(new BorderLayout());
        this.vendingMachine = vendingMachine;
        this.tempSlots = new ItemModel[9];

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
            JButton button = new JButton(Integer.toString(i));
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
            try {
                int intIndex = Integer.parseInt(buttonText);
                if (intIndex >= 0 && intIndex <= 9) {
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
                        // If "Add Item" is clicked, show another JOptionPane to get item details
                        JPanel panel = new JPanel(new GridLayout(4, 2));
                        panel.add(new JLabel("Name:"));
                        JTextField nameField = new JTextField(15);
                        panel.add(nameField);

                        panel.add(new JLabel("Price:"));
                        JTextField priceField = new JTextField(15);
                        panel.add(priceField);

                        panel.add(new JLabel("Calories:"));
                        JTextField caloriesField = new JTextField(15);
                        panel.add(caloriesField);

                        panel.add(new JLabel("Stock:"));
                        JTextField stockField = new JTextField(15);
                        panel.add(stockField);

                        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Item Details",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            // Process the entered item details here
                            String name = nameField.getText();
                            double price = 0.0;
                            double calories = 0.0;
                            int stock = 0;

                            try {
                                price = Double.parseDouble(priceField.getText());
                                calories = Double.parseDouble(caloriesField.getText());
                                stock = Integer.parseInt(stockField.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid input. Please enter valid numbers for price, calories, and stock.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Here you can use the name, price, calories, and stock variables as needed //
                            // update

                            ItemModel tempItem = new ItemModel(name, price, calories);
                            vendingMachine.getItemSlot().get(intIndex - 1).setItem(tempItem);
                            vendingMachine.getItemSlot().get(intIndex-1).addQuantity(stock);

                            tempSlots[intIndex-1] = tempItem;

                            // Update the button text to the item name
                            button.setText(name);
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                String[] options2 = { "Restock Item", "Reprice Item", "Cancel" };

                // Show the option dialog
                

                int choice2 = JOptionPane.showOptionDialog(null,
                        "Please select an option:",
                        "Three Options",
                        JOptionPane.YES_NO_CANCEL_OPTION, // Specify the option type
                        JOptionPane.QUESTION_MESSAGE, // Specify the message type
                        null, // Use the default icon
                        options2, // Provide the option buttons
                        options2[0]); // Set the default option (Optional)

                // Handle the user's choice
                if (choice2 == JOptionPane.YES_OPTION) { 
                    // Restock
                    String itemName = buttonText;
                    int itemIndex = -1;
    
                    // Find the index of the item with the given name
                    for (int i = 0; i < tempSlots.length; i++) {
                        if (tempSlots[i] != null && tempSlots[i].getName().equals(itemName)) {
                            itemIndex = i;
                            break;
                        }
                    }

                 
        
    
                    if (itemIndex != -1) {
                        ItemModel item = vendingMachine.getItemSlot().get(itemIndex).getDesignatedItem();
                        String itemDetails = "Name: " + item.getName() + "\n"
                + "Price: $" + item.getPrice() + "\n"
                + "Calories: " + item.getCalories() + "\n"
                + "Stock: " + vendingMachine.getItemSlot().get(itemIndex).getItemQuantity() + "\n";

        JOptionPane.showMessageDialog(null, itemDetails, "Item Details", JOptionPane.INFORMATION_MESSAGE);

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.add(new JLabel("Restock:"));
        JTextField stockField = new JTextField(5);
        panel2.add(stockField);

        int result = JOptionPane.showConfirmDialog(null, panel2, "Restock Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
                        if (result == JOptionPane.OK_OPTION) {
                            int quantity = 0;
                            try {
                                quantity = Integer.parseInt(stockField.getText());
                            } catch (NumberFormatException ex1) {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid input. Please enter a valid number.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
    
                            // Get the corresponding item slot from the VendingMachine
                            if (itemIndex >= 0 && itemIndex < vendingMachine.getItemSlot().size()) {
                                SlotModel itemSlot = vendingMachine.getItemSlot().get(itemIndex);
                                itemSlot.addQuantity(quantity);
    
                                // Show a message to inform the user about the restock
                                JOptionPane.showMessageDialog(null,
                                        "Successfully restocked " + quantity + " items for " + itemSlot.getDesignatedItem().getName(),
                                        "Restock Successful", JOptionPane.INFORMATION_MESSAGE);

                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid item index.", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }} else if (choice2 == JOptionPane.NO_OPTION) {
                // Reprice
                String itemName = buttonText;
                int itemIndex = -1;

                // Find the index of the item with the given name
                for (int i = 0; i < tempSlots.length; i++) {
                    if (tempSlots[i] != null && tempSlots[i].getName().equals(itemName)) {
                        itemIndex = i;
                        break;
                    }
                }

                if (itemIndex != -1) {
                    JPanel panel3 = new JPanel(new GridLayout(1, 2));
                    panel3.add(new JLabel("New Price:"));
                    JTextField priceField = new JTextField(10);
                    panel3.add(priceField);

                    int result = JOptionPane.showConfirmDialog(null, panel3, "Reprice Item",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        double newPrice = 0.0;
                        try {
                            newPrice = Double.parseDouble(priceField.getText());
                        } catch (NumberFormatException ex1) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input. Please enter a valid number for the new price.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Get the corresponding item from the tempSlots array
                        ItemModel item = vendingMachine.getItemSlot().get(itemIndex).getDesignatedItem();
                        if (item != null) {
                            item.setPrice(newPrice);

                            // Show a message to inform the user about the repriced item
                            JOptionPane.showMessageDialog(null,
                                    "Item " + item.getName() + " has been repriced to $" + newPrice,
                                    "Reprice Successful", JOptionPane.INFORMATION_MESSAGE);
                        }
                        System.out.println("PRICE: " + item.getPrice());
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public ItemModel[] getTempSlots() {
        return this.tempSlots;
    }
}
