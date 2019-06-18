package com.example.dday;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                }
                else
                    Toast.makeText(getApplicationContext(), "오답!!", Toast.LENGTH_LONG).show();
                if(numoftry==10)
                    Toast.makeText(getApplicationContext(), "실패!! 잠다깼쥬~?", Toast.LENGTH_LONG).show();
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

