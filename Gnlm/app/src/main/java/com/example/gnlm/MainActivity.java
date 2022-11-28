package com.example.gnlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button yazmayabasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yazmayabasla=findViewById(R.id.btnyaz);
        yazmayabasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yazmaekranınagit=new Intent(MainActivity.this,GunlukYaz.class);
                startActivity(yazmaekranınagit);
            }
        });
    }
}