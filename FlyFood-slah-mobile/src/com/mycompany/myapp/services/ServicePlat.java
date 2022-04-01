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
import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bouss
 */
public class ServicePlat {
     public ArrayList<Plat> pl;

    public static ServicePlat instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePlat() {
        req = new ConnectionRequest();
    }

    public static ServicePlat getInstance() {
        if (instance == null) {
            instance = new ServicePlat();
        }
        return instance;
    }

    public boolean addProduct(Plat p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL +"createactualite?nom=" + p.getNom()+"&Description=" +p.getDesc()+"&image=" + p.getImg();
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

    public ArrayList<Plat> parseTasksPl(String jsonText){
        try {
            pl=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Plat t = new Plat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                if (obj.get("nom")==null)
              t.setNom("null");
                else
                    t.setNom(obj.get("nom").toString());
                t.setDesc(obj.get("Description").toString());
                t.setImg(obj.get("image").toString());
                
                
                pl.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return pl;
    }
    public ArrayList<Plat> getAllPlats(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"getactualite";
        req.setUrl(url);
        req.setPost(false);
        req.addRequestHeader("accept", "application/json");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pl = parseTasksPl(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return pl;
    }

    
     public Plat Detailprod( int idProduit , Plat p) {
        
        String url = Statics.BASE_URL+"/product/list=?"+idProduit;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                p.setNom(obj.get("nom").toString());
                p.setDesc(obj.get("Description").toString());
                p.setImg(obj.get("image").toString());
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return p;
        
        
    }
    
    

         public boolean  Delete(Plat p){
       String url = Statics.BASE_URL + "dactualite/" +p.getId();
  
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
             //Update 
    public boolean update(Plat p) {
        String url = Statics.BASE_URL +"azer/"+p.getId()+"?nom=" + p.getNom()+"&Description=" +p.getDesc()+"&image=" + p.getImg();
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

 
 
