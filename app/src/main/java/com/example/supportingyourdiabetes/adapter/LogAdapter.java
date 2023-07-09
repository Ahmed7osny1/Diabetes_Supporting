package com.example.supportingyourdiabetes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.data.AdviceDataClass;
import com.example.supportingyourdiabetes.data.LogData;

import java.util.ArrayList;

public class LogAdapter extends BaseAdapter {

    Context context;
    ArrayList<LogData> story;
    public LogAdapter(Context context, ArrayList<LogData> story){
        this.context = context;
        this.story = story;
    }


    @Override
    public int getCount() {
        return story.size();
    }

    @Override
    public Object getItem(int i) {
        return story.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.log_item,
                    null,false);
        }

        TextView logValue = v.findViewById(R.id.logValue);
        TextView logDate = v.findViewById(R.id.logDate);
        TextView logTime = v.findViewById(R.id.logTime);

        logValue.setText(Integer.toString(story.get(position).getValue())+" mg/dl");
        logTime.setText(story.get(position).getTime());
        logDate.setText(story.get(position).getDate());


        return v;
    }
}