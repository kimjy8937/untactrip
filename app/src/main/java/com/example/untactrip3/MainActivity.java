package com.example.untactrip3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_seoul = (Button)findViewById(R.id.btn_seoul);
        Button btn_korea = (Button)findViewById(R.id.btn_korea);
        btn_seoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SeoulMenu.class);
                startActivity(intent);
            }
        });
        btn_korea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,KoreaMenu.class);
                startActivity(intent);
            }
        });




    }

}