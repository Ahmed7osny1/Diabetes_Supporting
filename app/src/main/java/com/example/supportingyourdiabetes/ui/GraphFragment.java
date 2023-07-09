package com.example.supportingyourdiabetes.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.adapter.LogAdapter;
import com.example.supportingyourdiabetes.data.LogData;
import com.example.supportingyourdiabetes.databinding.FragmentGraphBinding;
import com.example.supportingyourdiabetes.db.DbHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GraphFragment extends Fragment {

    FragmentGraphBinding binding;
    SQLiteDatabase db ;
    DbHelper dbHelper;

    int maxValue = 0;
    int minValue = 0;

    LogAdapter adapter;
    private ArrayList<LogData> ItemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGraphBinding.inflate(inflater, container, false);

        dbHelper = new DbHelper(requireContext());
        db = dbHelper.getWritableDatabase();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();

        String[] columns = {"MAX(value)", "MIN(value)"};
        Cursor cursor = db.query("my_table", columns, null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            maxValue = cursor.getInt(0);
            minValue = cursor.getInt(1);
        }
        cursor.close();

        binding.maxValue.setText(Integer.toString(maxValue)+" mg/dl");
        binding.min.setText(Integer.toString(minValue)+" mg/dl");
        binding.avg.setText(Integer.toString((maxValue+minValue)/2)+" mg/dl");

        createChart();

    }

    private void createChart(){

        LineChart lineChart = new LineChart(requireContext());
        lineChart.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        lineChart.setDrawGridBackground(false);
        lineChart.setData(new LineData());
        lineChart.invalidate();



        String[] columns = {"date","value"};
        String orderBy = "date ASC";
        Cursor cursor = db.query("my_table", columns, null, null, null, null, orderBy);

        // Loop through the result set and add data points to the chart
        LineData data = lineChart.getData();
        LineDataSet dataSet = new LineDataSet(null, "My Data");
        dataSet.setDrawCircles(false);
        dataSet.setLineWidth(2f);
        dataSet.setColor(Color.BLUE);

        ArrayList lineEntries = new ArrayList<>();

        while (cursor.moveToNext()) {
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            int value = cursor.getInt(cursor.getColumnIndexOrThrow("value"));
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString);
                long timestamp = date.getTime();
               lineEntries.add(new Entry(timestamp, value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        dataSet = new LineDataSet(lineEntries, "");
        data = new LineData(dataSet);
        lineChart.setData(data);

        data.addDataSet(dataSet);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        binding.chartView.addView(lineChart);
        // Close the Cursor and database
        cursor.close();
        db.close();

    }

    private void loadData(){

        String[] columns = {"id","value", "date", "time"};
        Cursor cursor = db.query("my_table", columns, null, null, null, null, null);

        // Loop through the result set and process the data
        while (cursor.moveToNext()) {
            int value = cursor.getInt(cursor.getColumnIndexOrThrow("value"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));

            LogData favItem = new LogData(
                    value,
                    date,
                    time
            );
            ItemList.add(favItem);

        }
        // Close the Cursor
        cursor.close();

        adapter = new LogAdapter(getContext(), ItemList);

        binding.logList.setAdapter(adapter);

    }

}