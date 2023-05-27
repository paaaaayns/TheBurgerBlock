package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuPagePlantBased extends AppCompatActivity {

    ImageView p1, p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_plant_based);

        p1 = findViewById(R.id.p1);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Plant-Based Block Buster";
                double itemPrice = 169.00;
                String itemCode = "PB BLCKBSTR";
                int itemImg = R.drawable.menua_plant_based_block_buster;

                redirectActivity(itemImg, itemName, itemPrice, itemCode, ItemSelectionPage.class);
            }
        });

        p2 = findViewById(R.id.p2);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "Plant-Based Long Chicken Sandwich";
                double itemPrice = 159.00;
                String itemCode = "PB LNG CHCKN SNDWCH";
                int itemImg = R.drawable.menua_plant_based_long_chicken_sandwich;

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
        txtLabel.setText("Plant-Based");
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