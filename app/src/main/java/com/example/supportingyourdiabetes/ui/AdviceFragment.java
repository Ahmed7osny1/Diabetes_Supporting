package com.example.supportingyourdiabetes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.adapter.AdviceAdapter;
import com.example.supportingyourdiabetes.data.AdviceDataClass;
import com.example.supportingyourdiabetes.databinding.FragmentAdviceBinding;

import java.util.ArrayList;
import java.util.List;

public class AdviceFragment extends Fragment {

    ArrayList<AdviceDataClass> dataList;
    AdviceAdapter adapter;
    FragmentAdviceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdviceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        dataList = new ArrayList<>();

        dataList.add(
                new AdviceDataClass(
                        "Healthy and Tasty",
                        R.string.camera,
                        R.drawable.download));

        dataList.add(
                new AdviceDataClass(
                "متجر سكرى ",
                R.string.camera,
                R.drawable.sugerstore));

        dataList.add(
                new AdviceDataClass(
                        "Eat Good",
                        R.string.camera,
                        R.drawable.eatgood));

        dataList.add(
                new AdviceDataClass(
                        "Diet Corner",
                        R.string.camera,
                        R.drawable.dietcorner));

        dataList.add(new AdviceDataClass(
                "Sea salt bakery",
                R.string.camera,
                R.drawable.sea));

        dataList.add(new AdviceDataClass(
                "Pharmacy Elezaby",
                R.string.camera,
                R.drawable.elezaby));

        dataList.add(new AdviceDataClass(
                "Pharmacy Sief",
                R.string.camera,
                R.drawable.seif));

        dataList.add(new AdviceDataClass(
                "Pharmacy Normandy ",
                R.string.camera,
                R.drawable.noor));


        // setting recyclerView layoutManager
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        binding.recycleViewStagged.setLayoutManager(layoutManager);
        adapter = new AdviceAdapter(requireContext(), dataList);

        // setting recycle view adapter
        binding.recycleViewStagged.setAdapter(adapter);
    }
}