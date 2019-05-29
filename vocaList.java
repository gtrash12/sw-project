package com.example.basic.voca;

import java.util.Vector;

public class vocaList {
    File vocfile = new File("xmldata/voca.xml");
    Vector<Voca> list = new Vector<Voca>();
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    NodeList nodeLst;
    void load(){
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(file);
        doc.getDocumentElement().normalize();
        nodeLst = doc.getElementsByTagName("voca");
        int lst_len = nodeLst.getLength();
        for(int i = 0; i < lst_len; i++){
            Node current = nodeLst.item(i);
            addVoca(new Voca(current.getElementByTagName("word"),current.getElementByTagName("mean")));
        }
    }
    
    void addVoca(Voca ele){
        list.addElement(ele);
    }
    void removeVoca(Voca ele){
        list.remove(ele);
    }
    
}
