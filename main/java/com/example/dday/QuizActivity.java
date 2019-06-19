package com.example.dday;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    TextView vocareText;
    EditText vocainEdit;
    Button submitButton;
    Voca voca_receiver;

    int numoftry;

    public void onBackPressed(){
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        final Intent intent = new Intent(QuizActivity.this,Alarm_Receiver.class);
        final Intent intent2 = new Intent(QuizActivity.this,MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        numoftry=0;

        voca_receiver = Vocalist.getInstance().getRandom();

        vocareText = (TextView) findViewById(R.id.voca_receiver);


        vocainEdit = (EditText) findViewById(R.id.voca_input);
        submitButton = (Button) findViewById(R.id.voca_submit);
        vocareText.setText(voca_receiver.getMean());
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compareVoca() == 1) {
                    Toast.makeText(getApplicationContext(), "정답!!", Toast.LENGTH_LONG).show();
                    intent.putExtra("extra","off");
                    sendBroadcast(intent);
                    startActivity(intent2);
                }
                else
                    Toast.makeText(getApplicationContext(), "오답!!", Toast.LENGTH_LONG).show();
                if(numoftry==10) {
                    Toast.makeText(getApplicationContext(), "실패!! 잠다깼쥬~?", Toast.LENGTH_LONG).show();
                    intent.putExtra("extra", "off");
                    sendBroadcast(intent);
                    Vocalist.getInstance().addVoca(voca_receiver);
                    startActivity(intent2);
                }
            }
        });
    }

    public int compareVoca() {
        numoftry++;
        if (voca_receiver.getWord().equals( vocainEdit.getText().toString()))
            return 1;
        else
            return 0;
    }
}

