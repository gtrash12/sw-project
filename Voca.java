package com.example.jinhee.voca;

public class Voca {
    private String word;
    private String mean;
    private boolean state = false;


    Voca(String w, String m){
        word = w;
        mean = m;
    }

    public String getWord(){
        return word;
    }

    public String getMean(){
        return mean;
    }

    public boolean change_state(){
        state = !state;
        return state;
    }
}
