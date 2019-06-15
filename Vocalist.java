package com.example.jinhee.voca;
import android.content.res.AssetManager;
import android.net.Uri;
import android.renderscript.ScriptGroup;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class Vocalist {
    private static final Vocalist vocas = new Vocalist();

    public static Vocalist getInstance() {
        return vocas;
    }

    private Vocalist() { }

    private Vector<Voca> DB = new Vector<Voca>();
    Vector<Voca> vocalist = new Vector<Voca>();
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    NodeList nodeLst;


    public void load(InputStream is, InputStream empty) throws ParserConfigurationException, IOException, SAXException {
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        dbf.setValidating(false);
        db = dbf.newDocumentBuilder();
        doc = db.parse(is);
        doc.getDocumentElement().normalize();
        nodeLst = doc.getElementsByTagName("item");
        int lst_len = nodeLst.getLength();
        for(int i = 0; i < lst_len; i++){
            NodeList current = nodeLst.item(i).getChildNodes();
            DB.addElement(new Voca(current.item(1).getTextContent() ,current.item(3).getTextContent()));
            vocalist.add(DB.elementAt(i));
        }
        doc = db.parse(empty);
    }

    void addVoca(Voca ele){
        vocalist.addElement(ele);
        Node newnode =  doc.createElement("item");
        Node word =  doc.createElement("word");
        word.setTextContent(ele.getWord());
        newnode.appendChild(word);
        Node mean =  doc.createElement("word");
        mean.setTextContent(ele.getWord());
        newnode.appendChild(mean);
        doc.appendChild(newnode);
    }
    void removeVoca(Voca ele){
        vocalist.remove(ele);
    }

    public Voca getRandom(){
        int randindex = (int)Math.random()*vocalist.size();
        return DB.elementAt(randindex);
    }

    public int vocas_len(){
        return vocalist.size();
    }
}
