package com.example.basic.voca;

import java.util.Vector;

public class vocaList {
    Vector<Voca> list = new Vector<Voca>();
    
    void addVoca(Voca ele){
        list.addElement(ele);
    }
    void removeVoca(Voca ele){
        list.remove(ele);
    }
}
