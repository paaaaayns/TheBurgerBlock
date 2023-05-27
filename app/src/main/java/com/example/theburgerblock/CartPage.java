package com.example.theburgerblock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartPage extends AppCompatActivity {

    EditText edtContactNumber, edtAddress, edtAdditionalDetails, edtDriverInstructions, edtCashAmount;

    double totalCartPrice;

    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        edtContactNumber = findViewById(R.id.edtContactNumber);
        edtAddress = findViewById(R.id.edtAddress);
        edtAddress = findViewById(R.id.edtAddress);
        edtAdditionalDetails = findViewById(R.id.edtAdditionalDetails);
        edtDriverInstructions = findViewById(R.id.edtDriverInstructions);
        edtCashAmount = findViewById(R.id.edtCashAmount);

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
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartPage.this);
                    builder.setTitle("Confirm Deletion");
                    builder.setMessage("Are you sure you want to delete this item?");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            View scrollView = findViewById(R.id.scrollView);
                            int scrollPosition = scrollView.getScrollY();

                            cartItems.remove(item);
                            container.removeView(itemList);

                            totalCartPrice -= itemPrice;

                            recreate();

                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.scrollTo(0, scrollPosition);
                                }
                            });

                            Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();

                            if (cartItems.isEmpty()) {
                                finish();
                                overridePendingTransition(R.anim.card_from_left_in, R.anim.card_from_left_out);
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", null);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

            container.addView(itemList);
            totalCartPrice += itemPrice;
        }

        TextView txtSubTotal = findViewById(R.id.txtSubTotal);
        txtSubTotal.setText("₱ " + decimalFormat.format(totalCartPrice));

        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtTotalPrice.setText("₱ " + decimalFormat.format(totalCartPrice + 59));

        // Back Button
        View toolBar = findViewById(R.id.toolBar);
        TextView pageName = toolBar.findViewById(R.id.pageName);
        pageName.setText("My Cart");
        ImageView btnBack = toolBar.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });

        // CheckOut
        View btn_checkout = findViewById(R.id.btn_checkout);
        Button btnOrder = btn_checkout.findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAddress.getText().toString().equals("auto")) {
                    edtAddress.setText("4023, Bataan Ext., Bagumbayan, Sta. Mesa, Manila");
                    edtAdditionalDetails.setText("4023, Bataan Ext., Bagumbayan, Sta. Mesa, Manila");
                    edtContactNumber.setText("9955062741");
                    edtCashAmount.setText("5000");
                    edtDriverInstructions.setText("left turn at shakeys, left turn at g's hideout, dead end");
                }
                else{
                    finish();
                    overridePendingTransition(R.anim.card_from_left_in, R.anim.card_from_left_out);
                }
            }
        });

        Button btnCheckOut = btn_checkout.findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = edtAddress.getText().toString();
                String additionalDetails = edtAdditionalDetails.getText().toString();
                String driverInstructions = edtDriverInstructions.getText().toString();
                String contactNumber = edtContactNumber.getText().toString();

                if (address.isEmpty() || additionalDetails.isEmpty() || driverInstructions.isEmpty() || contactNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please complete all required fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ReceiptPage.class);
                    intent.putExtra("address", address);
                    intent.putExtra("additionalDetails", additionalDetails);
                    intent.putExtra("driverInstructions", driverInstructions);
                    intent.putExtra("contactNumber", contactNumber);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.card_from_right_in, R.anim.card_from_right_out);

                    Toast.makeText(getApplicationContext(), "Order successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}