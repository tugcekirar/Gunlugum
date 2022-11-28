package com.example.gnlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class GunlukYaz extends AppCompatActivity {
    EditText tarih;
    EditText baslik;
    EditText yazi;
    Button kaydet;
    Button muzik;
    Button muzikdurdur;
    Button anasayfayadon;
    ListView listele;
    ArrayAdapter listadapter;
    MediaPlayer play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunluk_yaz);

        tarih=findViewById(R.id.edttarih);
        baslik=findViewById(R.id.edtbaslik);
        yazi=findViewById(R.id.edtmetin);
        kaydet=findViewById(R.id.btnkaydet);
        muzik=findViewById(R.id.btnmuzik);
        muzikdurdur=findViewById(R.id.btndurdur);
        listele=findViewById(R.id.list);
        anasayfayadon=findViewById(R.id.btndon);

        muzik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play==null){
                    play=MediaPlayer.create(GunlukYaz.this,R.raw.muzik);
                    play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopplayer();
                        }
                    });
                }
                play.start();
            }
        });
        muzikdurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopplayer();
            }
        });

        DatabaseHelper databaseHelper=new DatabaseHelper(GunlukYaz.this);
        getlistviewdata(databaseHelper.tumgunlukgetir());
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GunlukNesne nesne;
                try {
                    nesne=new GunlukNesne(-1,tarih.getText().toString(),baslik.getText().toString(),yazi.getText().toString());

                    Toast.makeText(GunlukYaz.this,"EKLENDİ",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    nesne=new GunlukNesne(-1,"","","");

                    Toast.makeText(GunlukYaz.this,"HATA",Toast.LENGTH_LONG).show();
                }
                DatabaseHelper databaseHelper=new DatabaseHelper(GunlukYaz.this);
                boolean success= databaseHelper.addoneGUN(nesne);
                getlistviewdata(databaseHelper.tumgunlukgetir());
            }
        });
        listele.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GunlukNesne g=(GunlukNesne) parent.getItemAtPosition(position);
                DatabaseHelper databaseHelper=new DatabaseHelper(GunlukYaz.this);
                databaseHelper.deletesatir(g);
                getlistviewdata(databaseHelper.tumgunlukgetir());
                Toast.makeText(GunlukYaz.this, "SİLİNDİ", Toast.LENGTH_SHORT).show();
            }
        });
        anasayfayadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anasayfa=new Intent(GunlukYaz.this,MainActivity.class);
                startActivity(anasayfa);
            }
        });
    }
    private void getlistviewdata(List<GunlukNesne> tumgunlukgetir) {
        listadapter = new ArrayAdapter<GunlukNesne>(GunlukYaz.this, android.R.layout.simple_list_item_1, tumgunlukgetir);
        listele.setAdapter(listadapter);
    }
    public void stopplayer() {
        if (play != null) {
            play.release();
            play = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopplayer();
    }
}