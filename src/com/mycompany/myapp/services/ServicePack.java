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
import com.mycompany.myapp.entities.Pack;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hazem
 */
public class ServicePack {
    public ArrayList<Pack> Pack;
    
    public static ServicePack instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
            
    private ServicePack() {
         req = new ConnectionRequest();
    }
    public static ServicePack getInstance() {
        if (instance == null) {
            instance = new ServicePack();
        }
        return instance;
    }
    public boolean addPack(Pack p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL + "pack/new?type=" + p.getType() + "&Description=" + p.getDescription()+"&prix=" +p.getPrix();
      // String url = Statics.BASE_URL + "create";
        System.out.println("********");
    
       req.setUrl(url);
       
//       req.addArgument("username", u.getUsername());
//       req.addArgument("email", u.getEmail()+"");
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

    public ArrayList<Pack> parseTasks(String jsonText){
        try {
            Pack =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Pack p = new Pack();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                String type= obj.get("type").toString() ;
                p.setType(type);
                p.setDescription((obj.get("Description").toString()));
                String prix= obj.get("prix").toString() ;
                p.setPrix(prix);
                
                
//                    String email= obj.get("email").toString() ;
//                    float id = Float.parseFloat(obj.get("id").toString());
//                    String username= obj.get("username").toString() ;
//                    String password= obj.get("password").toString() ;
//                    String tel= obj.get("tel").toString() ;
               
              
                Pack.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Pack;
    }
    
    public ArrayList<Pack> getAllPack(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"pack";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Pack = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Pack;
    }
    public boolean deletePack(Pack p) {
        String url = Statics.BASE_URL + "Delete/pack/" + p.getId();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
    
    
        public boolean UpdatePack(Pack p) {
        String url = Statics.BASE_URL + "pack/edit/" + p.getId()+"?type="+p.getType()+"&Description="+p.getDescription()+"&prix="+p.getPrix();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
    
}
