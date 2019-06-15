package com.example.jinhee.voca;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

public class VocaActivity extends AppCompatActivity {
    Button Vocabtt;
    LinearLayout Vocascroll;
    //static Vocalist vocas = new Vocalist();

    public void ChangeWM(View view){
        TextView tvText =(TextView) findViewById(view.getId());
        System.out.println(view.getId());
        if(Vocalist.getInstance().vocalist.elementAt(view.getId()).change_state() == true) {
            tvText.setText(Vocalist.getInstance().vocalist.elementAt(view.getId()).getMean());
        }else{
            tvText.setText(Vocalist.getInstance().vocalist.elementAt(view.getId()).getWord());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        Vocabtt = (Button) findViewById(R.id.vocabtt);
        Vocascroll = (LinearLayout) findViewById(R.id.vocascroll);

        int len = Vocalist.getInstance().vocas_len();
        for (int j = 0; j < len; j++) {
            Button btn = new Button(this);
            btn.setText(Vocalist.getInstance().vocalist.elementAt(j).getWord());
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
