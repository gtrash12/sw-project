package com.example.basic.voca;

public class Voca {
    private String word;
    private String mean;

    void Voca(String w, String m){
        word = w;
        mean = m;
    }

    public String getWord(){
        return word;
    }

    public String getMean(){
        return mean;
    }
    
    public boolean Compare(String ans){
        if(ans == word){
            return true;   
        }else
            return false;
    }
}
