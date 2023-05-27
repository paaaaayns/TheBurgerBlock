package com.example.theburgerblock;

public class CartItems {

    private int itemImg;
    private String itemName;
    private String itemSides;
    private String itemDrinks;
    private int itemQuantity;
    private double itemPrice;

    public CartItems(int itemImg, String itemName, String itemSides, String itemDrinks, int itemQuantity, double itemPrice) {
        this.itemImg = itemImg;
        this.itemName = itemName;
        this.itemSides = itemSides;
        this.itemDrinks = itemDrinks;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public int getItemImg() {
        return itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemSides() {
        return itemSides;
    }

    public String getItemDrinks() {
        return itemDrinks;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
