package com.chitooo.demomysqlnew.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chitooo.demomysqlnew.R;
import com.chitooo.demomysqlnew.model.InsertFoodResponseModel;
import com.chitooo.demomysqlnew.utils.APIService;
import com.chitooo.demomysqlnew.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sitaram on 8/4/2017.
 */

public class FoodInsertActivity extends AppCompatActivity {

    private static final String TAG = FoodInsertActivity.class.getSimpleName();

    private Button btnInsert;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_insert);

        btnInsert = (Button) findViewById(R.id.button_insert_food);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupInsertFood();
            }
        });
    }

    /**
     * this method used to open popup
     */
    private void popupInsertFood() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_insert_food, null);
        builder.setView(view);

        final EditText etFoodName = (EditText) view.findViewById(R.id.edit_text_food_name);
        final EditText etFoodQty = (EditText) view.findViewById(R.id.edit_text_food_quantity);

//        progressDialog.show();

        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                String foodName = etFoodName.getText().toString();
                String foodQty = etFoodQty.getText().toString();

                if (TextUtils.isEmpty(foodName)) {
                    Toast.makeText(FoodInsertActivity.this, "Food Name is required", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(foodQty)) {
                    Toast.makeText(FoodInsertActivity.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    insertData(foodName, foodQty);
                }
            }
        });

        builder.show();
    }

    /**
     * this method used to send data to server or our local server
     *
     * @param foodName
     * @param foodQty
     */
    private void insertData(String foodName, String foodQty) {
        APIService apiServiceInsert = ApiClient.getClient().create(APIService.class);
        Call<InsertFoodResponseModel> call = apiServiceInsert.insertFood(foodName, foodQty);
        call.enqueue(new Callback<InsertFoodResponseModel>() {
            @Override
            public void onResponse(Call<InsertFoodResponseModel> call, Response<InsertFoodResponseModel> response) {

                InsertFoodResponseModel insertFoodResponseModel = response.body();

                //check the status code
                if (insertFoodResponseModel.getStatus() == 1) {
                    Toast.makeText(FoodInsertActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Log.d(TAG, "aaaa onResponse:1 ");
                    finish();
                } else {
                    Toast.makeText(FoodInsertActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Log.d(TAG, "aaaa onResponse:0 ");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InsertFoodResponseModel> call, Throwable t) {
                Toast.makeText(FoodInsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}