/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hazem
 */
public class Pack {
    private  int id ;
    private String type,Description,prix;

    public Pack() {
    }
    

    public Pack(int id, String type, String Description, String prix) {
        this.id = id;
        this.type = type;
        this.Description = Description;
        this.prix = prix;
    }

    public Pack(String type, String Description, String prix) {
        this.type = type;
        this.Description = Description;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", type=" + type + ", Description=" + Description + ", prix=" + prix + '}';
    }
    
}
