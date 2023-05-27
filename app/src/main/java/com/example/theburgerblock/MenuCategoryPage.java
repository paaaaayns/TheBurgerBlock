package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuCategoryPage extends AppCompatActivity {

    ImageView imgBeef, imgChicken, imgPlantBased, imgSides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category_page);

        imgBeef = findViewById(R.id.imgBeef);
        imgBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MenuPageBeefBurgers.class);
            }
        });

        imgChicken = findViewById(R.id.imgChicken);
        imgChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MenuPageChickenSandwiches.class);
            }
        });

        imgPlantBased = findViewById(R.id.imgPlantBased);
        imgPlantBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MenuPagePlantBased.class);
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
        txtLabel.setText("Menu");
        ImageView btnBack = toolBarSecondary.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
    }

    public void redirectActivity(Class secondActivity) {
        Intent intent = new Intent(getApplicationContext(), secondActivity);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}