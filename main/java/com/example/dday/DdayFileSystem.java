package com.example.dday;


import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DdayFileSystem {
    private static final DdayFileSystem Fsystem = new DdayFileSystem();

    public static DdayFileSystem getInstance() {
        return Fsystem;
    }

    private DdayFileSystem() {
        dd = 1;
        yy = 1;
        mm = 1;
        d_day =1;
        event = "x";

    }
    File dday_save;

    String event;
    int yy;
    int mm;
    int dd;
    int d_day;

    public void open_savefile(File dday) {
        Fsystem.dday_save=dday;
    }

    public void save_dday(){
        try{
            BufferedWriter vbw = new BufferedWriter(new FileWriter(dday_save,false));;
            vbw.write(Integer.toString(yy)+"\n");
            vbw.write(Integer.toString(mm)+"\n");
            vbw.write(Integer.toString(dd)+"\n");
            vbw.write(event+"\n");
            vbw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load_dday(){
        try {
            BufferedReader vbr = new BufferedReader( new FileReader(dday_save));
            String readStr = "";

            while((readStr = vbr.readLine()) != null){
                System.out.println(readStr);
                yy = Integer.parseInt(readStr);
                mm = Integer.parseInt(vbr.readLine());
                dd = Integer.parseInt(vbr.readLine());
                event = vbr.readLine();
            }
            vbr.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
