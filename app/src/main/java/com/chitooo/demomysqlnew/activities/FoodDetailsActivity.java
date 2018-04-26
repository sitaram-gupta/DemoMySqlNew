package com.chitooo.demomysqlnew.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chitooo.demomysqlnew.R;
import com.chitooo.demomysqlnew.model.Food;
import com.chitooo.demomysqlnew.utils.APIService;
import com.chitooo.demomysqlnew.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodDetailsActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private static final String TAG = FoodDetailsActivity.class.getSimpleName();

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";

    //List view to show data
    private ListView listView;

    //List of type foods this list will store type Food which is our data model
    private List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data
        getBooks();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);
    }

    private void getBooks() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        APIService apiServiceFood = ApiClient.getClient().create(APIService.class);

        Call<List<Food>> call = apiServiceFood.getFoods();

        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
//                Dismissing the loading progressbar
                if (loading != null)
                    loading.dismiss();

                //Storing the data in our list
                foods.addAll(response.body());

                //Calling a method to show the list
                showList();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

    }

    //Our method to show list
    private void showList() {
        //String array to store all the book names
        String[] items = new String[foods.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < foods.size(); i++) {
            //Storing names to string array
            items[i] = foods.get(i).getFoodName();
            Log.d("aaaa", "showList:id " + foods.get(i).getFoodId());
            Log.d("aaaa", "showList:qty " + foods.get(i).getFoodQty());
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }


    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowFoodDetailsActivity.class);

        //Getting the requested food from the list
        Food food = foods.get(position);

        //Adding food details to intent
        intent.putExtra(KEY_BOOK_ID, food.getFoodId());
        intent.putExtra(KEY_BOOK_NAME, food.getFoodName());
//        intent.putExtra(KEY_BOOK_PRICE,food.getBody());
        intent.putExtra(KEY_BOOK_STOCK, food.getFoodQty());

        //Starting another activity to show food details
        startActivity(intent);
    }
}