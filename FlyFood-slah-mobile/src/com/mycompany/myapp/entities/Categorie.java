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
public class Categorie {
    private int id;
    private String sujet;

    public String getSujet() {
        return sujet;
    }

    public Categorie(String sujet) {
        this.sujet = sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Categorie(int id, String sujet) {
        this.id = id;
        this.sujet = sujet;
    }
    
   
    
    public Categorie() {
        
     
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", sujet=" + sujet + '}';
    }

  

}