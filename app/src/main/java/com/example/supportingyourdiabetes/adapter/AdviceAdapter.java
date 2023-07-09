package com.example.supportingyourdiabetes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.data.AdviceDataClass;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.ViewHolder> {

    // ArrayList
    ArrayList<AdviceDataClass> advice;
    Context context;

    // constructor
    public AdviceAdapter(Context context, ArrayList<AdviceDataClass> advice) {
        super();
        this.context = context;
        this.advice = advice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.advice_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // setting image resource
        viewHolder.adviceImg.setImageResource(advice.get(i).getDataImage());
        viewHolder.adviceText.setText(advice.get(i).getDataTitle());
        viewHolder.adviceDsc.setText(advice.get(i).getDataDesc());
    }

    @Override
    public int getItemCount() {
        return advice.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView adviceImg;
        TextView adviceText;
        TextView adviceDsc;
        public ViewHolder(View itemView) {
            super(itemView);
            // getting ImageView reference
            adviceImg = itemView.findViewById(R.id.advise_img);
            adviceText = itemView.findViewById(R.id.advise_title);
            adviceDsc = itemView.findViewById(R.id.advise_discription);
        }
    }
}
