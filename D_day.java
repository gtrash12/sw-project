package com.example.dday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class D_day extends Activity {

    TextView ddayText;
    TextView todayText;
    TextView resultText;
    TextView eventContents ;
    ImageButton knubtn;
    Button backbtn;
    //현재시각(년,월,일)
    int tYear;
    int tMonth;
    int tDay;
    //D-day 설정(년,월,일)
    int dYear=1;
    int dMonth=1;
    int dDay=1;
    //D-day 값
    long r;

    int resultNumber = 0;
    final int DATE_DIALOG_ID=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_day);

        ListView listView;
        EventAdapter adapter;
        ddayText=(TextView)findViewById(R.id.dday);
        todayText=(TextView)findViewById(R.id.today);
        resultText=(TextView)findViewById(R.id.result);
        knubtn=(ImageButton)findViewById(R.id.knubtn);
        eventContents = (TextView)findViewById(R.id.econtents);
        backbtn = (Button)findViewById(R.id.backbtn);

        //이벤트 설정내용 받는 부분
        Intent intent = getIntent();
        String data=intent.getStringExtra("contents");
        eventContents.setText(data);



        //환경설정 버튼 이벤트 부분
        knubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(D_day.this,Cgo.class);
                startActivity(intent1);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(D_day.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        //현재시간 불러오는 부분
        Calendar calendar = Calendar.getInstance();
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONDAY);
        tDay = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar dcalendar = Calendar.getInstance();
        dcalendar.set(dYear,dMonth,dDay);

        //cgo에서 dday 설정값 받음.
        Intent intent1 = new Intent(this.getIntent());
        /*
        dYear=intent1.getIntExtra("yearvalue",1);
        dMonth=intent1.getIntExtra("monthvalue",1);
        dDay=intent1.getIntExtra("dayvalue",1);
        */
        r=intent1.getIntExtra("ddayvalue",1);

        dYear=DdayFileSystem.getInstance().yy;
        dMonth=DdayFileSystem.getInstance().mm;
        dDay=DdayFileSystem.getInstance().dd;
        resultNumber=DdayFileSystem.getInstance().d_day;
        eventContents.setText(DdayFileSystem.getInstance().event);
        updateDisplay();





        //
        ArrayList<ListViewItem> oData = new ArrayList<>();
        //
        ListViewItem item = new ListViewItem();
        item.setD_day(resultNumber);
        oData.add(item);

        //변수 초기화
        adapter = new EventAdapter();
        listView = (ListView)findViewById(R.id.listview);

        //어뎁터 할당
        adapter.addItem("contents",123);
        listView.setAdapter(adapter);


        /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                String econtents = item.getEventContents();
                int edday = item.getDday();
            }
        });
        */


    }

    private void updateDisplay() {
        todayText.setText(String.format("%d년 %d월 %d일",tYear,tMonth+1,tDay));
        ddayText.setText(String.format("목표 날짜:%d년 %d월 %d일",dYear,dMonth+1,dDay));
        if(resultNumber>0){
            resultText.setText(String.format("D-%d",resultNumber));
        }
        else if(resultNumber == 0){
            resultText.setText(String.format("D-day"));
        }
        else{
            int absR=Math.abs(resultNumber);
            resultText.setText(String.format("D+%d",absR));
        }
    }




}