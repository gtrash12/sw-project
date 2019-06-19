package com.example.dday;

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




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;




public class Vocalist {
    private static final Vocalist vocas = new Vocalist();

    public static Vocalist getInstance() {
        return vocas;
    }

    private Vocalist() {
    }

    private Vector<Voca> DB = new Vector<Voca>();
    Vector<Voca> vocalist = new Vector<Voca>();
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    NodeList nodeLst;
    File voca_save;


    public void open_savefile(File vf) {
        vocas.voca_save = vf;
    }

    public void load(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        if(vocalist.size() > 0)
            return;
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
            //vocalist.add(DB.elementAt(i));
        }
        load_voca();
    }

    void addVoca(Voca ele){
        if(vocalist.contains(ele) == false) {
            vocalist.addElement(ele);
            save_voca();
        }
    }
    void removeVoca(Voca ele){
        vocalist.remove(ele);
        save_voca();
    }

    public Voca getRandom(){
        int randindex = (int)(Math.random()*DB.size());
        return DB.elementAt(randindex);
    }

    public int vocas_len(){
        return vocalist.size();
    }

    public void load_voca(){
        try {
            BufferedReader vbr = new BufferedReader( new FileReader(voca_save));
            String readStr = "";
            while((readStr = vbr.readLine()) != null){
                vocalist.add(DB.elementAt(Integer.parseInt(readStr)));
            }
            vbr.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save_voca(){
        try {
            BufferedWriter vbw = new BufferedWriter(new FileWriter(voca_save,false));;
            int len = vocalist.size();
            for(int i = 0; i < len; i ++){
                vbw.write(DB.indexOf(vocalist.elementAt(i))+"\n");
            }
            vbw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}