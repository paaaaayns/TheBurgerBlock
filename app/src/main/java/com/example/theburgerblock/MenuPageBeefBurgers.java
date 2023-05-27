package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuPageBeefBurgers extends AppCompatActivity {

    ImageView b1, b2, b3, b4, b5, b6, b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_beef_burgers);

        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemImg = R.drawable.menua_blockbuster;
                String itemName = "Block Buster";
                double itemPrice = 149.00;
                String itemCode = "BLCKBSTR";

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Cheese Burger";
                double itemPrice = 119.00;
                String itemCode = "CHS BRG";
                int itemImg = R.drawable.menua_cheese_burger;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Quarter Pounder";
                double itemPrice = 139.00;
                String itemCode = "QRTR PNDR";
                int itemImg = R.drawable.menua_quarter_pounder;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b4 = findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Four Cheese Burger";
                double itemPrice = 159.00;
                String itemCode = "4CHS BRGR";
                int itemImg = R.drawable.menua_four_cheese_burger;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b5 = findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Mushroom Burger";
                double itemPrice = 129.00;
                String itemCode = "MSHRM BRGR";
                int itemImg = R.drawable.menua_mushroom_burger;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b6 = findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Bacon Cheese Burger";
                double itemPrice = 149.00;
                String itemCode = "BCN CHS BRGR";
                int itemImg = R.drawable.menua_bacon_cheese_burger;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        b7 = findViewById(R.id.b7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Pepperoni Bacon Burger";
                double itemPrice = 179.00;
                String itemCode = "PPRN BCN BRGR";
                int itemImg = R.drawable.menua_pepperoni_bacon_burger;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });


        // Cart Button
        View toolBarPrimary = findViewById(R.id.toolBarPrimary);
        ImageView btnCart = toolBarPrimary.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<CartItems> cartItems = CartManager.getCartItems();
                if (cartItems.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cart is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), CartPage.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                }
            }
        });

        // Back Button
        View toolBarSecondary = findViewById(R.id.toolBarSecondary);
        TextView txtLabel = toolBarSecondary.findViewById(R.id.txtLabel);
        txtLabel.setText("Beef Burgers");
        ImageView btnBack = toolBarSecondary.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
    }

    public void redirectActivity(int itemImg, String itemName, double itemPrice, String itemCode, Class secondActivity) {
        Intent intent = new Intent(getApplicationContext(), secondActivity);
        intent.putExtra("itemName", itemName);
        intent.putExtra("itemPrice", itemPrice);
        intent.putExtra("itemCode", itemCode);
        intent.putExtra("itemImg", itemImg);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}