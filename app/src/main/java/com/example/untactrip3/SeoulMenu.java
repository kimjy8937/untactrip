package com.example.untactrip3;

import android.os.Bundle;
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

public class SeoulMenu extends AppCompatActivity {
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
        textView2.setText("서울 UNTACTRIP");
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.seoul_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerAdapter_seoul(getData(), SeoulMenu.this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList getData() {
        itemList = new ArrayList<>();
        List<String> listTitle = Arrays.asList("강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "서대문구", "마포구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구");
        for (int i = 0; i < listTitle.size(); i++) {
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            itemList.add(data);
        }
        return itemList;
    }
}