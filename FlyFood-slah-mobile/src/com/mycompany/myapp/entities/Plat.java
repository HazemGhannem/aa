/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Plat {
    private int id;
    private String nom,img,desc;

    
    
    
    
    
    public Plat(int id,String nom , String desc, String img) {
        this.id = id;
        this.nom = nom;
        this.desc = desc;
        this.img = img;
        
    }
    public Plat(String nom , String desc, String img) {
        
        this.nom = nom;
        this.desc = desc;
        this.img = img;
      
    }
    
    public Plat() {
        
     
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", nom=" + nom + ", img=" + img + ", desc=" + desc + '}';
    }

   
  
   
    
}
