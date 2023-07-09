package com.example.supportingyourdiabetes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.databinding.FragmentWelcomeBinding;
import java.util.ArrayList;

public class WelcomeFragment extends Fragment {

    FragmentWelcomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);

        imageSliderSetup();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ownFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_welcomeFragment_to_foodFragment
                );
            }
        });

        binding.ownMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_welcomeFragment_to_medicineFragment
                );
            }
        });

        binding.ownAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_welcomeFragment_to_adviceFragment
                );
            }
        });

        binding.checkDiagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_welcomeFragment_to_checkFragment
                );
            }
        });
    }

    private void imageSliderSetup() {
        ArrayList<SlideModel> imageList = new ArrayList<SlideModel>();

        imageList.add(
                new SlideModel(
                        R.drawable.diagnosis,
                        "Should you be screened for diabetes ?",
                        ScaleTypes.FIT
                )
        );
        imageList.add(
                new SlideModel(
                        R.drawable.lose_weight,
                        "Lose 5 percent of their weight, Reduce their risk of developing diabetes !",
                        ScaleTypes.FIT
                )
        );
        imageList.add(
                new SlideModel(
                        R.drawable.calendar_green,
                        "Focus on date and time of medicines and meals !",
                        ScaleTypes.FIT
                )
        );
        imageList.add(
                new SlideModel(
                        R.drawable.breakfast_icon,
                        "Maintain a healthy diet. ask your nutritionist for guidance.",
                        ScaleTypes.FIT
                )
        );

        ImageSlider imageSlider = binding.imageSlider;
        imageSlider.setImageList(imageList);
    }


}