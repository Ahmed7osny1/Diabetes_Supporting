package com.example.supportingyourdiabetes.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.databinding.FragmentCheckBinding;
import com.example.supportingyourdiabetes.db.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checkFragment extends Fragment {

    FragmentCheckBinding binding;
    SQLiteDatabase db;
    DbHelper dbHelper;
    String value = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCheckBinding.inflate(inflater, container, false);

        dbHelper = new DbHelper(requireContext());
        db = dbHelper.getWritableDatabase();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(new Date());


        binding.AddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value = binding.logTil.getEditText().getText().toString();


                if(!value.isEmpty()){
                    ContentValues values = new ContentValues();
                    values.put("value",Integer.parseInt(value));
                    values.put("date", date);
                    values.put("time", time);
                    long rowId = db.insert("my_table", null, values);
                    Toast.makeText(requireActivity(), "Add Successfully",
                            Toast.LENGTH_LONG).show();

                }else
                    Toast.makeText(requireActivity(), "Enter your Glucose",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

}