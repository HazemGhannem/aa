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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<User> User;
    
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addUsers(User u) {
        System.out.println(u);
        System.out.println("********");
        String url = Statics.BASE_URL + "register?username=" + u.getUsername() + "&email=" + u.getEmail()+"&Telephone=" +u.getTel()+"&password="+u.getPassword();
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

    public ArrayList<User> parseTasks(String jsonText){
        try {
            User =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                String email= obj.get("email").toString() ;
                u.setEmail(email);
                u.setPassword((obj.get("password").toString()));
                String Telephone= obj.get("Telephone").toString() ;
                u.setTel(Telephone);
                String Username= obj.get("username").toString() ;
                u.setUsername(Username);
                
//                    String email= obj.get("email").toString() ;
//                    float id = Float.parseFloat(obj.get("id").toString());
//                    String username= obj.get("username").toString() ;
//                    String password= obj.get("password").toString() ;
//                    String tel= obj.get("tel").toString() ;
               
              
                User.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return User;
    }
    
    public ArrayList<User> getAllUser(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"allusers/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                User = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }
    public boolean deleteUser(User u) {
        String url = Statics.BASE_URL + "Delete/" + u.getId();
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
    
    
        public boolean UpdateUser(User u) {
        String url = Statics.BASE_URL + "update/" + u.getId()+"?username="+u.getUsername()+"&email="+u.getEmail()+"&Telephone="+u.getTel();
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
        
        public void signin(TextField email,TextField password, Resources rs ) {
        
       
        String url = Statics.BASE_URL+"loginUser/json?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
                
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                //SessionManager.setNom(user.get("nom").toString());
                SessionManager.setUsername(user.get("username").toString());
                SessionManager.setTel(user.get("Telephone").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setPassowrd(user.get("password").toString());

                //photo 
                
                
                
                if(user.size() >0 ) // l9a user
                 
                    new ProfileForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
        
        
        
}
