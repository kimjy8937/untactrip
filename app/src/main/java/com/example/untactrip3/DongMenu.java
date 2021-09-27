package com.example.untactrip3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class DongMenu extends AppCompatActivity {
    private RecyclerAdapter_dong adapter;
    ArrayList<Data> itemList;
    private TextView textView2;
    ArrayList<Map<String, Object>> SeoulAreaList=null;
    public String gu;



    public String FindDongByCd(String code){

        String result=null;
        int i=0;

        for(i=0;i<SeoulAreaList.size();i++) {
            if (SeoulAreaList.get(i).get("lDongCd").toString().equals(code))
                result=SeoulAreaList.get(i).get("dong").toString();
        }
//        if(i>SeoulAreaList.size())
//            result="없는 동입니다";

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_menu);
        init();
        getData();
        textView2 = findViewById(R.id.textView2);
        textView2.setText(gu);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.si_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        adapter = new RecyclerAdapter_dong(getData(), DongMenu.this, gu);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList getData() {
        itemList = new ArrayList<>();
        Intent intent = getIntent();
        gu = intent.getExtras().getString("gu");

        Gson gson = new Gson();


        try{

            InputStream is = getAssets().open("SeoulArea.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);


            Map<String,Object> SeoulAreaData= gson.fromJson( jsonObject.toString(),new TypeToken<Map<String, Object>>(){}.getType());

            SeoulAreaList = (ArrayList) SeoulAreaData.get("data");
        }catch (Exception e){e.printStackTrace();}



        switch (gu) {
            case "강남구":
                for (long i = 1168010100; i <= 1168011800; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "강동구":
                for (long i = 1174010100; i <= 1174011000; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "강북구":
                for (long i = 1130510100; i <= 1130510400; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "강서구":
                for (long i = 1150010100; i <= 1150011300; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "관악구":
                for (long i = 1162010100; i <= 1162010300; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "광진구":
                for (long i = 1121510100; i <= 1121510900; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "구로구":
                for (long i = 1153010100; i <= 1153011200; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "금천구":
                for (long i = 1154510100; i <= 1154510300; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "노원구":
                for (long i = 1135010200; i <= 1135010600; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "도봉구":
                for (long i = 1132010500; i <= 1132010800; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "동대문구":
                for (long i = 1123010100; i <= 1123011000; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "동작구":
                for (long i = 1159010100; i <= 1159010900; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "서대문구":
                for (long i = 1141010100; i <= 1141010900; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "마포구":
                for (long i = 1144010100; i <= 1144012700; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "서초구":
                for (long i = 1165010100; i <= 1165011100; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "성동구":
                for (long i = 1120010100; i <= 1120012200; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "성북구":
                for (long i = 1129010100; i <= 1129013900; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "송파구":
                for (long i = 1171010100; i <= 1171011400; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "양천구":
                for (long i = 1147010100; i <= 1147010300; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "영등포구":
                for (long i = 1156010100; i <= 1156013400; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "용산구":
                for (long i = 1117010100; i <= 1117013600; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "은평구":
                for (long i = 1138010100; i <= 1138011400; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "종로구":
                for (long i = 1111010100; i <= 1111018700; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "중구":
                for (long i = 1114010100; i <= 1114017400; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;
            case "중랑구":
                for (long i = 1126010100; i <= 1126010600; i += 100) {
                    if(FindDongByCd(String.valueOf(i))==null)
                        continue;
                    Data data = new Data();
                    data.setTitle(new String(FindDongByCd(String.valueOf(i))));
                    itemList.add(data);
                    data.setGuName(gu);
                }
                break;

        }
        return itemList;
    }
}