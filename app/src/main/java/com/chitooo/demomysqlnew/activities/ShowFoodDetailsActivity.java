package com.chitooo.demomysqlnew.activities;

/**
 * Created by Sitaram on 8/2/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chitooo.demomysqlnew.R;

public class ShowFoodDetailsActivity extends AppCompatActivity {

    //Defining views
    private TextView textViewBookId;
    private TextView textViewBookName;
    private TextView textViewBookPrice;
    private TextView textViewBookInStock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_details);

        //Initializing Views
        textViewBookId = (TextView) findViewById(R.id.textViewBookId);
        textViewBookName = (TextView) findViewById(R.id.textViewBookName);
        textViewBookPrice = (TextView) findViewById(R.id.textViewBookPrice);
        textViewBookInStock = (TextView) findViewById(R.id.textViewBookInStock);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values by fetching from intent
        textViewBookId.setText(String.valueOf(intent.getIntExtra(FoodDetailsActivity.KEY_BOOK_ID, 0)));
        textViewBookName.setText(intent.getStringExtra(FoodDetailsActivity.KEY_BOOK_NAME));
        textViewBookPrice.setText(intent.getStringExtra(FoodDetailsActivity.KEY_BOOK_PRICE));
        textViewBookInStock.setText(String.valueOf(intent.getIntExtra(FoodDetailsActivity.KEY_BOOK_STOCK,0)));
    }
}