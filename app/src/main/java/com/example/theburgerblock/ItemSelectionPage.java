package com.example.theburgerblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemSelectionPage extends AppCompatActivity {

    ConstraintLayout layout1, layout2;
    ImageView imgItem;
    TextView txtName1, txtName2;
    TextView txtPrice1, txtPrice2;

    LinearLayout layoutMeal, layoutSides, layoutDrink, layoutQuantity;


    // Meal
    RadioGroup rbtGrpMealOrder;
    RadioButton rbtMeal1, rbtMeal2;

    // Sides
    RadioGroup rbtGrpSidesOrder;
    RadioButton rbtSidesR, rbtSidesM, rbtSidesL;

    // Drinks
    CheckBox chkDrink1, chkDrink2, chkDrink3;
    RadioGroup rbtGrpDrink1, rbtGrpDrink2, rbtGrpDrink3;
    RadioButton rbtDrink1R, rbtDrink1M, rbtDrink1L;
    RadioButton rbtDrink2R, rbtDrink2M, rbtDrink2L;
    RadioButton rbtDrink3R, rbtDrink3M, rbtDrink3L;

    // Quantity
    AutoCompleteTextView ddownQuantity;

    String selected = "";

    double finalPrice = 0;
    String finalCode = "";
    int finalQuantity = 0;

    String codeMeal = "", codeSides = "", codeDrinks = "", codeQuantity = "";
    double priceMeal = 0, priceSides = 0, priceDrinks = 0;

    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selection_page);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        double itemPrice = intent.getDoubleExtra("itemPrice", 0);
        String itemCode = intent.getStringExtra("itemCode");
        int itemImg = intent.getIntExtra("itemImg", 0);

        String itemPrice1 = String.format("%.2f", itemPrice + 30);
        String itemPrice2 = String.format("%.2f", itemPrice);

        priceMeal = itemPrice + 30;
        codeMeal = itemCode + " W/ F&D";

        // Display item information - img, name, price
        imgItem = findViewById(R.id.imgItem);
        imgItem.setImageResource(itemImg);

        // Meal
        rbtGrpMealOrder = findViewById(R.id.rbtGrpMealOrder);

        txtName1 = findViewById(R.id.txtName1);
        txtName1.setText(itemName + " w/ Fries and Drink");
        txtName2 = findViewById(R.id.txtName2);
        txtName2.setText(itemName + " Solo");

        txtPrice1 = findViewById(R.id.txtPrice1);
        txtPrice1.setText("₱ " + itemPrice1);
        txtPrice2 = findViewById(R.id.txtPrice2);
        txtPrice2.setText("₱ " + itemPrice2);

        // Sides
        layoutSides = findViewById(R.id.layoutSides);
        rbtGrpSidesOrder = findViewById(R.id.rbtGrpSidesOrder);

        rbtSidesR = findViewById(R.id.rbtSidesR);
        rbtSidesM = findViewById(R.id.rbtSidesM);
        rbtSidesL = findViewById(R.id.rbtSidesL);


        // Drinks
        layoutDrink = findViewById(R.id.layoutDrink);
        chkDrink1 = findViewById(R.id.chkDrink1);
        chkDrink2 = findViewById(R.id.chkDrink2);
        chkDrink3 = findViewById(R.id.chkDrink3);

        rbtGrpDrink1 = findViewById(R.id.rbtGrpDrink1);
        rbtGrpDrink2 = findViewById(R.id.rbtGrpDrink2);
        rbtGrpDrink3 = findViewById(R.id.rbtGrpDrink3);

        rbtDrink1R = findViewById(R.id.rbtDrink1R);
        rbtDrink1M = findViewById(R.id.rbtDrink1M);
        rbtDrink1L = findViewById(R.id.rbtDrink1L);

        rbtDrink2R = findViewById(R.id.rbtDrink2R);
        rbtDrink2M = findViewById(R.id.rbtDrink2M);
        rbtDrink2L = findViewById(R.id.rbtDrink2L);

        rbtDrink3R = findViewById(R.id.rbtDrink3R);
        rbtDrink3M = findViewById(R.id.rbtDrink3M);
        rbtDrink3L = findViewById(R.id.rbtDrink3L);
        // ===========================================

        rbtDrink1R.setEnabled(false);
        rbtDrink1M.setEnabled(false);
        rbtDrink1L.setEnabled(false);
        rbtDrink2R.setEnabled(false);
        rbtDrink2M.setEnabled(false);
        rbtDrink2L.setEnabled(false);
        rbtDrink3R.setEnabled(false);
        rbtDrink3M.setEnabled(false);
        rbtDrink3L.setEnabled(false);


        // Update once to display current price
        updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
        updatePrice(priceMeal, priceSides, priceDrinks, quantity);


        layout1 = findViewById(R.id.layout1);
        rbtMeal1 = findViewById(R.id.rbtMeal1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtMeal1.setChecked(true);
            }
        });

        layout2 = findViewById(R.id.layout2);
        rbtMeal2 = findViewById(R.id.rbtMeal2);
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtMeal2.setChecked(true);
            }
        });


        rbtGrpMealOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbtMeal1:
                        selected = "rbtMeal1";
                        priceMeal = itemPrice + 30;
                        codeMeal = itemCode + " W/ F&D";

                        layoutSides.setAlpha(1F);
                        layoutDrink.setAlpha(1F);

                        //rbtGrpSidesOrder.getCheckedRadioButtonId();
                        switch (rbtGrpSidesOrder.getCheckedRadioButtonId()) {
                            case R.id.rbtSidesR:
                                priceSides = 0;
                                codeSides = "FRIES [REGULAR]";
                                break;
                            case R.id.rbtSidesM:
                                priceSides = 25;
                                codeSides = "FRIES [MEDIUM]";
                                break;
                            case R.id.rbtSidesL:
                                priceSides = 35;
                                codeSides = "FRIES [LARGE]";
                                break;
                        }

                        // Checking
                        //Toast.makeText(getApplicationContext(), "Sides: " + priceSides, Toast.LENGTH_SHORT).show();

                        if (chkDrink1.isChecked()) {
                            chkDrink1.setEnabled(true);
                            chkDrink2.setEnabled(false);
                            chkDrink3.setEnabled(false);
                            rbtDrink1R.setEnabled(true);
                            rbtDrink1M.setEnabled(true);
                            rbtDrink1L.setEnabled(true);

                            switch (rbtGrpDrink1.getCheckedRadioButtonId()) {
                                case R.id.rbtDrink1R:
                                    priceDrinks = 0;
                                    codeDrinks = "COKE [REGULAR]";
                                    break;
                                case R.id.rbtDrink1M:
                                    priceDrinks = 15;
                                    codeDrinks = "COKE [MEDIUM]";
                                    break;
                                case R.id.rbtDrink1L:
                                    priceDrinks = 25;
                                    codeDrinks = "COKE [LARGE]";
                                    break;
                            }
                        } else if (chkDrink2.isChecked()) {
                            chkDrink1.setEnabled(false);
                            chkDrink2.setEnabled(true);
                            chkDrink3.setEnabled(false);
                            rbtDrink2R.setEnabled(true);
                            rbtDrink2M.setEnabled(true);
                            rbtDrink2L.setEnabled(true);

                            switch (rbtGrpDrink2.getCheckedRadioButtonId()) {
                                case R.id.rbtDrink2R:
                                    priceDrinks = 0;
                                    codeDrinks = "SPRITE [REGULAR]";
                                    break;

                                case R.id.rbtDrink2M:
                                    priceDrinks = 15;
                                    codeDrinks = "SPRITE [MEDIUM]";
                                    break;

                                case R.id.rbtDrink2L:
                                    priceDrinks = 25;
                                    codeDrinks = "SPRITE [LARGE]";
                                    break;
                            }
                        } else if (chkDrink3.isChecked()) {
                            chkDrink1.setEnabled(false);
                            chkDrink2.setEnabled(false);
                            chkDrink3.setEnabled(true);
                            rbtDrink3R.setEnabled(true);
                            rbtDrink3M.setEnabled(true);
                            rbtDrink3L.setEnabled(true);

                            switch (rbtGrpDrink3.getCheckedRadioButtonId()) {
                                case R.id.rbtDrink3R:
                                    priceDrinks = 0;
                                    codeDrinks = "ROYAL [REGULAR]";
                                    break;

                                case R.id.rbtDrink3M:
                                    priceDrinks = 15;
                                    codeDrinks = "ROYAL [MEDIUM]";
                                    break;

                                case R.id.rbtDrink3L:
                                    priceDrinks = 25;
                                    codeDrinks = "ROYAL [LARGE]";
                                    break;
                            }
                        } else {
                            chkDrink1.setEnabled(true);
                            chkDrink2.setEnabled(true);
                            chkDrink3.setEnabled(true);
                        }

                        rbtSidesR.setEnabled(true);
                        rbtSidesM.setEnabled(true);
                        rbtSidesL.setEnabled(true);

                        updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                        updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                        updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                        break;

                    case R.id.rbtMeal2:
                        selected = "rbtMeal2";
                        priceMeal = itemPrice;
                        priceSides = 0;
                        priceDrinks = 0;
                        codeMeal = itemCode + " SOLO";
                        codeSides = "";
                        codeDrinks = "";

                        layoutSides.setAlpha(0.5F);
                        layoutDrink.setAlpha(0.5F);

                        rbtSidesR.setEnabled(false);
                        rbtSidesM.setEnabled(false);
                        rbtSidesL.setEnabled(false);
                        rbtDrink1R.setEnabled(false);
                        rbtDrink1M.setEnabled(false);
                        rbtDrink1L.setEnabled(false);
                        rbtDrink2R.setEnabled(false);
                        rbtDrink2M.setEnabled(false);
                        rbtDrink2L.setEnabled(false);
                        rbtDrink3R.setEnabled(false);
                        rbtDrink3M.setEnabled(false);
                        rbtDrink3L.setEnabled(false);

                        chkDrink1.setEnabled(false);
                        chkDrink2.setEnabled(false);
                        chkDrink3.setEnabled(false);

                        updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                        updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                        break;
                }

                // Checking
                //Toast.makeText(getApplicationContext(), "Selected: " + codeMeal + "\nPrice: " + priceMeal, Toast.LENGTH_SHORT).show();
            }
        });


        rbtGrpSidesOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtSidesR:
                        priceSides = 0;
                        codeSides = "FRIES [REGULAR]";
                        break;

                    case R.id.rbtSidesM:
                        priceSides = 25;
                        codeSides = "FRIES [MEDIUM]";
                        break;

                    case R.id.rbtSidesL:
                        priceSides = 35;
                        codeSides = "FRIES [LARGE]";
                        break;
                }

                updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                updatePrice(priceMeal, priceSides, priceDrinks, quantity);

                // Checking
                //Toast.makeText(getApplicationContext(), "Code: " + codeSides + "\nPrice: " + priceSides, Toast.LENGTH_SHORT).show();
            }
        });

        chkDrink1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    priceDrinks = 0;
                    codeDrinks = "";

                    rbtDrink1R.setEnabled(true);
                    rbtDrink1M.setEnabled(true);
                    rbtDrink1L.setEnabled(true);

                    chkDrink2.setEnabled(false);
                    rbtDrink2R.setEnabled(false);
                    rbtDrink2M.setEnabled(false);
                    rbtDrink2L.setEnabled(false);
                    chkDrink3.setEnabled(false);
                    rbtDrink3R.setEnabled(false);
                    rbtDrink3M.setEnabled(false);
                    rbtDrink3L.setEnabled(false);

                    rbtGrpDrink1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rbtDrink1R:
                                    priceDrinks = 0;
                                    codeDrinks = "COKE [REGULAR]";
                                    break;

                                case R.id.rbtDrink1M:
                                    priceDrinks = 15;
                                    codeDrinks = "COKE [MEDIUM]";
                                    break;

                                case R.id.rbtDrink1L:
                                    priceDrinks = 25;
                                    codeDrinks = "COKE [LARGE]";
                                    break;
                            }

                            updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                            updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                            // Checking
                            //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    rbtGrpDrink1.clearCheck();
                    chkDrink2.setEnabled(true);
                    chkDrink3.setEnabled(true);
                    rbtDrink1R.setEnabled(false);
                    rbtDrink1M.setEnabled(false);
                    rbtDrink1L.setEnabled(false);

                    priceDrinks = 0;
                    codeDrinks = "";
                }

                updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                // Checking
                //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();
            }
        });

        chkDrink2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbtDrink2R.setEnabled(true);
                    rbtDrink2M.setEnabled(true);
                    rbtDrink2L.setEnabled(true);

                    chkDrink1.setEnabled(false);
                    rbtDrink1R.setEnabled(false);
                    rbtDrink1M.setEnabled(false);
                    rbtDrink1L.setEnabled(false);
                    chkDrink3.setEnabled(false);
                    rbtDrink3R.setEnabled(false);
                    rbtDrink3M.setEnabled(false);
                    rbtDrink3L.setEnabled(false);

                    rbtGrpDrink2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rbtDrink2R:
                                    priceDrinks = 0;
                                    codeDrinks = "SPRITE [REGULAR]";
                                    break;

                                case R.id.rbtDrink2M:
                                    priceDrinks = 15;
                                    codeDrinks = "SPRITE [MEDIUM]";
                                    break;

                                case R.id.rbtDrink2L:
                                    priceDrinks = 25;
                                    codeDrinks = "SPRITE [LARGE]";
                                    break;
                            }

                            updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                            updatePrice(priceMeal, priceSides, priceDrinks, quantity);

                            // Checking
                            //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    rbtGrpDrink2.clearCheck();
                    chkDrink1.setEnabled(true);
                    chkDrink3.setEnabled(true);
                    rbtDrink2R.setEnabled(false);
                    rbtDrink2M.setEnabled(false);
                    rbtDrink2L.setEnabled(false);

                    priceDrinks = 0;
                    codeDrinks = "";
                }

                updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                updatePrice(priceMeal, priceSides, priceDrinks, quantity);

                // Checking
                //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();
            }
        });

        chkDrink3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbtDrink3R.setEnabled(true);
                    rbtDrink3M.setEnabled(true);
                    rbtDrink3L.setEnabled(true);

                    chkDrink1.setEnabled(false);
                    rbtDrink1R.setEnabled(false);
                    rbtDrink1M.setEnabled(false);
                    rbtDrink1L.setEnabled(false);
                    chkDrink2.setEnabled(false);
                    rbtDrink2R.setEnabled(false);
                    rbtDrink2M.setEnabled(false);
                    rbtDrink2L.setEnabled(false);

                    rbtGrpDrink3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rbtDrink3R:
                                    priceDrinks = 0;
                                    codeDrinks = "ROYAL [REGULAR]";
                                    break;

                                case R.id.rbtDrink3M:
                                    priceDrinks = 15;
                                    codeDrinks = "ROYAL [MEDIUM]";
                                    break;

                                case R.id.rbtDrink3L:
                                    priceDrinks = 25;
                                    codeDrinks = "ROYAL [LARGE]";
                                    break;
                            }

                            updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                            updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                            // Checking
                            //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();

                        }
                    });

                } else {
                    rbtGrpDrink3.clearCheck();
                    chkDrink1.setEnabled(true);
                    chkDrink2.setEnabled(true);
                    rbtDrink3R.setEnabled(false);
                    rbtDrink3M.setEnabled(false);
                    rbtDrink3L.setEnabled(false);

                    priceDrinks = 0;
                    codeDrinks = "";
                }

                updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                updatePrice(priceMeal, priceSides, priceDrinks, quantity);
                // Checking
                //Toast.makeText(getApplicationContext(), "Code: " + codeDrinks + "\nPrice: " + priceDrinks, Toast.LENGTH_SHORT).show();
            }
        });


        String[] quantityArr = new String[99];
        for (int i = 0; i < quantityArr.length; i++) {
            quantityArr[i] = String.valueOf(i + 1);
        }

        ddownQuantity = findViewById(R.id.ddownQuantity);

        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, quantityArr);
        ddownQuantity.setAdapter(quantityAdapter);
        ddownQuantity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quantity = position + 1;
                codeQuantity = (String.valueOf(quantity));
                ddownQuantity.clearFocus();
                updateCode(codeMeal, codeSides, codeDrinks, codeQuantity);
                updatePrice(priceMeal, priceSides, priceDrinks, quantity);

                // Checking
                //Toast.makeText(getApplicationContext(), "Selected: " + quantity, Toast.LENGTH_SHORT).show();
            }
        });

        // Set the maximum height for the dropdown menu
        int maxHeight = getResources().getDimensionPixelSize(R.dimen.dropdown_menu_max_height);
        ddownQuantity.setDropDownHeight(maxHeight);


        // Continue Button
        View btn_continue_total = findViewById(R.id.btn_continue_total);
        TextView txtTotal = btn_continue_total.findViewById(R.id.txtTotal);
        Button btnAdd = btn_continue_total.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtMeal1.isChecked()) {
                    if (rbtGrpSidesOrder.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getApplicationContext(), "Please select a side.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!chkDrink1.isChecked() && !chkDrink2.isChecked() && !chkDrink3.isChecked()) {
                            Toast.makeText(getApplicationContext(), "Please select a drink.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (rbtGrpDrink1.getCheckedRadioButtonId() == -1 && rbtGrpDrink2.getCheckedRadioButtonId() == -1 && rbtGrpDrink3.getCheckedRadioButtonId() == -1) {
                                Toast.makeText(getApplicationContext(), "Please select a drink size.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Checking
                                //Toast.makeText(getApplicationContext(), "Selected: " + finalCode, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "Price: " + finalPrice, Toast.LENGTH_SHORT).show();

                                CartManager.addItem(itemImg, codeMeal, codeSides, codeDrinks, finalQuantity, finalPrice);
                                Toast.makeText(getApplicationContext(), "Item added to cart!", Toast.LENGTH_SHORT).show();

                                finish();
                                overridePendingTransition(R.anim.card_from_left_in, R.anim.card_from_left_out);
                            }
                        }
                    }
                } else {

                    // Checking
                    //Toast.makeText(getApplicationContext(), "Selected: " + finalCode, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Price: " + finalPrice, Toast.LENGTH_SHORT).show();

                    CartManager.addItem(itemImg, codeMeal, codeSides, codeDrinks, finalQuantity, finalPrice);
                    Toast.makeText(getApplicationContext(), "Item added to cart!", Toast.LENGTH_SHORT).show();

                    finish();
                    overridePendingTransition(R.anim.card_from_left_in, R.anim.card_from_left_out);
                }

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
        txtLabel.setText(itemName);
        ImageView btnBack = toolBarSecondary.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
    }

    private void updatePrice(double priceMeal, double priceSides, double priceDrinks, int quantity) {
        // Continue Button
        View btn_continue_total = findViewById(R.id.btn_continue_total);
        TextView txtTotal = btn_continue_total.findViewById(R.id.txtTotal);

        double totalPrice = quantity * (priceMeal + priceSides + priceDrinks);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String totalPriceString = "₱ " + decimalFormat.format(totalPrice);
        txtTotal.setText(totalPriceString);

        finalPrice = totalPrice;
        finalQuantity = quantity;
    }

    private void updateCode(String codeMeal, String codeSides, String codeDrinks, String codeQuantity) {
        finalCode = codeQuantity + "x " + codeMeal + " " + codeSides + " " + codeDrinks;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}