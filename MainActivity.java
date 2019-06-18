package com.example.dday;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends Activity {

    public File dday_save;
    public File voca_save;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goday = (Button)findViewById(R.id.gotodday);
        Button govoca = (Button)findViewById(R.id.gotovoca);
        Button gotoquiz = (Button)findViewById(R.id.gotoquiz);

        voca_save = new File(getFilesDir()+"Voca.save");
        dday_save = new File(getFilesDir()+"Alarm.save");
        if(voca_save.exists()==false){
            try {
                voca_save.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(dday_save.exists()==false){
            try {
                dday_save.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Vocalist.getInstance().open_savefile(voca_save);
        DdayFileSystem.getInstance().open_savefile(dday_save);

        try {
            AssetManager am = this.getResources().getAssets();
            InputStream is = null;
            is = am.open("vocas.xml");
            Vocalist.getInstance().load(is);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        DdayFileSystem.getInstance().load_dday();
        goday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,D_day.class);
                startActivity(intent);
            }
        });


        govoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,VocaActivity.class);
                startActivity(intent1);
            }
        });
        gotoquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,QuizActivity.class);
                startActivity(intent2);

            }
        });


    }




}
