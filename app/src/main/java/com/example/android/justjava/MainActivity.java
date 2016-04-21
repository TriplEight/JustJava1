package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 1;
    int whippedCream = 0;
    int peanuts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method creates the order summary.
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary (int price, int quantity, int whippedCream, int peanuts) {
        EditText username = (EditText) findViewById(R.id.user_name);
        String userName = username.getText().toString();

        String priceMessage = "Name: " + userName;
        priceMessage += "\nQuantity: " + quantity;
        if (whippedCream == 1) {
            priceMessage += "\nTopping: whipped cream";
        }
        if (peanuts == 1) {
            priceMessage += "\nTopping: peanuts";
        }

        priceMessage += "\nTotal: Э" + price;
        priceMessage += "\nDrink faster!";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(quantity, whippedCream, peanuts);
        displayMessage(createOrderSummary(price, quantity, whippedCream, peanuts));
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.whipped_cream:
                if (checked)
                // Put some meat on the sandwich
                    whippedCream = 1;
                else
                    whippedCream = 0;
                break;
            case R.id.peanuts:
                if (checked)
                // Cheese me
                    peanuts = 1;
                else
                    peanuts = 0;
                break;
        }
    }

    /**
     * Calculates the price of the order.
     *
     *@param quantity is the number of cups of coffee ordered
     *@return price
     */
    private int calculatePrice(int quantity, int whippedCream, int peanuts) {
        return (quantity * (5 + whippedCream + peanuts * 2));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * These methods displays quantity by pushing + and -.
     */
    public void increase(View view) {
        quantity = quantity + 1;
        if (quantity > 10) {
            quantity = 10;
            Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            //return;
            //TODO DENIED

        }
        displayQuantity(quantity);
        //displayPrice(quantity * 5);
    }

    public void decrease(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            //return;
            //TODO DENIED
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }
}
