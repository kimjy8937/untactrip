package com.example.untactrip3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter_si extends RecyclerView.Adapter<RecyclerAdapter_si.ItemViewHolder> {
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;
    private String gu;
    private ArrayList<String> code;

    RecyclerAdapter_si(ArrayList<Data> listData, Context context, String gu, ArrayList<String> code){
        this.listData = listData;
        this.context = context;
        this.gu = gu;
        this.code = code;
    }

    @NonNull
    @Override
    public RecyclerAdapter_si.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seoul_item,parent,false);
        return new RecyclerAdapter_si.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_si.ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount(){
        return listData.size();
    }

    void addItem(Data data) {
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView main_textView;
        private Data data;

        ItemViewHolder(View itemView) {
            super(itemView);
            main_textView = itemView.findViewById(R.id.seoul_main_name);
        }

        void onBind(Data data) {
            main_textView.setText(data.getTitle());
            itemView.setOnClickListener(this);
            main_textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), FlowDensitySi.class);
                    intent.putExtra("si", data.getTitle());
                    context.startActivity(intent);
                }
            });
        }
        @Override
        public void onClick(View v){
        }
    }
}
