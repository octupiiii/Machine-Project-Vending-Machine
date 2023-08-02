package Model;

import java.util.ArrayList;

public class VendingMachine {

    protected ArrayList<SlotModel> itemSlot;
    private ArrayList<DenominationModel> availableCash;
    private ArrayList<TransactionModel> transactions;
    private ArrayList<SlotModel> startingInventory, endingInventory;
    private double totalPaymentAmount;

    public VendingMachine() {
        this.itemSlot = new ArrayList<>();
        this.availableCash = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.startingInventory = new ArrayList<>();
        this.endingInventory = new ArrayList<>();
    }

    // Getters

    public ArrayList<SlotModel> getItemSlot() {
        return itemSlot;
    }

    public ArrayList<DenominationModel> getAvailableCash() {
        return availableCash;
    }

    public ArrayList<TransactionModel> getTransactions() {
        return transactions;
    }

    public ArrayList<SlotModel> getStartingInventory() {
        return startingInventory;
    }

    public ArrayList<SlotModel> getEndingInventory() {
        return endingInventory;
    }

    public double getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    // Setters

    public void setItemSlot(ArrayList<SlotModel> itemSlot) {
        this.itemSlot = itemSlot;
    }

    public void setAvailableCash(ArrayList<DenominationModel> availableCash) {
        this.availableCash = availableCash;
    }

    public void setTransactions(ArrayList<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public void setStartingInventory(ArrayList<SlotModel> startingInventory) {
        this.startingInventory = startingInventory;
    }

    public void setEndingInventory(ArrayList<SlotModel> endingInventory) {
        this.endingInventory = endingInventory;
    }

    public void setTotalPaymentAmount(double totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    // methods

    public void initializeSlots() {
        itemSlot.add(new SlotModel("1"));
        itemSlot.add(new SlotModel("2"));
        itemSlot.add(new SlotModel("3"));
        itemSlot.add(new SlotModel("4"));
        itemSlot.add(new SlotModel("5"));
        itemSlot.add(new SlotModel("6"));
        itemSlot.add(new SlotModel("7"));
        itemSlot.add(new SlotModel("8"));
        itemSlot.add(new SlotModel("9"));
    }

    public void initializeMoney() {
        availableCash.add(new DenominationModel("One Thousand-Bills", 1000.0));
        availableCash.add(new DenominationModel("Five Hundred-Bills", 500.0));
        availableCash.add(new DenominationModel("Two Hundred-Bills", 200.0));
        availableCash.add(new DenominationModel("One Hundred-Bills", 100.0));
        availableCash.add(new DenominationModel("Fifty-Bills", 50.0));
        availableCash.add(new DenominationModel("Twenty-Bills", 20.0));
        availableCash.add(new DenominationModel("Ten-Coins", 10.0));
        availableCash.add(new DenominationModel("Five-Coins", 5.0));
        availableCash.add(new DenominationModel("One-Coins", 1.0));
        availableCash.add(new DenominationModel("25-Centavos", 0.25));
    }

}
