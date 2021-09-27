package com.example.untactrip3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.example.untactrip3.api.SafeCasterApi;
import com.example.untactrip3.dto.Datum_dong;
import com.example.untactrip3.dto.FlowDensity_dong;
import com.example.untactrip3.service.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiMenu extends AppCompatActivity {
    String key="l7xx5165a84073b34123b052bcec46843934";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RetrofitClient retrofitClient;
    private SafeCasterApi safeCasterApi;
    private RecyclerAdapter_si adapter;
    ArrayList<Data> itemList;
    private TextView textView2;
    ArrayList<Map<String, Object>> SeoulAreaList=null;
    public String si;
    public static List<Datum_dong> items;
    public static double sum;
    public static double avg;
    public String ldongCd;
    public ArrayList<String> code = null;

    public ArrayList<String> DongOfGu(String gu){
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for(i=0;i <SeoulAreaList.size() ; i++) {
            if(SeoulAreaList.get(i).get("siGunGu") == null)
                continue;
            if(SeoulAreaList.get(i).get("dong")==null){
                if(SeoulAreaList.get(i).get("siDo").equals(si)){
                    list.add(SeoulAreaList.get(i).get("siGunGu").toString());
                }
            }
        }
        return list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_si_menu);
        retrofitClient=RetrofitClient.getInstance();
        safeCasterApi=RetrofitClient.getSafeCasterApi();

        SimpleDateFormat format3 = new SimpleDateFormat("hh");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        String format_time3 = format3.format (System.currentTimeMillis());
        String format_time2 = format2.format (System.currentTimeMillis());

        int h = Integer.parseInt(format_time3);
        init();
        getData();

        for(int i = 0; i<DongOfGu(si).size(); i++) {
            safeCasterApi.getFlowDensity_dong(key,
                    "20210927", "1111010100").enqueue(new Callback<FlowDensity_dong>() {
                @Override
                public void onResponse(Call<FlowDensity_dong> call, Response<FlowDensity_dong> response) {
                    FlowDensity_dong siMenu = response.body();
                    Log.d("retrofit", "Data fetch success");
                    items = siMenu.getData();
                    sum += items.get(h).getFlowDensityPercentile();
                }

                @Override
                public void onFailure(Call<FlowDensity_dong> call, Throwable t) {
                    Log.d("TEST", "절대유동인구 조회실패 ");
                }
            });
        }
        avg = sum;
        sum = 0.0;

        code = DongOfGu(si);
        ldongCd = DongOfGu(si).get(0);
        textView2 = findViewById(R.id.textView2);
        textView2.setText(DongOfGu(si).toString()+DongOfGu(si).size()+" "+format_time2 +" "+avg);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.si_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerAdapter_si(getData(),SiMenu.this, si, DongOfGu(si));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList getData() {
        itemList = new ArrayList<>();
        Intent intent = getIntent();
        si = intent.getExtras().getString("si");
        Gson gson = new Gson();
        try{

            InputStream is = getAssets().open("Area.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            Map<String,Object> SeoulAreaData= gson.fromJson( jsonObject.toString(),new TypeToken<Map<String, Object>>(){}.getType());
            SeoulAreaList = (ArrayList) SeoulAreaData.get("data");
        }catch (Exception e){e.printStackTrace();}



        for (int i = 0; i<DongOfGu(si).size(); i++){
//            if(FindDongByCd(DongOfGu(si).get(i))==null)
//                continue;
            Data data = new Data();
            data.setTitle(DongOfGu(si).get(i));
            itemList.add(data);
            data.setGuName(si);
        }

        return itemList;
    }
}
