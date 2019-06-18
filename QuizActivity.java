package com.example.vocaquiz;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

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
        try {
            AssetManager am = this.getResources().getAssets();
            InputStream is = null;
            is = am.open("vocas.xml");
            Vocalist.getInstance().load(is, am.open("empty.xml"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        numoftry=0;

        voca_receiver = Vocalist.getInstance().getRandom();

        vocareText = (TextView) findViewById(R.id.voca_receiver);
        vocareText.setText(vocainEdit.getText().toString());

        vocainEdit = (EditText) findViewById(R.id.voca_input);
        submitButton = (Button) findViewById(R.id.voca_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (numoftry < 10) {
                    if (compareVoca() == 1) {
                        Toast.makeText(getApplicationContext(), "정답!!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "오답!!", Toast.LENGTH_LONG).show();
                }
                if(numoftry==10)
                    Toast.makeText(getApplicationContext(), "실패!! 잠다깼쥬~?", Toast.LENGTH_LONG).show();
            }
        });
    }

    public int compareVoca() {
        numoftry++;
        if (voca_receiver.getWord() == vocainEdit.getText().toString())
            return 1;
        else
            return 0;
    }
}


