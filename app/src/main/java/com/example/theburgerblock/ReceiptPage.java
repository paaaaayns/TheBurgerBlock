package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReceiptPage extends AppCompatActivity {

    TextView txtAddress, txtAdditionalDetails, txtContactNumber;

    double totalCartPrice;

    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_page);

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        String additionalDetails = intent.getStringExtra("additionalDetails");
        String driverInstructions = intent.getStringExtra("driverInstructions");
        String contactNumber = intent.getStringExtra("contactNumber");

        txtAddress = findViewById(R.id.txtAddress);
        txtAddress.setText(address);
        txtAdditionalDetails = findViewById(R.id.txtAdditionalDetails);
        txtAdditionalDetails.setText(additionalDetails + ",\n" + driverInstructions);
        txtContactNumber = findViewById(R.id.txtContactNumber);
        txtContactNumber.setText("+63 " + contactNumber);

        LinearLayout container = findViewById(R.id.layoutCartList);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        ArrayList<CartItems> cartItems = CartManager.getCartItems();
        for (CartItems item : cartItems) {
            int itemImg = item.getItemImg();
            String itemName = item.getItemName();
            String itemSides = item.getItemSides();
            String itemDrinks = item.getItemDrinks();
            int itemQuantity = item.getItemQuantity();
            double itemPrice = item.getItemPrice();

            View itemList = inflater.inflate(R.layout.item_cart, container, false);

            ImageView imgItem = itemList.findViewById(R.id.imgItem);
            TextView txtItemName = itemList.findViewById(R.id.txtItemName);
            TextView txtItemSides = itemList.findViewById(R.id.txtItemSides);
            TextView txtItemDrinks = itemList.findViewById(R.id.txtItemDrinks);
            TextView txtItemPrice = itemList.findViewById(R.id.txtItemPrice);
            TextView txtItemQuantity = itemList.findViewById(R.id.txtItemQuantity);


            imgItem.setImageResource(itemImg);
            txtItemName.setText(itemName);
            txtItemSides.setText(itemSides);
            txtItemDrinks.setText(itemDrinks);
            txtItemPrice.setText("₱ " + decimalFormat.format(itemPrice));
            txtItemQuantity.setText(String.valueOf(itemQuantity));

            if (itemName.contains("SOLO")) {
                txtItemSides.setVisibility(View.GONE);
                txtItemDrinks.setVisibility(View.GONE);
            }

            ImageView imgDelete = itemList.findViewById(R.id.imgDelete);
            imgDelete.setVisibility(View.GONE);

            container.addView(itemList);
            totalCartPrice += itemPrice;
        }

        // Clearing cart
        ArrayList<CartItems> itemsToRemove = new ArrayList<>();
        for (CartItems item : cartItems) {
            itemsToRemove.add(item);
        }
        cartItems.removeAll(itemsToRemove);

        TextView txtSubTotal = findViewById(R.id.txtSubTotal);
        txtSubTotal.setText("₱ " + decimalFormat.format(totalCartPrice));

        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtTotalPrice.setText("₱ " + decimalFormat.format(totalCartPrice + 59));


        // Back Button
        View toolBar = findViewById(R.id.toolBar);
        TextView pageName = toolBar.findViewById(R.id.pageName);
        pageName.setText("Official Receipt");
        ImageView btnBack = toolBar.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}