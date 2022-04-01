/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Plat;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm(Categorie p) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        //product
        Button btnAddProd = new Button("Ajouter un act");
        Button btnListProd = new Button("Liste des act");
       //category
        Button btnAddTask = new Button("Ajouter une Categorie");
        Button btnListTasks = new Button("Liste des Categories");
        
        
        
        //product
        btnAddProd.addActionListener(e-> new AddPlat(current).show());
        btnListProd.addActionListener(e-> new ListPlat(current).show());
        addAll(btnAddProd,btnListProd);
        
        //category
        btnAddTask.addActionListener(e-> new AddCategory(current).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }

       
    
}
