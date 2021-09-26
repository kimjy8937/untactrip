package com.example.untactrip3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactrip3.dto.Datum_dong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter_Flow extends RecyclerView.Adapter<RecyclerAdapter_Flow.ViewHolder>{

    public static List<Datum_dong> items;
    private Context context;
    private String ldongName;

    public RecyclerAdapter_Flow(List<Datum_dong> items, Context context, String ldongName)
    {
        this.items = items;
        this.context = context;
        this.ldongName = ldongName;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item , parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum_dong item = items.get(position);
        item.setLdongCd(ldongName);
        holder.setItem(item);
    }




    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView hh, ldongCd, flowDensityPercentile;
        public ViewHolder(View itemView) {
            super(itemView);
            hh = itemView.findViewById(R.id.hh);
            ldongCd = itemView.findViewById(R.id.ldongCd);
            flowDensityPercentile = itemView.findViewById(R.id.flowDensityPercentile);
        }


        public void setItem(Datum_dong item){
            hh.setText(item.getHh() +"");
            ldongCd.setText(item.getLdongCd());
            double sum = 0, avg = 0;
            for(int i = 0; i<items.size(); i++){
                sum += items.get(i).getFlowDensityPercentile();
            }
            avg = sum/items.size();
            flowDensityPercentile.setText(""+ (double)Math.round(item.getFlowDensityPercentile()*100)/100);
            if((item.getFlowDensityPercentile()) >= avg){
                hh.setBackgroundColor(Color.parseColor("#ff0000"));
            }
        }
    }

}