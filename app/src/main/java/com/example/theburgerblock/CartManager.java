package com.example.theburgerblock;

import java.util.ArrayList;

public class CartManager {

    private static ArrayList<CartItems> cartItems = new ArrayList<>();

    public static void addItem(int itemImg, String codeMeal, String codeSides, String codeDrinks, int quantity, double finalPrice) {
        CartItems item = new CartItems(itemImg, codeMeal, codeSides, codeDrinks, quantity, finalPrice);
        cartItems.add(item);
    }

    public static ArrayList<CartItems> getCartItems() {
        return cartItems;
    }
}
