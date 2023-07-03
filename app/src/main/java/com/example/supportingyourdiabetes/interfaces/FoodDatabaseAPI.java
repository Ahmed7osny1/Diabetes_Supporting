package com.example.supportingyourdiabetes.interfaces;

import com.example.supportingyourdiabetes.data.FoodData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodDatabaseAPI {
    @GET("/api/food-database/v2/parser")
    Call<FoodData> getFoodItem(@Query("app_id") String appId,
                               @Query("app_key") String apiKey,
                               @Query("ingr") String foodInput,
                               @Query("nutrition-type") String nutritionType);
}
