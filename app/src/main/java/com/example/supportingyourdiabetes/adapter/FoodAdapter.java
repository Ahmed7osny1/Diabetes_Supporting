package com.example.supportingyourdiabetes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.data.FoodItem;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoodItem> food;
    public FoodAdapter(Context context, ArrayList<FoodItem> food){
        this.context = context;
        this.food = food;
    }


    @Override
    public int getCount() {
        return food.size();
    }

    @Override
    public Object getItem(int i) {
        return food.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.food_item,
                    null,false);
        }

        ImageView img = v.findViewById(R.id.food_img);
        TextView label = v.findViewById(R.id.foodTitle);
        TextView ENERC_KCAL = v.findViewById(R.id.ENERC_KCAL);
        TextView PROCNT = v.findViewById(R.id.PROCNT);
        TextView FAT = v.findViewById(R.id.FAT);
        TextView CHOCDF = v.findViewById(R.id.CHOCDF);
        TextView FIBTG = v.findViewById(R.id.FIBTG);

        Picasso.get()
                .load(food.get(position).getImg())
                .into(img);

        label.setText(food.get(position).getLabel());
        ENERC_KCAL.setText(Double.toString(food.get(position).getENERC_KCAL()));
        PROCNT.setText(Double.toString(food.get(position).getPROCNT()));
        FAT.setText(Double.toString(food.get(position).getFAT()));
        CHOCDF.setText(Double.toString(food.get(position).getCHOCDF()));
        FIBTG.setText(Double.toString(food.get(position).getFIBTG()));

        return v;
    }
}