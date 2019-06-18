package com.example.jinhee.voca;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Vector;
import android.widget.Toast;

public class VocaActivity extends AppCompatActivity {
    Button Vocabtt;
    LinearLayout Vocascroll;
    Button editbtt;
    int state = 1;
    Vector<Button> Vocabtts = new Vector<Button>();

    public void ModeChange(View view){
        if(state == 1){
            editbtt.setText("Delete");
            state = 2;
        }else{
            editbtt.setText("Edit");
            int len = Vocalist.getInstance().vocas_len();
            int delnum = 0;
            for (int j = 0; j < len; j++) {
                if(Vocalist.getInstance().vocalist.elementAt(j).getState() == true){
                    Vocalist.getInstance().removeVoca(Vocalist.getInstance().vocalist.elementAt(j));

                    Vocascroll.removeView(Vocabtts.elementAt(j));
                    Vocabtts.remove(Vocabtts.elementAt(j));
                    //Toast.makeText(getApplicationContext(), ((Button)findViewById(j)).getText(), Toast.LENGTH_LONG).show();
                    j--;
                    len--;
                    delnum++;
                }else  if(delnum > 0){
                    Vocabtts.elementAt(j).setId(j);
                }
            }
            Toast.makeText(getApplicationContext(), delnum+"개의 단어를 삭제했습니다.", Toast.LENGTH_LONG).show();
            state = 1;
        }
        Show();
    }

    void Show(){
        int len = Vocalist.getInstance().vocas_len();
        for (int j = 0; j < len; j++) {
            if(Vocalist.getInstance().vocalist.elementAt(j).getState()){
                Vocalist.getInstance().vocalist.elementAt(j).change_state();
            }
            Vocabtts.elementAt(j).setText(Vocalist.getInstance().vocalist.elementAt(j).getWord());
            Vocabtts.elementAt(j).setBackgroundColor(Color.WHITE);
        }
    }


    public void ChangeWM(View view){
        TextView tvText =(TextView) findViewById(view.getId());
        if(state == 1) {
            if (Vocalist.getInstance().vocalist.elementAt(view.getId()).change_state() == true) {
                tvText.setText(Vocalist.getInstance().vocalist.elementAt(view.getId()).getMean());
            } else {
                tvText.setText(Vocalist.getInstance().vocalist.elementAt(view.getId()).getWord());
            }
        }else{
            if (Vocalist.getInstance().vocalist.elementAt(view.getId()).change_state() == true) {
                tvText.setBackgroundColor(Color.GRAY);
            } else {
                tvText.setBackgroundColor(Color.WHITE);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voca);

        Vocabtt = (Button) findViewById(R.id.vocabtt);
        Vocascroll = (LinearLayout) findViewById(R.id.vocascroll);
        editbtt = (Button) findViewById(R.id.Editbtt);

        int len = Vocalist.getInstance().vocas_len();
        for (int j = 0; j < len; j++) {
            Button btn = new Button(this);
            btn.setText(Vocalist.getInstance().vocalist.elementAt(j).getWord());
            btn.setId(j);
            btn.setBackgroundColor(Color.WHITE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeWM(view);
                }
            });
            Vocascroll.addView(btn);
            Vocabtts.add(btn);
        }
    }
}