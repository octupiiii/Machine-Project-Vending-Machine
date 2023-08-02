package Model;

import java.util.ArrayList;

public class CustomizableProductModel extends ItemModel {

    private ArrayList<ArrayList<ItemModel>> ingredients;
    private ArrayList<ItemModel> scoops = new ArrayList<ItemModel>();;
    private ArrayList<ItemModel> syrups = new ArrayList<ItemModel>();;
    private ArrayList<ItemModel> toppings = new ArrayList<ItemModel>();;
    private ItemModel icHolder = null;
    /*
     * Item One - Ice Cream Flavor <ArrayList>
     * Item Two - Pumps of Syrups <ArrayList>
     * Item Three - Toppings <ArrayList>
     * Item Three - Cup / Cone / Waffle Cone / Waffle <Item>
     */

    public CustomizableProductModel() {
        super(null, 0, 0);
        this.ingredients = new ArrayList<ArrayList<ItemModel>>();
        this.scoops = new ArrayList<ItemModel>();
        this.syrups = new ArrayList<ItemModel>();
        this.toppings = new ArrayList<ItemModel>();
    }

    public CustomizableProductModel(String name, ArrayList<ItemModel> scoops, ArrayList<ItemModel> syrups,
            ArrayList<ItemModel> toppings, ItemModel icHolder) {
        super(name, 0, 0);
        this.scoops = scoops;
        this.syrups = syrups;
        this.toppings = toppings;
        addIngredients();

        this.icHolder = icHolder;
    }

    public void addIngredients() {
        this.ingredients.add(scoops);
        this.ingredients.add(syrups);
        this.ingredients.add(toppings);
    }

    public String getName() {
        return this.getName();
    }

    public void setName(String name) {
        this.setName(name);
    }

    public String createName() {
        String tempName = "Special " + icHolder.getName() + " Ice Cream";
        return tempName;
    }

    // add scoop to scoops list
    public void addScoop(ItemModel scoop) {
        this.scoops.add(scoop);
    }

    // add syrup to syrups list
    public void addSyrup(ItemModel syrup) {
        this.syrups.add(syrup);
    }

    public void addTopping(ItemModel topping) {
        this.toppings.add(topping);
    }

    public void setICHolder(ItemModel icHolder) {
        this.icHolder = icHolder;
    }

    public void computePrice() {
        double price = 0;
        for (ItemModel temp : scoops) {
            price += temp.getPrice();
        }
        for (ItemModel temp : syrups) {
            price += temp.getPrice();
        }
        for (ItemModel temp : toppings) {
            price += temp.getPrice();
        }
        price += this.icHolder.getPrice();
        this.setPrice(price);
    }

    public void computeCalories() {
        double calories = 0;
        for (ItemModel temp : scoops) {
            calories += temp.getCalories();
        }
        for (ItemModel temp : scoops) {
            calories += temp.getCalories();
        }
        for (ItemModel temp : scoops) {
            calories += temp.getCalories();
        }
        calories += this.icHolder.getCalories();
        this.setCalories(calories);
    }

    private void updateInfo() {
        computeCalories();
        computePrice();
    }

}
