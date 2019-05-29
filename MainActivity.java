package com.example.basic.voca;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button Vocabtt;
    LinearLayout Vocascroll;
    Context context;

    public void ChangeWM(View view){
        TextView tvText =(TextView) findViewById(view.getId());
        tvText.setText("ffff");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vocabtt = (Button) findViewById(R.id.vocabtt);
        Vocascroll = (LinearLayout) findViewById(R.id.vocascroll);
        context = this;


        for (int j = 0; j < 20; j++) {
            Button btn = new Button(context);
            btn.setText("단어" + String.valueOf(j));
            btn.setId(j);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeWM(view);
                }
            });
            Vocascroll.addView(btn);
        }
    }
}
