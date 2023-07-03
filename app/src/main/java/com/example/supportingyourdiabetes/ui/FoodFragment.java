package com.example.supportingyourdiabetes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.adapter.FoodAdapter;
import com.example.supportingyourdiabetes.data.FoodData;
import com.example.supportingyourdiabetes.data.FoodItem;
import com.example.supportingyourdiabetes.databinding.FragmentFoodBinding;
import com.example.supportingyourdiabetes.interfaces.FoodDatabaseAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodFragment extends Fragment {

    FragmentFoodBinding binding;
    private ArrayList<FoodItem> foodItemList = new ArrayList<>();

    FoodAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFoodData();

    }
    public void getFoodData() {

        foodItemList = new ArrayList<>();
        String userFoodInput = binding.etDataInput.getText().toString();
        String appId = "bc7b902b";
        String apiKey = "1ac553cacc0282856d6d4df80442734a";
        String nutritionType = "cooking";

        if (userFoodInput.contains(" ")) {
            userFoodInput = userFoodInput.trim().replaceAll(" ", "%20");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.edamam.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodDatabaseAPI api = retrofit.create(FoodDatabaseAPI.class);

        Call<FoodData> call = api.getFoodItem(appId, apiKey, userFoodInput,
                nutritionType);

        call.enqueue(new Callback<FoodData>() {
            @Override
            public void onResponse(Call<FoodData> call, Response<FoodData> response) {
                if (response.isSuccessful()) {
                    FoodData foodItem = response.body();
                    if (foodItem.getHints().size() > 0) {
                        for (int i =0;i<foodItem.getHints().size();i++) {

                            FoodData.Hint.Food food = foodItem.getHints().get(i).getFood();
                            FoodData.Hint.Food.Nutrients nutrients = food.getNutrients();

                            String item = food.getLabel();
                            String img = food.getImage();
                            double kcal = nutrients.getENERC_KCAL();
                            double protein = nutrients.getPROCNT();
                            double fat = nutrients.getFAT();
                            double carbs = nutrients.getCHOCDF();
                            double fibre = nutrients.getFIBTG();

                            FoodItem fooditem = new FoodItem(
                                    img,
                                    item,
                                    kcal,
                                    protein,
                                    fat,
                                    carbs,
                                    fibre
                            );
                            foodItemList.add(fooditem);
                        }

                        adapter = new FoodAdapter(getContext(), foodItemList);

                        binding.rvFood.setAdapter(adapter);

                    } else {
                        Toast.makeText(requireContext(), "Food can't be found, please try something else!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Please enter a food item in the box above. ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodData> call, Throwable t) {
                Toast.makeText(requireContext(),
                        "Failed to retrieve data. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}