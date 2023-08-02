package View;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // Card layout for switching view
    private CardLayout cardLayout;

    public MainFrame() {

        super("ICE ICE BaBY");
        cardLayout = new CardLayout();
        MainMenu menu = new MainMenu();
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineView vendingVMView = new VendingMachineView();
        MaintenanceView maintenanceVM = new MaintenanceView();

        // sets our layout as a card layout
        setLayout(cardLayout);

        new VMController(menu, vendingVMView, maintenanceVM);

        add(menu, "Main Menu");
        add(vendingVMView, "Vending Features");
        add(maintenanceVM, "Maintenace Feature");

        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
