package com.chitooo.demomysqlnew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chitooo.demomysqlnew.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void foodInsert(View view) {
        Intent intent = new Intent(this, FoodInsertActivity.class);
        startActivity(intent);
    }

    public void foodDetails(View view) {
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        startActivity(intent);
    }
}
