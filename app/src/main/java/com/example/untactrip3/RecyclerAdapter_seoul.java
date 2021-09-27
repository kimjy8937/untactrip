package com.example.untactrip3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactrip3.R;

import java.util.ArrayList;

public class RecyclerAdapter_seoul extends RecyclerView.Adapter<RecyclerAdapter_seoul.ItemViewHolder> {
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;

    RecyclerAdapter_seoul(ArrayList<Data> listData, Context context){
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter_seoul.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seoul_item,parent,false);
        return new RecyclerAdapter_seoul.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_seoul.ItemViewHolder holder, int position) {
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
        private LinearLayout item;
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
                    if(view.getContext().getClass() == SeoulMenu.class){
                        Intent intent = new Intent(view.getContext(), DongMenu.class);
                        intent.putExtra("gu", data.getTitle());
                        context.startActivity(intent);
                    }else if(view.getContext().getClass() == KoreaMenu.class){
                        Intent intent = new Intent(view.getContext(), SiMenu.class);
                        intent.putExtra("si",data.getTitle());
                        context.startActivity(intent);
                    }
                }
            });
        }
        @Override
        public void onClick(View v){
        }
    }
}
