package com.example.untactrip3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.untactrip3.api.SafeCasterApi;
import com.example.untactrip3.dto.Datum_dong;
import com.example.untactrip3.dto.FlowDensity_dong;
import com.example.untactrip3.service.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlowDensityDong extends AppCompatActivity {
    String key="l7xx5165a84073b34123b052bcec46843934";
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RetrofitClient retrofitClient;
    private SafeCasterApi safeCasterApi;
    private String ldongCd;
    private String ldongName;
    private String guName;
    private TextView date_info;
    private TextView text_state;
    private ImageView iv_state;
    public static double sum2;
    public static double avg;
    public static int state;
    public static List<Datum_dong> items;
    public static List<Datum_dong> emp_items;
    ArrayList<Map<String, Object>> SeoulAreaList=null;
    ArrayList<String> code = null;
    int avgcount=0;


    public String FindCdByDong(String Dong){
        String result=null;
        int i=0;

        for(i=0;i<SeoulAreaList.size();i++) {
            if (SeoulAreaList.get(i).get("dong")==null)
                continue;;
            if (SeoulAreaList.get(i).get("dong").toString().equals(Dong))
                    result=SeoulAreaList.get(i).get("lDongCd").toString();
        }

        if(i>SeoulAreaList.size())
            result="없는 동입니다";

        return result;
    }

    public String daybefore(int day){ //day만큼 전날
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format (System.currentTimeMillis() - (((long) 1000 * 60 * 60 * 24) * day));
        return time;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_density_dong);

        retrofitClient=RetrofitClient.getInstance();
        safeCasterApi=RetrofitClient.getSafeCasterApi();

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

        date_info = findViewById(R.id.date_info);
        iv_state = findViewById(R.id.iv_state);


        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH시 기준");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format3 = new SimpleDateFormat("hh");
        String format_time1 = format1.format (System.currentTimeMillis());
        String format_time2 = format2.format (System.currentTimeMillis());
        String format_time3 = format3.format (System.currentTimeMillis());

        int h = Integer.parseInt(format_time3);



        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        ldongName = intent.getExtras().getString("dong");
        guName = intent.getExtras().getString("gu");

        //평균값 avgadapter에 보내기
        recyclerView2 = findViewById(R.id.text_state);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView2.setLayoutManager(layoutManager2);



//        code = intent.getStringArrayListExtra("code");


        ldongCd = FindCdByDong(ldongName);



        sum2 = 0.0;
        date_info.setText(format_time1 + " " + guName + " " + ldongName + " 유동인구" );
        safeCasterApi.getFlowDensity_dong(key,
                format_time2, ldongCd).enqueue(new Callback<FlowDensity_dong>() {
            @Override
            public void onResponse(Call<FlowDensity_dong> call, Response<FlowDensity_dong> response) {
                FlowDensity_dong flowDensity_dong = response.body();
                Log.d("retrofit", "Data fetch success");
                items = flowDensity_dong.getData();


                mAdapter = new RecyclerAdapter_Flow(items, FlowDensityDong.this, ldongName);
                recyclerView.setAdapter(mAdapter);


                for(int i = 1; i <= 7 ; i ++){
                    safeCasterApi.getFlowDensity_dong(key, daybefore(i), ldongCd).enqueue(new Callback<FlowDensity_dong>() {
                        @Override
                        public void onResponse(Call<FlowDensity_dong> call, Response<FlowDensity_dong> response) {
                            FlowDensity_dong emp_dong = response.body();
                            Log.d("retrofit", "Data fetch success");
                            emp_items = emp_dong.getData();
                            sum2 += emp_items.get(h).getFlowDensityPercentile();
                            ++avgcount;
                            System.out.println(sum2);

                            if(avgcount==7) {
                                avg = sum2/7;
                                System.out.println(avg);
                                System.out.println(avg);

                                mAdapter = new RecyclerAdapter_avg(avg, FlowDensityDong.this, h, items);
                                recyclerView2.setAdapter(mAdapter);
                            }

                        }
                        @Override
                        public void onFailure(Call<FlowDensity_dong> call, Throwable t) {
                            Log.d("TEST", "절대유동인구 조회실패 ");
                        }
                    });

                    }

                //평균값 recycleradapter_avg에 보내기


                if (avg > items.get(h).getFlowDensityPercentile()) {
                    state = 1;

                } else {
                    state = 2;
                }
            }
            @Override
            public void onFailure(Call<FlowDensity_dong> call, Throwable t) {
                Log.d("TEST", "절대유동인구 조회실패 ");
            }
        });
        if(state == 1){
            iv_state.setImageResource(R.drawable.good);
        }
        else if(state == 2){
            iv_state.setImageResource(R.drawable.bad);

        }

    }
}