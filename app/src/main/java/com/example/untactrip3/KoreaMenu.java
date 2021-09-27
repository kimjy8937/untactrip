package com.example.untactrip3;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactrip3.R;
import com.example.untactrip3.RecyclerAdapter_seoul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KoreaMenu extends AppCompatActivity {
    private RecyclerAdapter_seoul adapter;
    private TextView textView2;
    ArrayList<Data> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul_menu);
        init();
        getData();
        textView2 = findViewById(R.id.textView2);
        textView2.setText("전국 UNTACTRIP");
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.seoul_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerAdapter_seoul(getData(), KoreaMenu.this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList getData() {
        itemList = new ArrayList<>();
        List<String> listTitle = Arrays.asList("강원도","경기도","경상남도","경상북도","광주광역시","대구광역시","대전광역시","부산광역시","세종특별자치시","울산광역시","인천광역시","전라남도","전라북도","제주특별자치도","충청남도","충청북도");
        for (int i = 0; i < listTitle.size(); i++) {
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            itemList.add(data);
        }
        return itemList;
    }
}