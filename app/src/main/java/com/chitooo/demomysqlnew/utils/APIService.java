package com.chitooo.demomysqlnew.utils;

import com.chitooo.demomysqlnew.model.Food;
import com.chitooo.demomysqlnew.model.InsertFoodResponseModel;
import com.chitooo.demomysqlnew.model.MSG;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Sitaram on 8/15/2016.
 */
public interface APIService {


    @FormUrlEncoded
    @POST("login/views/signup.php")
    Call<MSG> userSignUp(@Field("name") String name,
                         @Field("email") String email,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("login/views/login.php")
    Call<MSG> userLogIn(@Field("email") String email,
                        @Field("password") String password);

    /*Retrofit get annotation with our URL
        And our method that will return us the list ob Food
     */
    @GET("/get-data.php")
    Call<List<Food>> getFoods();

    @FormUrlEncoded
    @POST("AndroidPhpLogin/food/InsertFood.php")
    Call<InsertFoodResponseModel> insertFood(@Field("foodname") String foodName, @Field("foodqty") String foodQty);
}