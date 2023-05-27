package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuPageSides extends AppCompatActivity {

    ImageView s1, s2, s3, s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_sides);


        s1 = findViewById(R.id.s1);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Thick Cut Fries";
                String itemPrice = "49.00";
                String itemCode = "S FRS";
                int itemImg = R.drawable.menua_thick_cut_fries;


                Intent intent = new Intent(MenuPageSides.this, ItemSelectionPage.class);
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemPrice", itemPrice);
                intent.putExtra("itemCode", itemCode);
                intent.putExtra("itemImg", itemImg);
                startActivity(intent);
            }
        });

        s2 = findViewById(R.id.s2);
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Onion Rings";
                String itemPrice = "59.00";
                String itemCode = "S RNGS";
                int itemImg = R.drawable.menua_onion_rings;


                Intent intent = new Intent(MenuPageSides.this, ItemSelectionPage.class);
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemPrice", itemPrice);
                intent.putExtra("itemCode", itemCode);
                intent.putExtra("itemImg", itemImg);
                startActivity(intent);
            }
        });


        s3 = findViewById(R.id.s3);
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Hashbrown Bites";
                double itemPrice = 59.00;
                String itemCode = "S HSHBTS";
                int itemImg = R.drawable.menua_hashbrown_bites;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        s4 = findViewById(R.id.s4);
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Chicken Nuggets";
                double itemPrice = 79.00;
                String itemCode = "S NGGT";
                int itemImg = R.drawable.menua_chicken_nuggets;

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
        txtLabel.setText("Sides");
        ImageView btnBack = toolBarSecondary.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}