/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCategory {

    public ArrayList<Categorie> cat;

    public static ServiceCategory instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategory() {
        req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public boolean addTask(Categorie c) {
        System.out.println(c);
        System.out.println("********");
        String url = Statics.BASE_URL +"addca/?sujet=" + c.getSujet();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Categorie> parseTasks(String jsonText){
        try {
            cat=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Categorie t = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                
                    t.setSujet(obj.get("sujet").toString());
                
                cat.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return cat;
    }
    
    public ArrayList<Categorie> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"getcai";
        req.setUrl(url);
        req.setPost(false);
        req.addRequestHeader("accept", "application/json");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
       public boolean  Delete(Categorie p){
       String url = Statics.BASE_URL + "Delete/" +p.getId();
  
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

      
}
public boolean modifierProduct(Categorie p) {
        String url = Statics.BASE_URL +"updatee/"+p.getId()+"?sujet=" + p.getSujet();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
          
}
