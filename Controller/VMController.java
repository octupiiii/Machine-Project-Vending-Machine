package Controller;

import View.*;
import Model.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.Normalizer.Form;

import javax.swing.*;

public class VMController {

    private MainMenu menu;
    private VendingMachineView vendingVMView;
    private VendingMachine vendingMachine;
    private MaintenanceView maintenanceVM;
    private boolean vmStatus = false;
    private double totalAmount = 0.0;

    public VMController(MainMenu menu, VendingMachineView vendingVMView, MaintenanceView maintenanceVM) {
        this.menu = menu;
        this.vendingMachine = new VendingMachine();
        this.vendingVMView = vendingVMView;
        this.maintenanceVM = maintenanceVM;

        this.menu.createVM(e -> {

            JButton button = (JButton) e.getSource();
            String buttonLabel = button.getText();

            if (buttonLabel.equals("Create Vending Machine")) {
                String[] options = { "Regular", "Special" };
                int choice = JOptionPane.showOptionDialog(this.menu,
                        "Choose an option for the Vending Machine:",
                        "Vending Machine Options", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options,
                        options[0]);

                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this.menu, "You Successfully Created a Regular Vending Machine!");
                    vendingMachine = new VendingMachine();
                    vendingMachine.initializeMoney();
                    vendingMachine.initializeSlots();
                    vmStatus = true;
                } else if (choice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this.menu, "You Successfully Created a Special Vending Machine!");
                    vendingMachine = new SpecialVMModel();
                    vendingMachine.initializeMoney();
                    vendingMachine.initializeSlots();
                    vmStatus = true;
                }
            }

        });

        this.menu.testVM(e -> {
            JButton button = (JButton) e.getSource();
            String buttonLabel = button.getText();
            if (buttonLabel.equals("Test Vending Machine")) {
                if (vmStatus) {
                    String[] options = { "Vending Features", "Maintenance Features" };
                    int choice = JOptionPane.showOptionDialog(this.menu,
                            "Choose an option for the Vending Machine",
                            "Vending Machine Options", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            options,
                            options[0]);

                    if (choice == JOptionPane.YES_OPTION) {
                        menu.setVisible(false);
                        vendingVMView.setVisible(true);
                    } else if (choice == JOptionPane.NO_OPTION) {
                        menu.setVisible(false);
                        maintenanceVM.setVisible(true);
                    }
                } else
                    // Show an error message if the vending machine is not created yet
                    JOptionPane.showMessageDialog(this.menu, "Please create a vending machine first!");

            }

        });

        this.vendingVMView.backButton(e -> {
            JButton button = (JButton) e.getSource();
            String buttonLabel = button.getText();
            if (buttonLabel.equals("Cancel")) {
                vendingVMView.setVisible(false);
                menu.setVisible(true);
            }
        });

        this.maintenanceVM.backButton(e -> {
            JButton button = (JButton) e.getSource();
            String buttonLabel = button.getText();
            if (buttonLabel.equals("Cancel")) {
                maintenanceVM.setVisible(false);
                menu.setVisible(true);
            }
        });

    }

    public VendingMachine getVendingMachine() {
        return this.vendingMachine;
    }

}
